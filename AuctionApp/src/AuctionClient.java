/**
 * AuctionClient
 * This connects to an Auction server and gets information from the server
 *
 * @author Cyril Acquah Kofi
 * @author 10686868
 * @version 1.0.0
 * @since 01-05-2020
 */

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AuctionClient {

    private final static int PORT = 4444;
    private static Socket sock = null;
    private static String error = "Server is currently offline";
    private AuctionUser auctionUser = new AuctionUser();
    private long start = 0;
    private long stop = 0;

    public AuctionUser getUser() { return auctionUser; }

    public long getStart() { return start; }

    public long getStop() { return stop; }


    public Boolean signIn(AuctionUser user) {
        int id = 0;
        try {
            JSONObject json = (JSONObject)( new JSONParser().parse(user.toString()));
            json.put("action", "sign-in");

            System.out.println("[*] Connection to server");
            sock = new Socket("localhost", PORT);
            System.out.println("[*] Connected to server successfully!");

            DataInputStream input = new DataInputStream(sock.getInputStream());
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());

            System.out.println("[*] Sending data...");
            output.writeUTF(json.toJSONString());
            output.flush();
            System.out.println("[*] Done sending data...");


            System.out.println("[*] Reading data...");
            String request = input.readUTF();
            System.out.println("[*] Done reading data...");
            System.out.println(request);

            json = (JSONObject)( new JSONParser().parse(request));
            id = json.get("id") == null ? 0 : (int)((long)json.get("id"));
            String res = (String)json.get("error");
            if (res != null ) {
                error = res;
                return false;
            }

            start = json.get("start") == null ? 0 : (long)json.get("start");
            stop = json.get("stop") == null ? 0 : (long)json.get("stop");

        } catch (IOException ex) {
            System.out.println("Server connection failed!");
            return false;
        } catch (ParseException ex) {
            System.out.println(ex);
            return false;

        } finally {
            try {
                System.out.println("[*] Closing socket...");
                if (sock != null)
                    sock.close();
            } catch (IOException ex) {  return false; }
        }

        auctionUser = user;
        auctionUser.setId(id);
        return true;
    }

    public Boolean addItem(AuctionItem item) {
        try {
            JSONObject json = (JSONObject)( new JSONParser().parse(item.toString()));
            json.put("action", "add-item");
            json.put("user", auctionUser.getId());

            System.out.println("[*] Connection to server");
            sock = new Socket("localhost", PORT);
            System.out.println("[*] Connected to server successfully!");

            DataInputStream input = new DataInputStream(sock.getInputStream());
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());

            System.out.println("[*] Sending data...");
            output.writeUTF(json.toJSONString());
            output.flush();
            System.out.println("[*] Done sending data...");


            System.out.println("[*] Reading data...");
            String request = input.readUTF();
            System.out.println("[*] Done reading data...");
            System.out.println(request);
            json = (JSONObject)( new JSONParser().parse(request));
            String res = (String)json.get("error");
            if (res != null ) {
                error = res;
                return false;
            }
        } catch (IOException ex) {
            System.out.println("Server connection failed!");
            return false;
        } catch (ParseException ex) {
            System.out.println(ex);
            return false;

        } finally {
            try {
                System.out.println("[*] Closing socket...");
                if (sock != null)
                    sock.close();
            } catch (IOException ex) {  return false; }
        }

        return true;
    }

    public ArrayList<AuctionItem> getAuctionItems() {
        ArrayList<AuctionItem> items = new ArrayList<AuctionItem>();
        try {
            JSONObject json = new JSONObject();
            json.put("action", "get-items");
            json.put("user", auctionUser.getId());

            System.out.println("[*] Connection to server");
            sock = new Socket("localhost", PORT);
            System.out.println("[*] Connected to server successfully!");

            DataInputStream input = new DataInputStream(sock.getInputStream());
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());

            System.out.println("[*] Sending data...");
            output.writeUTF(json.toJSONString());
            output.flush();
            System.out.println("[*] Done sending data...");

            System.out.println("[*] Reading data...");
            String request = input.readUTF();
            System.out.println("[*] Done reading data...");
            System.out.println(request);
            Object object = new JSONParser().parse(request);
            JSONArray array = object == null ? new JSONArray() : (JSONArray)(object);
            array.forEach(o -> {
                items.add(new AuctionItem().fromJSON((JSONObject)o));
            });

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        } finally {
            try {
                System.out.println("[*] Closing socket...");
                if (sock != null)
                    sock.close();
            } catch (IOException ex) {  ex.printStackTrace(); }
        }

        return items;
    }


    public Boolean isSold(int item) {
        float highest = 0;
        int id = 0;
        try {
            JSONObject json = new JSONObject();
            json.put("action", "get-highest");
            json.put("item", item);

            System.out.println("[*] Connection to server");
            sock = new Socket("localhost", PORT);
            System.out.println("[*] Connected to server successfully!");

            DataInputStream input = new DataInputStream(sock.getInputStream());
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());

            System.out.println("[*] Sending data...");
            output.writeUTF(json.toJSONString());
            output.flush();
            System.out.println("[*] Done sending data...");

            System.out.println("[*] Reading data...");
            String request = input.readUTF();
            System.out.println("[*] Done reading data...");
            System.out.println(request);
            Object object = new JSONParser().parse(request);
            json = (JSONObject)( new JSONParser().parse(request));
            highest = json.get("highest") == null ? 0.0f : (float)((double)json.get("highest"));

            id = json.get("id") == null ? 0 : (int)((long)json.get("id"));
            if (highest == 0.0) return false;

            String res = (String)json.get("error");
            if (res != null ) {
                error = res;
                return false;
            }

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        } finally {
            try {
                System.out.println("[*] Closing socket...");
                if (sock != null)
                    sock.close();
            } catch (IOException ex) {  ex.printStackTrace(); }
        }

        return true;
    }

    public Boolean isHighest(int item) {
        float highest = 0;
        int id = 0;
        try {
            JSONObject json = new JSONObject();
            json.put("action", "get-highest");
            json.put("item", item);

            System.out.println("[*] Connection to server");
            sock = new Socket("localhost", PORT);
            System.out.println("[*] Connected to server successfully!");

            DataInputStream input = new DataInputStream(sock.getInputStream());
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());

            System.out.println("[*] Sending data...");
            output.writeUTF(json.toJSONString());
            output.flush();
            System.out.println("[*] Done sending data...");

            System.out.println("[*] Reading data...");
            String request = input.readUTF();
            System.out.println("[*] Done reading data...");
            System.out.println(request);
            Object object = new JSONParser().parse(request);
            json = (JSONObject)( new JSONParser().parse(request));
            highest = json.get("highest") == null ? 0.0f : (float)((double)json.get("highest"));

            id = json.get("id") == null ? 0 : (int)((long)json.get("id"));
            if (id == auctionUser.getId()) return true;

            String res = (String)json.get("error");
            if (res != null ) {
                error = res;
                return false;
            }

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        } finally {
            try {
                System.out.println("[*] Closing socket...");
                if (sock != null)
                    sock.close();
            } catch (IOException ex) {  ex.printStackTrace(); }
        }

        return false;
    }


    public float getHighest(int item) {
        float highest = 0;
        try {
            JSONObject json = new JSONObject();
            json.put("action", "get-highest");
            json.put("item", item);

            System.out.println("[*] Connection to server");
            sock = new Socket("localhost", PORT);
            System.out.println("[*] Connected to server successfully!");

            DataInputStream input = new DataInputStream(sock.getInputStream());
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());

            System.out.println("[*] Sending data...");
            output.writeUTF(json.toJSONString());
            output.flush();
            System.out.println("[*] Done sending data...");

            System.out.println("[*] Reading data...");
            String request = input.readUTF();
            System.out.println("[*] Done reading data...");
            System.out.println(request);
            Object object = new JSONParser().parse(request);
            json = (JSONObject)( new JSONParser().parse(request));
            highest = json.get("highest") == null ? 0.0f : (float)((double)json.get("highest"));
            String res = (String)json.get("error");
            if (res != null ) {
                error = res;
                return -1.0f;
            }

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        } finally {
            try {
                System.out.println("[*] Closing socket...");
                if (sock != null)
                    sock.close();
            } catch (IOException ex) {  ex.printStackTrace(); }
        }

        return highest;
    }


    public Boolean signUp(AuctionUser user) {
        int id = 0;
        try {
            JSONObject json = (JSONObject)( new JSONParser().parse(user.toString()));
            json.put("action", "sign-up");

            System.out.println("[*] Connection to server");
            sock = new Socket("localhost", PORT);
            System.out.println("[*] Connected to server successfully!");

            DataInputStream input = new DataInputStream(sock.getInputStream());
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());

            System.out.println("[*] Sending data...");
            output.writeUTF(json.toJSONString());
            output.flush();
            System.out.println("[*] Done sending data...");


            System.out.println("[*] Reading data...");
            String request = input.readUTF();
            System.out.println("[*] Done reading data...");
            System.out.println(request);
            json = (JSONObject)( new JSONParser().parse(request));
            id = json.get("id") == null ? 0 : (int)((long)json.get("id"));
            String res = (String)json.get("error");
            if (res != null ) {
                error = res;
                return false;
            }
        } catch (IOException ex) {
            System.out.println("Server connection failed!");
            return false;
        } catch (ParseException ex) {
            System.out.println(ex);
            return false;

        } finally {
            try {
                System.out.println("[*] Closing socket...");
                if (sock != null)
                    sock.close();
            } catch (IOException ex) {  return false; }
        }

        auctionUser = user;
        auctionUser.setId(id);
        return true;
    }

    public Boolean placeBid(AuctionItem item, float price) {
        try {
            JSONObject json = new JSONObject();
            json.put("action", "place-bid");
            json.put("bid", price);
            json.put("user", auctionUser.getId());
            json.put("item", item.getId());

            System.out.println("[*] Connection to server");
            sock = new Socket("localhost", PORT);
            System.out.println("[*] Connected to server successfully!");

            DataInputStream input = new DataInputStream(sock.getInputStream());
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());

            System.out.println("[*] Sending data...");
            output.writeUTF(json.toJSONString());
            output.flush();
            System.out.println("[*] Done sending data...");


            System.out.println("[*] Reading data...");
            String request = input.readUTF();
            System.out.println("[*] Done reading data...");
            System.out.println(request);
            json = (JSONObject)( new JSONParser().parse(request));
            String res = (String)json.get("error");
            if (res != null ) {
                error = res;
                return false;
            }

        } catch (IOException ex) {
            System.out.println("Server connection failed!");
            return false;
        } catch (ParseException ex) {
            System.out.println(ex);
            return false;

        } finally {
            try {
                System.out.println("[*] Closing socket...");
                if (sock != null)
                    sock.close();
            } catch (IOException ex) {  return false; }
        }
        return true;
    }


    public Boolean unBid(AuctionItem item) {
        try {
            JSONObject json = new JSONObject();
            json.put("action", "un-bid");
            json.put("user", auctionUser.getId());
            json.put("item", item.getId());

            System.out.println("[*] Connection to server");
            sock = new Socket("localhost", PORT);
            System.out.println("[*] Connected to server successfully!");

            DataInputStream input = new DataInputStream(sock.getInputStream());
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());

            System.out.println("[*] Sending data...");
            output.writeUTF(json.toJSONString());
            output.flush();
            System.out.println("[*] Done sending data...");


            System.out.println("[*] Reading data...");
            String request = input.readUTF();
            System.out.println("[*] Done reading data...");
            System.out.println(request);
            json = (JSONObject)( new JSONParser().parse(request));
            String res = (String)json.get("error");
            if (res != null ) {
                error = res;
                return false;
            }

        } catch (IOException ex) {
            error = "Server connection failed!";
            System.out.println(error);
            return false;
        } catch (ParseException ex) {
            error = "Invalid response!";
            System.out.println(error);
            System.out.println(ex);
            return false;
        } finally {
            try {
                System.out.println("[*] Closing socket...");
                if (sock != null)
                    sock.close();
            } catch (IOException ex) {  return false; }
        }
        return true;
    }

    public String getLastError() {
        return error;
    }
}