package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.factory.MapTile;
import vooga.games.mariogame.sprites.MarioSprite;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class FlagTile extends MapTile {

	public FlagTile(double x, double y, BufferedImage... images) {
		super(x, y, images);
	}
	
	@Override
	public void actOnCollision(Sprite sprite) {
		((MarioSprite)sprite).setLevelFinsihed(true);
	}

}
