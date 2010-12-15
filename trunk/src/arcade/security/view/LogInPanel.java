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
import arcade.security.util.userserviceutil.User;
import arcade.security.util.userserviceutil.UserService;
import arcade.security.util.userserviceutil.UserServiceFactory;
import arcade.security.util.LogInHandler;

import arcade.security.control.*;
import arcade.util.database.MySqlAdapter;

/**
 * Log In Panel. 
 * @author Jiaqi Yan
 * @author Meng Li
 * @author Andrew Brown
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

	public void addSubmitButtonListeners(ActionListener listener){
		submitButton.addActionListener(listener);
	}
	
	public void addSignUpButtonListener(ActionListener listener){
		signUpButton.addActionListener(listener);
	}
	
	public void addForgetButtonListener(ActionListener listener){
		forgotPasswordButton.addActionListener(listener);
	}

	
	public String getUserNameUserInput(){
		return new String(usernameField.getText());
	}
	
	public char[] getPasswordUserInput(){
		return passwordField.getPassword();
	}
	
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

