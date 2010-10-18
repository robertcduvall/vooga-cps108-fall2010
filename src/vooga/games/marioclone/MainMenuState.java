package vooga.games.marioclone;

import java.awt.Graphics2D;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.GameFontManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

public class MainMenuState extends GameState {

	private ImageBackground mainMenu;
	private int WIDTH;
	private int HEIGHT;
	private GameFont menuFont;

	
	public MainMenuState(int width, int height, GameFontManager fontManager) {
		super();
		menuFont = fontManager.getFont("MENU");
		mainMenu = new ImageBackground(Resources.getImage("MenuBG"));
		WIDTH = width;
		HEIGHT = height;
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		mainMenu.render(g);
		menuFont.drawString(g, "WELCOME TO MARIOCLONE!", WIDTH / 4, HEIGHT / 2 + 50);
		menuFont.drawString(g, "PRESS A AND D TO MOVE LEFT AND RIGHT.",(WIDTH / 4), (HEIGHT / 2) + 125);
		menuFont.drawString(g, "PRESS W TO JUMP AND S TO CROUCH.", (WIDTH / 4), (HEIGHT / 2) + 200);
		menuFont.drawString(g, "PRESS SPACE TO PLAY!", (WIDTH / 4), (HEIGHT / 2) + 250);
	}
	

}
