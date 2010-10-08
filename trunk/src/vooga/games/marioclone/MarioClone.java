package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.marioclone.tiles.Tile;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

public class MarioClone extends Game {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	PlayField playfield;
	
	public static void main(String[] args)  throws IOException {
		GameLoader gl = new GameLoader();
		MarioClone game = new MarioClone();
		gl.setup(game, new Dimension(WIDTH, HEIGHT), false);
		gl.start();
//		game.init();
	}	
	
	
	public void initResources() {
		Resources.setGame(this);
		bsLoader = new BaseLoader(new BaseIO(MarioClone.class), Color.white);
		TileMap m = null;
		try {
			Resources.loadFile("src/vooga/games/marioclone/resourcelist.txt");
			System.out.println("loaded resource list");
			m = new TileMap(bsLoader.getBaseIO().getURL("testmap.txt"));
			System.out.println("loaded tiles");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Tile> tiles = m.getTiles();
		
		playfield = new PlayField();
		playfield.setBackground(new ImageBackground(Resources.getImage("Background")));
		playfield.add(tiles.get(0));
	}
	
	
//	public static void main(String[] args) throws IOException {
//		ResourceHandler.loadFile("resourcelist.txt");
//		System.out.println("loaded resource list");
//		TileMap m = new TileMap("testmap.txt");
//		System.out.println("loaded tiles");
//		List<Tile> tiles = m.getTiles();
//	}
}
