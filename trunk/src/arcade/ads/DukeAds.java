package arcade.ads;

import java.awt.Graphics2D;
import java.awt.Image;

public class DukeAds extends ImageAds
{

	public DukeAds(String name, Image img)
	{
		super(name, img);
		// TODO Auto-generated constructor stub
	}

	public void render(Graphics2D gs)
	{
		System.out.println(img==null);
		System.out.println("rendered");
		gs.drawString("it's weird", 10, 10);
		gs.drawImage(img, 10,10, null);

	}

	@Override
	public boolean isActive()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
