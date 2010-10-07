package vooga.games.marioclone;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.marioclone.tiles.Tile;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.PlayField;

public class MarioClone extends Game {

	private static final int GAME_WIDTH = 700;
	private static final int GAME_HEIGHT = 500;
	PlayField playfield;
	
	public static void main(String[] args)  throws IOException {
		MarioClone game = new MarioClone();
		game.init();
	}	
	
	
	private void init() throws IOException {
		Resources.setGame(this);
		bsLoader = new BaseLoader(new BaseIO(MarioClone.class), Color.white);

		Resources.loadFile("src/vooga/games/marioclone/resourcelist.txt");
		System.out.println("loaded resource list");
		TileMap m = new TileMap(bsLoader.getBaseIO().getURL("testmap.txt"));
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
