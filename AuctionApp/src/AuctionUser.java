/**
 * AuctionUser
 * This is the a details for the blue print of the a user
 *
 * @author Cyril Acquah Kofi
 * @author 10686868
 * @version 1.0.0
 * @since 01-05-2020
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class AuctionUser {

    private String name = "";
    private String email = "";
    private String username = "";
    private String password = "";
    private int id = 0;

    AuctionUser() {}

    AuctionUser(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("email", email);
        json.put("username", username);
        json.put("password", password);
        return json.toJSONString();
    }


    public JSONObject toJSON() {
        try{
            return (JSONObject)(new JSONParser().parse(toString()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new JSONObject();
        }
    }
}