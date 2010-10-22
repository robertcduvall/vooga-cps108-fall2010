package vooga.games.tronlegacy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.Game;
import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayString;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.state.GameState;

public class PauseState extends GameState {

	OverlayString pauseOverlay;
	Blah game;

	public PauseState(Blah aGame) {

		game = aGame;
		pauseOverlay = new OverlayString("Paused - U to Unpause", Color.BLACK);

	}

	public void render(Graphics2D g) {
		pauseOverlay.render(g);
	}

	public void update(long elapsedTime) {
		pauseOverlay.update(elapsedTime);

		if (game.keyPressed(KeyEvent.VK_U)) {
			game.togglePauseGame();
		}
	}

}
