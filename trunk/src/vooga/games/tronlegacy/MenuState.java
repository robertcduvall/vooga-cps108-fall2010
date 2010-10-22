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

public class MenuState extends GameState {

	OverlayString menuOverlay;
	Blah game;

	public MenuState(Blah aGame) {

		game = aGame;
		menuOverlay = new OverlayString("Press Space to Start", Color.BLACK);

	}

	public void render(Graphics2D g) {
		System.out.println("lolol");
		menuOverlay.render(g);
	}

	public void update(long elapsedTime) {
		menuOverlay.update(elapsedTime);

		if (game.keyPressed(KeyEvent.VK_SPACE)) {
			game.startGame();
		}
	}

}
