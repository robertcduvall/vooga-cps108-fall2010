package arcade.lobby.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import arcade.lobby.model.Registration;
import arcade.lobby.view.RegisterPanel;
import arcade.lobby.view.IView;
import arcade.lobby.model.IModel;



public class RegisterController {

	private RegisterPanel myPanel;
	private Registration myRegistration;
	
	public RegisterController (IModel model, IView view) {
		myPanel = (RegisterPanel) view;
		myRegistration = (Registration) model;
		addListener();
	}
	
	public void addListener() {
		myPanel.addSubmitListener(new SubmitEvent());
	}
	
	private void switchToProfilePage() {
		
	}
	
	private class SubmitEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switchToProfilePage();
		}
		
	}
	
	
}
