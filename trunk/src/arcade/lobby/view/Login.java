package arcade.lobby.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import arcade.lobby.controller.LoginController;
import arcade.lobby.controller.RegisterController;
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
	
	public Login(){
		initialize();
		createController();
	}
	
	private void createController() {
		new LoginController(this);
		new RegisterController(this);
	}

	private void initialize() {
		myDock = new ValidatorDock("wrap 4");
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		addTextField("usernameLogin","Username: ");
		addPasswordField("passwordLogin","Password: ");
		login = new JButton("Login");
		register = new JButton("Register");
		panel.add(myDock);
		panel.add(login);
		panel.add(register);
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
	
	public String getUserNameUserInput(){
		return ((JTextField)(myDock.getComponent("usernameLogin"))).getText();
	}
	
	public char[] getPasswordUserInput(){
		return ((JPasswordField)(myDock.getComponent("passwordLogin"))).getPassword();
	}


}
