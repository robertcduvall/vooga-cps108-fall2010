package arcade.lobby.controller;

import arcade.lobby.model.Profile;
import arcade.lobby.model.IModel;
import arcade.lobby.view.ProfileEditPanel;
import arcade.lobby.view.IView;

public class ProfileController implements IController {

	Profile myProfile;
	ProfileEditPanel myEditPanel;
	
	public ProfileController (IModel model, IView view) {
		myProfile = (Profile) model;
		myEditPanel = (ProfileEditPanel) view;
		setup();
	}


	public void setup() {
		myEditPanel = new ProfileEditPanel(myProfile);
	}

	
	
}
