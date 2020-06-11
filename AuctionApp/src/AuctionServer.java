/**
 * AuctionServer
 * This is the a simple multithreaded server that connects connects to
 *
 * @author Cyril Acquah Kofi
 * @author 10686868
 * @version 1.0.0
 * @since 01-05-2020
 */


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class AuctionServer extends Thread {

    public final static int PORT = 4444;
    private static ServerSocket server = null;
    private static Connection con;

    private static long start = 0;
    private static long stop = 0;

    public static void main(String[] args) {
        int port = PORT;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Auction", "root", "rootroot");

            server = new ServerSocket(port);
            System.out.println("Server started on port ::: " + port);
        } catch (SQLException ex) {
            System.out.println("SQL Connection failed!");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Couldn't start the server");
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        while (true) {
            try {
                Socket connection = server.accept();
                connection.setSoTimeout(60000);
                System.out.println("New Client ::: " + connection);
                Thread task = new AuctionTask(connection);
                task.start();
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }
        }
    }

    private static void startStop() {
        if (start == 0) {
            start = System.currentTimeMillis();
            stop = start + ( 4 * 60 * 1000 );
        }
    }

    private static class AuctionTask extends Thread {

        private Socket connection;
        private static AuctionUser user = new AuctionUser();
        private static AuctionItem auctionItem = new AuctionItem();

        private int action = 0;
        private int item = 0;
        private int id = 0;
        private float price;

        AuctionTask(Socket connection) {
            this.connection = connection;
        }

        /**
         * This function checks the database for users and adds
         * them if the user doesn't already exist
         *
         * @return All the latest items up for the auction
         */
        private static String signUp() {

            String query = "INSERT INTO Users (name, email, username, password ) VALUES ( ?, ?, ?, ? )";
            Statement stmt;
            ResultSet rs;
            PreparedStatement ps;
            int ID = 0;

            try {

                String check = "SELECT * FROM Users WHERE email = ? OR username = ?";
                ps = con.prepareStatement(check);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getUsername());
                rs = ps.executeQuery();
                if (rs.next())
                    return "{ \"error\" : \"There is a user with similar details\" }";

                ps = con.prepareStatement(query);
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getUsername());
                ps.setString(4, user.getPassword());
                ps.executeUpdate();

                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT ID FROM Users");

                if (rs.next())
                    ID = rs.getInt(1);

            } catch (Exception ex) {
                ex.printStackTrace();
                return "{ \"error\" : \"Internal Server error!\" }";
            }

            return String.format("{\"status\" : \"done\", \"id\" : %d }", ID);
        }

        /**
         * This function checks the database for users and checks their
         * information if the user doesn't already exist
         *
         * @return All the latest items up for the auction
         */
        private static String signIn() {

            ResultSet rs;
            PreparedStatement ps;
            int ID;

            try {
                String check = "SELECT * FROM Users WHERE username = ? AND password = ?";
                ps = con.prepareStatement(check);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                rs = ps.executeQuery();

                if (rs.next()) ID = rs.getInt(1);
                else return "{ \"error\" : \"Invalid Credentials\"}";

            } catch (Exception ex) {
                ex.printStackTrace();
                return " \"error\" : \"Internal Server error!\" }";
            }

            startStop();
            return
                    String.format("{ \"status\" : \"done\", \"id\" : %d, \"start\" : %d, \"stop\" : %d }",
                            ID, start, stop);
        }

        /**
         * This function checks the database for items and processes
         * them and serves it into a list that is return to the server
         *
         * @return All the latest items up for the auction
         */
        private static String getAuctionItems() {
            Statement stmt;
            ResultSet rs;
            ArrayList<AuctionItem> items = new ArrayList<AuctionItem>();
            try {
                AuctionItem item;
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM Items");
                while (rs.next()) {
                    item = new AuctionItem();
                    item.setId(rs.getInt("ID"));
                    item.setUrl(rs.getString("url"));
                    item.setName(rs.getString("name"));
                    item.setDescription(rs.getString("description"));
                    item.setPrice(rs.getFloat("price"));
                    item.setHighest(rs.getFloat("highest"));
                    items.add(item);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return "{ \"error\" : \"Internal Server Error!\" }";
            }

            JSONArray array = new JSONArray();
            items.forEach(auctionItem -> {
                array.add(auctionItem.toJSON());
            });
            return array.toJSONString();
        }

        /**
         * This function returns the highest bit for
         *
         * @param item The id of the item
         * @return The price of the highest bid
         */
        private static String getAuctionTopBid(int item) {
            ResultSet rs;
            PreparedStatement ps;
            float price = 0.0f;
            try {
                ps = con.prepareStatement("SELECT highest FROM Items WHERE ID = ?");
                ps.setInt(1, item);
                rs = ps.executeQuery();
                if (rs.next()) price = rs.getFloat("highest");

                ps = con.prepareStatement("SELECT user FROM Bids WHERE item = ? AND price = ?");
                ps.setInt(1, item);
                ps.setFloat(2, price);
                rs = ps.executeQuery();

                if (rs.next()) return String.format("{ \"highest\" : %f, \"id\" : %d }", price, rs.getInt(1));
                else return "{ \"error\" : \"Item not found\" }";
            } catch (SQLException ex) {
                ex.printStackTrace();
                return "{ \"error\" : \"Internal Server Error!\" }";
            }
        }


        /**
         * Place a bid for an item at an auction
         *
         * @param id    The ID of the user
         * @param item  The name of the item
         * @param price The price of the item
         * @return The results after placing the bid
         */
        private static String placeBid(int id, int item, float price) {
            ResultSet rs;
            PreparedStatement ps;
            try {
                ps = con.prepareStatement("SELECT * FROM Users WHERE ID = ?");
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if (!rs.next()) return "{ \"error\" : \"User not found\" }";

                ps = con.prepareStatement("SELECT * FROM Items WHERE ID = ?");
                ps.setInt(1, item);
                rs = ps.executeQuery();

                if (!rs.next()) return "{ \"error\" : \"Item not found\" }";
                if (rs.getFloat("highest") >= price || rs.getFloat("price") > price)
                    return String.format("{ \"error\" : \"Bid is low\", \"highest\" : %f, \"price\" : %f }",
                            rs.getFloat("highest"), rs.getFloat("price"));

                ps = con.prepareStatement("UPDATE Items SET highest = ? WHERE ID = ?");
                ps.setFloat(1, price);
                ps.setInt(2, item);
                ps.executeUpdate();

                ps = con.prepareStatement("INSERT INTO Bids (user, item, price ) VALUES ( ?, ?, ? ) ");
                ps.setInt(1, id);
                ps.setInt(2, item);
                ps.setFloat(3, price);
                ps.executeUpdate();

                return "{ \"status\" : \"You are now the highest bidder\" }";
            } catch (SQLException ex) {
                ex.printStackTrace();
                return "{ \"error\" : \"Internal Server Error!\" }";
            }
        }


        /**
         * Opt out of a bid for an item at an auction
         *
         * @param id    The ID of the user
         * @param item  The name of the item
         * @return The results after placing the bid
         */
        private static String unBid(int id, int item) {
            ResultSet rs;
            PreparedStatement ps;
            try {
                ps = con.prepareStatement("SELECT * FROM Users WHERE ID = ?");
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if (!rs.next()) return "{ \"error\" : \"User not found\" }";

                ps = con.prepareStatement("SELECT * FROM Items WHERE ID = ?");
                ps.setInt(1, item);
                rs = ps.executeQuery();

                if (!rs.next()) return "{ \"error\" : \"Item not found\" }";

                ps = con.prepareStatement("DELETE FROM Bids WHERE user = ? AND item = ?");
                ps.setInt(1, id);
                ps.setInt(2, item);
                ps.executeUpdate();

                ps = con.prepareStatement("SELECT * FROM Bids WHERE item = ? ORDER BY price DESC");
                ps.setInt(1, item);
                rs = ps.executeQuery();
;

                float price = 0.0f;
                if(rs.next()) price = rs.getFloat("price");

                ps = con.prepareStatement("UPDATE Items SET highest = ? WHERE ID = ?");
                ps.setFloat(1, price);
                ps.setInt(2, item);
                ps.executeUpdate();
                return String.format("{ \"status\" : \"done\", \"price\" : %f }", price);

            } catch (SQLException ex) {
                ex.printStackTrace();
                return "{ \"error\" : \"Internal Server Error!\" }";
            }
        }


        /**
         * Place a bid for an item at an auction
         *
         * @param id The ID of the user
         * @return The results after placing the bid
         */
        private static String addItem(int id) {

            String query = "INSERT INTO Items (name, url, description, price, highest ) VALUES ( ?, ?, ?, ?, ? )";
            ResultSet rs;
            PreparedStatement ps;
            int ID = 0;

            try {

                String check = "SELECT * FROM Users WHERE username = ? AND password = ?";
                ps = con.prepareStatement(check);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                rs = ps.executeQuery();

                if (rs.next()) ID = rs.getInt(1);
                else return "{ \"error\" : \"Permission denied\"}";

                ps = con.prepareStatement(query);
                ps.setString(1, auctionItem.getName());
                ps.setString(2, auctionItem.getUrl());
                ps.setString(3, auctionItem.getDescription());
                ps.setFloat(4, auctionItem.getPrice());
                ps.setFloat(5, auctionItem.getHighest());
                ps.executeUpdate();

            } catch (Exception ex) {
                ex.printStackTrace();
                return "{ \"error\" : \"Internal Server error!\" }";
            }

            return String.format("{ \"status\" : \"done\", \"id\" : %d }", ID);

        }

        /**
         * Parse String the extract the clients instructions to the server
         *
         * @param request The client request string
         * @return Boolean value
         */
        Boolean parse(String request) {
            try {

                JSONObject json = (JSONObject) (new JSONParser().parse(request));
                String res = (String) json.get("action");

                if (res != null && (res.equals("sign-in") || res.equals("sign-up"))) {
                    if (res.equals("sign-in")) {
                        action = 1;
                        res = (String) json.get("username");
                        if (res != null) {
                            user.setUsername(res);
                            res = (String) json.get("password");
                            if (res != null) {
                                user.setPassword(res);
                                return true;
                            }
                        }
                    } else {
                        action = 2;
                        res = (String) json.get("username");
                        if (res != null) {
                            user.setUsername(res);
                            res = (String) json.get("password");
                            if (res != null) {
                                user.setPassword(res);
                                res = (String) json.get("email");
                                if (res != null) {
                                    user.setEmail(res);
                                    res = (String) json.get("name");
                                    if (res != null) {
                                        user.setName(res);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                } else if (res.equals("get-highest")) {
                    action = 3;
                    item = (int) ((long) json.get("item"));
                    if (item != 0) return true;
                } else if (res.equals("get-items")) {
                    action = 4;
                    id = (int) ((long) json.get("user"));
                    if (id != 0) return true;
                } else if (res.equals("place-bid")) {
                    action = 5;
                    item = (int) ((long) json.get("item"));
                    if (item != 0) {
                        id = json.get("user") == null ? 0 : (int)((long)json.get("user"));
                        if (id != 0) {
                            price = (float) ((double) json.get("bid"));
                            if (price != 0.0) return true;
                        }
                    }
                } else if (res.equals("un-bid")) {
                    action = 6;
                    item = (int) ((long) json.get("item"));
                    if (item != 0) {
                        id = json.get("user") == null ? 0 : (int)((long)json.get("user"));
                        if (id != 0) return true;
                    }
                } else if (res.equals("add-item")) {
                    action = 7;
                    id = (int) ((long) json.get("user"));
                    if (id == 0) return false;
                    res = (String) json.get("url");
                    if (res != null) {
                        auctionItem.setUrl(res);
                        res = (String) json.get("name");
                        if (res != null) {
                            auctionItem.setName(res);
                            res = (String) json.get("description");
                            if (res != null) {
                                auctionItem.setDescription(res);
                                price = (float) ((double) json.get("price"));
                                if (res != null) {
                                    auctionItem.setPrice(price);
                                    price = (float) ((double) json.get("highest"));
                                    if (res != null) {
                                        auctionItem.setHighest(price);
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
            return false;
        }

        @Override
        public void run() {
            try {
                DataInputStream input = new DataInputStream(connection.getInputStream());
                DataOutputStream output = new DataOutputStream(connection.getOutputStream());

                System.out.println("[*] Reading data...");
                String request = input.readUTF();
                System.out.println("[*] Done reading data...");
                System.out.println(request);

                String response;
                if (parse(request)) {
                    if (action == 1) response = signIn();
                    else if (action == 2) response = signUp();
                    else if (action == 3) response = getAuctionTopBid(item);
                    else if (action == 4) response = getAuctionItems();
                    else if (action == 5) response = placeBid(id, item, price);
                    else if (action == 6) response = unBid(id, item);
                    else if (action == 7) response = addItem(id);
                    else response = "{ \"error\" : \"Service is not available\"}";
                } else response = "{ \"error\" : \"Invalid request!\" }";

                System.out.println("[*] Sending data...");
                output.writeUTF(response);
                output.flush();
                System.out.println("[*] Done sending data...");

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}