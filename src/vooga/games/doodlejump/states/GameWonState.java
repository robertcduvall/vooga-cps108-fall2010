package vooga.games.doodlejump.states;

import java.awt.Graphics2D;

import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.MenuGameState;
import vooga.games.doodlejump.buttons.RestartButton;

/**
 * The GameWonState class extends MenuGameState and describes what happens when
 * the game ends. A Play Button is added to the background which will restart
 * the game.
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class GameWonState extends MenuGameState {

	private static final String DEFAULT_PLAY_STRING = "defaultPlay";
	private Game myGame;
	private RestartButton myPlayButton;
	private EventPool myEventPool;
	private ImageBackground mainBackground;

	public GameWonState(Game game) {
		super();
		myGame = game;
	}

	@Override
	public void initialize() {
		myPlayButton = new RestartButton(myGame);
		mainBackground = new ImageBackground(
				Resources.getImage(DEFAULT_PLAY_STRING));
		myEventPool = new EventPool();
		addButton(myPlayButton);
		myEventPool.addEvent(myPlayButton);
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
		super.render(g);
		mainBackground.render(g);
	}
}
