package vooga.games.tronupdate.state;


import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.factory.LevelManager;
import vooga.games.tron.GridSpace;
import vooga.games.tronupdate.items.TronPlayer;


public class PlayState extends GameState{
	
	private Game game;
	PlayField playField;
	private TronPlayer firstPlayer;
	private TronPlayer secondPlayer;
	private GridSpace gridSpace;	
	private Control firstPlayerControl;
	private BetterSprite[][] firstPlayerBlocks;
	private BetterSprite[][] secondPlayerBlocks;
	
	public static final int GRID_WIDTH=Resources.getInt("width")/Resources.getInt("playerimagewidth");
	public static final int GRID_HEIGHT=Resources.getInt("height")/Resources.getInt("playerimagewidth");
	public static final String PLAYER_CLASS="vooga.games.tronupdate.items.TronPlayer";
	public static final int PLAYER_IMAGE_WIDTH=Resources.getInt("playerimagewidth");
	
	public PlayState(Game game){
		//super();
		this.game = game;
		
	}

	@Override
	public void initialize() {
		initializeSprites();
		initializeControl();
		initializeBlocks();
		initializeEnvironment();

		iniEvents();
		//initializeOverlay();
	}
	
//	private void initLevel(){
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
	
	private void iniEvents() {
		// TODO Auto-generated method stub
		
	}

	private void initializeSprites() {
	    gridSpace=new GridSpace(GRID_WIDTH,GRID_HEIGHT);
		firstPlayer = new TronPlayer(Resources.getImage("redlazer") , gridSpace.getTotalRow() / 10, gridSpace.getTotalColumn() / 2 , gridSpace,PLAYER_IMAGE_WIDTH, "right"); ;
		
	}
	
	private void initializeEnvironment() {
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
		
		for(int i=0;i<firstPlayerBlocks.length;i++){
			for(int j=0;j<firstPlayerBlocks[0].length;j++)
		playField.getGroup(spritegroups[1]).add(firstPlayerBlocks[i][j]);
		}
//		for()
//			playField.getGroup(spritegroups[1]).add(rightPlayerBlocks);
	}

	private void initializeControl() {
		initFirstPlayerControls(firstPlayer);
		
	}


	private void initFirstPlayerControls(TronPlayer player){
	//	BetterSprite player1 = (BetterSprite) player; 
		firstPlayerControl = new KeyboardControl(player, game);
		firstPlayerControl.addInput(KeyEvent.VK_DOWN, "down", PLAYER_CLASS);
		firstPlayerControl.addInput(KeyEvent.VK_RIGHT, "right", PLAYER_CLASS);
		firstPlayerControl.addInput(KeyEvent.VK_UP, "up", PLAYER_CLASS);
		firstPlayerControl.addInput(KeyEvent.VK_LEFT, "left", PLAYER_CLASS);
		//myField.addControl("player1", playerControl);//?
	}
	
	private void initPlayer2Controls(BetterSprite player){
		Control playerControl = new KeyboardControl(player, game);
		playerControl.addInput(KeyEvent.VK_LEFT, "playerXDirectionMove", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_RIGHT, "rotateRight", PLAYER_CLASS);
		playerControl.addInput(KeyEvent.VK_UP, "thrust", PLAYER_CLASS);
//		playerControl.addInput(KeyEvent.VK_SPACE, "fire", "vooga.games.asteroids.sprites.Ship");
	//	myField.addControl("player2", playerControl); //??
	}
	
	/**
	 * fills in the grid spaces behind the players with collidable blocks. 
	 */
	public void buildBlockWall(){

		for(int i=0; i<firstPlayerBlocks.length; i++){
			for(int j=0; j<firstPlayerBlocks[0].length; j++){

				if(firstPlayer.blocks[i][j]==true){
					firstPlayerBlocks[i][j].setLocation(j*PLAYER_IMAGE_WIDTH, i*PLAYER_IMAGE_WIDTH);
				}
//				if(player2.blocks[i][j]==true){
//					player2Blocks[i][j].setLocation(j*PLAYER_IMAGE_WIDTH, i*PLAYER_IMAGE_WIDTH);
//				}
			}			
		}		
	}
	/**
	 * initialize the blocks  
	 */
	public void initializeBlocks(){
		    firstPlayerBlocks=new BetterSprite[GRID_WIDTH+2][GRID_HEIGHT+2];
			for(int i=0;i<firstPlayer.blocks.length;i++){
				for(int j=0;j<firstPlayer.blocks[0].length;j++){
					firstPlayerBlocks[i][j]=new BetterSprite(Resources.getImage("redlazer"),-50,-50);
					//blocksGroup.add(tronPlayerBlocksList.get(count)[i][j]);
				}
			}	
			
//			for(int i=0;i<player.blocks.length;i++){
//				for(int j=0;j<player.blocks[0].length;j++){
//					rightPlayerBlocks.get(count)[i][j]=new Sprite(getImage("resources/lazer"+count+".png"),-50,-50);
//					//blocksGroup.add(tronPlayerBlocksList.get(count)[i][j]);
//				}
//			}	
	}
	
	
	public void render(Graphics2D g) {
		playField.render(g);
		
	}
	
	
	public void update(long elapsedTime) {
		buildBlockWall();
		playField.update(elapsedTime);
		firstPlayerControl.update();
	}
	
	
}
