package vooga.games.zombies.gamestates;

import java.awt.event.KeyEvent;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.*;
import vooga.engine.overlay.*;
import vooga.engine.state.GameState;
import vooga.games.zombies.Blah;
import vooga.games.zombies.Constants;

public class EndGameState extends GameState implements Constants {

	private OverlayTracker tracker;
	private OverlayString overlayGameOverString;
	private Blah game;

	public EndGameState(Blah g) {
		OverlayCreator.setGame(g);
		game = g;
		tracker = OverlayCreator.createOverlays(STATES_XML_PATH);

	}

	public void initialize() {
		overlayGameOverString = tracker.getOverlay("gameOver", overlayGameOverString);

		PlayField p = new PlayField();
		p.add(overlayGameOverString);
		KeyboardControl control = new KeyboardControl(game,game);
		control.addInput(KeyEvent.VK_ESCAPE, "reset", MAIN_CLASS);
		p.addControl("pause", control);
		this.addPlayField(p);
	}

}
