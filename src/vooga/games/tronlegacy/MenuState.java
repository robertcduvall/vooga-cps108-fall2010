package vooga.games.tronlegacy;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.overlay.OverlayString;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

public class MenuState extends GameState {

	OverlayString menuOverlay;
	Blah game;
	private PlayField playField;
	
	private static final int TEXT_X_POSITION = 135;
	private static final int TEXT_Y_POSITION = 200;

	public MenuState(Blah aGame) {
		game = aGame;
		menuOverlay = new OverlayString("Press Space to Start", Color.CYAN);
		menuOverlay.setLocation(TEXT_X_POSITION, TEXT_Y_POSITION);
		playField = new PlayField();
		playField.setBackground(new ImageBackground(Resources.getImage("titleBackground")));
	}

	public void render(Graphics2D g) {
		playField.render(g);
		menuOverlay.render(g);
	}

	public void update(long elapsedTime) {
		menuOverlay.update(elapsedTime);
		if (game.keyPressed(KeyEvent.VK_SPACE)) {
			game.startGame();
		}
	}
}
