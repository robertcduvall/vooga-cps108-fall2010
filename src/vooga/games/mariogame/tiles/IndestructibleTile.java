package vooga.games.mariogame.tiles;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class IndestructibleTile extends Tile {

	public IndestructibleTile(double x, double y, BufferedImage... images) {
		super(x, y, images);
	}

	public void actOnCollision(Sprite sprite) {
		// do nothing
	}

}
