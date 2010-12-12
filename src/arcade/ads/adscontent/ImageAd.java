package arcade.ads.adscontent;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;

import arcade.ads.util.AdDate;

/**
 * 
 * This is simple class will provide general functionality for all ads, such as
 * name, start and end date, maximum vies, onClick, onMouseOver, update, and
 * render methods. We feel like these are the very basic methods and ideas that
 * every single ad is going to need.
 * 
 * @author Hao He (hh89@duke.edu)
 * @author Nick Straub (njs7@duke.edu)
 * @author Scott Winkleman (saw26@duke.edu)
 * @author Kate Yang (kly2@duke.edu)
 * 
 * @version 1.0
 */
public class ImageAd extends BasicAd {

	private Image img;

	public ImageAd(String name) {
		super(name);
	}

	public ImageAd(String name, Image img) {
		super(name);
		this.img = img;
		setHeight(img.getHeight(null));
		setWidth(img.getWidth(null));

	}

	@Override
	public void onLeftClick() {
		openBrowser();
	}

	@Override
	public void onRightClick() {
		// do nothing

	}

	@Override
	public void onMouseOver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(JPanel panel) {
		Graphics2D g = (Graphics2D) panel.getGraphics();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	}

	@Override
	public void render(JPanel panel, int width, int height) {
		Graphics2D g = (Graphics2D) panel.getGraphics();
		g.drawImage(img, 0, 0, width, height, null);

	}

	public void setParameters(NamedNodeMap attributes) {
		String name = attributes.getNamedItem("name").getNodeValue();
		this.name = name;
		try {
			this.img = ImageIO.read(new File(attributes.getNamedItem("image")
					.getNodeValue()));
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.effectiveDate = new AdDate(attributes.getNamedItem("startTime")
				.getNodeValue());
		this.expireDate = new AdDate(attributes.getNamedItem("endTime")
				.getNodeValue());
		this.targetURL = attributes.getNamedItem("targetURL").getNodeValue();
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
