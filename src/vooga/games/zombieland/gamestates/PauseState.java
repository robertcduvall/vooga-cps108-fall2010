package vooga.games.zombieland.gamestates;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import vooga.engine.control.KeyboardControl;
import vooga.engine.core.*;
import vooga.engine.overlay.*;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.zombieland.Blah;
import vooga.games.zombieland.Constants;

public class PauseState extends GameState implements Constants {

	private OverlayTracker tracker;
	private OverlayString overlayPauseString;
	private Blah game;

	public PauseState(Blah g) {
		OverlayCreator.setGame(g);
		game = g;
		tracker = OverlayCreator.createOverlays(PAUSE_XML_PATH);

	}

	public void initialize() {
//		int pauseX = Resources.getInt("overlayPauseStringX");
//		int pauseY = Resources.getInt("overlayPauseStringY");
		overlayPauseString = tracker.getOverlay("pause", overlayPauseString);
//		overlayPauseString.setX(pauseX);
//		overlayPauseString.setY(pauseY);

		PlayField p = new PlayField();
		p.add(overlayPauseString);
		KeyboardControl control = new KeyboardControl(game,game);
		control.addInput(KeyEvent.VK_ESCAPE, "play", MAIN_CLASS);
		control.addInput(KeyEvent.VK_P, "play", MAIN_CLASS);
		p.addControl("pause", control);
		this.addPlayField(p);
	}

}
