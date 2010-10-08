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
	TowerDefense myGame;

	public PlayerCursor(String name, String stateName, Sprite s, SpriteGroup towerGroup, TowerDefense game) {
		super(name, stateName, s);
		this.towerGroup = towerGroup;
		myGame = game;
	}
	
	public void buildTower() throws MalformedURLException{
		try {
			File file = new File("src/vooga/games/towerdefense/resources/images/tower.png");
			URL imageURL = file.toURI().toURL();
			Tower image = new Tower(ImageUtil.getImage(imageURL), myGame.getMouseX(), myGame.getMouseY(), 40, 1000);
			image.setTargetGroup(myGame.getEnemyGroup());
			towerGroup.add(image);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	

}
