import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author kojobaffoe
 * ID - 10680839
 * 
 * @author Joseph Sola-Eniafe
 * ID - 10685616 
 *
 */

public class Signup extends JFrame {

    private static final long serialVersionUID = 1L;
    private AuctionClient auctionClient = new AuctionClient();
    private AuctionUser auctionUser;



	public Signup() {
        super("SIGN UP PAGE");
        //creating font 
        Font font1 = new Font("Chalkboard", Font.BOLD, 10);
        Font font2 = new Font("Apple Symbols", Font.BOLD, 13);
        Font font3 = new Font("Chalkboard", Font.BOLD, 8);


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
        JPanel signup = new JPanel();
        signup.setLayout(null);
        signup.setSize(720, 470);
        signup.setBackground(Color.BLACK);
        signup.setBounds(150, 165, 720, 470);


        //name box
        JTextField nameField = new JTextField(40);
        nameField.setSize(400, 35);
        nameField.setBounds(350, 80, 300, 35);
        nameField.setBackground(Color.darkGray);
        nameField.setForeground(new Color(255, 204, 51));
        nameField.setFont(font1);
        nameField.setToolTipText("Enter your full name");
        signup.add(nameField);


        //name label
        JLabel name = new JLabel("FULL NAME: ");
        name.setBounds(350, 55, 300, 35);
        name.setFont(font1);
        name.setForeground(new Color(255, 204, 51));
        signup.add(name);


        //email box
        JTextField emailField = new JTextField(40);
        emailField.setSize(400, 35);
        emailField.setBounds(350, 140, 300, 35);
        emailField.setBackground(Color.darkGray);
        emailField.setForeground(new Color(255, 204, 51));
        emailField.setFont(font1);
        emailField.setToolTipText("Enter email address");
        signup.add(emailField);

        //email label
        JLabel email = new JLabel("EMAIL: ");
        email.setBounds(350, 115, 300, 35);
        email.setForeground(new Color(255, 204, 51));
        email.setFont(font1);
        signup.add(email);

        // username box
        JTextField usernameField = new JTextField(40);
        usernameField.setSize(400, 35);
        usernameField.setBounds(350, 200, 300, 35);
        usernameField.setBackground(Color.darkGray);
        usernameField.setForeground(new Color(255, 204, 51));
        usernameField.setFont(font1);
        usernameField.setToolTipText("Choose your username");
        signup.add(usernameField);

        //username label
        JLabel username = new JLabel("USERNAME: ");
        username.setBounds(350, 175, 300, 35);
        username.setForeground(new Color(255, 204, 51));
        username.setFont(font1);
        signup.add(username);

        //password box
        JPasswordField passwordField = new JPasswordField(30);
        passwordField.setSize(400, 35);
        passwordField.setBounds(350, 260, 300, 35);
        passwordField.setBackground(Color.darkGray);
        passwordField.setForeground(new Color(255, 204, 51));
        passwordField.setToolTipText("Choose valid password");
        signup.add(passwordField);

        //password label
        JLabel password = new JLabel("PASSWORD: ");
        password.setBounds(350, 235, 300, 35);
        password.setForeground(new Color(255, 204, 51));
        password.setFont(font1);
        signup.add(password);

        // Agree to terms  checkbox
        JCheckBox agreeToBox = new JCheckBox("Agree to terms and conditions ", false);
        agreeToBox.setBounds(350, 290, 30, 30);
        agreeToBox.setSize(300, 30);
        agreeToBox.setFont(font1);
        //UIManager.put("agreeToBox.focus",new Color(255,204,51)); //on focus
        UIManager.put("agreeToBox.select", new Color(255, 204, 51)); //on select
        agreeToBox.setForeground(new Color(255, 204, 51));
        agreeToBox.setBackground(new Color(255, 204, 51));
        signup.add(agreeToBox);
        
       

        //signup button
        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(555, 320, 90, 30);
        signupButton.setBackground(new Color(0, 0, 0, 0));
        signupButton.setForeground(new Color(255, 204, 51));
        signupButton.setOpaque(true);
        signupButton.setFont(font1);
        signupButton.setBorderPainted(true);
        signupButton.setVisible(true);
        signup.add(signupButton);

//        
//        Signup.addComponentListener(new ActionListener(){
//        	  public void actionPerformed(ActionEvent e) {
//        if(nameField.getText() == "" || usernameField.getText() == "" || emailField.getText() == ""|| passwordField.getPassword().length == 0) 
//        	{
//        	signupButton.setEnabled(false);	
//        	}
//        else {
//        	signupButton.setEnabled(true);
//        }
//        }
//        });

        // Sign In label
        JLabel login = new JLabel("ALREADY HAVE AN ACCOUNT? ");
        login.setBounds(350, 380, 300, 35);
        login.setForeground(new Color(255, 204, 51));
        login.setFont(font1);
        signup.add(login);


        //sign in button
        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(390, 410, 70, 20);
        signInButton.setFont(font3);
        signInButton.setBackground(new Color(0, 0, 0, 0));
        signInButton.setForeground(new Color(255, 204, 51));
        signInButton.setOpaque(true);
        signInButton.setBorderPainted(true);
        signInButton.setVisible(true);
        signup.add(signInButton);


        signInButton.addActionListener(actionEvent -> {
            dispose();
            new Login().setVisible(true);
        });

            // Linking sign in button to the Login Form
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                AuctionUser user = new AuctionUser(
                        nameField.getText(),
                        emailField.getText(),
                        usernameField.getText(),
                        new String(passwordField.getPassword()));
                
                if (    user.getName() != "" && user.getName() != null &&
                        user.getEmail() != "" && user.getEmail() != null &&
                        user.getUsername() != "" && user.getUsername() != null &&
                        user.getPassword() != "" && user.getPassword() != null &&
                        auctionClient.signUp(user)) {
                    JOptionPane.showMessageDialog(null,
                            "Account created successfully! You can now Login!");
                    dispose();
                    new Login().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null,
                   auctionClient.getLastError());
                }
            }
        });


        //creating background image
        ImageIcon background_image = new ImageIcon("/Users/kojobaffoe/Desktop/AuctionApp/images/gray.jpg");
        Image img = background_image.getImage();
        Image img1 = img.getScaledInstance(1000, 900, Image.SCALE_SMOOTH);
        background_image = new ImageIcon(img1);
        JLabel background = new JLabel("", background_image, JLabel.CENTER);
        background.setForeground(new Color(0, 0, 0, 80));
        background.setBackground(new Color(0, 0, 0, 80));


        //creating inner panel background
        ImageIcon innerImage = new ImageIcon("/Users/kojobaffoe/Desktop/AuctionApp/images/innerBLACK2.jpg");
        Image innerImg = innerImage.getImage();
        Image innerImg1 = innerImg.getScaledInstance(720, 470, Image.SCALE_SMOOTH);
        innerImage = new ImageIcon(innerImg1);
        JLabel innerBackground = new JLabel(" ", innerImage, JLabel.CENTER);
        innerBackground.setBounds(0, 0, 700, 450);
        innerBackground.setSize(720, 470);


        background.add(signup);
        signup.add(innerBackground);
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


	public static void main(String[] args) {
        new Signup().setVisible(true);
    }
}