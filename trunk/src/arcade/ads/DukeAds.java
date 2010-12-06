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
		gs.drawString("ret", 10, 10);
		gs.drawImage(img, 0,0,800,600, null);

	}

	@Override
	public boolean isActive()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
