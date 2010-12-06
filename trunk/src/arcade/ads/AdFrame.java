package arcade.ads;

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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

	public AdFrame(AdsManager manager) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		myManager = manager;

		setTitle("Ads");

		setLayout(new FlowLayout());

		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		makeButtons(panel1);
		// this.add(panel1);

		JPanel panel2 = new JPanel();
		// this.add(panel2);
		getContentPane().add(panel1);
		getContentPane().add(panel2);

		pack();
		setVisible(true);

		myManager.setGraphics(panel2.getGraphics());
		myManager.runAdsThread();
		// myManager.update();
		// myManager.render();
	}

	/**
	 * Create the
	 * 
	 */
	public void makeButtons(JPanel panel) {
		panel.setLayout(new FlowLayout());

		JButton prev = new JButton("Previous Ad");
		panel.add(prev);
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPrevious();
			}
		});

		JButton next = new JButton("Next Ad");
		panel.add(next);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getNext();
			}
		});
	}

	public void getPrevious() {
		myManager.prevAds();
	}

	public void getNext() {
		myManager.nextAds();
	}

	public static void main(String args[]) {
		AdsManager manager = new AdsManager();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(System.getProperty("user.dir")
					+ "/src/arcade/ads/resources/duke.png"));
		} catch (IOException e) {
			System.out.println("werwe");
		}

		manager.add(new DukeAds("duke", img));

		new AdFrame(manager);
	}

}
