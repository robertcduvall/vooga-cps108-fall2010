package arcade.core.examples;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;

import arcade.ads.adsmanager.AdsManager;
import arcade.core.Panel;

public class Ads extends Panel {
	
	private AdsManager myManager;
	
	public Ads() {
//		ImageIcon icon = new ImageIcon("src/arcade/core/RatingStar.gif");
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//		setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		JLabel randomAd = new JLabel("Buy Coke.");
//		add(randomAd);
//
//		JLabel evenMoreLabels = new JLabel(icon);
//		add(evenMoreLabels);
		initialize();
	}
	
	private void initialize()
	{
		JPanel panel2 = new JPanel();

		myManager = new AdsManager(panel2);
		myManager.setActiveAds(new File("src/arcade/ads/resources/ads.txt"));
		myManager.setRenderedAds();
		//myManager.setRenderedAds("action");
		add(panel2);


		//myManager.setPanel(panel2);
		myManager.runAdsManager();
		// myManager.update();
		// myManager.render();
	}
}
