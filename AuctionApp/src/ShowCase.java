import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.util.ArrayList;


/**
 *
 * @author kojobaffoe
 * ID - 10680839
 * 
 * @author Joseph Sola-Eniafe
 * ID - 10685616 
 *
 */

public class ShowCase extends JFrame{


    
    private static final long serialVersionUID = 1L;

    private ArrayList<AuctionItem> itemsList;
    private AuctionClient auctionClient;
    long time = 0;
    private Timer Timer0;


    public ShowCase(AuctionClient client) {
        super("SHOW CASE");

        auctionClient = client;
        itemsList = auctionClient.getAuctionItems();
        
		 long now = (long)(System.currentTimeMillis()/1000);
		 long stop = (long)(auctionClient.getStop()/1000);
		 time = (long)(stop - now);


        //fonts
        Font font = new Font("Chalkboard", Font.BOLD, 10);
        Font font2 = new Font("Apple Symbols", Font.BOLD, 13);
        Font font3 = new Font("Godzilla", Font.BOLD, 25);
        Font font4 = new Font("Apple Symbols", Font.BOLD|Font.ITALIC , 9);
        Font font5 = new Font("Apple Symbols", Font.BOLD|Font.ITALIC , 11);
        Font font6 = new Font("Chalkboard", Font.BOLD, 12);
        Font font8 = new Font("Apple Symbols", Font.BOLD, 35);

    
        //header panel

        JPanel header = new JPanel();
        header.setBackground(new Color(0,0,0,50));
        header.setBounds(0,0,1000,50); 
        header.setLayout(null);
        header.setBackground(Color.BLACK);

        
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

        //Announcement panel
        JPanel announce = new JPanel();
        announce.setBackground(new Color(0,0,0,5));
        announce.setLayout(null);
        announce.setBounds(220, 80, 730, 100);

        //setting welcome msg
        JLabel announceLabel = new JLabel("SALES START AT 3PM GMT!");
        announceLabel.setFont(font3);
        announceLabel.setBounds(40, 30, 500, 20);
        announceLabel.setForeground(new Color(255,204,51));
        announce.add(announceLabel);

        // setting note
        JLabel noteLabel = new JLabel("PROMO AUCTIONS!");
        noteLabel.setFont(font2);
        noteLabel.setBounds(40, 60, 500, 10);
        noteLabel.setForeground(Color.lightGray);
        announce.add(noteLabel);

        // start of sales note
        JLabel startLabel = new JLabel("EXPERIENCE THE BEST AUCTION SALES!");
        startLabel.setFont(font4);
        startLabel.setBounds(40, 80, 500, 10);
        startLabel.setForeground(Color.white);
        announce.add(startLabel);

        // setting app name
        JLabel appNameLabel = new JLabel("BAFFOE");
        appNameLabel.setFont(font);
        appNameLabel.setForeground(new Color(255,204,51));
        appNameLabel.setBounds(68,17,100,10);
        header.add(appNameLabel);

        JLabel appName = new JLabel("AUCTIONS");
        appName.setFont(font2);
        appName.setForeground(Color.darkGray);
        appName.setBounds(62,29,100,10);
        header.add(appName);
//
//        //creating search box
//
//        JTextField search = new JTextField("Search available items..");
//        search.setFont(font5);
//        search.setLayout(null);
//        search.setSize(300, 35);
//        search.setBounds(130, 10, 300, 35);
//        search.setBackground(Color.darkGray);
//        search.setForeground(new Color(255,204,51));
//        header.add(search);
//
//        // adding search button
//        ImageIcon icon = new ImageIcon("/Users/kojobaffoe/Desktop/eclipse/Auction/images/search.png");
//        Image icon1 = icon.getImage();
//        Image icon2 = icon1.getScaledInstance(15,15,Image.SCALE_SMOOTH);
//        icon = new ImageIcon(icon2);
//
//        JButton searchButton = new JButton(icon);
//        searchButton.setSize(15, 10);
//        searchButton.setBackground(Color.darkGray);
//        searchButton.setOpaque(true);
//        searchButton.setBorderPainted(false);
//        searchButton.setBounds(430, 14, 30, 27);
//        header.add(searchButton);
//        //search.add(searchButton);
//
//        //show search results msg
//         searchButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//            JOptionPane.showMessageDialog(null, "No results for your search");
//         }
//        });
        
		        
		 
        // creating logout button

        JButton logoutButton = new JButton("Logout");
        logoutButton.setSize(60, 10);

        logoutButton.setFont(font6);
        logoutButton.setBackground(new Color(0,0,0,0));
        logoutButton.setForeground(new Color(255,204,51));
        logoutButton.setOpaque(true);
        logoutButton.setBorderPainted(true);
        logoutButton.setBounds(850, 14, 100, 27);
         header.add(logoutButton);

         // Linking logout button to the login Page
         logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            dispose();
             new Login().setVisible(true); 
         }
        });
        
         
         //creating back button 
         ImageIcon backIcon = new ImageIcon("/Users/kojobaffoe/Desktop/AuctionApp/images/arrows.png");
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
         
         //show search results msg
          backButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             dispose();
             new  Login().setVisible(true); 
          }
         });

         
         
        
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
        showcase.setBackground(new Color(0,0,0,15));
        showcase.setBounds(220,205, 730, 550);
        showcase.setBorder(new RoundedBorder(20));
        side.setBorder(new RoundedBorder(20));

        //creating grid layout for Showcase
        GridLayout layout = new GridLayout(4, 4);
        layout.setHgap(10);
        layout.setVgap(10);
        showcase.setLayout(layout);

        for (AuctionItem auctionItem : itemsList) {
            ImageIcon AuctionIcon = new ImageIcon(auctionItem.getUrl());
            Image AuctionIconImg = AuctionIcon.getImage();
            Image AuctionIconImage = AuctionIconImg.getScaledInstance(165,120,Image.SCALE_SMOOTH);
            AuctionIcon = new ImageIcon(AuctionIconImage);
            JButton b1 = new JButton(AuctionIcon);
            b1.setBorderPainted(false);
            b1.setOpaque(true);
            b1.setToolTipText(auctionItem.getName());
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new Bid(auctionClient, itemsList, auctionItem).setVisible(true);
                }
            });
            showcase.add(b1);
           
        }
        

        
//        //creating timer panel 
      JPanel timer = new JPanel();       
      timer.setSize(150,300);
      timer.setBounds(50,550,150,205);
      timer.setBackground(Color.BLACK);
      timer.setLayout(null);
      
    //hour Label
      JLabel hour = new JLabel(" ");
      hour.setFont(font8);
      hour.setBounds(10,70,1000,50);
      hour.setForeground(new Color(255,204,51));
      timer.add(hour);
      
      	Timer0 = new Timer(1000,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				

				time--;
				if(time>=0) {
				hour.setText(""+ time + " secs");
				}
				if (time <= 0) {
                    String msg = "";
                    for (AuctionItem auctionItem : itemsList) {
                        if (auctionClient.isHighest(auctionItem.getId())) {
                            msg += "" + auctionItem.getName() + "  -  " + auctionItem.getHighest() + "\n";
                            
                        }
                       
                    }
            
                    JOptionPane.showMessageDialog(null, msg);
                    Timer0.stop();
				}
			}
			
			 
   	});
      	
      	

       Timer0.start();

        

        //creating announcement background
        ImageIcon announceImage = new ImageIcon("/Users/kojobaffoe/Desktop/AuctionApp/images/header.jpg");
        Image announceImg = announceImage.getImage();
        Image annouceImg1 = announceImg.getScaledInstance(730,92,Image.SCALE_SMOOTH);
         announceImage = new ImageIcon(annouceImg1);
        JLabel annouceBackground = new JLabel("",announceImage ,JLabel.CENTER);

        annouceBackground.setBounds(0, 5, 720, 80);
        annouceBackground.setSize(730, 90);
        announce.add(annouceBackground);


         //creating background image
         ImageIcon background_image = new ImageIcon("/Users/kojobaffoe/Desktop/AuctionApp/images/gray.jpg");
         Image img = background_image.getImage();
         Image img1 = img.getScaledInstance(1000,900,Image.SCALE_SMOOTH);
         background_image = new ImageIcon(img1);
         JLabel background = new JLabel("",background_image ,JLabel.CENTER);
         
         background.setLayout(null);
         background.add(timer);
         background.add(showcase);
         background.add(announce);
         background.add(side);
         background.add(header);
         background.setBounds(0, 0, 1000, 900);
         add(background);
         



         //making frame 
         setSize(1000,900);
         setVisible(true);
         setLayout(null);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setResizable(false);
    }

}
