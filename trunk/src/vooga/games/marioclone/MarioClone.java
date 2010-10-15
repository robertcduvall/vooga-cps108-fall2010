
package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Randomizer;
import vooga.engine.resource.RandomizerException;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;

public class MarioClone extends Game {

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
    private static final int GAME_PLAY = 1;
    private static final int GAME_OVER = 2;
    private static final int GAME_WIN = 3;

    private int myGameState;

    
    private Background marioBackground, gameOver, gameWin;
    private GameFont myGameFont;
	private KeyboardControl myControl;
	private MarioPlayField playfield;
	private MarioSprite mario;
	private PlayField end, win;
	private GameStateManager gsm;
	private GamePlayState gamePlayState;
	private MainMenuState menuState;
	private EndGameState endGameState;
	
	
	public static void main(String[] args)  throws IOException {
		GameLoader gl = new GameLoader();
		MarioClone game = new MarioClone();
		gl.setup(game, new Dimension(WIDTH, HEIGHT), false);
		gl.start();
	}	
	
	{distribute = true;}

	public void initResources() {


		
		// Code and image lovingly borrowed from Grandius group - thanks guys!
        myGameFont = fontManager.getFont(getImages("images/font.png", 20, 3), " !            .,0123" +
                "456789:   -? ABCDEFG" + "HIJKLMNOPQRSTUVWXYZ ");
        
        
		Resources.setGame(this);
		bsLoader = new BaseLoader(new BaseIO(MarioClone.class), Color.white);
		TileMap map = null;
		try {
			Resources.loadFile("src/vooga/games/marioclone/resourcelist.txt");
			System.out.println("loaded resource list");
			System.out.println("loaded tiles");
		} catch (IOException e) {
			e.printStackTrace();
		}	
        
        
		gsm = new GameStateManager();
		
		menuState = new MainMenuState(myGameFont,WIDTH,HEIGHT);
		
		mario = new MarioSprite("mario","regular",Resources.getImage("MarioR"),Resources.getImage("MarioL"));
		gamePlayState = new GamePlayState(mario,WIDTH,HEIGHT);
		
		endGameState = new EndGameState();
		gsm.addGameState(menuState);
		gsm.addGameState(gamePlayState);
		gsm.addGameState(endGameState);
		
		gsm.switchTo(menuState);
        
	
		gameOver = new ImageBackground(Resources.getImage("GameOverBG"));
		gameWin = new ImageBackground(Resources.getImage("GameWIN"));
		
		end = new PlayField();
		win = new PlayField();
		
		win.setBackground(gameWin);
		end.setBackground(gameOver);


		myControl = new KeyboardControl(mario,this);
		
		myControl.addInput(KeyEvent.VK_D, "moveRight", "vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_A, "moveLeft", "vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_W, "jumpCmd", "vooga.games.marioclone.MarioSprite");
		
	}
	
	
	
	@Override
	public void update(long elapsedTime) {
		
		gsm.update(elapsedTime);
		
//		if (myEnemiesRemaining.getStat() == 0){
//			myGameState = GAME_WIN;
//		}
		
		if(!mario.isActive()){
			myGameState = GAME_OVER;
		}
		
		if(!mario.isActive()) myGameState = GAME_OVER;
		
		if (menuState.isActive()){
			if (keyPressed(KeyEvent.VK_SPACE))
				gsm.switchTo(gamePlayState);
		}
    	
    	if (myGameState == GAME_PLAY){

    	}  

		
	}
	
	@Override
	public void render(Graphics2D g) {
		gsm.render(g);
		
		if (myGameState == GAME_PLAY) {

		}
		
		if (myGameState == GAME_OVER){
			end.render(g);
		}
		
		if (myGameState == GAME_WIN){
			win.render(g);
		}
		}
	
}
