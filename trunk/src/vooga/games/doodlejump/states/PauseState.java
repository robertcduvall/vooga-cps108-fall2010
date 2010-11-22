package vooga.games.doodlejump.states;

import java.awt.event.KeyEvent;

import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.event.EventPool;
import vooga.widget.Button;


import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.Stat;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.doodlejump.buttons.PlayButton;
import vooga.games.doodlejump.buttons.ResumeButton;


public class PauseState extends GameState {

	private Game myGame;
	private ResumeButton myResumeButton;
	private EventPool myEventPool;

	public PauseState(Game game, PlayField field) {
		myGame = game;
		myResumeButton = new ResumeButton(myGame);
	}

	@Override
	public void initialize() {
		addButton(myResumeButton);
		myEventPool.addEvent(myResumeButton);
	}

	@Override
	public void update(long elapsedTime) {

	}

}