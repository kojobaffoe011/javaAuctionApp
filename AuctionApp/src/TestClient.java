/**
 * TestClient
 * This creates data and sends to the server for testing
 *
 * @author Cyril Acquah Kofi
 * @author 10686868
 * @version 1.0.0
 * @since 01-05-2020
 */

import java.util.ArrayList;

public class TestClient {

    public static void main(String[] args) {
        // Socket client for handling network calls to the server
        AuctionClient client = new AuctionClient();

        // An Abstraction of a user
        AuctionUser user = new AuctionUser();

        String homeDir = "/Users/kojobaffoe/Desktop/AuctionApp/images";
        // An Abstraction of a item
        AuctionItem item = new AuctionItem();
        ArrayList<AuctionItem> items = new ArrayList<AuctionItem>();

        items.add(new AuctionItem(homeDir + "item1.jpg", "Coloured Vans Women", "Very lovely vans", 200.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item3.jpg", "Vans", "Nice vans", 200.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item2.jpg", "Kwahu Estate House", "A small estate", 200.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item4.jpg", "Green Car", "A new race car", 2000000.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item5.jpg", "Bicycle", "Children' bicycle", 1000.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item6.jpg", "Patek Phillipe Men's Watch", "Expensive watch", 1300.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item7.jpg", "DW Women's watch", "Nice Watches for women", 4400.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item8.jpg", "Ada House", "A How for items", 7676700.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item9.jpg", "Yahama Trumpet", "Golden trumpet", 900.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item10.jpg", "D-Link Telephone", "Telephone from the past", 100.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item11.jpg", "Furniture for Home", "Some new home furniture", 8700.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item12.jpg", "Lambo Lezz Geaux", "Awesome fast car", 300000.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item13.jpg", "Zara Men's Suit", "Some men's suit", 800.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item14.jpg", "Samsung TV", "A Plasma TV", 2000.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item15.jpg", "Binatone Washing Machine", "A new washing machine", 6000.0f, 0.0f));
        items.add(new AuctionItem(homeDir + "item16.jpg", "Philips Freezer", "deep freezer", 2000.0f, 0.0f));


        // Creating an Auction itemhighest
        item.setUrl("http://google.com");
        item.setDescription("This the best car used in 007");
        item.setName("Ferrari");
        item.setPrice(200000.0f);
        item.setHighest(3565762.0f);

        // Creating a user
        user.setUsername("johndoe87");
        user.setPassword("pass");
        user.setEmail("john@email.com");
        user.setName("John Doe");

        // Simple API for signing up
         client.signUp(user);

        // Simple API for signing in
        client.signIn(user);


        items.forEach(item1 -> {
            client.addItem(item1);
        });

        // Returns a list of auctioned items from the server
        items = client.getAuctionItems();
        System.out.println(items);

        items.forEach(item1 -> {
            client.placeBid(item1, 500);
            System.out.println(client.getHighest(item1.getId()));
        });

        items.forEach(item1 -> {
            client.unBid(item1);
            System.out.println(client.getHighest(item1.getId()));
        });
    }

}
