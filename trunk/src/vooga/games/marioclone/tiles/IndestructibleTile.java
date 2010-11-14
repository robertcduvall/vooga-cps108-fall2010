package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class IndestructibleTile extends Tile {

	public IndestructibleTile(double x, double y, BufferedImage image) {
		super(image, x, y);
	}

	public void actOnCollision(Sprite sprite) {
		// do nothing
	}

}
