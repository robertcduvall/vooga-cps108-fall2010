package arcade.core.examples;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.*;

import arcade.ads.adsmanager.AdsManager;
import arcade.core.api.Window;

public class ExampleWindow extends Window {

	private AdsManager myManager;

	public ExampleWindow() {
		super(200, 200);
		setName("ExampleWindow");
	}

	// @Override
	// protected void createContents() {
	// JPanel panel = new JPanel();
	// JLabel a=new JLabel("Ads");
	// JButton b=new JButton("close");
	// b.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent e) {
	// setVisible(false);
	// dispose();
	// }
	// });
	// a.setAlignmentX(CENTER_ALIGNMENT);
	// b.setAlignmentX(CENTER_ALIGNMENT);
	// panel.add(a);
	// panel.add(b);
	// panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	// add(panel);
	// }

	@Override
	protected void createContents() {
		setLayout(new BorderLayout());

		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		makeButtons(panel1);
		// this.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setPreferredSize(new Dimension(400, 400));
		addListener(panel2);

		System.out.println("panel2 null ? :  " + panel2 == null);
		myManager = new AdsManager(panel2);
		myManager.setActiveAds(new File("src/arcade/ads/resources/ads.txt"));
		// myManager.setRenderedAds();
		System.out.println(myManager.getActiveAdsGroup() == null);
		 myManager.setRenderedAds("Action");
		add(panel1, BorderLayout.PAGE_START);
		add(panel2, BorderLayout.CENTER);
		// myManager.setPanel(panel2);
		myManager.runAdsManager();
		// myManager.update();
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
				myManager.render();
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
				myManager.render();
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
