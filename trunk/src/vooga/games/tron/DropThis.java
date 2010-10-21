package vooga.games.tron;

/**
 * @author Meng Li,Brent Sodman,JiaQi Yan
 * This is the main class that implements the main body of the game.
 */

import vooga.engine.overlay.OverlayString;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.tron.bonus.SpeedBonus;
import vooga.games.tron.players.TronPlayer;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.background.ImageBackground;


// Java Foundation Classes (JFC)
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DropThis extends vooga.engine.core.Game {

	private static final int WIDTH=480;
	private static final int HEIGHT=480;
	private static final int PLAYER_IMAGE_WIDTH=5;
	private static final int FRAME_RATE=20;
	private static final int RANDOM_BLOCK_SIZE = 15;
	private static final int MINIMUM_RANDOM_BLOCKS = 5;
	private static final int RANDOM_LEVEL_BLOCKS = 3;
//comment this because we already created a property and put it inside
//	private static final String LEVEL_FILE = "src/vooga/games/tron/resources/levels.txt";

	private static final int GRID_WIDTH=WIDTH/PLAYER_IMAGE_WIDTH;
	private static final int GRID_HEIGHT=HEIGHT/PLAYER_IMAGE_WIDTH;

	ImageBackground imageBack;
	public ColorBackground backGround;

	GameStateManager gameStateManager;
	GameState startMenu,pause,play,gameover;
	boolean showStartMenu;

	TronLevelManager levelManager;

	TronPlayer player1;
	TronPlayer player2;
	List<TronPlayer> tronPlayerList;

	Sprite[][] player1Blocks;
	Sprite[][] player2Blocks;
	List<Sprite[][]> tronPlayerBlocksList;

	//Sprite[][] levelBlocks;

	boolean[][] levelBlocks;
	int row,col;

	SpriteGroup playerGroup1;
	SpriteGroup playerGroup2;
	SpriteGroup blocksGroup;
	SpriteGroup bonusGroup;
	SpriteGroup overlayGroup;

	OverlayString overlayString;

	CollisionManager PlayerAndEnemyCollision;
	CollisionManager PlayerAndEnemyCollision2;
	CollisionManager Player1AndBonusCollision;
	CollisionManager Player2AndBonusCollision;
	CollisionManager PlayerAndBoundariesCollision;
	CollisionManager PlayerAndBoundariesCollision2;

	PlayField playfield;

	MoveController moveController;
	MoveController2 moveController2;
	ComputerController computerController;

	GridSpace gridSpace;

	boolean isCollision;  
	boolean playervsplayer;
	Queue<SpeedBonus> bonusList;
	/**
	 * Initializing the resources, initializing the game
	 */
	public void initResources() {	
		setFPS(FRAME_RATE); 	
	//	ResourcesBeta.initialize(this, getResourcePath());
//		try {
//			ResourcesBeta.loadPropertiesFile("game.properties");
//		} catch (IOException e) {
//			System.out.println("Failed to load resources/game.properties");
//			e.printStackTrace();
//			System.exit(1);
//		}
		
		backGround=new ColorBackground(Color.WHITE,WIDTH,HEIGHT);
		gridSpace=new GridSpace(GRID_WIDTH,GRID_HEIGHT);
		showStartMenu=true;
		gameStateManager=new GameStateManager();

		startMenu = new GameState();
		play=new GameState();
		pause=new GameState();
		gameover=new GameState();
		gameStateManager.addGameState(startMenu);
		gameStateManager.addGameState(play);
		gameStateManager.addGameState(pause);
		gameStateManager.addGameState(gameover);
		
		if(showStartMenu){
			gameStateManager.switchTo(startMenu);
		}

		overlayString=new OverlayString("Game Over! Press space to continue");
		Font font = new Font("mine", Font.PLAIN, 22);
		overlayString.setFont(font);
		overlayString.setColor(Color.RED);
		
		
		File levelFile = new File(ResourcesBundle.getString("levelfile"));
		levelManager = new TronLevelManager(levelFile);

		initializeNewLevel();

		playMusic(ResourcesBundle.getString("music"));
	}
	/**
	 * Create a random level with random blocks
	 */

	public void initializeNewLevel() {
		overlayGroup=new SpriteGroup("Game Over");
		playerGroup1=new SpriteGroup("player1");
		playerGroup1.clear();
		playerGroup2=new SpriteGroup("player2");
		playerGroup2.clear();
		bonusGroup=new SpriteGroup("bonus");
		bonusGroup.clear();
		tronPlayerList=new ArrayList<TronPlayer>();

		player1=new TronPlayer(getImage(ResourcesBundle.getString("lazer_red")) , gridSpace.getTotalRow() / 10, gridSpace.getTotalColumn() / 2 , gridSpace,PLAYER_IMAGE_WIDTH, "right");       
		player2=new TronPlayer(getImage(ResourcesBundle.getString("lazer_blue")), gridSpace.getTotalRow() * 9 / 10, gridSpace.getTotalColumn() / 2, gridSpace,PLAYER_IMAGE_WIDTH, "left");


		tronPlayerList.add(player1);
		tronPlayerList.add(player2);

		row = player1.blocks.length;
		col = player1.blocks[0].length;
		levelBlocks = new boolean[row][col];
		for(int i=0;i<row;i++){
			Arrays.fill(levelBlocks[i],false);
		}


		tronPlayerBlocksList=new ArrayList<Sprite[][]>();
		player1Blocks=new Sprite[GRID_WIDTH+2][GRID_HEIGHT+2];
		player2Blocks=new Sprite[GRID_WIDTH+2][GRID_HEIGHT+2];

		tronPlayerBlocksList.add(player1Blocks);
		tronPlayerBlocksList.add(player2Blocks);

		bonusList=new LinkedList<SpeedBonus>();

		playerGroup1.add(player1);
		playerGroup2.add(player2);
		overlayGroup.add(overlayString);

		blocksGroup=new SpriteGroup("blocks");
		blocksGroup.clear();


		//uncomment these lines once the disparity between vooga.Sprite and golden.Sprite is solved

		//if (levelManager.outOfLevels()){
		createRandomLevelBlocks();
		//} else {
		//	for (Sprite tempSprite : levelManager.getCurrentLevel()){
		//		blocksGroup.add(tempSprite);
		//	}	
		//}

		createRandomBonus();
		for(SpeedBonus bonus:bonusList){
			bonus.setActive(false);
			bonusGroup.add(bonus);
		}

		moveController=new MoveController(this, player1);
		computerController=new ComputerController(this, player2);
		moveController2=new MoveController2(this,player2);
		
		initializeBlocks();
		initializeCollisionManagers();
		initializePlayfield();

		player1.setActive(true);
		player2.setActive(true);
		isCollision=false;

	}


	/**
	 * create random bonuses for the level
	 */
	public void createRandomBonus(){
		for (int i = 0; i < 5; i++){		
			int randomX = (int)Math.ceil(Math.random()* GRID_WIDTH);
			int randomY = (int)Math.ceil(Math.random()* GRID_HEIGHT);    	
			bonusList.add(new SpeedBonus(getImage(ResourcesBundle.getString("lazer_yellow")), (randomX),(randomY),PLAYER_IMAGE_WIDTH));	
		}

	}
	/**
	 * initialize the blocks  
	 */
	public void initializeBlocks(){
		int count=0;
		for(TronPlayer player:tronPlayerList){	
			for(int i=0;i<player.blocks.length;i++){
				for(int j=0;j<player.blocks[0].length;j++){
					tronPlayerBlocksList.get(count)[i][j]=new Sprite(getImage("resources/lazer"+count+".png"),-50,-50);
					blocksGroup.add(tronPlayerBlocksList.get(count)[i][j]);
				}
			}	
			count++;
		}	
	}
	/**
	 * create random obstacle blocks for the level
	 */
	public void createRandomLevelBlocks(){	
		int randomNumberOfBlocks = (int)Math.ceil(Math.random() * RANDOM_LEVEL_BLOCKS) + MINIMUM_RANDOM_BLOCKS;

		for (int i = 0; i < randomNumberOfBlocks; i++){
			int randomRow = (int)Math.floor(Math.random()*row);
			int randomCol = (int)Math.floor(Math.random()*col);
			int randomWidth = (int)Math.ceil(Math.random()*RANDOM_BLOCK_SIZE);
			int randomHeight = (int)Math.ceil(Math.random()*RANDOM_BLOCK_SIZE);



			randomHeight = (randomRow+randomHeight>=row)? row-randomRow:randomHeight;
			randomWidth = (randomCol+randomWidth>=col)? col-randomCol:randomWidth;

			for(int index1=0;index1<randomHeight;index1++){
				for(int index2 = 0;index2<randomWidth;index2++){
					blocksGroup.add(new Sprite(getImage(ResourcesBundle.getString("lazer_green")),(randomCol+index1)*PLAYER_IMAGE_WIDTH,(randomRow+index2)*PLAYER_IMAGE_WIDTH));
					levelBlocks[randomRow+index1][randomCol+index2] = true;
				}		
			}
		}	
	}
	/**
	 * Initialize the playfield
	 */
	public void initializePlayfield(){
		playfield=new PlayField();
		//playfield.addGroup(overlayGroup);
		playfield.addGroup(playerGroup1);
		playfield.addGroup(playerGroup2);
		playfield.addGroup(blocksGroup);
		playfield.addGroup(bonusGroup);
		playfield.addCollisionGroup(playerGroup1,blocksGroup,PlayerAndEnemyCollision);
		playfield.addCollisionGroup(playerGroup2,blocksGroup,PlayerAndEnemyCollision2);
		playfield.addCollisionGroup(playerGroup1, null, PlayerAndBoundariesCollision);
		playfield.addCollisionGroup(playerGroup2, null, PlayerAndBoundariesCollision2);
		playfield.addCollisionGroup(playerGroup1, bonusGroup, Player1AndBonusCollision);
		playfield.addCollisionGroup(playerGroup2, bonusGroup, Player2AndBonusCollision);

	}
	/**
	 * Initialize the collision managers
	 */
	public void initializeCollisionManagers(){

		PlayerAndEnemyCollision=new PlayerAndEnemyCollision();   
		PlayerAndEnemyCollision.setCollisionGroup(playerGroup1, blocksGroup);
		PlayerAndEnemyCollision2=new PlayerAndEnemyCollision();  
		PlayerAndEnemyCollision2.setCollisionGroup(playerGroup2, blocksGroup);	
		Player1AndBonusCollision=new Player1AndBonusCollision(player1);
		Player1AndBonusCollision.setCollisionGroup(playerGroup1, bonusGroup);
		Player2AndBonusCollision=new Player2AndBonusCollision(player2);
		Player2AndBonusCollision.setCollisionGroup(playerGroup2, bonusGroup);
		PlayerAndBoundariesCollision=new PlayerAndBoundariesCollision(0,0,WIDTH,HEIGHT);
		PlayerAndBoundariesCollision.setCollisionGroup(playerGroup1, null);
		PlayerAndBoundariesCollision2=new PlayerAndBoundariesCollision(0,0,WIDTH,HEIGHT);
		PlayerAndBoundariesCollision2.setCollisionGroup(playerGroup2, null);

	}
	/**
	 * Update the frames
	 */


	public void update(long elapsedTime) {
		if(gameover.isActive()){
			overlayString.setLocation(WIDTH/7,HEIGHT/2);
			if(keyPressed(KeyEvent.VK_SPACE)){
				gameStateManager.switchTo(play);
			}
		}
		if(startMenu.isActive()){
			playfield.setBackground(new ImageBackground(getImage(ResourcesBundle.getString("gamestart_image"))));
			if(keyPressed(KeyEvent.VK_A)){
				playervsplayer=true;
				gameStateManager.switchTo(play);	
			}
			else if(keyPressed(KeyEvent.VK_B)){	
				playervsplayer=false;
				gameStateManager.switchTo(play);	
			}
		}

		else if(pause.isActive()){
			playfield.setBackground(new ImageBackground(getImage(ResourcesBundle.getString("gamepause_image"))));
			if(keyPressed(KeyEvent.VK_P)){
				gameStateManager.switchTo(play);
			}
		}

		else if(play.isActive()){
			overlayString.setLocation(-100,-100);
			if(keyPressed(KeyEvent.VK_P)){
				gameStateManager.switchTo(pause);
			}
			else{
				PlayerAndBoundariesCollision2.checkCollision();
				PlayerAndBoundariesCollision.checkCollision();
				PlayerAndEnemyCollision.checkCollision();
				PlayerAndEnemyCollision2.checkCollision();
				Player1AndBonusCollision.checkCollision();
				Player2AndBonusCollision.checkCollision();
				if ((!player1.isActive() || !player2.isActive())){
					afterCollision();
				}

				if(Math.random()<0.01&&!bonusList.isEmpty()){	
					bonusList.poll().setActive(true);
				}

				moveController.checkInput(); 
			    if(playervsplayer){
				moveController2.checkInput(); 
				}
				else{			
				computerController.aiUpdate(tronPlayerList,levelBlocks);
				}
				if(!isCollision){
					buildBlockWall();
					player1.setLocation(player1.playerXDirectionMove(),player1.playerYDirectionMove());//running without user control   	
					player2.setLocation(player2.playerXDirectionMove(),player2.playerYDirectionMove());//running without user control  
				} 
			}
		}

	}

	/**
	 * fills in the grid spaces behind the players with collidable blocks. 
	 */
	public void buildBlockWall(){

		for(int i=0; i<player1Blocks.length; i++){
			for(int j=0; j<player1Blocks[0].length; j++){

				if(player1.blocks[i][j]==true){
					player1Blocks[i][j].setLocation(j*PLAYER_IMAGE_WIDTH, i*PLAYER_IMAGE_WIDTH);
				}
				if(player2.blocks[i][j]==true){
					player2Blocks[i][j].setLocation(j*PLAYER_IMAGE_WIDTH, i*PLAYER_IMAGE_WIDTH);
				}
			}			
		}		
	}
	/**
	 * handles what happens after a collision happens (game over phase)
	 */
	public void afterCollision(){
		gameStateManager.switchTo(gameover);
		isCollision=true;
		playSound(ResourcesBundle.getString("sound"));
		System.out.println("Game Over!");
		long currentTime=System.currentTimeMillis();
		while(System.currentTimeMillis()-currentTime<2000){ //delay between levels		
		}
		initializeNewLevel();
	}
	/**
	 * render the graphics
	 */
	public void render(Graphics2D g) {
		if (startMenu.isActive()) {
			playfield.getBackground().render(g);
		}
		else if(play.isActive()){
			playfield.setBackground(backGround);
			backGround.render(g);
			playfield.render(g);
		}	
		else if(pause.isActive()){
			playfield.getBackground().render(g);
		}
		else if(gameover.isActive()){
			playfield.setBackground(backGround);
			playfield.addGroup(overlayGroup);
			playfield.getBackground().render(g);
			playfield.getGroup("Game Over").render(g);
		}
	}

	
//	private String getResourcePath(){
//		String gamePath = getClass().getPackage().toString();
//		String defaultPath = "src/" + gamePath.substring(8, gamePath.length())+"/resources/";
//		return defaultPath.replace('.', '/');
//	}
	
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DropThis(), new Dimension(WIDTH,HEIGHT), false);
		game.start();
	}




}


