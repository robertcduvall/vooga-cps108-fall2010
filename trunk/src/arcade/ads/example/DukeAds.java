package arcade.ads.example;

import java.awt.Event;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;

import arcade.ads.adscontent.ImageAd;

public class DukeAds extends ImageAd
{
	

	public DukeAds() {
		super();
	}
	public DukeAds(String name, Image img)
	{
		super(name, img);
		// TODO Auto-generated constructor stub
	}

	public void render(Graphics2D gs)
	{
		gs.drawImage(img, 0,0,800,600, null);
	}
	
	@Override
	public boolean isActive()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setParameters(NamedNodeMap attributes) {
		String name = attributes.getNamedItem("name").getNodeValue();
		this.name = name;
		try {
			this.img = ImageIO.read(new File(attributes.getNamedItem("image").getNodeValue()));
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.effectiveDate = Long.parseLong(attributes.getNamedItem("startTime").getNodeValue());
		this.expireDate = Long.parseLong(attributes.getNamedItem("endTime").getNodeValue());
		this.targetURL = attributes.getNamedItem("targetURL").getNodeValue();
	}
	
}
