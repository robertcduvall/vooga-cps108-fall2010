package arcade.security.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import arcade.core.Arcade;
import arcade.security.gui.SecurityButton;
import arcade.security.resourcesbundle.LabelResources;
import arcade.security.resourcesbundle.StaticFileResources;
import arcade.security.util.LogInHandler;
import net.miginfocom.swing.MigLayout;
import arcade.security.control.*;

/**
 * Log In Panel. 
 * @author Jiaqi Yan
 *
 */
public class LogInPanel extends ViewState{
	private JButton submitButton;
	private JButton cancelButton;
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
		cancelButton = new SecurityButton(LabelResources.getLabel("LoginframeCancel"),new ImageIcon(StaticFileResources.getPath("logincancel")),"Cancel");
		signUpButton = new SecurityButton(LabelResources.getLabel("LoginframeSignup"),"SignUp");
		forgotPasswordButton = new SecurityButton(LabelResources.getLabel("LoginframeForgot"),"ForgottenPassword");
		
		signUpButton.requestFocus(true);
		forgotPasswordButton.requestFocus(true);
		submitButton.requestFocus(true);
		
		add(submitButton,"");
		add(cancelButton,"wrap");
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
		
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);					
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
				return;
			}
			if(!LogInHandler.isPasswordValid(username, password)){
				JOptionPane.showMessageDialog(ControlTab.getPanel(),"Password is not valid");
				return;
			}
			JOptionPane.showMessageDialog(ControlTab.getPanel(),"Successfully logged in!");
			ControlTab.setValidate("arcade.security.view.AdminPanel");
			Arcade.switchToTab(0);
		}
	}
}
