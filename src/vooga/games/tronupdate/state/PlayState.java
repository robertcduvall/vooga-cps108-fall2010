package vooga.games.tronupdate.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.control.KeyboardControl;
import vooga.engine.control.Control;
import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.tronupdate.events.InvokeHelpEvent;
import vooga.games.tronupdate.events.TronGamePauseEvent;
import vooga.games.tronupdate.items.Player;
//import vooga.games.tronupdate.util.AI;
import vooga.games.tronupdate.util.AI_0;
import vooga.games.tronupdate.util.Direction;
import vooga.games.tronupdate.util.GridSpace;
import vooga.games.tronupdate.util.Grid;
import vooga.games.tronupdate.util.Mode;
import vooga.games.tronupdate.util.RandomLayoutGenerator;

public class PlayState extends GameState {
	private Game game;
	private GameStateManager gameStateManager;
	private PlayField playField;
	private EventPool eventPool;
	private Player[] players;
	private SpriteGroup[] playerGroups;
	private SpriteGroup wallGroup;
	//private ArrayList<SpriteGroup> playerGroups;
	private int numPlayers = 2;
	private int initControl = 1;
	// temporary implementation
	private SpriteGroup playerGroup1, playerGroup2;
	private Player player1, player2;
	private Grid[][] grid;
	private RandomLayoutGenerator layoutGenerator;
	public static final int GRID_WIDTH = Resources.getInt("width")
			/ Resources.getInt("playerimagewidth");
	public static final int GRID_HEIGHT = Resources.getInt("height")
			/ Resources.getInt("playerimagewidth");
	public static final int PLAYER_IMAGE_WIDTH = Resources
			.getInt("playerimagewidth");
	public static final String PLAYER_CLASS = "vooga.games.tronupdate.items.Player";

	public PlayState(Game g, GameStateManager gm) {
		game = g;
		gameStateManager = gm;
	}

	@Override
	public void initialize() {
		initializePlayers();
		initializeControl();
		initializeEnvironment();
		initializeBlocks();
		initializeEvents();
		// game.playMusic(Resources.getSound("backgroundmusic"));
	}

	private void initializePlayers() {
		players = new Player[numPlayers];
		playerGroups = new SpriteGroup[numPlayers];
		players[0] = new Player(Resources.getImage("redlazer"), initPlayerY(1),
				initPlayerX(1), Direction.right, PLAYER_IMAGE_WIDTH,
				GRID_HEIGHT, GRID_WIDTH);
		players[1] = new Player(Resources.getImage("bluelazer"), initPlayerY(2),
				initPlayerX(2), Direction.left, PLAYER_IMAGE_WIDTH,
				GRID_HEIGHT, GRID_WIDTH);
	}

	private void initializeControl() {
		if(Mode.isMultiple()){	
			System.out.println("multiple am called");
			setKeyboardControl(0);
			setKeyboardControl(1);
		}
		else if(Mode.isSingle()){
			System.out.println("single am called");
			setKeyboardControl(1);
			setAIControl(0);
		}
		else if(Mode.isAI()){
			System.out.println("ai am called");
			setAIControl(0);
			setAIControl(1);
		}
	}
	
	private void setKeyboardControl(int index){
		Control playerControl = new KeyboardControl(players[index],game);
		if(index==0) playerControl = initializeFirstControl(playerControl);
		if(index==1) playerControl = initializeSecondControl(playerControl);
		players[index].setKeyboardControl(playerControl);
	}
	
	private void setAIControl(int index){
		players[index].setAsAI(true);
		AI_0 playerAI = new AI_0(players[index]);
		players[index].setAI(playerAI);
	}
	
	private void initializeBlocks() {
		layoutGenerator = new RandomLayoutGenerator();
		int[] startX = {initPlayerX(0),initPlayerY(1)};
		int[] startY = {initPlayerX(0),initPlayerY(1)};
		grid = layoutGenerator.generateGrid(GRID_HEIGHT, GRID_WIDTH,startX,startY);
		//grid = new Grid[GRID_HEIGHT][GRID_WIDTH];
		//for (int i = 0; i < GRID_HEIGHT; i++) {
		//	for (int j = 0; j < GRID_WIDTH; j++) {
		//		grid[i][j] = new Grid();
		//	}
		//}
		for (int i = 0; i < GRID_HEIGHT; i++) {
			for (int j = 0; j < GRID_WIDTH; j++) {
				if(grid[i][j].isWall())	drawBlock("wall","greenlazer",j,i);
			}
		}
	}
	
	private void initializeEnvironment() {
		playField = new PlayField();
		playField.addColorBackground(Color.WHITE);
		playField.setBackground(0);
		// SpriteGroup group = new SpriteGroup("blocks");
		// playField.addGroup(group);
		playerGroup1 = new SpriteGroup("first");
		playerGroup2 = new SpriteGroup("second");
		wallGroup = new SpriteGroup("wall");
		playField.addGroup(playerGroup1);
		playField.addGroup(playerGroup2);
		playField.addGroup(wallGroup);
	}

	public void initializeEvents() {
		eventPool = new EventPool();
		TronGamePauseEvent tronGamePauseEvent = new TronGamePauseEvent(game,gameStateManager); 
		InvokeHelpEvent invokeHelpEvent = new InvokeHelpEvent(game,gameStateManager,6);
		
		eventPool.addEvent(tronGamePauseEvent);
		eventPool.addEvent(invokeHelpEvent);
	}

	private Control initializeFirstControl(Control control) {
		control.addInput(KeyEvent.VK_S, "down", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_D, "right", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_W, "up", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_A, "left", PLAYER_CLASS);
		return control;
	}

	private Control initializeSecondControl(Control control) {
		control.addInput(KeyEvent.VK_DOWN, "down", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_RIGHT, "right", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_UP, "up", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_LEFT, "left", PLAYER_CLASS);
		return control;
	}

	private int initPlayerX(int player) {
		return (player == 1) ? (GRID_WIDTH / 10) : GRID_WIDTH * 9 / 10;
	}

	private int initPlayerY(int player) {
		return GRID_HEIGHT / 2;
	}

	private void updatePlayers() {
		int[] row = new int[numPlayers];
		int[] col = new int[numPlayers];
		for(int i=0;i<numPlayers;i++){ 
			players[i].update(grid);
		}
		for(int i=0;i<numPlayers;i++){
			row[i]=players[i].getRow();
			col[i]=players[i].getCol();
		}
		for(int i=0;i<numPlayers;i++){
			if(players[i].outOfBoundary()){
				gameStateManager.switchTo(gameStateManager.getGameState(Resources.getInt("GameOverState")));
				return;
			}
		}
		for(int i=0;i<numPlayers;i++){
			//System.out.println();
			grid[row[i]][col[i]].setTaken(true);
			grid[row[i]][col[i]].setPlayer(i);
		}
		//player1.update(grid);
		//player2.update(grid);
		//int row1 = player1.getRow();
		//int row2 = player2.getRow();
		//int col1 = player1.getCol();
		//int col2 = player2.getCol();
//		if((player1.outOfBoundary() || player2.outOfBoundary())){
//			gameStateManager.switchTo(gameStateManager.getGameState(Resources.getInt("GameOverState")));
//			return;
//		}
//		grid[row1][col1].setTaken(true);
//		grid[row1][col1].setPlayer(1);
//		grid[row2][col2].setTaken(true);	
//		grid[row2][col2].setPlayer(2);
		drawBlock("first","redlazer",col[0],row[0]);
		drawBlock("second","bluelazer",col[1],row[1]);
	}
	
	private void drawBlock(String player,String image,int col,int row){
		playField.getGroup(player).add(new BetterSprite(Resources.getImage(image),
						PLAYER_IMAGE_WIDTH * col, PLAYER_IMAGE_WIDTH * row));
	}

	public void update(long elapsedTime) {
		controlUpdate();
		updatePlayers();
		checkForCollision();
		eventPool.checkEvents();
	}
	
	private void controlUpdate(){
		if(initControl==1){
			initializeControl();
			initControl = 0;
		}
	}
	private void checkForCollision(){
		for(int i=0;i<GRID_HEIGHT;i++){
			for(int j=0;j<GRID_WIDTH;j++){
				if(grid[i][j].collides()){
					System.out.println(GRID_HEIGHT+" "+GRID_WIDTH);
					System.out.println("Collision happens at "+i+" "+j);
					game.playSound(Resources.getSound("explosionSound"));
					gameStateManager.switchTo(gameStateManager.getGameState(Resources.getInt("GameOverState")));
				}
			}
		}
	}

	public void render(Graphics2D g) {
		playField.render(g);
	}
}
