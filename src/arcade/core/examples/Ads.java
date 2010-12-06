package arcade.core.examples;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import arcade.core.Panel;

public class Ads extends Panel {
	public Ads() {
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel randomAd = new JLabel("Buy Coke.");
		add(randomAd);

		JLabel evenMoreLabels = new JLabel(icon);
		add(evenMoreLabels);
	}
}
