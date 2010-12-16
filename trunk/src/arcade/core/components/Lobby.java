package arcade.core.components;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import arcade.core.Panel;
import arcade.lobby.model.Profile;
import arcade.wall.controllers.WallTabController;

/**
 * A Panel to be added to the main view of the Arcade. Displays your inbox
 * and the option to create a new message
 * 
 * @author Aaron Choi, Derek Zhou, Yang Su
 *
 */


@SuppressWarnings("serial")
public class Lobby extends Panel {
	
	private WallTabController myController;
	Profile myProfile;
	
	public Lobby(){
		initialize();
	}
	
	private void initialize()
	{
		myController = new WallTabController();
		add(myController.getView().getMessagesPanel());
	}
}
