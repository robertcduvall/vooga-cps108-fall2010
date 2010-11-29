package vooga.games.doodlejump.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.background.ImageBackground;

import vooga.widget.Button;
import vooga.engine.core.Game;
import vooga.engine.event.EventPool;

import vooga.engine.resource.Resources;
import vooga.engine.state.MenuGameState;
import vooga.games.doodlejump.buttons.PlayButton;

/**
 * The StartMenuState class extends MenuGameState and creates a menu that
 * BlahThis can be started from.
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class StartMenuState extends MenuGameState {

	private static final String DEFAULT_PLAY_STRING = "defaultPlay";

	private Game myGame;
	private PlayButton myPlayButton;
	private EventPool myEventPool;
	private ImageBackground mainBackground;

	public StartMenuState() {
		super();
	}

	@Override
	public void initialize() {
		myPlayButton = new PlayButton();
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