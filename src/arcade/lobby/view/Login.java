package arcade.lobby.view;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
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
	public Login() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Login");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getUsernameField(), null);
			jContentPane.add(getPasswordField(), null);
			jContentPane.add(getUsernameText(), null);
			jContentPane.add(getPasswordText(), null);
			jContentPane.add(getSignInButton(), null);
			jContentPane.add(getRegisterButton(), null);
			jContentPane.add(getRegisterText(), null);
		}
		return jContentPane;
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
					password = passwordField.getText();
					//Verify username + password combination;
					System.out.println(username+" is logging in with pw: "+password.toString());
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
			registerButton.setBounds(new Rectangle(107, 130, 90, 27));
			registerButton.setText("Register");
			registerButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//TODO Create new Profile
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
			registerText.setBounds(new Rectangle(50, 106, 195, 23));
			registerText.setText("Need a username? Register Now!");
			registerText.setEditable(false);
		}
		return registerText;
	}

}  //  @jve:decl-index=0:visual-constraint="22,8"
