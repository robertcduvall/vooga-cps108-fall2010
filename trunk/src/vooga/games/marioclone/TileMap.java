package vooga.games.marioclone;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.resource.ResourceHandler;
import vooga.games.marioclone.tiles.BreakTile;
import vooga.games.marioclone.tiles.ChangingTile;
import vooga.games.marioclone.tiles.IndestructibleTile;
import vooga.games.marioclone.tiles.Tile;
import vooga.games.marioclone.tiles.Tile.State;

public class TileMap {
	List<Tile> tiles;
	
	public TileMap(String filename) throws IOException {
		tiles = new ArrayList<Tile>();
		loadTiles(filename);
	}
	
	public List<Tile> getTiles() {
		return tiles;
	}
	
	public void updateTiles() {
		for(Tile t : tiles) {
			if(t.getState()==State.removed) tiles.remove(t);
		}
	}
	
	public void loadTiles(String filename) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		int width = 0;
		int height = 0;
		
		InputStream is = ResourceHandler.getResourceAsStream(filename);
		
		if (is == null) {
			throw new IOException("No such map: " + filename);
		}
		
		// read every line in the text file into the list
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
					tiles.add(new IndestructibleTile(j,k,(BufferedImage) ResourceHandler.getMapping("Indestructible")));
					break;
				case('B'):
					tiles.add(new BreakTile(j,k,(BufferedImage) ResourceHandler.getMapping("Break")));
					break;	
				case('C'):
					List<BufferedImage> changingImages = new ArrayList<BufferedImage>();
					changingImages.add((BufferedImage) ResourceHandler.getMapping("Changing1"));
					changingImages.add((BufferedImage) ResourceHandler.getMapping("Changing2"));
					changingImages.add((BufferedImage) ResourceHandler.getMapping("Changing3"));
					tiles.add(new ChangingTile(j,k,changingImages));
					break;
				}
			}
		}
		
		
	}
	
}
