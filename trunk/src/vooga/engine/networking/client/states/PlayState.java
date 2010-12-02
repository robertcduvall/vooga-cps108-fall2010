package vooga.engine.networking.client.states;

import java.awt.event.MouseEvent;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.BetterSprite;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.factory.LevelManager;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.control.*;

public class PlayState extends GameState{
	private Game game;
	private PlayField field;
	private LevelManager levelManager;
	private boolean xTurn;
	
	public PlayState(Game game, LevelManager levelManager){
		this.game = game;
		this.levelManager = levelManager;
		xTurn = true;
	}

	@Override
	public void initialize() {
		field = levelManager.loadFirstLevel();
		System.out.println(field.getBackground());
		initControls();
		this.addPlayField(field);
	}

	public void initControls(){
		Control gameControl = new MouseControl(this, game);
		gameControl.addInput(MouseEvent.BUTTON1, "addPiece", "vooga.engine.networking.client.states.PlayState");
		field.addControl("game", gameControl);
	}
	
	public void addPiece(){
		int mouseX = game.bsInput.getMouseX();
		int mouseY = game.bsInput.getMouseY();
		int pieceX = (mouseX / Resources.getInt("squareDimension")) * Resources.getInt("squareDimension");
		int pieceY = (mouseY / Resources.getInt("squareDimension")) * Resources.getInt("squareDimension");
		pieceX = xTurn ? pieceX + Resources.getInt("xOffsetX") : pieceX + Resources.getInt("oOffsetX");
		pieceY = xTurn ? pieceY + Resources.getInt("xOffsetY") : pieceY + Resources.getInt("oOffsetY");
		System.out.println(mouseX + " " + pieceX);
		System.out.println(mouseY + " " + pieceY);
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
}
