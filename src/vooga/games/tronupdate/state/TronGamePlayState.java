package vooga.games.tronupdate.state;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.golden.gamedev.object.SpriteGroup;


import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.core.BetterSprite;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.tronupdate.util.GridSpace;
import vooga.games.tron.Player1AndBonusCollision;
import vooga.games.tron.Player2AndBonusCollision;
import vooga.games.tronupdate.collisions.PlayerAndBoundariesCollision;
import vooga.games.tron.PlayerAndEnemyCollision;
import vooga.games.tronupdate.collisions.PlayerAndBonusCollision;
import vooga.games.tronupdate.events.TronGamePauseEvent;
import vooga.games.tronupdate.items.TronPlayer;
import vooga.games.tronupdate.util.Direction;
import vooga.games.zombieland.collision.BZCollisionManager;
import vooga.games.zombieland.collision.HICollisionManager;
import vooga.games.zombieland.collision.PZCollisionManager;
import vooga.games.zombieland.collision.WallBoundManager;


public class TronGamePlayState extends GameState{
	
	private Game game;
	private GameStateManager stateManager;
	private GameState tronGamePauseState;
	private PlayField playField;
	private TronPlayer firstPlayer;
	private TronPlayer secondPlayer;
	private GridSpace gridSpace;	
	private Control firstPlayerControl;
	private Control secondPlayerControl;
	private BetterSprite[][] firstPlayerBlocks;
	private BetterSprite[][] secondPlayerBlocks;
	
	public static final int GRID_WIDTH=Resources.getInt("width")/Resources.getInt("playerimagewidth");
	public static final int GRID_HEIGHT=Resources.getInt("height")/Resources.getInt("playerimagewidth");
	public static final String PLAYER_CLASS="vooga.games.tronupdate.items.TronPlayer";
	public static final int PLAYER_IMAGE_WIDTH=Resources.getInt("playerimagewidth");
	
	public TronGamePlayState(Game game){
		//super();
		this(game,null,null);
		
	}
	
	public TronGamePlayState(Game game, GameStateManager stateManager, GameState tronGamePauseState){
		//super();
		this.game = game;
		this.stateManager=stateManager;
		this.tronGamePauseState=tronGamePauseState;
	}

	@Override
	public void initialize() {
		initializeSprites();
		initializeControl();
		initializeBlocks();
		initializeEnvironment();
		initializeCollision();
		//initializeOverlay();
		initializeEvents();
		
	}
	
	public void initializeCollision() {
		PlayerAndEnemyCollision playerEnemyCollision=new PlayerAndEnemyCollision();   
		PlayerAndBoundariesCollision playerBoundariesCollision=new PlayerAndBoundariesCollision(0,0,Resources.getInt("width"),Resources.getInt("height"));
		PlayerAndBonusCollision playerBonusCollision=new PlayerAndBonusCollision();		
		
		playField.addCollisionGroup(playField.getGroup("player"), playField.getGroup("block"), playerEnemyCollision);
		playField.addCollisionGroup(playField.getGroup("player"), playField.getGroup("player"), playerBoundariesCollision);
		playField.addCollisionGroup(playField.getGroup("player"), playField.getGroup("bonus"), playerBonusCollision);
		
		
	}

//	private void initializeLevel(){
//		
//		TronPlayer player =(TronPlayer)(getGroup("playerSpriteGroup").getSprites()[0]);
//		//TronPlayer leftTronPlayer= new Tronplayer();
//		initPlayer1Controls(player);
//	//	BetterSprite player2 =(BetterSprite)(getGroup("playerSpriteGroup").getSprites()[1]);
//		//initPlayer2Controls(player2);
//		//BetterSprite playerrandom = new BetterSprite();
//		
//
//	}
	
	public void initializeEvents() {
		EventPool eventPool = new EventPool();
	    playField.addEventPool(eventPool);
	    TronGamePauseEvent tronGamePauseEvent = new TronGamePauseEvent(game,tronGamePauseState,stateManager); 
	    playField.addEvent(tronGamePauseEvent);
	    //TO-DO
	}

	public void initializeSprites() {
	    gridSpace=new GridSpace(GRID_WIDTH,GRID_HEIGHT);
		firstPlayer = new TronPlayer(Resources.getImage("redlazer") , initialFirstPlayerXPosition(), initialFirstPlayerYPosition() ,PLAYER_IMAGE_WIDTH, Direction.right);
		secondPlayer = new TronPlayer(Resources.getImage("bluelazer") , initialSecondPlayerXPosition(), initialSecondPlayerYPosition() ,PLAYER_IMAGE_WIDTH, Direction.left);
	}
	
	public int initialFirstPlayerXPosition(){
		return gridSpace.getTotalRow() / 10;
	}
	
	public int initialFirstPlayerYPosition(){
		return gridSpace.getTotalColumn() / 2;
	}
	
	public int initialSecondPlayerXPosition(){
		return gridSpace.getTotalRow()*9 / 10;
	}
	
	public int initialSecondPlayerYPosition(){
		return gridSpace.getTotalColumn() / 2;
	}
	
	public void initializeEnvironment() {
		playField = new PlayField();
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
		secondPlayerControl.addInput(KeyEvent.VK_S, "down", PLAYER_CLASS);
		secondPlayerControl.addInput(KeyEvent.VK_D, "right", PLAYER_CLASS);
		secondPlayerControl.addInput(KeyEvent.VK_W, "up", PLAYER_CLASS);
		secondPlayerControl.addInput(KeyEvent.VK_A, "left", PLAYER_CLASS);
//		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "vooga.games.asteroids.sprites.Ship");
	//	myField.addControl("player2", playerControl); //??
	}
	
	/**
	 * fills in the grid spaces behind the players with collidable blocks. 
	 */
	public void buildBlockWall(){

		for(int i=0; i<firstPlayerBlocks.length; i++){
			for(int j=0; j<firstPlayerBlocks[0].length; j++){

				if(firstPlayer.grid.isGridTaken(i, j)){
					firstPlayerBlocks[i][j].setLocation(j*PLAYER_IMAGE_WIDTH, i*PLAYER_IMAGE_WIDTH);
				}
				if(secondPlayer.grid.isGridTaken(i, j)){
					secondPlayerBlocks[i][j].setLocation(j*PLAYER_IMAGE_WIDTH, i*PLAYER_IMAGE_WIDTH);
				}
			}			
		}		
	}
	/**
	 * initialize the blocks  
	 */
	public void initializeBlocks(){
		  //  firstPlayerBlocks=new BetterSprite[GRID_WIDTH+2][GRID_HEIGHT+2];
		firstPlayerBlocks=new BetterSprite[GRID_WIDTH][GRID_HEIGHT];
			for(int i=0;i<firstPlayer.grid.getTotalRow();i++){
				for(int j=0;j<firstPlayer.grid.getTotalColumn();j++){
					firstPlayerBlocks[i][j]=new BetterSprite(Resources.getImage("redlazer"),-50,-50);
					//blocksGroup.add(tronPlayerBlocksList.get(count)[i][j]);
				}
			}	
			secondPlayerBlocks=new BetterSprite[GRID_WIDTH][GRID_HEIGHT];
			for(int i=0;i<secondPlayer.grid.getTotalRow();i++){
				for(int j=0;j<secondPlayer.grid.getTotalColumn();j++){
					secondPlayerBlocks[i][j]=new BetterSprite(Resources.getImage("bluelazer"),-50,-50);
					//blocksGroup.add(tronPlayerBlocksList.get(count)[i][j]);
				}
			}	
	}
	
	
	public void render(Graphics2D g) {
		playField.render(g);
		
	}
	
	
	public void update(long elapsedTime) {
		buildBlockWall();
		playField.update(elapsedTime);
		firstPlayerControl.update();
		secondPlayerControl.update();
	}
	
	
}
