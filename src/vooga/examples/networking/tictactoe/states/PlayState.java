package vooga.examples.networking.tictactoe.states;

import java.awt.event.MouseEvent;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.factory.LevelParser;
import vooga.examples.networking.tictactoe.Move;
import vooga.examples.networking.tictactoe.events.GameLostEvent;
import vooga.examples.networking.tictactoe.events.GameTiedEvent;
import vooga.examples.networking.tictactoe.events.GameWonEvent;
import vooga.engine.networking.client.ClientConnection;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.control.*;

/**
 * The main GameState for the TicTacToe game. Handles placing pieces and dealing with the network to get opposing moves.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class PlayState extends GameState{
	private Game game;
	private PlayField field;
	private LevelManager levelManager;
	private EventPool eventPool;
	private GameOverState gameWonState;
	private GameOverState youLostState;
	private GameOverState theyQuitState;
	private GameOverState tieState;
	private TheirTurnState theirTurnState;
	private ClientConnection connection;
	private boolean myTurn;
	private Move oMove;

	/**
	 * Constructor for the PlayState.
	 * 
	 * @param game Game for the GameStateManager and other convenience methods
	 * @param levelManager the LevelManager for making the other PlayStates
	 * @param connection the ClientConnection through which all communication with the server is done
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public PlayState(Game game, LevelManager levelManager, ClientConnection connection){
		super(connection);
		this.game = game;
		this.levelManager = levelManager;
		this.connection = connection;
	}
	
	/**
	 * Load the first and only level, set up the Control object for clicking to add pieces, and create all the different GameStates and Events
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void initialize() {
		field = levelManager.loadFirstLevel();
		initControls();
		this.addPlayField(field);
		initLevel();
	}

	/**
	 * Set up Control object that handles adding a piece when the user clicks in a square.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void initControls(){
		Control gameControl = new MouseControl(this, game);
		gameControl.addInput(MouseEvent.BUTTON1, "addPiece", Resources.getString("playStateDirectory"));
		field.addControl("game", gameControl);
	}

	/**
	 * Sets up the gameWon, wait, youLost, theyQuit, and tie GameStates as well as the GameWon, GameLost, and GameTied events.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void initLevel(){
		LevelParser levelParser = new LevelParser();
		PlayField gameWonField = levelParser.getPlayfield(Resources.getString("gameWonXml"), game);
		gameWonState = new GameOverState(gameWonField);
		game.getGameStateManager().addGameState(gameWonState);
		PlayField waitField = levelParser.getPlayfield(Resources.getString("waitForTurnXml"), game);
		theirTurnState = new TheirTurnState(game, connection, waitField, this);
		game.getGameStateManager().addGameState(theirTurnState);
		PlayField youLostField = levelParser.getPlayfield(Resources.getString("youLostXml"), game);
		youLostState = new GameOverState(youLostField);
		game.getGameStateManager().addGameState(youLostState);
		PlayField theyQuitField = levelParser.getPlayfield(Resources.getString("theyQuitXml"), game);
		theyQuitState = new GameOverState(theyQuitField);
		game.getGameStateManager().addGameState(theyQuitState);
		PlayField tieField = levelParser.getPlayfield(Resources.getString("tieXml"), game);
		tieState = new GameOverState(tieField);
		game.getGameStateManager().addGameState(tieState);
		eventPool = new EventPool();
		eventPool.addEvent(new GameWonEvent(field, this));
		eventPool.addEvent(new GameLostEvent(field, this));
		eventPool.addEvent(new GameTiedEvent(field, this));
	}

	/**
	 * Called by Control whenever the user clicks in a square. Decides whether or not to add it based on if there is a piece already in that square.
	 * If it's empty, it adds the piece, sets myTurn to false, and sends the Move with the row and column of where the piece was placed to the socket
	 * with the line
	 * <xmp>
	 * connection.sendData(new Move(row, col));
	 * </xmp>
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void addPiece(){
		int mouseX = game.bsInput.getMouseX();
		int mouseY = game.bsInput.getMouseY();
		int pieceX = (mouseX / Resources.getInt("squareDimension")) * Resources.getInt("squareDimension");
		int pieceY = (mouseY / Resources.getInt("squareDimension")) * Resources.getInt("squareDimension");
		pieceX = pieceX + Resources.getInt("xOffsetX");
		pieceY = pieceY + Resources.getInt("xOffsetY");
		SpriteGroup xGroup = field.getGroup("xGroup");
		SpriteGroup oGroup = field.getGroup("oGroup");
		boolean add = true;
		for(Sprite sprite : xGroup.getSprites()){
			if(sprite == null)
				continue;
			int x = (int)(sprite.getX());
			int y = (int)(sprite.getY());
			if(Math.abs(x - pieceX) <= 2 && Math.abs(y - pieceY) <= 5)
				add = false;
		}
		for(Sprite sprite : oGroup.getSprites()){
			if(sprite == null)
				continue;
			int x = (int)(sprite.getX());
			int y = (int)(sprite.getY());
			if(Math.abs(x - pieceX) <= 2 && Math.abs(y - pieceY) <= 5)
				add = false;
		}
		if(add){
			xGroup.add(new BetterSprite(Resources.getImage("X"), pieceX, pieceY));
			int col = pieceX / Resources.getInt("squareDimension");
			int row = pieceY / Resources.getInt("squareDimension");
			myTurn = false;
			connection.sendData(new Move(row, col));
		}
	}

	/**
	 * Called in interpretMessage if the data its parsing is an integer so it represents a Move. It gets the row and the column from the Move and
	 * adds it to the board.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void placeOpposingPiece(){
		int col = oMove.getCol();
		int row = oMove.getRow();
		int pieceX = col * Resources.getInt("squareDimension") + Resources.getInt("oOffsetX");
		int pieceY = row * Resources.getInt("squareDimension") + Resources.getInt("oOffsetY");
		field.getGroup("oGroup").add(new BetterSprite(Resources.getImage("O"), pieceX, pieceY));
	}

	/**
	 * If the String starts with the Move class identifier then deserialize the String into a Move object and place the piece on the
	 * board. Otherwise set the message to the String we received.
	 * 
	 * @param data data received from the socket
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void interpretMessage(String data){
		if(data.startsWith(Move.getIdentifier())){
			oMove = (Move) (Move.deserialize(data));
			placeOpposingPiece();
		}
		else
			setMessage(data);
	}

	/**
	 * Checks events and then calls super.update which handles all the necessary networking calls.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public void update(long elapsedTime){
		eventPool.checkEvents();
		super.update(elapsedTime);
	}
	
	/**
	 * Called by the GameState API to determine whether or not it should listen for a message from the socket. In this case it returns true if
	 * there everyone is still connected to the socket and it's not my turn. <b>Must be overriden to implement networking API.</b>
	 * 
	 * @return whether or not to listen for a message from the socket
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	@Override
	public boolean shouldGetData(){
		return (connection.isConnected() && !myTurn);
	}
	
	/**
	 * Called when the player wins. Tells the server the game is over and to cease the connection and switches to the gameWonState.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void won(){
		connection.sendGameOver();
		game.getGameStateManager().switchTo(gameWonState);
		return;
	}
	
	/**
	 * Called when the player ties. Tells the server the game is over and to cease the connection and switches to the tieState.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void tied(){
		connection.sendGameOver();
		game.getGameStateManager().switchTo(tieState);
		return;
	}
	
	/**
	 * Called when the player loses. Tells the server the game is over and to cease the connection and switches to the youLostState.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void lost(){
		connection.sendGameOver();
		game.getGameStateManager().switchTo(youLostState);
		return;
	}
	
	/**
	 * Called when the other player quits. Tells the server the game is over and to cease the connection and switches to the theyQuitState.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void quit(){
		connection.sendGameOver();
		game.getGameStateManager().switchTo(theyQuitState);
		return;
	}
	
	/**
	 * Called when it is the opponent's turn. Switches to the theirTurnState until further notice.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void theirTurn(){
		game.getGameStateManager().switchTo(theirTurnState);
		return;
	}
	
	/**
	 * Called when the it is the player's turn. Just sets myTurn to true so the player knows they can go.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void yourTurn(){
		myTurn = true;
	}
}
