package vooga.games.tronupdate.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.control.KeyboardControl;
import vooga.engine.control.Control;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;
import vooga.games.tronupdate.items.Player;
import vooga.games.tronupdate.util.Direction;
import vooga.games.tronupdate.util.GridSpace;
import vooga.games.tronupdate.util.Grid;
import vooga.games.tronupdate.util.Mode;


public class PlayState extends GameState{
	private Game game;
	private GameStateManager gameStateManager;
	private PlayField playField;
	private EventPool eventPool;
	private ArrayList<Player> players;
	private Player player1,player2;
	private GridSpace gridSpace;
	private Grid[][] grid;
	public static final int GRID_WIDTH=Resources.getInt("width")/Resources.getInt("playerimagewidth");
	public static final int GRID_HEIGHT=Resources.getInt("height")/Resources.getInt("playerimagewidth");
	public static final int PLAYER_IMAGE_WIDTH=Resources.getInt("playerimagewidth");
	public static final String PLAYER_CLASS="vooga.games.tronupdate.items.TronPlayer";
	
	public PlayState(Game g,GameStateManager gm){
		game = g;
		gameStateManager = gm;
		players = new ArrayList<Player>(2);
	}
	
	public void initialize(){
		initializePlayers();
		initializeControl();
		initializeBlocks();
		initializeEnvironment();
		initializeEvents();
		//game.playMusic(Resources.getSound("backgroundmusic"));
	}
	
	private void initializePlayers(){
		player1 = new Player(Resources.getImage("redlazer"),initPlayerX(1),initPlayerY(1),Direction.right,PLAYER_IMAGE_WIDTH);
		player2 = new Player(Resources.getImage("bluelazer"),initPlayerX(2),initPlayerY(2),Direction.left,PLAYER_IMAGE_WIDTH);
	}
	
	private void initializeControl(){
		Control firstPlayerControl = new KeyboardControl(player1,game);
		Control secondPlayerControl = new KeyboardControl(player2, game);
		initializeFirstControl(firstPlayerControl);
		initializeSecondControl(secondPlayerControl);
		player1.setControl(firstPlayerControl);
		player2.setControl(secondPlayerControl);
	}
	
	private void initializeBlocks(){
		gridSpace=new GridSpace(GRID_HEIGHT,GRID_WIDTH);
		grid = new Grid[GRID_HEIGHT][GRID_WIDTH];
	}
	
	private void initializeEnvironment(){
		playField = new PlayField();
		playField.addColorBackground(Color.WHITE);
		SpriteGroup group = new SpriteGroup("blocks");
		playField.addGroup(group);
	}
	
	private void initializeEvents(){
		eventPool = new EventPool();
	}
	
	private void initializeFirstControl(Control control){
		control.addInput(KeyEvent.VK_DOWN, "down", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_RIGHT, "right", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_UP, "up", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_LEFT, "left", PLAYER_CLASS);
	}
	
	private void initializeSecondControl(Control control){
		control.addInput(KeyEvent.VK_DOWN, "down", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_RIGHT, "right", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_UP, "up", PLAYER_CLASS);
		control.addInput(KeyEvent.VK_LEFT, "left", PLAYER_CLASS);
	}
	
	private int initPlayerX(int player){
		return (player==1)? (gridSpace.getTotalColumn() / 10): gridSpace.getTotalColumn()*9 / 10;
	}
	private int initPlayerY(int player){
		return gridSpace.getTotalRow() / 2;
	}
	
	private void drawBlocks(){	
	
	}
	
	private void updatePlayers(){
		player1.update();
		player2.update();
		grid[player1.getRow()][player1.getCol()].isTaken();
		grid[player1.getRow()][player1.getCol()].setPlayer(1);
		grid[player2.getRow()][player2.getCol()].isTaken();
		grid[player2.getRow()][player2.getCol()].setPlayer(2);
	}
	
	
	public void update(){
		updatePlayers();
		drawBlocks();
	}
	
	public void render(Graphics2D g) {
		playField.render(g);
	}
	
	 
}
