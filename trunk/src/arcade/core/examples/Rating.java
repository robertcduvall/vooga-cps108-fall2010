package arcade.core.examples;

import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import arcade.core.Panel;

public class Rating extends Panel {

	public Rating() {
		JLabel rateThis = new JLabel("Rate This Game");
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		Image scaled = icon.getImage().getScaledInstance(25, 25,
				java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaled);
		JLabel label = new JLabel(icon);
		add(rateThis);
		add(label);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

}
