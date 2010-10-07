package vooga.games.marioclone;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import vooga.engine.core.Game;
import vooga.engine.resource.ResourceHandler;
import vooga.games.marioclone.tiles.Tile;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;

public class MarioClone {

	public static void main(String[] args)  throws IOException {
		Game game = new Game();
		ResourceHandler.setGame(game);
		game.bsLoader = new BaseLoader(new BaseIO(MarioClone.class), Color.white);

		System.out.println(game.bsLoader.getBaseIO().getRootPath(BaseIO.CLASS_URL));
		URL a = game.bsLoader.getBaseIO().getURL("resourcelist.txt");
		ResourceHandler.loadFile("resourcelist.txt");
		System.out.println("loaded resource list");
		TileMap m = new TileMap("testmap.txt");
		System.out.println("loaded tiles");
		List<Tile> tiles = m.getTiles();
	}	
	
	
//	public static void main(String[] args) throws IOException {
//		ResourceHandler.loadFile("resourcelist.txt");
//		System.out.println("loaded resource list");
//		TileMap m = new TileMap("testmap.txt");
//		System.out.println("loaded tiles");
//		List<Tile> tiles = m.getTiles();
//	}
}
