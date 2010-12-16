package arcade.lobby.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import arcade.lobby.model.Profile;
import arcade.lobby.model.IModel;
import arcade.lobby.model.ProfileSet;
import arcade.lobby.view.ProfilePanel;
import arcade.lobby.view.IView;
import arcade.lobby.view.ProfileEditPanel;

public class ProfileController implements IController {

	Profile myModel;
	ProfilePanel myView;
	
	ResourceBundle resource = ResourceBundle.getBundle("arcade.lobby.resources.resources");
	
	public ProfileController (IModel model, IView view) {
		myModel = (Profile) model;
		myView = (ProfilePanel) view;
		myView.refresh();
		addListeners();
	}


	private void addListeners() {
		myView.addEditButtonListener(new EditEvent());
//		myView.getEditPanel().addSaveListener(new SaveEvent());
	}
	
//	private void saveProfile() {
//		ProfileEditPanel editPanel = myView.getEditPanel();
//		if(editPanel.validateFields()) {
//			myModel.setAvatar(editPanel.getText(resource.getString("avatar")));
//			myModel.setEmail(editPanel.getText(resource.getString("email")));
//			myModel.setName(editPanel.getText(resource.getString("firstName")),
//					editPanel.getText(resource.getString("lastName")));
//			myModel.setBirthday(editPanel.getText(resource.getString("birthday")));
//			ProfileSet.updateProfile(myModel);
//		}
//		editPanel.refresh(myModel);
//	}
	
	private class EditEvent implements ActionListener {		

		@Override
		public void actionPerformed(ActionEvent e) {
			myView.changeEditMode();
		}
	}
	
//	private class SaveEvent implements ActionListener {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			saveProfile();
//			myView.changeEditMode();
//		}
//	}
	
	
}
