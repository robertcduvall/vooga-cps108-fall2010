package vooga.games.marioclone;

import java.io.IOException;
import java.util.List;

import vooga.engine.resource.ResourceHandler;
import vooga.games.marioclone.tiles.Tile;


/**
 * 
 * @author Cameron McCallie, Andrew Brown, David Herzka
 *
 */
public class MarioClone 
{

	public static void main(String[] args) throws IOException
	{
		ResourceHandler.loadFile("resourcelist.txt");
		TileMap m = new TileMap("testmap.txt");
		List<Tile> tiles = m.getTiles();
	}
}
