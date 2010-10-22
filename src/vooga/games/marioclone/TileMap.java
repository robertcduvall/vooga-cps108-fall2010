package vooga.games.marioclone;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import vooga.engine.player.control.ItemSprite;
import vooga.engine.resource.Resources;
import vooga.games.marioclone.items.Coin;
import vooga.games.marioclone.items.GravityItem;
import vooga.games.marioclone.tiles.BreakTile;
import vooga.games.marioclone.tiles.ChangingTile;
import vooga.games.marioclone.tiles.CoinTile;
import vooga.games.marioclone.tiles.IndestructibleTile;
import vooga.games.marioclone.tiles.ItemTile;
import vooga.games.marioclone.tiles.Tile;
import vooga.games.marioclone.tiles.Tile.State;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class TileMap {

	public int TILE_SIZE = 64;

	private List<Tile> tiles;
	private SpriteGroup tileGroup;
	public int width;
	public int height;

	public TileMap(File file) throws IOException {
		tiles = new CopyOnWriteArrayList<Tile>();
		tileGroup = new SpriteGroup("Tile Group");
		loadTiles(file);
	}

	public List<ItemSprite> getNewItems() {
		List<ItemSprite> list = new ArrayList<ItemSprite>();
		for (Tile t : tiles) {
			ItemSprite item = t.checkItem();
			if (item != null)
				list.add(item);
		}
		return list;
	}

	public List<Tile> getTiles() {
		return tiles;
	}

	public void updateTiles() {
		for (Tile t : tiles) {
			if (t.getState() == State.removed)
				removeTile(t);
		}
	}

	private void removeTile(Tile t) {
		tiles.remove(t);
		tileGroup.remove(t);
	}

	private void addTile(Tile t) {
		tiles.add(t);
		tileGroup.add(t);
	}

	public SpriteGroup getTileGroup() {
		return tileGroup;
	}

	public void setTileGroup(SpriteGroup tileGroup) {
		this.tileGroup = tileGroup;
	}

	private double tilesToPixels(int coord) {
		return coord * TILE_SIZE;
	}

	public void loadTiles(File file) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		width = 0;
		height = 0;

		if (file == null) {
			throw new IOException("No such map!");
		}

		// read every line in the text file into the list
		BufferedReader reader = new BufferedReader(new FileReader(file));

		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}
			// add every line except for comments
			if (!line.startsWith("#")) {
				lines.add(line);
				width = Math.max(width, line.length());
			}
		}

		// parse the lines to create a tile list
		Character curChar = ' ';
		height = lines.size();
		for (int j = 0; j < width; j++) {
			for (int k = 0; k < height; k++) {
				double x = tilesToPixels(j);
				double y = tilesToPixels(k);
				curChar = (j < lines.get(k).length()) ? lines.get(k).charAt(j)
						: ' ';
				switch (curChar) {
				case (' '):
					break;
				case ('F'):
					addTile(new IndestructibleTile(x, y, Resources
							.getImage("GrassTile")));
					break;
				case ('D'):
					addTile(new IndestructibleTile(x, y, Resources
							.getImage("DirtTile")));
					break;
				case ('B'):
					addTile(new BreakTile(x, y, Resources.getImage("Break")));
					break;
				case ('C'):
					List<BufferedImage> changingImages = new ArrayList<BufferedImage>();
					changingImages.add(Resources.getImage("Changing1"));
					changingImages.add(Resources.getImage("Changing2"));
					changingImages.add(Resources.getImage("Changing3"));
					changingImages.add(Resources.getImage("Changing4"));
					addTile(new ChangingTile(x, y, changingImages));
					break;
				case ('G'):
					List<BufferedImage> itemTileImages = new ArrayList<BufferedImage>();
					itemTileImages.add(Resources.getImage("ItemTile1"));
					itemTileImages.add(Resources.getImage("ItemTile2"));
					GravityItem gravityItem = new GravityItem(new Sprite(
							Resources.getImage("GravityItem")), .5);
					gravityItem.setLocation(x
							+ (TILE_SIZE - gravityItem.getWidth()) / 2, y
							- gravityItem.getHeight());
					addTile(new ItemTile(x, y, itemTileImages, gravityItem));
					break;
				case ('S'):
					List<BufferedImage> coinTileImages = new ArrayList<BufferedImage>();
					coinTileImages.add(Resources.getImage("ItemTile1"));
					coinTileImages.add(Resources.getImage("ItemTile2"));
					Coin coin = new Coin(new Sprite(Resources
							.getImage("Coin")));
					coin.setLocation(x + (TILE_SIZE - coin.getWidth()) / 2, y
							- coin.getHeight());
					addTile(new CoinTile(x, y, coinTileImages, coin, 1));
					break;
				}
			}
		}

	}

}
