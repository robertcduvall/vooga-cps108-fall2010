package arcade.security.view;

import javax.swing.*;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.*;

import arcade.core.Arcade;
import arcade.security.gui.SecurityButton;
import arcade.security.resourcesbundle.LabelResources;
import arcade.security.resourcesbundle.StaticFileResources;
import arcade.security.util.LogInHandler;
import net.miginfocom.swing.MigLayout;
import arcade.security.UserServiceUtil.User;
import arcade.security.UserServiceUtil.UserService;
import arcade.security.UserServiceUtil.UserServiceFactory;
import arcade.security.control.*;
import arcade.util.database.MySqlAdapter;

/**
 * Log In Panel. 
 * @author Jiaqi Yan
 * @atthor Meng Li
 *
 */
public class LogInPanel extends ViewState{
	
	private static final long serialVersionUID = 1L;
	private final static Logger log=Logger.getLogger(LogInPanel.class);
	private JButton submitButton;
	private JButton logoutButton;
	private JButton signUpButton;
	private JButton forgotPasswordButton;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private int maxPasswordLength = 9;
	private int maxUserNameLength = 9;
	private String username;
	private char[] password;
	
	public LogInPanel() {
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
		logoutButton = new SecurityButton(LabelResources.getLabel("Logout"),new ImageIcon(StaticFileResources.getPath("logincancel")),"Cancel");
		signUpButton = new SecurityButton(LabelResources.getLabel("LoginframeSignup"),"SignUp");
		forgotPasswordButton = new SecurityButton(LabelResources.getLabel("LoginframeForgot"),"ForgottenPassword");
		
		signUpButton.requestFocus(true);
		forgotPasswordButton.requestFocus(true);
		submitButton.requestFocus(true);
		
		add(submitButton,"");
		add(logoutButton,"wrap");
		add(signUpButton);
		add(forgotPasswordButton);
		
		addListeners();
	}
	
	protected void addListeners(){
		submitButton.addActionListener(new SubmitEvent());
		
		signUpButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlTab.setValidate("arcade.security.view.SignUpPanel");
			}	
		});
		
		logoutButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				UserService userService = UserServiceFactory.getUserService();
				User user = userService.getCurrentUser();
				user.setUserAs("default");
				log.info("Current User role: "+user.getRole());			
			}				
		});
		
		forgotPasswordButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlTab.setValidate("arcade.security.view.RetrievePasswordPanel");
			}
		});
		
	}	
	
	private class SubmitEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			username = usernameField.getText();
			password = passwordField.getPassword();
			if(!LogInHandler.userNameExists(username)){
				JOptionPane.showMessageDialog(ControlTab.getPanel(),"Username does not exist");
				usernameField.selectAll();
				usernameField.requestFocus(true);
				return;
			}
			if(!LogInHandler.isPasswordValid(username, password)){
				JOptionPane.showMessageDialog(ControlTab.getPanel(),"Password is not valid");
				passwordField.selectAll();
				passwordField.requestFocus(true);
				return;
			}
			JOptionPane.showMessageDialog(ControlTab.getPanel(),"Successfully logged in!");
			ControlTab.setValidate("arcade.security.view.AdminPanel");
			Arcade.switchToTab(0);
		}
	}
}
