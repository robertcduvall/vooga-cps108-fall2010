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
import vooga.examples.networking.tictactoe.TicTacToeConnection;
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
	private GameOverState errorState;
	private TheirTurnState waitState;
	private TicTacToeConnection connection;
	private boolean myTurn, theirTurn;
	private boolean won, tied, lost, quit;
	private Move oMove;

	public PlayState(Game game, LevelManager levelManager, TicTacToeConnection connection){
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
		gameWonState = new GameOverState(game, gameWonField);
		game.getGameStateManager().addGameState(gameWonState);
		PlayField waitField = levelParser.getPlayfield(Resources.getString("waitForTurnXml"), game);
		waitState = new TheirTurnState(game, connection, waitField, this);
		game.getGameStateManager().addGameState(waitState);
		PlayField youLostField = levelParser.getPlayfield(Resources.getString("youLostXml"), game);
		youLostState = new GameOverState(game, youLostField);
		game.getGameStateManager().addGameState(youLostState);
		PlayField theyQuitField = levelParser.getPlayfield(Resources.getString("theyQuitXml"), game);
		theyQuitState = new GameOverState(game, theyQuitField);
		game.getGameStateManager().addGameState(theyQuitState);
		PlayField tieField = levelParser.getPlayfield(Resources.getString("tieXml"), game);
		tieState = new GameOverState(game, tieField);
		game.getGameStateManager().addGameState(tieState);
		PlayField errorField = levelParser.getPlayfield(Resources.getString("errorXml"), game);
		errorState = new GameOverState(game, errorField);
		game.getGameStateManager().addGameState(errorState);
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
			eventPool.checkEvents();
			int col = pieceX / Resources.getInt("squareDimension");
			int row = pieceY / Resources.getInt("squareDimension");
			myTurn = false;
			connection.sendData(new Move(row, col));
		}
	}

	public void placeOpposingPiece(){
		int col = oMove.getCol();
		int row = oMove.getRow();
		int pieceX = col * Resources.getInt("squareDimension") + Resources.getInt("oOffsetX");
		int pieceY = row * Resources.getInt("squareDimension") + Resources.getInt("oOffsetY");
		field.getGroup("oGroup").add(new BetterSprite(Resources.getImage("O"), pieceX, pieceY));
		eventPool.checkEvents();
	}

	public void setWon(boolean didWin){
		won = didWin;
	}

	public void setTied(boolean didTie){
		tied = didTie;
	}

	public void setLost(boolean didLose){
		lost = didLose;
	}

	public void setOMove(Move move){
		oMove = move;
	}

	public void interpretMessage(String data){
		if(data.equals(Resources.getString("quitString")))
			quit = true;
		else if(data.equals(Resources.getString("theirTurnString")))
			theirTurn = true;
		else if(data.equals(Resources.getString("yourTurnString")))
			myTurn = true;
		else{
			oMove = (Move) (Move.deserialize(data));
			placeOpposingPiece();
		}
	}

	@Override
	public void update(long elapsedTime){
		if(won){
			connection.sendGAMEOVER();
			game.getGameStateManager().switchTo(gameWonState);
			return;
		}
		if(tied){
			connection.sendGAMEOVER();
			game.getGameStateManager().switchTo(tieState);
			return;
		}
		if(lost){
			connection.sendGAMEOVER();
			game.getGameStateManager().switchTo(youLostState);
			return;
		}
		if(quit){
			connection.sendGAMEOVER();
			game.getGameStateManager().switchTo(theyQuitState);
			return;
		}
		if(theirTurn){
			game.getGameStateManager().switchTo(waitState);
			theirTurn = false;
			return;
		}
		super.update(elapsedTime);
		if(connection.isConnected() && !myTurn){
			interpretMessage(connection.getData());
		}
	}
}
