package arcade.lobby.controller;

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
	}
	
	
}
