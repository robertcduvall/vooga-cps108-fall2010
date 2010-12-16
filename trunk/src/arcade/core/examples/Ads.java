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
		

		
		setLayout(new BorderLayout());

		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		makeButtons(panel1);
		// this.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		addListener(panel2);

		myManager = new AdsManager(new JPanel());
		myManager.setActiveAds(new File("src/arcade/ads/resources/ads.txt"));
		myManager.setRenderedAds();
		//myManager.setRenderedAds("action");
		
		add(panel1, BorderLayout.PAGE_START);
		add(panel2, BorderLayout.CENTER);


		//myManager.setPanel(panel2);
		myManager.runAdsManager();
		// myManager.update();
		// myManager.render();
	}

	/**
	 * Create the JButtons to add to the panel
	 * 
	 */
	private void makeButtons(JPanel panel) {
		panel.setLayout(new FlowLayout());

		JButton prev = new JButton("Previous Ad");
		panel.add(prev);
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myManager.getRenderedAdsGroup().prevAds();
			}
		});

		JButton rotate = new JButton("Rotate Ad");
		panel.add(rotate);
		rotate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myManager.rotate();
			}
		});

		JButton stoprotate = new JButton("Stop Rotate Ad");
		panel.add(stoprotate);
		stoprotate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myManager.stoprotate();
			}
		});

		JButton next = new JButton("Next Ad");
		panel.add(next);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myManager.getRenderedAdsGroup().nextAds();
			}
		});
	}

	/**
	 * add MouseListener to Image panel
	 */
	private void addListener(JPanel panel) {
		panel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// myManager.getCurrentAd().onLeftClick();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
}
