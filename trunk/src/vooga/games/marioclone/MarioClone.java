package vooga.games.marioclone;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import vooga.engine.core.Game;
import vooga.engine.resource.Resources;
import vooga.games.marioclone.tiles.Tile;
import vooga.games.marioclone.*;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;

import com.golden.gamedev.object.background.*;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ColorBackground;
import vooga.engine.player.control.KeyboardControl;

public class MarioClone extends Game {

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
	PlayField playfield;
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
		
		Background marioBackground = new ColorBackground(Color.cyan);
		marioBackground.setClip(0, 0, WIDTH, HEIGHT);
		playfield = new PlayField();
		playfield.setBackground(marioBackground);
		tileGroup = new SpriteGroup("Tile Group");
		for(Tile t : tiles) {
			tileGroup.add(t);
		}
		playfield.addGroup(tileGroup);
		
		marioGroup = new SpriteGroup("Mario Group");
		MarioSprite mario = new MarioSprite("mario","regular",new Sprite(getImage("images/mario.png")));
		mario.setLocation(0, 515);
		marioGroup.add(mario);
		playfield.addGroup(marioGroup);
		
		myControl = new KeyboardControl(mario,this);
		myControl.addInput(KeyEvent.VK_D, "moveRight", "vooga.games.marioclone.MarioSprite", null);
		myControl.addInput(KeyEvent.VK_A, "moveLeft", "vooga.games.marioclone.MarioSprite", null);
		myControl.addInput(KeyEvent.VK_W, "jump", "vooga.games.marioclone.MarioSprite", null);
		myControl.addInput(KeyEvent.VK_S, "crouch", "vooga.games.marioclone.MarioSprite", null);
		
		playfield.addCollisionGroup(marioGroup, tileGroup, new MarioToTileCollision());
	}
	
	@Override
	public void update(long elapsedTime) {
		myControl.update();

		playfield.update(elapsedTime);
	}
	
	@Override
	public void render(Graphics2D g) {
		playfield.render(g);
	}
	
}
