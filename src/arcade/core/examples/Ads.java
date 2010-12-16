package arcade.core.examples;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import arcade.ads.adsmanager.AdsManager;
import arcade.core.Panel;

public class Ads extends Panel {
	
	//private AdsManager myManager;
	private static final int AVATAR_WIDTH = 50;
	
	public Ads() {
		initialize();
	}
	
	/**
	 * Since ads couldn't integrate into arcade, we placed a 
	 * placeholder here so when they do finish they can add the code back in.
	 * 
	 * The code that is commented out is the lines of code they told us to use 
	 * which did not work even after their efforts.
	 *
	 */
	private void initialize()
	{
		/*JPanel panel2 = new JPanel();

		myManager = new AdsManager(panel2);
		myManager.setActiveAds(new File("src/arcade/ads/resources/ads.txt"));
		myManager.setRenderedAds();
		add(panel2);

		myManager.runAdsManager();
		
		*/
		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		scaleImage(icon);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel randomAd = new JLabel("Buy Coke.");
		add(randomAd);

		JLabel evenMoreLabels = new JLabel(icon);
		add(evenMoreLabels);
		
	}
	
	private void scaleImage(ImageIcon icon) {
		Image image = icon.getImage();
		icon.setImage(image.getScaledInstance(AVATAR_WIDTH,
				AVATAR_WIDTH * icon.getIconHeight() / icon.getIconWidth(), 0));
	}
}
