import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.NumberFormatter;

import static java.lang.Float.parseFloat;

public class Bid extends JFrame{
	private Timer timer;
	
/**
 *
 * @author kojobaffoe
 * ID - 10680839
 * 
 * @author Joseph Sola-Eniafe
 * ID - 10685616 
 *
 **/
	private static final long serialVersionUID = 1L;
	long time = 0;
	int count = 0;
	String timeString = "15:10 GMT";
	private AuctionItem auctionItem;
	private AuctionClient auctionClient;
	private ArrayList<AuctionItem> auctionItems;
	private final String imageDir = "/Users/kojobaffoe/Desktop/AuctionApp/images/";

	public Bid(AuctionClient client, ArrayList<AuctionItem> items, AuctionItem item) {
		 super("BIDDING PAGE");

		 auctionClient = client;
		 auctionItem = item;

		 long now = (long)(System.currentTimeMillis()/1000);
		 long stop = (long)(auctionClient.getStop()/1000);
		 time = (long)(stop - now);
		 timeString = (new Date(auctionClient.getStart())).toString();

	     //creating font 
	     //creating font
		 Font font = new Font("Chalkboard", Font.BOLD, 13);
	     Font font1 = new Font("Chalkboard", Font.BOLD, 10);
	     Font font2 = new Font("Apple Symbols", Font.BOLD, 13);
	     Font font3 = new Font("Godzilla", Font.BOLD, 25);
	     Font font4 = new Font("Apple Symbols", Font.BOLD|Font.ITALIC , 9);
	     Font font5 = new Font("Apple Symbols", Font.BOLD|Font.ITALIC , 11);
	     Font font6 = new Font("Chalkboard", Font.BOLD, 12);
	     Font font7 = new Font("Chalkboard", Font.BOLD, 20);
	     Font font8 = new Font("Apple Symbols", Font.BOLD, 40);
	     Font font9 = new Font("Chalkboard", Font.BOLD, 20);
	     
	     

	     //header
	     JPanel header = new JPanel();
	     header.setBackground(new Color(0,0,0,50));
	     header.setBounds(0,0,1000,50); 
	     header.setLayout(null);
	     header.setBackground(Color.BLACK);
	  
	     
	     // setting app name
	        JLabel appNameLabel = new JLabel(auctionClient.getUser().getName());
	        appNameLabel.setFont(font1);
	        appNameLabel.setForeground(new Color(255,204,51));
	        appNameLabel.setBounds(68,17,100,10);
	        header.add(appNameLabel);

	        JLabel appName = new JLabel(auctionClient.getUser().getEmail());
	        appName.setFont(font2);
	        appName.setForeground(Color.darkGray);
	        appName.setBounds(62,29,100,10);
	        header.add(appName);

	     //side panel
	        JPanel side = new JPanel();
	        side.setBackground(Color.BLACK);
	        side.setBounds( 50,80, 150, 450 );
	        side.setLayout(null);
	        
	        
	     // adding about button
	        JButton aboutButton = new JButton("About");
	        aboutButton.setFont(font6);
	        aboutButton.setSize(60, 10);
	        aboutButton.setBackground(new Color(0,0,0,0));
	        aboutButton.setForeground(new Color(255,204,51));
	        aboutButton.setOpaque(true);
	        aboutButton.setBorderPainted(true);
	        aboutButton.setBounds(25, 20, 100, 27);
	         side.add(aboutButton);

	         aboutButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            JOptionPane.showMessageDialog(null, " \n Baffoe Auctions was set up by three university graduates \n who want to make auction sales easy, affordable and effective for everyone. \n Patronize us and enjoy the best auction deals you have ever seen. \n Thank You! ");
	            
	         }
	        });
	         
	         //adding Contact Us button
			JButton contactButton = new JButton("Contact Us");
	        contactButton.setFont(font6);
	        contactButton.setSize(60, 10);
	        contactButton.setBackground(new Color(0,0,0,0));
	        contactButton.setForeground(new Color(255,204,51));
	        contactButton.setOpaque(true);
	        contactButton.setBorderPainted(true);
	        contactButton.setBounds(15, 60 , 120, 27);
	        side.add(contactButton);

	         contactButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            JOptionPane.showMessageDialog(null, "For any enquiries, Contact us on: \n +233540405131 \n kojobaffoe100@yahoo.com");
	         }
	        });

	         //adding text area
	         JTextArea suggestion = new JTextArea(" \n \n \n \n \n \n \n  Make suggestions about \n  auction items here");
	         suggestion.setSize(30, 300);
	         suggestion.setBackground(Color.darkGray);
	         suggestion.setForeground(new Color(255,204,51));
	         suggestion.setBounds(15, 100, 120, 250);
	         suggestion.setFont(font5);
	         side.add(suggestion);

	         // adding submit button
	         JButton submitButton = new JButton("Submit");
	         submitButton.setFont(font6);
	         submitButton.setBackground(new Color(0,0,0,0));
	         submitButton.setForeground(new Color(255,204,51));
	         submitButton.setOpaque(true);
	         submitButton.setBorderPainted(true);
	         submitButton.setBounds(25, 360 , 100, 27);
	         side.add(submitButton);

	         submitButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            JOptionPane.showMessageDialog(null, " Thank You!, your suggestion is most welcome");
	         }
	        });

	       //creating back button 
	         ImageIcon backIcon = new ImageIcon(imageDir+"/arrows.png");
	         Image backIcon1 = backIcon.getImage();
	         Image backIcon2 = backIcon1.getScaledInstance(15,15,Image.SCALE_SMOOTH);
	         backIcon = new ImageIcon(backIcon2);

	         JButton backButton = new JButton(backIcon);
	         backButton.setSize(15, 10);
	         backButton.setBackground(new Color(0,0,0,0));
	         backButton.setOpaque(true);
	         backButton.setBorderPainted(true);
	         backButton.setBounds(10 , 14, 30, 27);
	         header.add(backButton);

	         backButton.addActionListener(actionEvent -> {
	         	dispose();
	         	new ShowCase(auctionClient).setVisible(true);
			 });


	       //Announcement panel
	         JPanel announce = new JPanel();
	         announce.setBackground(new Color(0,0,0,5));
	         announce.setLayout(null);
	         announce.setBounds(220, 80, 730, 100);

	         //setting welcome msg
	         JLabel announceLabel = new JLabel("PLACE A BID!");
	         announceLabel.setFont(font3);
	         announceLabel.setBounds(40, 30, 500, 20);
	         announceLabel.setForeground(new Color(255,204,51));
	         announce.add(announceLabel);

	         // setting note
	         JLabel noteLabel = new JLabel("AND WIN ITEMS AT THE BEST PRICES!");
	         noteLabel.setFont(font2);
	         noteLabel.setBounds(40, 60, 500, 10);
	         noteLabel.setForeground(Color.lightGray);
	         announce.add(noteLabel);

	         // start of sales note
	         JLabel startLabel = new JLabel("Sales start at " );
	         startLabel.setFont(font4);
	         startLabel.setBounds(40, 80, 500, 10);
	         startLabel.setForeground(Color.white);
	         announce.add(startLabel);


	         class RoundedBorder implements Border {

	             private int radius;

	             RoundedBorder(int radius) {
	                 this.radius = radius;
	             }
	             @Override
	             public Insets getBorderInsets(Component c) {
	                 return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	             }
	             @Override
	             public boolean isBorderOpaque() {
	                 return true;
	             }
	             @Override
	             public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	                 g.drawRoundRect(x,y,width-1,height-1,radius,radius);
	             }
	         }

	         //Showcase Panel
	         JPanel showcase = new JPanel();
	         showcase.setBackground(Color.BLACK);
	         showcase.setBounds(550,205,400 , 550);
	         showcase.setBorder(new RoundedBorder(20));
	         showcase.setLayout(null);
	         side.setBorder(new RoundedBorder(20));

	         // creating name of item
	         JLabel itemName = new JLabel();
	         itemName.setText(auctionItem.getName());
	         itemName.setFont(font7);
	         itemName.setForeground(Color.lightGray);
	         itemName.setBounds(25, 10, 400, 50);
	         showcase.add(itemName);

	         // creating availability status
	         JLabel availStat = new JLabel("Availability: ");
	         availStat.setFont(font6);
	         availStat.setForeground(Color.lightGray);
	         availStat.setBounds(25,40,200,50);
	         showcase.add(availStat);

	         JLabel stat = new JLabel("In Stock");
	         stat.setFont(font);
	         stat.setBounds(100, 40, 100, 50);
	         stat.setForeground(new Color(255,204,51));
	         showcase.add(stat);
	         
	         //add description
	         JLabel descriptionLabel = new JLabel("Item Description: ");
	         descriptionLabel.setFont(font6);
	         descriptionLabel.setForeground(Color.lightGray);
	         descriptionLabel.setBounds(25,100,200,50);
	         showcase.add(descriptionLabel);

	         
	         JLabel description = new JLabel("" + auctionItem.getDescription());
	         description.setFont(font);
	         description.setBounds(135, 100, 10000, 50);
	         description.setForeground(new Color(255,204,51));
	         showcase.add(description);
	         
	         

	         //time label
	         JLabel timeStart = new JLabel("Auction Started at: ");
	         timeStart.setFont(font6);
	         timeStart.setForeground(Color.lightGray);
	         timeStart.setBounds(25, 148, 300, 50);
	         showcase.add(timeStart);


	         JLabel timeLeft = new JLabel("Time Left: ");
	         timeLeft.setFont(font6);
	         timeLeft.setForeground(Color.lightGray);
	         timeLeft.setBounds(25, 205, 100, 50);
	         showcase.add(timeLeft);

	         JLabel bidHour = new JLabel(timeString);
	         bidHour.setFont(font8);
	         bidHour.setBounds(25,180,1000,50);
	         bidHour.setForeground(new Color(255,204,51));
	         showcase.add(bidHour);

	         //hour Label
	         JLabel hour = new JLabel(" ");
	         hour.setFont(font8);
	         hour.setBounds(25,235,1000,50);
	         hour.setForeground(new Color(255,204,51));
	         showcase.add(hour);

	         //place Bid Button
	         JButton placeBidButton = new JButton("Place BId");
	         placeBidButton.setFont(font1);
	         placeBidButton.setBounds(270,500,90,30);
	         placeBidButton.setOpaque(true);
	         placeBidButton.setBorderPainted(true);
	         placeBidButton.setBackground(new Color(0,0,0,0));
	         placeBidButton.setForeground(new Color(255,204,51));
	         showcase.add(placeBidButton);
	         
	         //Unbid button
	         JButton unBidButton = new JButton("unBId");
	         unBidButton.setFont(font1);
	         unBidButton.setBounds(30,499,90,30);
	         unBidButton.setOpaque(true);
	         unBidButton.setBorderPainted(true);
	         unBidButton.setBackground(new Color(0,0,0,0));
	         unBidButton.setForeground(new Color(255,204,51));
	         showcase.add(unBidButton);

	         //bid History
	         JLabel bidHistory = new JLabel("Bid History: ");
	         bidHistory.setFont(font6);
	         bidHistory.setForeground(Color.lightGray);
	         bidHistory.setBounds(25, 260, 100, 50);
	         showcase.add(bidHistory);

	         //bid label
	         JLabel bidLabel = new JLabel(" Bid(s)");
	         bidLabel.setFont(font8);
	         bidLabel.setForeground(new Color(255,204,51));
	         bidLabel.setBounds(80, 295, 100, 50);
	         showcase.add(bidLabel);

	         //bid Count
	         JLabel bidCount = new JLabel(""+ count);
	         bidCount.setFont(font8);
	         bidCount.setForeground(new Color(255,204,51));
	         bidCount.setBounds(25, 295, 300, 50);
	         showcase.add(bidCount);


	         // Starting Bid
	         JLabel startBid = new JLabel("Starting Bid: ");
	         startBid.setFont(font6);
	         startBid.setForeground(Color.lightGray);
	         startBid.setBounds(25, 320, 100, 50);
	         showcase.add(startBid);

	         JLabel sBid = new JLabel("");
	         sBid.setText("¢ " +   auctionItem.getPrice());
	         sBid.setSize(200, 35);
	         sBid.setBounds(25, 360, 300, 40);
	         sBid.setForeground(new Color(255,204,51));
	         sBid.setFont(font8);
	         showcase.add(sBid);

	         //max Bid
	         JLabel maxBid = new JLabel("Enter your Bid: ");
	         maxBid.setFont(font6);
	         maxBid.setForeground(Color.lightGray);
	         maxBid.setBounds(25, 380, 300, 50);
	         showcase.add(maxBid);

	         NumberFormat format = NumberFormat.getInstance();
	         NumberFormatter formatter = new NumberFormatter(format);
	         formatter.setValueClass(Float.class);
	         formatter.setMinimum(null);
	         formatter.setMaximum(Float.MAX_VALUE);
	         formatter.setAllowsInvalid(false);
	         formatter.setOverwriteMode(true);
	         format.setGroupingUsed(false);
	         // If you want the value to be committed on each keystroke instead of focus lost
	         formatter.setCommitsOnValidEdit(true);


	         JFormattedTextField BidField = new JFormattedTextField(formatter);
	         BidField.setSize(200, 35);
	         BidField.setBounds(45, 420, 170, 40);
	         BidField.setBackground(Color.darkGray);
	         BidField.setForeground(new Color(255,204,51));
	         BidField.setFont(font8);
	         BidField.setToolTipText("Bid should be higher than current bid");
	         BidField.setValue(item.getHighest() == 0.0 ? item.getPrice() : item.getHighest());
	         showcase.add(BidField);

		    JLabel cedi = new JLabel("¢");

	         cedi.setSize(200, 35);
	         cedi.setBounds(25, 422, 170, 40);
	         cedi.setForeground(new Color(255,204,51));
	         cedi.setFont(font8);

	         showcase.add(cedi);

	         //panel in show case panel
	         JPanel bid = new JPanel();
	         bid.setBackground(new Color(0,0,0,15));
	         bid.setSize(500,500);
	         bid.setBounds(220,205,320,325 );
	         bid.setBorder(new RoundedBorder(20));
	         bid.setLayout(null);

			ImageIcon AuctionIcon = new ImageIcon(auctionItem.getUrl());
			Image AuctionIconImg = AuctionIcon.getImage();
			Image AuctionIconImage = AuctionIconImg.getScaledInstance(300,300,Image.SCALE_SMOOTH);
			AuctionIcon = new ImageIcon(AuctionIconImage);
			JLabel itemIcon = new JLabel("", AuctionIcon, JLabel.RIGHT);
			itemIcon.setBounds(0, 2, 310, 320);
			bid.add(itemIcon);

	         // display Highest Bid Panel
	         JPanel hBid = new JPanel();
	         hBid.setBackground(Color.BLACK);
	         hBid.setBounds(50, 550, 490, 205);
	         hBid.setLayout(null);

	         //displaying highestBids
	         JLabel display = new JLabel("HIGHEST BIDDER: ");
	         display.setBounds(30,30,300, 50);
	         display.setFont(font9);
	         display.setForeground(Color.lightGray);
	         hBid.add(display);
	         
	         JLabel display1 = new JLabel("HIGHEST BID: ");
	         display1.setBounds(30,100,300, 50);
	         display1.setFont(font9);
	         display1.setForeground(Color.lightGray);
	         hBid.add(display1);



	         JLabel highestBidder = new JLabel(" ");
	         highestBidder.setBounds(200, 33, 300, 50);
	         highestBidder.setForeground(new Color(255,204,51));
	         highestBidder.setFont(font);
		     hBid.add(highestBidder);
		     

	         JLabel cedi1 = new JLabel(" ¢");
	         cedi1.setBounds(200, 110, 50, 40);
	         cedi1.setForeground(new Color(255,204,51));
	         cedi1.setFont(font8);
		     hBid.add(cedi1);


	         JLabel cedi1Bid = new JLabel(""+ auctionItem.getHighest());
	         cedi1Bid.setBounds(250, 110, 170, 40);
	         cedi1Bid.setForeground(new Color(255,204,51));
	         cedi1Bid.setFont(font8);
		     hBid.add(cedi1Bid);

	         //creating announcement background
	         ImageIcon announceImage = new ImageIcon(imageDir+"/header.jpg");
	         Image announceImg = announceImage.getImage();
	         Image annouceImg1 = announceImg.getScaledInstance(730,92,Image.SCALE_SMOOTH);
	         announceImage = new ImageIcon(annouceImg1);
	         JLabel annouceBackground = new JLabel("",announceImage ,JLabel.CENTER);

	         annouceBackground.setBounds(0, 5, 720, 80);
	         annouceBackground.setSize(730, 90);
	         announce.add(annouceBackground);

	        //creating background image
	        ImageIcon background_image = new ImageIcon(imageDir+"/gray.jpg");
	        Image img = background_image.getImage();
	        Image image1 = img.getScaledInstance(1000,900,Image.SCALE_SMOOTH);
	        background_image = new ImageIcon(image1);
	        JLabel background = new JLabel("",background_image ,JLabel.CENTER);
	        background.setForeground(new Color(0,0,0,80));
	        background.setBackground(new Color(0,0,0,80));
	        
	        background.add(hBid);
	        background.add(bid);
	        background.add(showcase);
	        background.add(announce);
	        background.add(side);
	        background.setBounds(0, 0, 1000, 900);
	        background.add(header);
	        add(background);

	        placeBidButton.addActionListener(actionEvent -> {
	        	if (!auctionClient.placeBid(auctionItem, parseFloat(BidField.getText())) ) {
					JOptionPane.showMessageDialog(null,
							auctionClient.getLastError());
				} else count += 1;
				bidCount.setText(""+count);
			});

	        unBidButton.addActionListener(actionEvent -> {
				if (!auctionClient.unBid(auctionItem)) {
					JOptionPane.showMessageDialog(null,
							auctionClient.getLastError());
				} else {
					count = 0;
				}
				bidCount.setText(""+count);
			});

			//timer
			timer = new Timer(1000,new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					time--;
					hour.setText(""+ (time < 0 ? 0 : time) + " secs");
					if(time <= 0) {
						if (auctionClient.isSold(item.getId())) {
							stat.setText("Sold");
						} else stat.setText("Item wasn't sold");
						placeBidButton.setEnabled(false);
						unBidButton.setEnabled(false);
						BidField.setEnabled(false);
						timer.stop();
					}

					if (time > 0 ) {
						float highest = auctionClient.getHighest(auctionItem.getId());
						if ( highest < parseFloat(BidField.getText()))
							placeBidButton.setEnabled(true);
						else placeBidButton.setEnabled(false);
						highest = highest < 0.0f ? 0.0f : highest;
						auctionItem.setHighest(highest);
						cedi1Bid.setText(""+highest);
						if (auctionClient.isHighest(item.getId())) highestBidder.setText("You are the highest bidder");
						else highestBidder.setText("You are not the current highest bidder");
					}
				}
			});
			timer.start();
	        
	        //making frame 
	         setSize(1000,900);
	         setVisible(true);
	         setLayout(null);
	         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //  setUndecorated(true);
	         setResizable(false);
	}
}
