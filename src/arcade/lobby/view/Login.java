package arcade.lobby.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import arcade.lobby.controller.LoginController;
import arcade.lobby.controller.LogoutController;
import arcade.lobby.controller.RegisterController;
import arcade.lobby.model.ProfileSet;
import arcade.util.guiComponents.ValidatingComponent;
import arcade.util.guiComponents.Validator;
import arcade.util.guiComponents.ValidatorDock;

@SuppressWarnings("serial")
public class Login extends JPanel{
	ValidatorDock myDock;
	private int maxPasswordLength = 10;
	private int maxUserNameLength = 10;
	private JButton login;
	private JButton register;
	private JButton logout;
	private JLabel name;
	
	public Login(){
		initialize();
		createController();
	}
	
	private void createController() {
		new LoginController(this);
		new RegisterController(this);
		new LogoutController(this);
	}

	private void initialize() {
		myDock = new ValidatorDock("wrap 4");
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("hidemode 3"));
		addTextField("usernameLogin","Username: ");
		addPasswordField("passwordLogin","Password: ");
		login = new JButton("Login");
		register = new JButton("Register");
		logout = new JButton("Logout");
		logout.setVisible(false);
		name = new JLabel();
		name.setVisible(false);
		name.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel.add(myDock);
		panel.add(login);
		panel.add(register);
		panel.add(name);
		panel.add(logout);
		panel.setMaximumSize(new Dimension(200, 5));
		this.add(panel, BorderLayout.EAST);
	
	}	
	
	private void addTextField(String name, String label,
			Validator<JTextField>... validators) {
		JTextField textField = new JTextField(maxUserNameLength);
		ValidatingComponent<JTextField> vc = new ValidatingComponent<JTextField>(
				textField, label, validators);
		myDock.addValidatingComponent(vc, name);
	}
	
	private void addPasswordField(String name, String label,
			Validator<JPasswordField>... validators) {
		JPasswordField passwordField = new JPasswordField(maxPasswordLength);
		ValidatingComponent<JPasswordField> vc = new ValidatingComponent<JPasswordField>(
				passwordField, label, validators);
		myDock.addValidatingComponent(vc, name);
	}
	
	public void addLoginButtonListeners(ActionListener listener){
		login.addActionListener(listener);
	}
	
	public void addRegisterButtonListener(ActionListener listener){
		register.addActionListener(listener);
	}
	
	public void addLogoutButtonListener(ActionListener listener){
		logout.addActionListener(listener);
	}
	
	public String getUserNameUserInput(){
		return ((JTextField)(myDock.getComponent("usernameLogin"))).getText();
	}
	
	public char[] getPasswordUserInput(){
		return ((JPasswordField)(myDock.getComponent("passwordLogin"))).getPassword();
	}

	public void switchToLogout() {
		myDock.setVisible(false);
		login.setVisible(false);
		register.setVisible(false);
		name.setVisible(true);
		logout.setVisible(true);
		name.setText("Hello " + ProfileSet.getCurrentProfile().getFirstName());
	}
	
	public void switchToLogin(){
		myDock.setVisible(true);
		login.setVisible(true);
		register.setVisible(true);
		name.setVisible(false);
		logout.setVisible(false);
		resetPassword();
		resetUsername();
	}

	public void resetPassword() {
		((JPasswordField)myDock.getComponent("passwordLogin")).setText("");
	}
	
	public void resetUsername(){
		((JTextField)myDock.getComponent("usernameLogin")).setText("");
	}


}
