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
import vooga.examples.networking.tictactoe.TicTacToeConnection;
import vooga.examples.networking.tictactoe.events.GameLostEvent;
import vooga.examples.networking.tictactoe.events.GameTiedEvent;
import vooga.examples.networking.tictactoe.events.GameWonEvent;
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
	private TheirTurnState theirTurnState;
	private TicTacToeConnection connection;
	private boolean myTurn, theirTurn;
	private boolean won, tied, lost, quit;
	private int oMove;
	
	public PlayState(Game game, LevelManager levelManager, TicTacToeConnection connection){
		this.game = game;
		this.levelManager = levelManager;
		this.connection = connection;
		//xTurn = true;
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
		gameControl.addInput(MouseEvent.BUTTON1, "addPiece", "vooga.examples.networking.tictactoe.states.PlayState");
		field.addControl("game", gameControl);
	}
	
	public void initLevel(){
		LevelParser levelParser = new LevelParser();
		PlayField gameWonField = levelParser.getPlayfield(Resources.getString("gameWonXml"), game);
		gameWonState = new GameOverState(game, gameWonField);
		game.getGameStateManager().addGameState(gameWonState);
		PlayField theirTurnField = levelParser.getPlayfield(Resources.getString("theirTurnXml"), game);
		theirTurnState = new TheirTurnState(game, connection, theirTurnField, this);
		game.getGameStateManager().addGameState(theirTurnState);
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
//		pieceX = xTurn ? pieceX + Resources.getInt("xOffsetX") : pieceX + Resources.getInt("oOffsetX");
//		pieceY = xTurn ? pieceY + Resources.getInt("xOffsetY") : pieceY + Resources.getInt("oOffsetY");
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
			//if(xTurn){
				xGroup.add(new BetterSprite(Resources.getImage("X"), pieceX, pieceY));
				eventPool.checkEvents();
				int col = pieceX / Resources.getInt("squareDimension");
				int row = pieceY / Resources.getInt("squareDimension");
				myTurn = false;
				connection.sendMove(col * 10 + row);
//			}
//			else{
//				oGroup.add(new BetterSprite(Resources.getImage("O"), pieceX, pieceY));
//			}
		}
		//xTurn = !xTurn;
	}
	
	public void placeOpposingPiece(){
		int col = oMove < 10 ? 0 : oMove / 10;
		int row = oMove % 10;
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
	
	public void setOMove(int move){
		oMove = move;
	}
	
	public void checkMessages(int status){
		if(status == Resources.getInt("theyQuit"))
			quit = true;
		else if(status == Resources.getInt("error"))
			game.getGameStateManager().switchTo(errorState);
		else if(status == Resources.getInt("theirTurn")){
			theirTurn = true;
		}
		else if(status == Resources.getInt("yourTurn")){
			myTurn = true;
		}
		else{
			oMove = status;
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
			game.getGameStateManager().switchTo(theirTurnState);
			theirTurn = false;
			return;
		}
		super.update(elapsedTime);
		if(connection.isConnected() && !myTurn){
			checkMessages(connection.getTheirMove());
		}
	}
}
