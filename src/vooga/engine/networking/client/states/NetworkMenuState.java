package vooga.engine.networking.client.states;

import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import vooga.engine.control.KeyboardControl;
import vooga.engine.control.MouseControl;
import vooga.engine.core.Game;
import vooga.engine.core.PlayField;
import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;
import vooga.engine.state.MenuGameState;
import vooga.examples.networking.zombies.Blah;
import vooga.examples.networking.zombies.Constants;
import vooga.widget.Button;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.ImageUtil;

public abstract class NetworkMenuState extends GameState{

	private Game game;
	private KeyboardControl keyboardControl;
	private MouseControl mouseControl;
	protected String config;
	protected String enterAddress;
	private BufferedImage DEFAULT_NETWORK_MENU_BACKGROUND = Resources.getImage("networkMenuBackground");
	private PlayField menuPlayField = new PlayField();

	public NetworkMenuState(Game g) {
		game = g;
		keyboardControl = new KeyboardControl(game, game);
		mouseControl = new MouseControl(game, game);
	}
	
	private SpriteGroup createButtons() {
		SpriteGroup buttons = new SpriteGroup("buttons");
		buttons.add((Sprite) new NetworkMenuButton(Resources.getImage("networkMenuButton2P"), 0 , 0));
		buttons.add((Sprite) new NetworkMenuButton(Resources.getImage("networkMenuButton3P"), 0 , 30));
		buttons.add((Sprite) new NetworkMenuButton(Resources.getImage("networkMenuButtonPrivate"), 0 , 60));
		buttons.add((Sprite) new NetworkMenuButton(Resources.getImage("networkMenuButtonRandom"), 0 , 90));
		return buttons;
		
	}
	
	protected void setEnterAddress (String address) {
		enterAddress = address;
	}

	public void initialize(int windowWidth, int windowHeight) {
		//ResourceBundle rb = ResourceBundle.getBundle(config);
		BufferedImage menuBackground = ImageUtil.resize(	DEFAULT_NETWORK_MENU_BACKGROUND, windowWidth, windowHeight);
												//Integer.parseInt(rb.getString("GAME_WIDTH")),
												//Integer.parseInt(rb.getString("GAME_HEIGHT")));
		Sprite menuBackGroundSprite = new Sprite(DEFAULT_NETWORK_MENU_BACKGROUND, 0, 0);
		//menuPlayField.addGroup(createButtons());
		menuPlayField.add(menuBackGroundSprite);
		getKeyboardControl().addInput(KeyEvent.VK_ENTER, "startWaitState", enterAddress);
		menuPlayField.addControl("keyboard", keyboardControl);
		menuPlayField.addControl("mouse", mouseControl);
		this.addPlayField(menuPlayField);
	}

	public KeyboardControl getKeyboardControl() {
		return keyboardControl;
	}

	public MouseControl getMouseControl() {
		return mouseControl;
	}
}