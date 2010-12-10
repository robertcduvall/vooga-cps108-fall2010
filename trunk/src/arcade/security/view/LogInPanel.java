package arcade.security.view;
import javax.swing.*;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.*;

import arcade.core.Arcade;
import arcade.core.Tab;
import arcade.security.gui.SecurityButton;
import arcade.security.model.LoginProcessModel;
import arcade.security.resourcesbundle.LabelResources;
import arcade.security.resourcesbundle.StaticFileResources;
import arcade.security.util.userserviceutil.User;
import arcade.security.util.userserviceutil.UserService;
import arcade.security.util.userserviceutil.UserServiceFactory;
import arcade.security.util.LogInHandler;
import net.miginfocom.swing.MigLayout;
import arcade.security.control.*;
import arcade.util.database.MySqlAdapter;

/**
 * Log In Panel. 
 * @author Jiaqi Yan
 * @atthor Meng Li
 *
 */
public class LogInPanel extends Tab{
	
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
	private String username;
	private char[] password;

	
	private Control controller;
	
	public LogInPanel(Control controller) {
		this.controller = controller;
		//controller.setView(this);
		controller.setModel(new LoginProcessModel(controller));
		setName("Log in Service");
		setToolTipText("Please log in here");
		createAndShowGUI();
	}
	
	public LogInPanel() {
		controller = new Control();
		controller.setView(this);
		controller.setModel(new LoginProcessModel(controller));
		setName("Log in page");
	}
	

	

	@Override
	public JComponent getContent() {
		//TODO:put it on the EDT thread, Meng will try to do this later
		createAndShowGUI();
		return this;
	}
	
	private void createAndShowGUI() {
	    //this.setPreferredSize(new Dimension(1000, 750));
		//this.setLocation(1000, 300);
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
		
		addListeners();
		setVisible(true);
		
	}


	protected void addListeners(){
		submitButton.addActionListener(new SubmitEvent());
		
		signUpButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//controller.validatePanelSwitch("arcade.security.view.SignUpPanel");
				controller.switchToSignUpPage();
			}	
		});
		
		
		forgotPasswordButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//controller.validatePanelSwitch("arcade.security.view.RetrievePasswordPanel");
				controller.switchToForgetPasswordPage();
			}
		});
		
	}	
	
	public JPanel getCurrentPanel(){
		return this;
	}
	
	private class SubmitEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			username = usernameField.getText();
			password = passwordField.getPassword();
			//should be put in model
//			if(!LogInHandler.userNameExists(username)){
//				JOptionPane.showMessageDialog(getCurrentPanel(),"Username does not exist");
//				usernameField.selectAll();
//				usernameField.requestFocus(true);
//				return;
//			}
//			if(!LogInHandler.isPasswordValid(username, password)){
//				JOptionPane.showMessageDialog(getCurrentPanel(),"Password is not valid");
//				passwordField.selectAll();
//				passwordField.requestFocus(true);
//				return;
//			}
			JOptionPane.showMessageDialog(getCurrentPanel(),"Successfully logged in!");
			if(isAdmin()){
				UserService userService = UserServiceFactory.getUserService();
				User user = userService.getCurrentUser();
				log.info("Before log in, Current User role: "+user.getRole());
				user.setUserAs("login_user");
				log.info("After log in, Current User role: "+user.getRole());
				controller.switchToAdminPage();
				//controller.validatePanelSwitch("arcade.security.view.AdminPanel");
				
			}
			else{
				Arcade.switchToTab(0); 
			}	
		}

		private boolean isAdmin() {
			return true;
		}
	}

}

