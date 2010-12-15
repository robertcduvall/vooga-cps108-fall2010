package arcade.wall.controllers;

import javax.swing.JComponent;
import javax.swing.JPanel;

import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;

/**
 * The VOOGA WallTab. 
 * @author Cam, John, Bhawana
 */
@SuppressWarnings("serial")
public class WallTab extends JPanel implements Tab {

	private WallTabController myController;
	//TODO create model and pass into controller
	Profile myProfile;
	
	public WallTab(){
		super();
		setToolTipText("Click here to see your Wall.");
		setName("Wall");
//		this.setTitle("MainWallTab");
		myController = new WallTabController();
		myProfile = ProfileSet.currentProfile;
		add(myController.getView().getPanel());
//		this.pack();
//		this.setVisible(true);
		refresh();
	}

	@Override
	public JComponent getContent() {
		return this;
	}

	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		repaint();
		String userName = "";
		System.out.println(myProfile);
		try {
			myProfile = ProfileSet.currentProfile;
			userName = myProfile.getUserName();
		} catch (NullPointerException e) {
			userName = "Guest";
		}
		setName(userName+"'s Wall");
		System.out.println("refreshing");
//		reload();
	}
	
	//From ProfilePanel
//	private void reload() {
//		// To test with no current profile by switching between users 1 and 2:
//		// myProfile = ProfileSet.getProfile((myProfile.getUserId())%2+1);
//		myProfile = ProfileSet.currentProfile;
//		myViewPanel.refresh(myProfile);
//		myEditPanel.refresh(myProfile);
//		editMode = !editMode;
//		refreshContent();
//		editMode = !editMode;
//		refreshContent();
//	}
}
