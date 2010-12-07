package vooga.games.tronupdate.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.core.BetterSprite;
import vooga.engine.event.EventPool;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.tronupdate.util.GridSpace;
import vooga.games.tronupdate.util.Mode;
import vooga.games.tronupdate.collisions.PlayerAndBoundariesCollision;
import vooga.games.tronupdate.collisions.PlayerAndEnemyCollision;

import vooga.games.tronupdate.collisions.PlayerAndBonusCollision;
import vooga.games.tronupdate.events.TronGamePauseEvent;
import vooga.games.tronupdate.events.InvokeHelpEvent;
import vooga.games.tronupdate.items.Bonus;
import vooga.games.tronupdate.items.SpeedUpBonus;
import vooga.games.tronupdate.items.TronPlayer;
import vooga.games.tronupdate.util.Direction;
import vooga.games.tronupdate.util.AI;

public class TronGamePlayState extends GameState{
	private Game game;
	private GameStateManager stateManager;
	private PlayField playField;
	private TronPlayer firstPlayer;
	private TronPlayer secondPlayer;
	private ArrayList<TronPlayer> players;
	private GridSpace gridSpace;	
	private Control firstPlayerControl;
	private Control secondPlayerControl;
	private BetterSprite[][] firstPlayerBlocks;
	private BetterSprite[][] secondPlayerBlocks;
	private boolean[][] blocksTaken;
	private EventPool eventPool; 
	private Queue<SpeedUpBonus> bonusList;
	private SpeedUpBonus prev=null;
	private AI ai;
	private int currentLevel=0;//0,1,2
	private int totalLevel=3;	
	public Timer timer = new Timer(50);
	private boolean[][] levelBlocks;
	private int totalRow,totalCol;
	private int i=0;
	private int j=0;
	public static final int GRID_WIDTH=Resources.getInt("width")/Resources.getInt("playerimagewidth");
	public static final int GRID_HEIGHT=Resources.getInt("height")/Resources.getInt("playerimagewidth");
	public static final String PLAYER_CLASS="vooga.games.tronupdate.items.TronPlayer";
	public static final int PLAYER_IMAGE_WIDTH=Resources.getInt("playerimagewidth");

	private static int numBlocks = 5;//15;
	private static int minumumBlocks = 5;
	private static int blockSize = 3;

	public TronGamePlayState(Game game){
		this(game,null);
	}

	public TronGamePlayState(Game game, GameStateManager stateManager){
		//super();
		this.game = game;
		this.stateManager=stateManager;
	}
	public int getCurrentLevel(){
		return currentLevel;
	}
	public void setLevel(int level){
		currentLevel=level;
	}
	public int getTotalLevel(){
		return totalLevel;
	}
	
	@Override
	public void initialize() {
		initializeSprites();
		initializeControl();
		initializeBlocks();
		initializeEnvironment();
		initializeCollision();
		initializeOverlay();
		initializeEvents();
		//game.playMusic(Resources.getSound("backgroundmusic"));
	}

	public void initializeOverlay(){
		OverlayCreator.setGame(game);
		OverlayTracker tracker = OverlayCreator.createOverlays(Resources.getString("overlayFileURL"));
		playField.addGroup(tracker.getOverlayGroup("Play"));
	}

	public void initializeCollision() {
		PlayerAndEnemyCollision playerEnemyCollision=new PlayerAndEnemyCollision(game,stateManager);   
		PlayerAndBoundariesCollision playerBoundariesCollision=new PlayerAndBoundariesCollision(0,0,Resources.getInt("width"),Resources.getInt("height"),game,stateManager);
		PlayerAndBonusCollision playerBonusCollision=new PlayerAndBonusCollision(game);		

		playField.addCollisionGroup(playField.getGroup("player"), playField.getGroup("block"), playerEnemyCollision);
		playField.addCollisionGroup(playField.getGroup("player"), playField.getGroup("player"), playerBoundariesCollision);
		playField.addCollisionGroup(playField.getGroup("player"), playField.getGroup("bonus"), playerBonusCollision);
	}

	public void initializeEvents() {
		eventPool = new EventPool();
		TronGamePauseEvent tronGamePauseEvent = new TronGamePauseEvent(game,stateManager); 
		InvokeHelpEvent invokeHelpEvent = new InvokeHelpEvent(game,stateManager,Resources.getInt("PlayState"));
		
		eventPool.addEvent(tronGamePauseEvent);
		eventPool.addEvent(invokeHelpEvent);
	}

	
	public void initializeSprites() {
		gridSpace=new GridSpace(GRID_HEIGHT,GRID_WIDTH);
		firstPlayer = new TronPlayer(Resources.getImage("redlazer") , initialFirstPlayerXPosition(), initialFirstPlayerYPosition() ,PLAYER_IMAGE_WIDTH, Direction.right);
		secondPlayer = new TronPlayer(Resources.getImage("bluelazer") , initialSecondPlayerXPosition(), initialSecondPlayerYPosition() ,PLAYER_IMAGE_WIDTH, Direction.left);
	}

	public int initialFirstPlayerXPosition(){
		return gridSpace.getTotalColumn() / 10;
	}

	public int initialFirstPlayerYPosition(){
		return gridSpace.getTotalRow() / 2;
	}

	public int initialSecondPlayerXPosition(){
		return gridSpace.getTotalColumn()*9 / 10;
	}

	public int initialSecondPlayerYPosition(){
		return gridSpace.getTotalRow() / 2;
	}

	public void initializeEnvironment() {
		i=0;
		j=0;
		playField = new PlayField();
		playField.addColorBackground(Color.WHITE);
		playField.setBackground(0);
		String spritegroupslist = Resources.getString("gameitemlist");
		String delim = Resources.getString("delim");
		String[] spritegroups = spritegroupslist.split(delim);
		for(int i = 0; i < spritegroups.length; i++)
		{
			SpriteGroup currentGroup = new SpriteGroup(spritegroups[i]);
			playField.addGroup(currentGroup);
		}
		playField.getGroup(spritegroups[0]).add(firstPlayer);
		playField.getGroup(spritegroups[0]).add(secondPlayer);

		for(int i=0;i<firstPlayerBlocks.length;i++){
			for(int j=0;j<firstPlayerBlocks[0].length;j++)
				playField.getGroup(spritegroups[1]).add(firstPlayerBlocks[i][j]);
		}
		for(int i=0;i<secondPlayerBlocks.length;i++){
			for(int j=0;j<secondPlayerBlocks[0].length;j++)
				playField.getGroup(spritegroups[1]).add(secondPlayerBlocks[i][j]);
		}	
		createRandomBonus();
		for(SpeedUpBonus bonus:bonusList){
			bonus.setActive(false);
			playField.getGroup(spritegroups[2]).add(bonus);
		}
		if(currentLevel==0){
			createLevelBlocks(numBlocks,minumumBlocks,blockSize);
			numBlocks=15;
		}
		else if (currentLevel==1){
			createLevelBlocks(numBlocks,minumumBlocks,blockSize);
			numBlocks=10;
		}
		else if (currentLevel==2){
			
		}
	}

	/**
	 * create random obstacle blocks for the level
	 */
	public void createLevelBlocks(int numBlocks,int minumumBlocks,int blockSize){	

		int randomNumberOfBlocks = (int)Math.ceil(Math.random() * numBlocks) + minumumBlocks;

		for (int i = 0; i < randomNumberOfBlocks; i++){
			int randomRow = (int)Math.floor(Math.random()*totalRow);
			int randomCol = (int)Math.floor(Math.random()*totalCol);
			int randomWidth = (int)Math.ceil(Math.random()*blockSize);
			int randomHeight = (int)Math.ceil(Math.random()*blockSize);

			randomHeight = (randomRow+randomHeight>=totalRow)? totalRow-randomRow:randomHeight;
			randomWidth = (randomCol+randomWidth>=totalCol)? totalCol-randomCol:randomWidth;

			for(int index1=0;index1<randomHeight;index1++){
				for(int index2 = 0;index2<randomWidth;index2++){
					playField.getGroup(Resources.getString("block")).add(new BetterSprite(Resources.getImage("greenlazer"),(randomCol+index1)*PLAYER_IMAGE_WIDTH,(randomRow+index2)*PLAYER_IMAGE_WIDTH));
					//	blocksGroup.add(new Sprite(Resources.getImage("lazer_green"),(randomCol+index1)*PLAYER_IMAGE_WIDTH,(randomRow+index2)*PLAYER_IMAGE_WIDTH));
					levelBlocks[randomRow+index1][randomCol+index2] = true;
				}		
			}
		}	
	}

	/**
	 * create random bonuses for the level
	 */
	public void createRandomBonus(){
		bonusList=new LinkedList<SpeedUpBonus>();

		for (int i = 0; i < 20; i++){		
			int randomX = (int)Math.ceil(Math.random()* GRID_WIDTH);
			int randomY = (int)Math.ceil(Math.random()* GRID_HEIGHT);    	
			bonusList.add(new SpeedUpBonus(Resources.getImage("yellowbonus"), randomY, randomX,PLAYER_IMAGE_WIDTH));	
		}
	}

	public void initializeControl() {
		initFirstPlayerControls(firstPlayer);
		initSecondPlayerControls(secondPlayer);
	}

	public void initFirstPlayerControls(TronPlayer player){
		firstPlayerControl = new KeyboardControl(player, game);
		firstPlayerControl.addInput(KeyEvent.VK_DOWN, "down", PLAYER_CLASS);
		firstPlayerControl.addInput(KeyEvent.VK_RIGHT, "right", PLAYER_CLASS);
		firstPlayerControl.addInput(KeyEvent.VK_UP, "up", PLAYER_CLASS);
		firstPlayerControl.addInput(KeyEvent.VK_LEFT, "left", PLAYER_CLASS);
	}

	public void initSecondPlayerControls(TronPlayer player){
		secondPlayerControl = new KeyboardControl(player, game);
		ai = new AI_0(secondPlayer);
		if(Mode.isSingle()){
			secondPlayer.setAsAI(true);
			//ai.setPlayer(secondPlayer);
			//secondPlayerControl = new KeyboardControl(player, game);	
		}
		else{
			secondPlayerControl.addInput(KeyEvent.VK_S, "down", PLAYER_CLASS);
			secondPlayerControl.addInput(KeyEvent.VK_D, "right", PLAYER_CLASS);
			secondPlayerControl.addInput(KeyEvent.VK_W, "up", PLAYER_CLASS);
			secondPlayerControl.addInput(KeyEvent.VK_A, "left", PLAYER_CLASS);
			secondPlayer.setAsAI(false);
		}
	}

	/**
	 * fills in the grid spaces behind the players with collidable blocks. 
	 */
	public void updateBlocks(){
		for(int i=0; i<firstPlayerBlocks.length; i++){
			for(int j=0; j<firstPlayerBlocks[0].length; j++){

				if(firstPlayer.grid.isGridTaken(i, j)){
					firstPlayerBlocks[i][j].setLocation(j*PLAYER_IMAGE_WIDTH, i*PLAYER_IMAGE_WIDTH);
					blocksTaken[i][j]=true;
				}
				if(secondPlayer.grid.isGridTaken(i, j)){
					secondPlayerBlocks[i][j].setLocation(j*PLAYER_IMAGE_WIDTH, i*PLAYER_IMAGE_WIDTH);
					blocksTaken[i][j]=true;
				}
			}			
		}
	//playField.getGroup(Resources.getString("block")).add(new BetterSprite(Resources.getImage("greenlazer"),i*PLAYER_IMAGE_WIDTH,j*PLAYER_IMAGE_WIDTH));
	}
	/**
	 * initialize the blocks  
	 */
	public void initializeBlocks(){
		firstPlayerBlocks=new BetterSprite[GRID_HEIGHT][GRID_WIDTH];
		for(int i=0;i<firstPlayer.grid.getTotalRow();i++){
			for(int j=0;j<firstPlayer.grid.getTotalColumn();j++){
				firstPlayerBlocks[i][j]=new BetterSprite(Resources.getImage("redlazer"),-50,-50);
			}
		}	
		secondPlayerBlocks=new BetterSprite[GRID_HEIGHT][GRID_WIDTH];
		for(int i=0;i<secondPlayer.grid.getTotalRow();i++){
			for(int j=0;j<secondPlayer.grid.getTotalColumn();j++){
				secondPlayerBlocks[i][j]=new BetterSprite(Resources.getImage("bluelazer"),-50,-50);
			}
		}	
		blocksTaken = new boolean[GRID_HEIGHT][GRID_WIDTH];
		for(int i=0;i<GRID_HEIGHT;i++){
			Arrays.fill(blocksTaken[i],false);
		}

		totalRow = firstPlayer.grid.getTotalRow();
		totalCol = firstPlayer.grid.getTotalColumn();
		levelBlocks = new boolean[totalRow][totalCol];
		for(int i=0;i<totalRow;i++){
			Arrays.fill(levelBlocks[i],false);
		}
	}
	
	public void render(Graphics2D g) {
		playField.render(g);
	}

	public void update(long elapsedTime) {
		updateBlocks();
		if (timer.action(elapsedTime)){//&&currentLevel==1) {		
			if(i>=totalRow){
				if(j>=totalCol){
					j=-1;
				}
				j++;
				i=-1;
			}
			i++;		
		}

		if(Math.random()<0.04&&!bonusList.isEmpty()){
			spawnBonus();
		}
		playField.update(elapsedTime);
		eventPool.checkEvents();
		firstPlayerControl.update();
		if(Mode.isSingle()) ai.aiUpdate(blocksTaken);
		if(Mode.isMultiple()) secondPlayerControl.update();
	}

	public void spawnBonus() {
		if(prev==null||prev.isConsumed()){
			SpeedUpBonus b=bonusList.poll();
			prev=b;
			b.setActive(true);
		}
	}
}
