package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;
import java.util.List;

import vooga.games.marioclone.items.Coin;

import com.golden.gamedev.object.Sprite;

@SuppressWarnings("serial")
public class CoinTile extends ItemTile {
	
	int myUses;

	public CoinTile(double x, double y, List<BufferedImage> images, Coin coin, int uses) {
		super(x, y, images, coin);
		myUses = uses;
	}

	public boolean isUsedUp() {
		return myUses < 0;
	}
	
	@Override
	public void actOnCollision(Sprite sprite) {
		((Coin) myItem).spawn();
		myUses--;
		super.actOnCollision(sprite);
	}

}
