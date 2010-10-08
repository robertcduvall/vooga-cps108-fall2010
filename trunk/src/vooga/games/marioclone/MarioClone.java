package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import vooga.engine.core.Game;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.games.marioclone.tiles.Tile;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;

public class MarioClone extends Game {

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
	MarioPlayField playfield;
	KeyboardControl myControl;
	SpriteGroup marioGroup, tileGroup;
	
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
		TileMap map = null;
		try {
			Resources.loadFile("src/vooga/games/marioclone/resourcelist.txt");
			System.out.println("loaded resource list");
			map = new TileMap(bsLoader.getBaseIO().getURL("testmap.txt"));
			System.out.println("loaded tiles");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Background marioBackground = new ColorBackground(Color.cyan);
		marioBackground.setClip(0, 0, WIDTH, HEIGHT);
		playfield = new MarioPlayField();
		playfield.setBackground(marioBackground);
		tileGroup = new SpriteGroup("Tile Group");

		playfield.addTileMap(map);
		
		
		marioGroup = new SpriteGroup("Mario Group");
		MarioSprite mario = new MarioSprite("mario","regular",new Sprite(getImage("images/mario.png")));
		mario.setLocation(40, 400);
		marioGroup.add(mario);
		playfield.addGroup(marioGroup);
		
		myControl = new KeyboardControl(mario,this);
		myControl.addInput(KeyEvent.VK_D, "moveRight", "vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_A, "moveLeft", "vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_W, "jump", "vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_S, "crouch", "vooga.games.marioclone.MarioSprite");
		
		playfield.addCollisionGroup(marioGroup, playfield.getTileMap().getTileGroup(), new MarioToTileCollision());
	}
	
	@Override
	public void update(long elapsedTime) {
		myControl.update();

		playfield.update(elapsedTime);
		
		
		Sprite[] mg = marioGroup.getSprites();
		Sprite[] tg = tileGroup.getSprites();
		

	}
	
	@Override
	public void render(Graphics2D g) {
		playfield.render(g);
	}
	
}
