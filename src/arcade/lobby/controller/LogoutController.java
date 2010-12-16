package arcade.lobby.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import arcade.lobby.model.ProfileSet;
import arcade.lobby.view.Login;
import arcade.security.model.LoginProcess;

public class LogoutController {

	private Login myView;
	
	public LogoutController(Login view){
		myView = view;
		myView.addLogoutButtonListener(new LogoutEvent());
	}
	
	private void switchView(){
		myView.switchToLogin();
	}
	
	private class LogoutEvent implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ProfileSet.setCurrentProfile(null);
			switchView();
		}
	}
	
}
