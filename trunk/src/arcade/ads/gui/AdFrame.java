package arcade.ads.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import arcade.ads.adscontent.ImageAd;
import arcade.ads.adsmanager.AdsManager;

import com.golden.gamedev.util.ImageUtil;

/**
 * 
 * 
 * @author Hao He (hh89@duke.edu)
 * @author Nick Straub (njs7@duke.edu)
 * @author Scott Winkleman (saw26@duke.edu)
 * @author Kate Yang (kly2@duke.edu)
 * 
 * @version 1.0
 */
public class AdFrame extends JFrame {

	AdsManager myManager;

	public AdFrame(AdsManager manager, Dimension adDimension) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		myManager = manager;

		setTitle("Ads");

		setLayout(new BorderLayout());

		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		makeButtons(panel1);
		// this.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setPreferredSize(adDimension);
		addListener(panel2);

		getContentPane().add(panel1, BorderLayout.PAGE_START);
		getContentPane().add(panel2, BorderLayout.CENTER);

		pack();
		setVisible(true);

		myManager.setPanel(panel2);
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

	public static void main(String args[]) {
		AdsManager manager = new AdsManager();

//		BufferedImage img1 = null;
//		BufferedImage img2 = null;
//		try {
//			// System.out.println(System.getProperty("user.dir") );
//			img1 = ImageIO.read(new File("src/arcade/ads/resources/duke.png"));
//			img2 = ImageIO.read(new File("src/arcade/ads/resources/unc.png"));
//		} catch (IOException e) {
//			// System.out.println("wer");
//		}
//
//		manager.getRenderedAdsGroup().add((new ImageAd("duke", img1)));
//		manager.getRenderedAdsGroup().add((new ImageAd("unc", img2)));
		manager.setAds("src/arcade/ads/resources/xmlFiles.txt");
		// BufferedImage img = null;
		// try
		// {
		// img = ImageIO.read(new
		// File(System.getProperty("user.dir")+"/src/arcade/ads/resources/duke.png"));
		// } catch (IOException e)
		// {
		// System.out.println("werwe");
		// }
		//
		// manager.add(new DukeAds("duke", img));
		// System.out.println(manager.retrieve());

		new AdFrame(manager, new Dimension(800, 600));
	}
}
