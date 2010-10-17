package vooga.games.marioclone;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import vooga.engine.resource.Resources;
import vooga.games.marioclone.tiles.BreakTile;
import vooga.games.marioclone.tiles.ChangingTile;
import vooga.games.marioclone.tiles.IndestructibleTile;
import vooga.games.marioclone.tiles.Tile;
import vooga.games.marioclone.tiles.Tile.State;

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
	
	public List<Tile> getTiles() {
		return tiles;
	}
	
	public void updateTiles() {
		for(Tile t : tiles) {
			if(t.getState()==State.removed) removeTile(t);
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
		return coord*TILE_SIZE;
	}
	
	private double pixelsToTiles(int coord) {
		return coord/TILE_SIZE;
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
		for(int j = 0; j < width; j++) {
			for(int k = 0; k < height; k++) {
				curChar = (j < lines.get(k).length()) ? lines.get(k).charAt(j) : ' ';
				switch(curChar) {
				case(' '):
					break;
				case('I'):
					addTile(new IndestructibleTile(tilesToPixels(j),tilesToPixels(k),Resources.getImage("ITile")));
					break;
				case('B'):
					addTile(new BreakTile(tilesToPixels(j),tilesToPixels(k),Resources.getImage("Break")));
					break;	
				case('C'):
					List<BufferedImage> changingImages = new ArrayList<BufferedImage>();
					changingImages.add(Resources.getImage("Changing1"));
					changingImages.add(Resources.getImage("Changing2"));
					changingImages.add(Resources.getImage("Changing3"));
					addTile(new ChangingTile(tilesToPixels(j),tilesToPixels(k),changingImages));
					break;
				}
			}
		}
		
		
	}
	
}
