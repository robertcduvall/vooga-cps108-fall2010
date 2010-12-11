package arcade.lobby.view;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import arcade.lobby.controller.Main;
import arcade.lobby.controller.Validator;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField usernameField = null;
	private JPasswordField passwordField = null;
	private JTextPane usernameText = null;
	private JTextPane passwordText = null;
	private JButton signInButton = null;
	private JButton registerButton = null;
	private JTextPane registerText = null;
	
	private String username;
	private String password;
	/**
	 * This is the default constructor
	 */
	public LoginPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 300);
		this.setLayout(null);
		this.add(getUsernameField(), null);
		this.add(getPasswordField(), null);
		this.add(getUsernameText(), null);
		this.add(getPasswordText(), null);
		this.add(getSignInButton(), null);
		this.add(getRegisterButton(), null);
		this.add(getRegisterText(), null);
		//Main.MainFrame.getJMenuBar().setVisible(false);
	}

	/**
	 * This method initializes usernameField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUsernameField() {
		if (usernameField == null) {
			usernameField = new JTextField();
			usernameField.setBounds(new Rectangle(80, 8, 167, 25));
			//usernameField.setText("Username");
		}
		return usernameField;
	}

	/**
	 * This method initializes passwordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(new Rectangle(80, 38, 167, 25));
			//passwordField.setText("Password");
		}
		return passwordField;
	}

	/**
	 * This method initializes usernameText	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getUsernameText() {
		if (usernameText == null) {
			usernameText = new JTextPane();
			usernameText.setBounds(new Rectangle(5, 9, 68, 23));
			usernameText.setText("Username:");
			usernameText.setEditable(false);
		}
		return usernameText;
	}

	/**
	 * This method initializes passwordText	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getPasswordText() {
		if (passwordText == null) {
			passwordText = new JTextPane();
			passwordText.setBounds(new Rectangle(7, 39, 67, 23));
			passwordText.setText("Password:");
			passwordText.setEditable(false);
		}
		return passwordText;
	}

	/**
	 * This method initializes signInButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSignInButton() {
		if (signInButton == null) {
			signInButton = new JButton();
			signInButton.setBounds(new Rectangle(108, 72, 87, 25));
			signInButton.setText("Sign In");
			signInButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					username = usernameField.getText();
					password = new String(passwordField.getPassword());
					//Verify username + password combination and login
					System.out.println(username+" is logging in with pw: "+password.toString());
					if(!Validator.checkUsername(username)){
						System.out.println("user exists");
						//make this next line take the userId of the person who just logged in
						//Main.ProfileSet.setUser(1);
						//Main.MainFrame.setContentPane(new DefaultPanel());
					}
					else{
						JOptionPane.showMessageDialog(Main.MainFrame,
						"This username does not exist");
//						System.out.println("This username does not exist");
					}
				}
			});
		}
		return signInButton;
	}

	/**
	 * This method initializes registerButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRegisterButton() {
		if (registerButton == null) {
			registerButton = new JButton();
			registerButton.setBounds(new Rectangle(107, 150, 90, 27));
			registerButton.setText("Register");
			registerButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//Main.MainFrame.setContentPane(new RegisterPanel());
					System.out.println("Create new profile");
				}
			});
		}
		return registerButton;
	}

	/**
	 * This method initializes registerText	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getRegisterText() {
		if (registerText == null) {
			registerText = new JTextPane();
			registerText.setBounds(new Rectangle(50, 106, 195, 40));
			registerText.setText("Need a username? Register Now!");
			registerText.setEditable(false);
		}
		return registerText;
	}

}  //  @jve:decl-index=0:visual-constraint="22,8"
