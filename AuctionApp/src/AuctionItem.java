/**
 * AuctionItem
 * This auction item describes the information about an auctioned item
 *
 * @author Cyril Acquah Kofi
 * @author 10686868
 * @version 1.0.0
 * @since 01-05-2020
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AuctionItem {

    private String url = "";
    private String name = "";
    private String description = "";
    private float price = 0.0f;
    private float highest = 0.0f;
    private int id = 0;

    AuctionItem(String url, String name, String description, float price, float highest) {
        this.url = url;
        this.name = name;
        this.description = description;
        this.price = price;
        this.highest = highest;
    }

    AuctionItem() {}

    public AuctionItem fromJSON(JSONObject json) {
        id = json.get("id") == null ? 0 : (int)((long)json.get("id"));
        url = (String)json.get("url");
        name = (String)json.get("name");
        description = (String)json.get("description");
        price = (float)((double)json.get("price"));
        highest = (float)((double)json.get("highest"));
        return this;
    }


    /**
     *  This method returns the description of the
     *  auction item
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * This sets the description of the item
     *
     * @param description The description of the
     *                    Auction item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *  This method returns the location of item image
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method sets the URL of the auction item
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method get the price information of an item
     *
     * @return float The price of an item
     */
    public float getPrice() {
        return price;
    }

    /**
     * This method sets the price of an item
     *
     * @param price The item price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * This method returns the name of an item
     *
     * @return String the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of an item
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the highest bid
     *
     * @return the highest bid
     */
    public float getHighest() {
        return highest;
    }

    /**
     * This method takes the highest bid
     *
     * @param highest
     */
    public void setHighest(float highest) {
        this.highest = highest;
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
        json.put("url", url);
        json.put("name", name);
        json.put("description", description);
        json.put("price", price);
        json.put("highest", highest);
        json.put("id", id);
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
