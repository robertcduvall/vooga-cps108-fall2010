package vooga.games.towerdefense;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.ImageUtil;

import vooga.engine.core.Game;
import vooga.engine.player.control.PlayerSprite;

public class PlayerCursor extends PlayerSprite{
	
	private static final long serialVersionUID = -8174656868252384431L;
	SpriteGroup towerGroup;

	public PlayerCursor(String name, String stateName, Sprite s, SpriteGroup towerGroup) {
		super(name, stateName, s);
		this.towerGroup = towerGroup;
	}
	
	public void buildTower(int x, int y) throws MalformedURLException{
		try {
			File file = new File("src/vooga/games/towerdefense/images/tower.png");
			URL imageURL = file.toURI().toURL();
			Sprite image = new Sprite(ImageUtil.getImage(imageURL), x, y);
			towerGroup.add(image);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void buildTower() throws MalformedURLException{
		URL imageURL = new URL("images/tower.png");
		Sprite image = new Sprite(ImageUtil.getImage(imageURL), 20, 20);
		towerGroup.add(image); 
	}

	

}
