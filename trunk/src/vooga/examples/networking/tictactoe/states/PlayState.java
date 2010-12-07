package vooga.examples.networking.tictactoe.states;

import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

public class PlayState extends GameState{
	private Game game;
	private PlayField field;
	private LevelManager levelManager;
	private EventPool eventPool;
	private GameOverState gameWonState;
	private GameOverState youLostState;
	private GameOverState theyQuitState;
	private GameOverState tieState;
	private TheirTurnState waitState;
	private ClientConnection connection;
	private boolean myTurn;
	private String statusString;
	private Move oMove;

	public PlayState(Game game, LevelManager levelManager, ClientConnection connection){
		this.game = game;
		this.levelManager = levelManager;
		this.connection = connection;
	}

	@Override
	public void initialize() {
		field = levelManager.loadFirstLevel();
		initControls();
		this.addPlayField(field);
		initLevel();
	}

	public void initControls(){
		Control gameControl = new MouseControl(this, game);
		gameControl.addInput(MouseEvent.BUTTON1, "addPiece", Resources.getString("playStateDirectory"));
		field.addControl("game", gameControl);
	}

	public void initLevel(){
		LevelParser levelParser = new LevelParser();
		PlayField gameWonField = levelParser.getPlayfield(Resources.getString("gameWonXml"), game);
		gameWonState = new GameOverState(gameWonField);
		game.getGameStateManager().addGameState(gameWonState);
		PlayField waitField = levelParser.getPlayfield(Resources.getString("waitForTurnXml"), game);
		waitState = new TheirTurnState(game, connection, waitField, this);
		game.getGameStateManager().addGameState(waitState);
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
			//Networking code
			connection.sendData(new Move(row, col));
		}
	}

	//Networking code
	public void placeOpposingPiece(){
		int col = oMove.getCol();
		int row = oMove.getRow();
		int pieceX = col * Resources.getInt("squareDimension") + Resources.getInt("oOffsetX");
		int pieceY = row * Resources.getInt("squareDimension") + Resources.getInt("oOffsetY");
		field.getGroup("oGroup").add(new BetterSprite(Resources.getImage("O"), pieceX, pieceY));
		eventPool.checkEvents();
	}
	
	public void setStatusString(String status){
		statusString = status;
	}

	public void interpretMessage(String data){
		try{
			Integer.parseInt(data);
			oMove = (Move) (Move.deserialize(data));
			placeOpposingPiece();
		}
		catch(NumberFormatException e){
			setStatusString(data);
		}
	}
	
	public boolean checkStatus(){
		if(statusString == null || statusString.length() == 0){
			return false;
		}
		else{
			try {
				Method statusAction = this.getClass().getMethod(statusString);
				statusAction.invoke(this);
			} 
			catch (Exception e) {
				System.out.println("Status action error" + e + ". Make sure the names of your stauses match the name of your status methods!");
				System.exit(1);
			}
			statusString = null;
			return true;
		}
	}

	@Override
	public void update(long elapsedTime){
		eventPool.checkEvents();
		if(checkStatus())
			return;
		super.update(elapsedTime);
		if(connection.isConnected() && !myTurn){
			interpretMessage(connection.getData());
		}
	}
	
	public void won(){
		connection.sendGameOver();
		game.getGameStateManager().switchTo(gameWonState);
		return;
	}
	
	public void tied(){
		connection.sendGameOver();
		game.getGameStateManager().switchTo(tieState);
		return;
	}
	
	public void lost(){
		connection.sendGameOver();
		game.getGameStateManager().switchTo(youLostState);
		return;
	}
	
	public void quit(){
		connection.sendGameOver();
		game.getGameStateManager().switchTo(theyQuitState);
		return;
	}
	
	public void theirTurn(){
		game.getGameStateManager().switchTo(waitState);
		return;
	}
	
	public void yourTurn(){
		myTurn = true;
	}
}
