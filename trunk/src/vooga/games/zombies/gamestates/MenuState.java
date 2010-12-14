package vooga.games.zombies.gamestates;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;

import vooga.engine.control.KeyboardControl;
import vooga.engine.control.MouseControl;
import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.games.zombies.Blah;
import vooga.games.zombies.Constants;

public abstract class MenuState extends GameState implements Constants {
	private Blah game;
	private KeyboardControl keyboardControl;
	private MouseControl mouseControl;

	public MenuState(Blah g) {
		game = g;
		keyboardControl = new KeyboardControl(game, game);
		mouseControl = new MouseControl(game, game);
	}

	public void initialize(String imageName) {
		BufferedImage menu = ImageUtil.resize(Resources.getImage(imageName),
				GAME_WIDTH, GAME_HEIGHT);
		Sprite menuSprite = new Sprite(menu, 0, 0);
		PlayField p = new PlayField();
		p.add(menuSprite);
		p.addControl("keyboard", keyboardControl);
		p.addControl("mouse", mouseControl);
		this.addPlayField(p);
	}

	public KeyboardControl getKeyboardControl() {
		return keyboardControl;
	}

	public MouseControl getMouseControl() {
		return mouseControl;
	}
}
