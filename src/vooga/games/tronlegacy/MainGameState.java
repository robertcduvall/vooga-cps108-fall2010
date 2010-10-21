package vooga.games.tronlegacy;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Sprite;

import vooga.engine.event.EventManager;
import vooga.engine.level.LevelManager;
import vooga.engine.player.control.KeyboardControl;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.level.Level;

public class MainGameState extends GameState {
	
	//note to self: the new levelmanager in the factory package will help this a lot once it is fixed
	
	private final static Point humanPlayerStartingLocation = new Point (48,240);
	private final static Point computerPlayerStartingLocation = new Point (432,240);
	private final static String LEVEL_FILEPATH = "src/vooga/games/tronlegacy/resources/levels/";
	
	private EventManager eventManager;
	private LevelManager levelManager;
	
	private CyclePlayer humanPlayer;
	private CyclePlayer computerPlayer;
	private KeyboardControl keyboardControl;
	
	private SpriteGroup players;
	private SpriteGroup levelBlocks;
	private PlayField currentPlayField;
	
	public void initialize() {

		eventManager = new EventManager();
		levelManager = new LevelManager();
		
		initializePlayers();
		initializeLevels(LEVEL_FILEPATH);
		
		levelManager.setMyCurrentLevel(1);
		
		deployLevel();
		
	}
	
	public void render(Graphics2D g){
		currentPlayField.render(g);
	}
	
	public void update(long elapsedTime){
		currentPlayField.update(elapsedTime);
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
		
		humanPlayer.addPlayerControl(keyboardControl);
		
		players = new SpriteGroup("players");
		players.add(humanPlayer);
		players.add(computerPlayer);
		
	}
	
	public void initializeLevels(String filepath){
	
		
		try {
			levelManager.addLevels(filepath);
		} catch (FileNotFoundException e) {
			System.out.println("Failed loading levels.");
		}
		
		
	}
	
	public void deployLevel(){
		
		levelBlocks = null;
		currentPlayField = null;
		levelBlocks = new SpriteGroup("levelBlocks");
		currentPlayField = convertLevelToPlayField(levelManager.getCurrentLevel());
		
		currentPlayField.addGroup(players);
		
		TronCollision collisionManager = new TronCollision();
		currentPlayField.addCollisionGroup(players, levelBlocks, collisionManager);
		
		
	}
	
	//this method will be deprecated as soon as the new level-playfield-factory stuff is fixed
	public PlayField convertLevelToPlayField(Level level){
		
		PlayField returnField = new PlayField();
		
		for (ArrayList<Sprite> spriteList : level.getSpritesList()){
			for (Sprite sprite : spriteList){
				levelBlocks.add(sprite);
			}
		}
		
		returnField.addGroup(levelBlocks);
		
		return returnField;
	}
	
}
