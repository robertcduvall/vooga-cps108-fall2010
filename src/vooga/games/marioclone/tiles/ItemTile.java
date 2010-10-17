package vooga.games.marioclone.tiles;

import java.awt.image.BufferedImage;
import java.util.List;

import vooga.games.marioclone.items.GravityItem;
import vooga.games.marioclone.items.Item;

import com.golden.gamedev.object.Sprite;

public class ItemTile extends ChangingTile {
	
	private Item myItem;

	public ItemTile(double x, double y, List<BufferedImage> images) {
		super(x, y, images);
		myItem = new GravityItem(.5);
	}

	@Override
	public void actOnCollision(Sprite sprite) {
		super.actOnCollision(sprite);
		
	}
	
	@Override
	public Item checkNewItems() {
		return super.checkNewItems();
	}

}
