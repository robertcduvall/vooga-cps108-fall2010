package vooga.games.towerdefense;

import java.net.MalformedURLException;
import java.net.URL;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.ImageUtil;

import vooga.engine.player.control.ItemSprite;

public class TowerSprite extends ItemSprite{
	
	SpriteGroup towerGroup;

	public TowerSprite(Sprite s, SpriteGroup towerGroup) {
		super(s);
		this.towerGroup = towerGroup;
	}
	
	public void act(){
		try {
			URL imageURL = new URL("images/tower.png");
			Sprite image = new Sprite(ImageUtil.getImage(imageURL), 20, 20);
			towerGroup.add(image);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		 
	}

}
