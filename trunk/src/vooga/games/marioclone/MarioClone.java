
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
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;

public class MarioClone extends Game {

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
	private static final int MAIN_MENU = 0;
    private static final int GAME_PLAY = 1;
    private static final int GAME_PAUSED = 2;
    private static final int GAME_OVER = 3;
    private int myGameState;
    private boolean isGameOver;
    
    GameFont myGameFont;
    Background marioBackground, mainMenu, gameOver;
	MarioPlayField playfield;
	MarioSprite mario;
	PlayField menu, end, paused;
	KeyboardControl myControl;
	SpriteGroup marioGroup, tileGroup, enemyGroup;
	
	public static void main(String[] args)  throws IOException {
		GameLoader gl = new GameLoader();
		MarioClone game = new MarioClone();
		gl.setup(game, new Dimension(WIDTH, HEIGHT), false);
		gl.start();
	}	
	
	
	public void initResources() {
		myGameState = MAIN_MENU;
		
		// Code and image lovingly borrowed from Grandius group - thanks guys!
        myGameFont = fontManager.getFont(getImages("images/font.png", 20, 3), " !            .,0123" +
                "456789:   -? ABCDEFG" + "HIJKLMNOPQRSTUVWXYZ ");
        
		Resources.setGame(this);
		bsLoader = new BaseLoader(new BaseIO(MarioClone.class), Color.white);
		TileMap map = null;
		try {
			Resources.loadFile("src/vooga/games/marioclone/resourcelist.txt");
			System.out.println("loaded resource list");
			map = new TileMap(bsLoader.getBaseIO().getURL("testmap.txt"));
			System.out.println("loaded tiles");
		} catch (IOException e) {
			e.printStackTrace();
		}		
		marioBackground = new ColorBackground(Color.cyan);
		marioBackground.setClip(0, 0, WIDTH, HEIGHT);
		mainMenu = new ImageBackground(Resources.getImage("MenuBG"));
		gameOver = new ImageBackground(Resources.getImage("GameOverBG"));
		
		playfield = new MarioPlayField();
		menu = new PlayField();
		end = new PlayField();
		paused = new PlayField();
		
		menu.setBackground(mainMenu);
		end.setBackground(gameOver);
		paused.setBackground(marioBackground);
		
		tileGroup = new SpriteGroup("Tile Group");
		playfield.addTileMap(map);
		
		marioGroup = new SpriteGroup("Mario Group");
		mario = new MarioSprite("mario","regular",Resources.getImage("MarioR"),Resources.getImage("MarioL"));
		mario.setLocation(150, 290);
		marioGroup.add(mario);
		playfield.addGroup(marioGroup);
		
		enemyGroup = new SpriteGroup("Enemy Group");
		Enemy enemy = new Enemy("enemy1","regular",Resources.getImage("EnemyR"),Resources.getImage("EnemyL"));
		enemy.setLocation(150, 600);
		enemyGroup.add(enemy);
		
		myControl = new KeyboardControl(mario,this);
		myControl.addInput(KeyEvent.VK_D, "moveRight", "vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_A, "moveLeft", "vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_W, "jump", "vooga.games.marioclone.MarioSprite");
		
		playfield.addCollisionGroup(marioGroup, playfield.getTileMap().getTileGroup(), new MarioToTileCollision());
	}
	
	@Override
	public void update(long elapsedTime) {
		System.out.println(myGameState);
		mario.stop();
		
		// Start of the game - main menu screen
		if (myGameState == MAIN_MENU){
			if (keyPressed(KeyEvent.VK_SPACE))
				myGameState = GAME_PLAY;
		}
    	
		// Game in progress state and transitions
    	if (myGameState == GAME_PLAY){
    		myControl.update();
        	playfield.update(elapsedTime);
        	if (isGameOver)
        		myGameState = GAME_OVER;
        	if (keyPressed(KeyEvent.VK_P)){
        		myGameState = GAME_PAUSED;
        	}
    	}  
    	
    	// Paused game state
    	if (myGameState == GAME_PAUSED){
    		System.out.println("In the paused state.");
    		mario.setActive(false);
    		if (keyPressed(KeyEvent.VK_P)){
    			mario.setActive(true);
    			myGameState = GAME_PLAY;
    		}
    	}
    	
    	// Game over state
    	if (myGameState == GAME_OVER)
    		if (keyPressed(KeyEvent.VK_SPACE))
    			myGameState = MAIN_MENU;
		
	}
	
	@Override
	public void render(Graphics2D g) {
		if (myGameState == MAIN_MENU) {
			menu.render(g);
			myGameFont.drawString(g, "WELCOME TO MARIOCLONE!", WIDTH / 4, HEIGHT / 2 + 50);
			myGameFont.drawString(g, "PRESS A AND D TO MOVE LEFT AND RIGHT.",(WIDTH / 4), (HEIGHT / 2) + 125);
			myGameFont.drawString(g, "PRESS W TO JUMP AND S TO CROUCH.", (WIDTH / 4), (HEIGHT / 2) + 200);
			myGameFont.drawString(g, "PRESS SPACE TO PLAY!", (WIDTH / 4), (HEIGHT / 2) + 250);
		}
		
		if (myGameState == GAME_PLAY) {
            playfield.setBackground(marioBackground);
			playfield.render(g);
		}
		
		if (myGameState == GAME_PAUSED){
			paused.render(g);
			myGameFont.drawString(g, "GAME PAUSED - PRESS SPACE TO RESUME", WIDTH / 4, HEIGHT / 2);
		}
		
		if (myGameState == GAME_OVER){
			end.render(g);
		}
		}
	
}
