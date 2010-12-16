package arcade.core.examples;

import arcade.core.Panel;
import arcade.lobby.model.Profile;
import arcade.wall.controllers.WallTabController;

public class Rating extends Panel {
	private WallTabController myController;
	Profile myProfile;
	
	public Rating() {
		/*
		JLabel rateThis = new JLabel("Rate This Game");
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		Image scaled = icon.getImage().getScaledInstance(25, 25,
				java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaled);
		JLabel label = new JLabel(icon);
		add(rateThis);
		add(label);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		*/
		initialize();
	}
	
	private void initialize()
	{
		myController = new WallTabController();
		add(myController.getView().getFeedbackPanel());
	}

}
