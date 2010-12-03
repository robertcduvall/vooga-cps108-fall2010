package vooga.engine.networking.client.states;

import java.awt.event.MouseEvent;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.engine.factory.LevelManager;
import vooga.engine.factory.LevelParser;
import vooga.engine.networking.client.TicTacToeConnection;
import vooga.engine.networking.client.events.GameWonEvent;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.control.*;

public class PlayState extends GameState{
	private Game game;
	private PlayField field;
	private LevelManager levelManager;
	private EventPool eventPool;
	private GameState gameWonState;
	private TicTacToeConnection connection;
	private boolean xTurn;
	private boolean won;
	
	public PlayState(Game game, LevelManager levelManager, TicTacToeConnection connection){
		this.game = game;
		this.levelManager = levelManager;
		this.connection = connection;
		xTurn = true;
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
		gameControl.addInput(MouseEvent.BUTTON1, "addPiece", "vooga.engine.networking.client.states.PlayState");
		field.addControl("game", gameControl);
	}
	
	public void initLevel(){
		LevelParser levelParser = new LevelParser();
		PlayField gameWonField = levelParser.getPlayfield(Resources.getString("gameWonXml"), game);
		gameWonState = new GameWonState(game, gameWonField);
		game.getGameStateManager().addGameState(gameWonState);
		eventPool = new EventPool();
		eventPool.addEvent(new GameWonEvent(field, this));
	}
	
	public void addPiece(){
		int mouseX = game.bsInput.getMouseX();
		int mouseY = game.bsInput.getMouseY();
		int pieceX = (mouseX / Resources.getInt("squareDimension")) * Resources.getInt("squareDimension");
		int pieceY = (mouseY / Resources.getInt("squareDimension")) * Resources.getInt("squareDimension");
		pieceX = xTurn ? pieceX + Resources.getInt("xOffsetX") : pieceX + Resources.getInt("oOffsetX");
		pieceY = xTurn ? pieceY + Resources.getInt("xOffsetY") : pieceY + Resources.getInt("oOffsetY");
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
			if(xTurn){
				xGroup.add(new BetterSprite(Resources.getImage("X"), pieceX, pieceY));
			}
			else{
				oGroup.add(new BetterSprite(Resources.getImage("O"), pieceX, pieceY));
			}
		}
		xTurn = !xTurn;
	}
	
	public void setWon(boolean didWin){
		won = didWin;
	}
	
	@Override
	public void update(long elapsedTime){
		if(won){
			game.getGameStateManager().switchTo(gameWonState);
		}
		super.update(elapsedTime);
		eventPool.checkEvents();
	}
}
