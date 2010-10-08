package vooga.games.doodlejump;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.state.GameState;
import vooga.engine.state.GameStateManager;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;



public class GameStateTest extends Game {
	
	private GameStateManager game_state_manager;
	private GameState start_menu, game_play, pause_menu;
	private PlayField playfield;	
	private Background start_background, game_background, pause_background;

	@Override
	public void initResources() {
		game_state_manager = new GameStateManager();

		//start_menu = new GameState();
		//game_play = new GameState();
		//pause_menu = new GameState();
		
		game_state_manager.addGameState(start_menu);
		game_state_manager.addGameState(game_play);
		game_state_manager.addGameState(pause_menu);
		
		game_state_manager.switchTo(start_menu);
		
		start_background = new ImageBackground(getImage("images/default-play.png"));
		game_background = new ImageBackground(getImage("images/background.png"));
		pause_background = new ImageBackground(getImage("images/pause-cover-resume.png"));

		playfield = new PlayField(start_background);
	}

	@Override
	public void update(long elapsedTime) {
		if (KeyEvent.KEY_PRESSED == KeyEvent.VK_P && start_menu.isActive()) {
			game_state_manager.switchTo(game_play);
			playfield.setBackground(game_background);
		}
		
		if (KeyEvent.KEY_PRESSED == KeyEvent.VK_P) {
			game_state_manager.toggle(game_play);
			game_state_manager.toggle(pause_menu);
			if (game_play.isActive()) {
				playfield.setBackground(game_background);
			}
			else {
				playfield.setBackground(pause_background);
			}
		}
		
		playfield.update(elapsedTime);
	}

	@Override
	public void render(Graphics2D g) {
		game_state_manager.render(g);
		playfield.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new GameStateTest(), new Dimension(532, 850), false);
		game.start();
	}
}
