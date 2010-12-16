package arcade.lobby.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import arcade.core.Arcade;
import arcade.lobby.view.Login;
import arcade.security.control.SignUpControl;
import arcade.security.model.LoginProcess;
import arcade.security.util.userserviceutil.UserServiceFactory;

public class LoginController {

	private Login myView;
	private LoginProcess myModel;
	private SignUpControl securityControl;
	
	public LoginController(Login view){
		myView = view;
		myModel = new LoginProcess();
		myView.addLoginButtonListeners(new LoginEvent());
		securityControl = new SignUpControl();
	}
	
	public boolean isSuccessfulLogin(String username, char[] password){
		return myModel.isSuccessfulLogin(username, String.valueOf(password));
	}
	
	private void switchView(){
		myView.switchToLogout();
	}
	
	private boolean isAdmin(){
		return true;
		//return UserServiceFactory.getCurrentUser().getUserType().equals("Admin");
	}
	
	private void PopUpAdminPage(){
		securityControl.PopUpAdminPage();
	}
	
	
	
	private class LoginEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String username = new String(myView.getUserNameUserInput());
			char[] password = myView.getPasswordUserInput();
			if(!isSuccessfulLogin(username, password)){ //next step towards true mvc:move this to controller
			//if(!LogInHandler.successfulLogin(username, password)){
				JOptionPane.showMessageDialog(myView, "Your username and password combination does not match");
				//usernameField.selectAll();
				//usernameField.requestFocus(true);
				myView.resetPassword();
				return;
			}
			
			//PrivilegeMap.setCurrentUser(username);
			//JOptionPane.showMessageDialog(view.getCurrentPanel(),"privilege is"+PrivilegeMap.getPrivilegeString());
			
			switchView();
			if(isAdmin())	PopUpAdminPage();
		}
	}
	
	
}
