package vooga.games.marioclone.map;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vooga.engine.resource.ResourceHandler;
import vooga.games.marioclone.map.tiles.*;

public class Map {
	List<Tile> tiles;
	
	public void loadTiles(String filename) throws IOException {
		ArrayList<String> lines = new ArrayList<String>();
		int width = 0;
		int height = 0;
		
		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(filename);
		if (url == null) {
			throw new IOException("No such map: " + filename);
		}
		
		// read every line in the text file into the list
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				url.openStream()));
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
					tiles.add(new ChangingTile(j,k,(BufferedImage) ResourceHandler.getMapping("Changing")));
					break;
				}
			}
		}
		
		
	}
	
}
