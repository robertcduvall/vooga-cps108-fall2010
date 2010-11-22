package vooga.games.doodlejump.states;

import java.awt.Graphics2D;

import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.core.Game;
import vooga.engine.event.EventPool;
import vooga.engine.resource.Resources;
import vooga.engine.state.MenuGameState;
import vooga.games.doodlejump.buttons.RestartButton;

public class GameWonState extends MenuGameState{

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
		mainBackground = new ImageBackground(Resources.getImage("defaultPlay"));
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
