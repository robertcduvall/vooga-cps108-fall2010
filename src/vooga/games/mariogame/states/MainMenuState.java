package vooga.games.mariogame.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import vooga.engine.control.Control;
import vooga.engine.control.KeyboardControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.state.MenuGameState;

/**
 * 
 * @author David Herzka, Cameron McCallie, Andrew Brown
 * 
 *         This extension of GameState represents the opening main menu screen
 *         of the game.
 * 
 */

public class MainMenuState extends MenuGameState {

	private Game myGame;
	private PlayField myPlayfield;
	
	public MainMenuState(Game game, PlayField playfield, GamePlayState playState) {
		
		
		super();
		myPlayfield = playfield;
		myGame = game;
		initControls();
	}
	
	@Override
	public void update(long t) {
		super.update(t);
		myPlayfield.update(t);
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		myPlayfield.render(g);
	}
	
	@Override
	public void initialize() {
	}
	
	private void initControls(){
		Control gameControl = new KeyboardControl(myGame,myGame);
		gameControl.addInput(KeyEvent.VK_SPACE, "resumeGame", "vooga.games.mariogame.Blah");
		myPlayfield.addControl("start", gameControl);
	}

}
