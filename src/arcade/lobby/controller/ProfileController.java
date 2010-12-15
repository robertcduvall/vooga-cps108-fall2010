package arcade.lobby.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import arcade.lobby.model.Profile;
import arcade.lobby.model.IModel;
import arcade.lobby.view.ProfilePanel;
import arcade.lobby.view.IView;

public class ProfileController implements IController {

	Profile myProfile;
	ProfilePanel myPanel;
	
	public ProfileController (IModel model, IView view) {
		myProfile = (Profile) model;
		myPanel = (ProfilePanel) view;
		setup();
	}


	public void setup() {
		myPanel = new ProfilePanel();
		myPanel.addEditButtonListener(new EditEvent());
	}

	
	private class EditEvent implements ActionListener {		

		@Override
		public void actionPerformed(ActionEvent e) {
			myPanel.changeEditMode();
		}
	}
	
	
}
