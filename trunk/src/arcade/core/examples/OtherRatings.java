package arcade.core.examples;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import arcade.core.Panel;


public class OtherRatings extends Panel {
	public OtherRatings(){
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		JLabel rateOthers = new JLabel("Rate These Other Games");
		JLabel moreLabels = new JLabel(icon);
		
		add(rateOthers);
		add(moreLabels);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	}
}
