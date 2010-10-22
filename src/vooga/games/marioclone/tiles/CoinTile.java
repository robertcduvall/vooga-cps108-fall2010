package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;
import java.util.List;

import vooga.games.marioclone.MarioSprite;
import vooga.games.marioclone.items.Coin;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class CoinTile extends ItemTile {

	public CoinTile(double x, double y, List<BufferedImage> images, Coin coin) {
		super(x, y, images, coin);
	}

	@Override
	public void actOnCollision(Sprite sprite) {
		if (!isLocked()) {
			((Coin) myItem).spawn();
			((MarioSprite) sprite).incScore(10);
		}
		super.actOnCollision(sprite);
	}

}
