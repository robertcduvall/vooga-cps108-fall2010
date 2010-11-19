package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class IndestructibleTile extends Tile {

	public IndestructibleTile(BufferedImage image, double x, double y) {
		super(image, x, y);
	}

	public void actOnCollision(Sprite sprite) {
		// do nothing
	}

}
