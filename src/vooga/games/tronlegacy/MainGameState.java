package vooga.games.tronlegacy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.CollisionBounds;
import com.golden.gamedev.util.ImageUtil;

import sun.misc.Queue;
import vooga.engine.core.Game;
import vooga.engine.event.EventManager;
import vooga.engine.level.LevelManager;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.level.Level;

public class MainGameState extends GameState {
	
	//note to self: the new levelmanager in the factory package will help this a lot once it is fixed
	
	private final static int FPS = 100;
	private final static Point humanPlayerStartingLocation = new Point (48,240);
	private final static Point computerPlayerStartingLocation = new Point (432,240);
	private final static String LEVEL_FILEPATH = "src/vooga/games/tronlegacy/resources/levels/";
	
	private EventManager eventManager;
	private TLevelManager levelManager;
	
	private Stat<Integer> currentScore;
	
	private CyclePlayer humanPlayer;
	private CyclePlayer computerPlayer;
	private KeyboardControl keyboardControl;
	private Game game;
	
	private SpriteGroup players;
	private SpriteGroup levelBlocks;
	private SpriteGroup playerBlocks;
	private SpriteGroup overlayGroup;
	private PlayField currentPlayField;
	
	private LinkedList<Sprite> blockQueue = new LinkedList<Sprite>();
	
	int currentLevel = 1;
	boolean gameOver = false;
	boolean paused = false;
	
	public void initialize(Game aGame) {
		
		game = aGame;
		
		eventManager = new EventManager();
		levelManager = new TLevelManager();
		
		initializePlayers();
		initializeOverlays();
		levelManager.addLevels(LEVEL_FILEPATH, new File("levels.txt"));
				
		deployLevel();
		
	}
	
	public void render(Graphics2D g){
	
		currentPlayField.render(g);
		
		for (Sprite currentSprite: blockQueue){
			currentSprite.render(g);
		}
		
	}
	
	public void update(long elapsedTime){
		keyboardControl.update();
		deployPlayerBlocks();
		currentPlayField.update(elapsedTime);
		computerPlayer.aiUpdate(currentPlayField);
		checkLevelEnd();
	}
	
	public void deployPlayerBlocks(){
		
		for (Sprite currentPlayer : players.getSprites()){
			if (currentPlayer != null){
			blockQueue.add(new Sprite(currentPlayer.getImage(),
										currentPlayer.getX(),
										currentPlayer.getY()));
			
			}
		}
		
		while (blockQueue.size() > FPS/5){
			playerBlocks.add(blockQueue.remove());
		}
		
	}
	
	public void initializePlayers(){
		
		Sprite humanSprite = new Sprite(Resources.getImage("humanPlayer"),humanPlayerStartingLocation.x,humanPlayerStartingLocation.y);
		Sprite computerSprite = new Sprite(Resources.getImage("computerPlayer"),computerPlayerStartingLocation.x,computerPlayerStartingLocation.y);
		
		humanPlayer = new CyclePlayer("humanPlayer", 
									  "gameplay",
									  humanSprite,
									  eventManager);
		
		computerPlayer = new CyclePlayer("computerPlayer",
										 "gameplay",
										 computerSprite,
										 eventManager);
		
		humanPlayer.changeDirection("RIGHT");
		computerPlayer.changeDirection("LEFT");
		
		keyboardControl = new KeyboardControl(humanPlayer, game);
		keyboardControl = humanPlayer.addPlayerControl(keyboardControl);
		
		
		players = new SpriteGroup("players");
		players.add(humanPlayer);
		players.add(computerPlayer);
		
	}

	
	public void deployLevel(){
		
		levelBlocks = null;
		playerBlocks = null;
		currentPlayField = null;
		
		currentPlayField = levelManager.getCurrentPlayField(new File(LEVEL_FILEPATH+"/level"+currentLevel+".txt"));
		
		currentPlayField.setBackground(new ColorBackground(Color.WHITE));
				
		resetPlayers();
		
		currentPlayField.addGroup(players);
		currentPlayField.addGroup(overlayGroup);
		
		playerBlocks = new SpriteGroup("playerBlocks");
		levelBlocks = (currentPlayField.getGroup("levelSprites"));
		
		currentPlayField.addGroup(playerBlocks);
		
		currentPlayField.addCollisionGroup(players, players, new TronCollision());
		currentPlayField.addCollisionGroup(players, levelBlocks, new TronCollision());
		currentPlayField.addCollisionGroup(players, null, new TronBounds(currentPlayField.getBackground()));
		currentPlayField.addCollisionGroup(players, playerBlocks, new TronCollision());
						
	}
	
	public void resetPlayers(){
		
		humanPlayer.setLocation(humanPlayerStartingLocation.x, humanPlayerStartingLocation.y);
		computerPlayer.setLocation(computerPlayerStartingLocation.x, computerPlayerStartingLocation.y);
		
		humanPlayer.setActive(true);
		computerPlayer.setActive(true);
		
		blockQueue = new LinkedList<Sprite>();
		
	}
	
	public void checkLevelEnd(){
		if (!humanPlayer.isActive()){
			gameOver = true;
		} else if (!computerPlayer.isActive()){
			currentLevel++;
			currentScore.setStat(currentScore.getStat() + 50);
			deployLevel();
		}
	}
	
	public boolean checkGameOver(){
		return gameOver;
	}
	
	public boolean checkPaused(){
		return paused;
	}
	
	private void initializeOverlays() {
		
		OverlayTracker tracker = OverlayCreator.createOverlays("src/vooga/games/tronlegacy/resources/overlays.xml");
		
		overlayGroup = tracker.getOverlayGroups().get(0);
		currentScore = tracker.getStats().get(0);
				
	}
	

}
