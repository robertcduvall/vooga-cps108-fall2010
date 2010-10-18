package vooga.games.marioclone;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Randomizer;
import vooga.engine.resource.RandomizerException;
import vooga.engine.resource.Resources;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;

public class MarioLevel {
	
	private Game myGame;
	private KeyboardControl myControl;
	private MarioPlayField myPlayField;
	private MarioSprite myMario;
	private Stat<Integer> myEnemiesKilled;
	private OverlayStat myScoreOverlay;
	private OverlayStat myLivesOverlay;
	private Timer myTimer;
	private static final int FREQ_ENEMIES = 5000;
	private static final int NUM_ENEMIES = 1;
	private int myWidth;
	private int myHeight;
	private int myBackgroundWidth;
	private int myBackgroundHeight;
	private boolean myLevelFinished;
	
	public MarioLevel(File mapFile, int width, int height, Game game) {
		myLevelFinished = false; 
		myGame = game;
		TileMap map = null;
		try {
			map = new TileMap(mapFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		myWidth = width;
		myHeight = height;
		myBackgroundWidth = map.width*map.TILE_SIZE;
		myBackgroundHeight = map.height*map.TILE_SIZE;
		myPlayField = new MarioPlayField(map);
		myPlayField.addGroup(map.getTileGroup());
		myEnemiesKilled = new Stat<Integer>(new Integer(0));
		myScoreOverlay = new OverlayStat("Score: ",
				myEnemiesKilled);
		myScoreOverlay.setLocation(myWidth - 1000, 5);

		getPlayField().add(myScoreOverlay);
		
		Background marioBackground = new ColorBackground(Color.blue,myBackgroundWidth, myBackgroundHeight);
		marioBackground.setClip(0, 0, myWidth, myHeight);
		myMario = new MarioSprite("mario", "regular", Resources
				.getImage("MarioR"), Resources.getImage("MarioL"));
		myMario.setLocation(150, 290);
		setUpKeyboard();
		getPlayField().getGroup("Mario Group").add(myMario);
		spawnEnemies();
		getPlayField().setBackground(marioBackground);
		myTimer = new Timer(FREQ_ENEMIES);
	}
	
	public MarioSprite getMario() {
		return myMario;
	}

	public void setMario(MarioSprite myMario) {
		this.myMario = myMario;
	}

	public MarioPlayField getPlayField() {
		return myPlayField;
	}

	public int getWidth() {
		return myWidth;
	}

	public int getHeight() {
		return myHeight;
	}

	public void update(long elapsedTime) {
		myControl.update();
		if(myMario.getX() >= (myBackgroundWidth-200)){
			myLevelFinished = true;
		}
		if(myMario.getX() > myMario.getMaxX()){
			scrollLevel();
		}
		getPlayField().update(elapsedTime);
		int numKilled = removeKilled();
		if (myTimer.action(elapsedTime))
			spawnEnemies();
		System.out.println(numKilled);
		myEnemiesKilled.setStat(myEnemiesKilled.getStat().intValue()+numKilled);
		myTimer = new Timer(FREQ_ENEMIES);
	}
	
	private int removeKilled(){
		SpriteGroup group = getPlayField().getGroup("Enemy Group");
		Sprite[] sprites = group.getSprites();
		int num = 0;
		for(int i=0; i<group.getSize(); i++){
			if(!sprites[i].isActive()){
				System.out.println("removing sprite");
				num++;
				group.remove(i);
			}
		}
		return num;
	}
	public boolean getLevelFinished() {
		return myLevelFinished;
	}

	public void render(Graphics2D g){
		myPlayField.render(g);
	}
	
	private void scrollLevel() {
		getPlayField().getBackground().setToCenter(myMario);
	}
	
	/**
	 * This method is responsible for spawning enemies at random locations on
	 * the map.
	 * 
	 */

	public void spawnEnemies() {
		for (int j = 0; j < NUM_ENEMIES; j++) {
			Enemy enemy = new Enemy("enemy1", "regular", Resources
					.getImage("EnemyR"), Resources.getImage("EnemyL"));
			try {
				enemy.setLocation(Randomizer.nextDouble(0, myWidth), Randomizer
						.nextDouble(0, myHeight));
			} catch (RandomizerException e) {
				System.out.println("Error - randomizer fail");
			}
			getPlayField().getGroup("Enemy Group").add(enemy);
		}
	}
	
	private void setUpKeyboard(){
		myControl = new KeyboardControl(myMario, myGame);

		myControl.addInput(KeyEvent.VK_D, "moveRight",
				"vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_A, "moveLeft",
				"vooga.games.marioclone.MarioSprite");
		myControl.addInput(KeyEvent.VK_W, "jumpCmd",
				"vooga.games.marioclone.MarioSprite");
		myMario.setHealth(myMario.getMaxHealth());
		myMario.setActive(true);
	}
	
	
}
