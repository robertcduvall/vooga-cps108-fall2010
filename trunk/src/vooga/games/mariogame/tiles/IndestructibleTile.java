package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;

import vooga.engine.factory.MapTile;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class IndestructibleTile extends MapTile {

	public IndestructibleTile(double x, double y, BufferedImage... images) {
		super(x, y, images);
	}

	public void actOnCollision(Sprite sprite) {
		// do nothing
	}

}
