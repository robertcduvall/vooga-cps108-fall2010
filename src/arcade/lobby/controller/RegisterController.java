package arcade.lobby.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import arcade.lobby.view.Login;
import arcade.security.view.SignUpPanel;



/**
 * Controls the Sign up process for new user.
 * Brings up the registration window when the
 * registration button in the login view is clicked.
 * @author justin
 */
public class RegisterController {

	private Login myView;
	
	public RegisterController (Login view) {
		myView = view;
		myView.addRegisterButtonListener(new RegisterEvent());
	}
	
	private class RegisterEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame registerFrame = new JFrame();
			SignUpPanel signUpPanel = new SignUpPanel();
			signUpPanel.setLogin(myView);
			registerFrame.add(signUpPanel);
			registerFrame.pack();
			registerFrame.setVisible(true);
		}
		
	}
	
	
}
