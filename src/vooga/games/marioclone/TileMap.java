package vooga.games.marioclone;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
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
import com.golden.gamedev.object.collision.CollisionGroup;

public class TileMap {
	
	private static double TILE_SIZE = 64;
	
	private List<Tile> tiles;
	private SpriteGroup tileGroup;
	
	public TileMap(URL url) throws IOException {
		tiles = new CopyOnWriteArrayList<Tile>();
		tileGroup = new SpriteGroup("Tile Group");
		loadTiles(url);
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
	
	public void loadTiles(URL url) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		int width = 0;
		int height = 0;
		
		if (url == null) {
			throw new IOException("No such map!");
		}
		
		// read every line in the text file into the list
		InputStream is = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				
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
					System.out.println("Tile position x: "+tilesToPixels(j)+" y: "+tilesToPixels(k));
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
