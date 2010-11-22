package vooga.games.doodlejump.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.widget.Button;

import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.MenuGameState;
import vooga.games.doodlejump.buttons.PlayButton;
import vooga.games.doodlejump.buttons.ResumeButton;

/**
 * The PauseState class extends MenuGameState and describes what happens when
 * the game is paused.
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class PauseState extends MenuGameState {

	private Game myGame;
	private ResumeButton myResumeButton;
	private EventPool myEventPool;
	private ImageBackground mainBackground;

	public PauseState(Game game) {
		super();
		myGame = game;
	}

	@Override
	public void initialize() {
		myResumeButton = new ResumeButton(myGame);
		myEventPool = new EventPool();
		mainBackground = new ImageBackground(Resources.getImage("pauseCover"));
		addButton(myResumeButton);
		myEventPool.addEvent(myResumeButton);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		getMenuPlayfield().update(elapsedTime);
		myEventPool.checkEvents();
	}

	/**
	 * Method called to render fonts to the screen, as well as the background.
	 */
	public void render(Graphics2D g) {
		mainBackground.render(g);
		super.render(g);
	}
}