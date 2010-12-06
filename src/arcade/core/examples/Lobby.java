package arcade.core.examples;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arcade.core.Panel;

public class Lobby extends Panel {
	public Lobby(){
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel lobbyFriends = new JLabel("You have 0 friends");
		add(lobbyFriends);

		JLabel moreLabels = new JLabel(icon);
		add(moreLabels);
	}
}
