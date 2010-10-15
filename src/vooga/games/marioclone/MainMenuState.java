package vooga.games.marioclone;

import java.awt.Graphics2D;

import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.resource.Resources;
import vooga.engine.state.GameState;

public class MainMenuState extends GameState {

	private ImageBackground mainMenu;
	private PlayField menu;
	private GameFont myGameFont;
	private int WIDTH;
	private int HEIGHT;

	
	public MainMenuState(GameFont gameFont, int width, int height) {
		super();
		myGameFont = gameFont;
		WIDTH = width;
		HEIGHT = height;
	}
	
	@Override
	public void initialize() {
		mainMenu = new ImageBackground(Resources.getImage("MenuBG"));
		menu = new PlayField();
		menu.setBackground(mainMenu);
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		menu.render(g);
		myGameFont.drawString(g, "WELCOME TO MARIOCLONE!", WIDTH / 4, HEIGHT / 2 + 50);
		myGameFont.drawString(g, "PRESS A AND D TO MOVE LEFT AND RIGHT.",(WIDTH / 4), (HEIGHT / 2) + 125);
		myGameFont.drawString(g, "PRESS W TO JUMP AND S TO CROUCH.", (WIDTH / 4), (HEIGHT / 2) + 200);
		myGameFont.drawString(g, "PRESS SPACE TO PLAY!", (WIDTH / 4), (HEIGHT / 2) + 250);
	}
	

}
