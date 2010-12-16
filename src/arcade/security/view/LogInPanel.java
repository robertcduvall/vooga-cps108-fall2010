package arcade.security.view;
import javax.swing.*;


import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.*;

import arcade.core.Arcade;
import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.security.gui.SecurityButton;
import arcade.security.model.LoginProcess;
import arcade.security.resourcesbundle.LabelResources;
import arcade.security.resourcesbundle.StaticFileResources;

import arcade.security.util.userserviceutil.UserServiceFactory;
import arcade.security.util.LogInHandler;

import arcade.security.control.*;
import arcade.util.database.MySqlAdapter;

/**
 * View object for the Login panel. Used in conjunction with
 * security.model.LoginProcess and security.control.LogInPanelControl.
 * 
 * @author Meng Li, Jiaqi Yan, Andrew Brown, Nick Hawthorne
 * 
 */
public class LogInPanel extends JPanel  implements Tab,IView{
	
	private static final long serialVersionUID = 1L;
	private final static Logger log=Logger.getLogger(LogInPanel.class);
	private JButton submitButton;
	//private JButton logoutButton;
	private JButton signUpButton;
	private JButton forgotPasswordButton;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private int maxPasswordLength = 9;
	private int maxUserNameLength = 9;

	/**
	 * Constructor for the login panel
	 */
	public LogInPanel() {
		setName("Log in Service");
		createAndShowGUI();
		LoginProcess model = new LoginProcess();
		LogInPanelControl controller = new LogInPanelControl(this,model);
	}
	
	@Override
	public JComponent getContent() {
		//TODO:put it on the EDT thread, Meng will try to do this later
		
		//TODO this code has been moved to the LogInPanel() constructor
//		createAndShowGUI();
//		LoginProcess model = new LoginProcess();
//		LogInPanelControl controller = new LogInPanelControl(this,model);	
		return this;
	}
	/**
	 * Creates the GUI and sets it to visible
	 */
	private void createAndShowGUI() {
		setLayout(new MigLayout());
		JLabel image=new JLabel(new ImageIcon(StaticFileResources.getPath("loginimage")));
		JLabel usernameLabel=new JLabel(LabelResources.getLabel("UserId"));		
		JLabel passwordLabel=new JLabel(LabelResources.getLabel("Password"));
		
		usernameField=new JTextField(maxUserNameLength);	
		passwordField=new JPasswordField(maxPasswordLength);
		
		add(image,"cell 0 0 2 1");
		add(usernameLabel,"cell 0 1");
		add(usernameField,"cell 1 1,wrap");
		add(passwordLabel,"cell 0 2");
		add(passwordField,"wrap");
		
		submitButton = new SecurityButton(LabelResources.getLabel("LoginframeSubmit"),new ImageIcon(StaticFileResources.getPath("loginsubmit")),"Log in");
		//logoutButton = new SecurityButton(LabelResources.getLabel("Logout"),new ImageIcon(StaticFileResources.getPath("logincancel")),"Cancel");
		signUpButton = new SecurityButton(LabelResources.getLabel("LoginframeSignup"),new ImageIcon(StaticFileResources.getPath("login_signup")),"SignUp");
		forgotPasswordButton = new SecurityButton(LabelResources.getLabel("LoginframeForgot"),new ImageIcon(StaticFileResources.getPath("login_forget")),"ForgottenPassword");
		
		signUpButton.requestFocus(true);
		forgotPasswordButton.requestFocus(true);
		submitButton.requestFocus(true);
		
		add(submitButton,"cell 0 3");
		//add(logoutButton,"wrap");
		add(signUpButton,"cell 0 3");
		add(forgotPasswordButton,"wrap");
		setVisible(true);
	}
	/**
	 * Adds listeners for the Submit button
	 * 
	 * @param listener
	 */
	public void addSubmitButtonListeners(ActionListener listener){
		submitButton.addActionListener(listener);
	}
	/**
	 * Adds listeners for the Signup button
	 * 
	 * @param listener
	 */
	public void addSignUpButtonListener(ActionListener listener){
		signUpButton.addActionListener(listener);
	}
	/**
	 * Adds listeners for the Forget Password button
	 * 
	 * @param listener
	 */
	public void addForgetButtonListener(ActionListener listener){
		forgotPasswordButton.addActionListener(listener);
	}

	/**
	 * Gets the text input by the user trying to log in
	 * 
	 * @return the username input by the user trying to log in
	 */
	public String getUserNameUserInput(){
		return new String(usernameField.getText());
	}
	/**
	 * Gets the text input from the password box by the user trying to log in
	 * 
	 * @return the password input by the user trying to log in
	 */
	public char[] getPasswordUserInput(){
		return passwordField.getPassword();
	}
	/**
	 * Returns the current Panel
	 * 
	 * @return
	 */
	public JPanel getCurrentPanel(){
		return this;
	}

	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		
	}


		
	

}

