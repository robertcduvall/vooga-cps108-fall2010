package arcade.lobby.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import arcade.core.Arcade;
import arcade.lobby.view.Login;
import arcade.security.model.LoginProcess;
import arcade.security.util.userserviceutil.UserServiceFactory;

public class LoginController {

	private Login myView;
	private LoginProcess myModel;
	
	public LoginController(Login view){
		myView = view;
		myModel = new LoginProcess();
		myView.addLoginButtonListeners(new LoginEvent());
	}
	
	public boolean isSuccessfulLogin(String username, char[] password){
		return myModel.isSuccessfulLogin(username, String.valueOf(password));
	}
	
	private void switchView(){
		myView.switchToLogout();
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
			
			myModel.logIn(username);
			switchView();
		}
	}
	
	
}
