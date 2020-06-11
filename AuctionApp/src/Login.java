import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 
 * @author kojobaffoe
 * ID - 10680839
 * 
 * @author Joseph Sola-Eniafe
 * ID - 10685616 
 */

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;

    private AuctionClient auctionClient = new AuctionClient();
    private AuctionUser auctionUser;

    //@SuppressWarnings("deprecation")
    public Login() {
        super("LOGIN PAGE");

        String imagePath = "/Users/kojobaffoe/Desktop/AuctionApp/images";
        ArrayList<AuctionItem> items = new ArrayList<AuctionItem>();

        //creating font 
        Font font1 = new Font("Chalkboard", Font.BOLD, 10);
        Font font2 = new Font("Apple Symbols", Font.BOLD, 13);

        //header
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 0, 0, 50));
        header.setBounds(0, 0, 1000, 50);
        header.setLayout(null);
        header.setBackground(Color.BLACK);

        // setting app name
        JLabel appNameLabel = new JLabel("BAFFOE");
        appNameLabel.setFont(font1);
        appNameLabel.setForeground(new Color(255, 204, 51));
        appNameLabel.setBounds(68, 17, 100, 10);
        header.add(appNameLabel);

        JLabel appName = new JLabel("AUCTIONS");
        appName.setFont(font2);
        appName.setForeground(Color.darkGray);
        appName.setBounds(62, 29, 100, 10);
        header.add(appName);


        //inner panel
        JPanel login = new JPanel();
        login.setLayout(null);
        login.setSize(720, 470);
        login.setBackground(Color.BLACK);
        login.setBounds(150, 165, 720, 470);

        //username box
        JTextField usernameField = new JTextField(40);
        usernameField.setSize(400, 35);
        usernameField.setBounds(350, 150, 300, 35);
        usernameField.setBackground(Color.darkGray);
        usernameField.setForeground(new Color(255, 204, 51));
        usernameField.setFont(font1);
        usernameField.setToolTipText("Enter your username");
        login.add(usernameField);

        //username label 
        JLabel username = new JLabel("USERNAME: ");
        username.setBounds(350, 120, 300, 35);
        username.setFont(font1);
        username.setForeground(new Color(255, 204, 51));
        login.add(username);

        //password box
        JPasswordField passwordField = new JPasswordField(30);
        passwordField.setSize(400, 35);
        passwordField.setBounds(350, 250, 300, 35);
        passwordField.setBackground(Color.darkGray);
        passwordField.setForeground(new Color(255, 204, 51));
        passwordField.setToolTipText("Enter valid password");
        login.add(passwordField);
        //password label
        JLabel password = new JLabel("PASSWORD: ");
        password.setBounds(350, 220, 300, 35);
        password.setFont(font1);
        password.setForeground(new Color(255, 204, 51));
        login.add(password);

        // remember password checkbox
        JCheckBox rememberPassword = new JCheckBox(" Remember Password ", false);
        rememberPassword.setBounds(350, 280, 30, 30);
        rememberPassword.setSize(200, 30);
        rememberPassword.setForeground(new Color(255, 204, 51));
        rememberPassword.setFont(font1);
        login.add(rememberPassword);

        //login button 
        JButton loginButton = new JButton("Login");
        loginButton.setSize(100, 35);
        loginButton.setBounds(555, 320, 90, 30);
        loginButton.setBackground(new Color(0, 0, 0, 0));
        loginButton.setForeground(new Color(255, 204, 51));
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(true);
        loginButton.setVisible(true);
        //  loginButton.setEnabled(true);
        loginButton.setFont(font1);
        login.add(loginButton);


        // Linking login button to the ShowCase Page
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AuctionUser user = new AuctionUser(
                        "",
                        "",
                        usernameField.getText(),
                        new String(passwordField.getPassword()));
                if (    user.getUsername() != "" && user.getUsername() != null &&
                        user.getPassword() != "" && user.getPassword() != null &&
                        auctionClient.signIn(user)) {
                    JOptionPane.showMessageDialog(null,
                            "Login successfuly!");
                    dispose();
                    new ShowCase(auctionClient).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null,
                            auctionClient.getLastError());
                }
            }
        });

        //creating back button
        ImageIcon backIcon = new ImageIcon("/Users/kojobaffoe/Desktop/AuctionApp/images/arrows.png");
        Image backIcon1 = backIcon.getImage();
        Image backIcon2 = backIcon1.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        backIcon = new ImageIcon(backIcon2);

        JButton backButton = new JButton(backIcon);
        backButton.setSize(15, 10);
        backButton.setBackground(new Color(0, 0, 0, 0));
        backButton.setOpaque(true);
        backButton.setBorderPainted(true);
        backButton.setBounds(10, 14, 30, 27);
        header.add(backButton);

        //show search results msg
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Signup().setVisible(true);
            }
        });


        //creating background image
        ImageIcon background_image = new ImageIcon("/Users/kojobaffoe/Desktop/AuctionApp/images/gray.jpg");
        Image img = background_image.getImage();
        Image img1 = img.getScaledInstance(1000, 900, Image.SCALE_SMOOTH);
        background_image = new ImageIcon(img1);
        JLabel background = new JLabel("", background_image, JLabel.CENTER);

        //creating inner panel background
        ImageIcon innerImage = new ImageIcon("/Users/kojobaffoe/Desktop/AuctionApp/images/innerBLACK2.jpg");
        Image innerImg = innerImage.getImage();
        Image innerImg1 = innerImg.getScaledInstance(720, 470, Image.SCALE_SMOOTH);
        innerImage = new ImageIcon(innerImg1);
        JLabel innerBackground = new JLabel(" ", innerImage, JLabel.CENTER);
        innerBackground.setBounds(0, 0, 700, 450);
        innerBackground.setSize(720, 470);

        background.add(login);
        login.add(innerBackground);
        background.setBounds(0, 0, 1000, 900);
        background.add(header);
        add(background);

        //making frame 
        setSize(1000, 900);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

    }

}
