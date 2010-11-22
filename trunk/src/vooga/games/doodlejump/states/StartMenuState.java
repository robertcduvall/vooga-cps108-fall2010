package vooga.games.doodlejump.states;

import java.awt.event.KeyEvent;

import vooga.widget.Button;
import vooga.engine.core.Game;
import vooga.engine.event.EventPool;

import vooga.engine.state.MenuGameState;
import vooga.games.doodlejump.buttons.PlayButton;

public class StartMenuState extends MenuGameState {

	private Game myGame;
	private PlayButton myPlayButton;
	private EventPool myEventPool;

	public StartMenuState(Game game) {
		myGame = game;
		myPlayButton = new PlayButton(myGame);

	}

	@Override
	public void initialize() {
		addButton(myPlayButton);
		myEventPool.addEvent(myPlayButton);
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		myEventPool.checkEvents();
	}

}