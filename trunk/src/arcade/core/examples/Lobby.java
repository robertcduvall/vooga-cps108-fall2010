package arcade.core.examples;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import arcade.core.Panel;
import arcade.lobby.model.Profile;
import arcade.wall.controllers.WallTabController;

@SuppressWarnings("serial")
public class Lobby extends Panel {
	
	private WallTabController myController;
	Profile myProfile;
	
	public Lobby(){
		/*
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel lobbyFriends = new JLabel("You have 0 friends");
		add(lobbyFriends);

		JLabel moreLabels = new JLabel(icon);
		add(moreLabels);*/
		initialize();
	}
	
	private void initialize()
	{
		myController = new WallTabController();
		add(myController.getView().getMessagesPanel());
	}
}
