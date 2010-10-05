package vooga.games.zombieland;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

import vooga.engine.core.Game;
import vooga.games.jumper.Jumper;
import vooga.engine.player.control.PlayerSprite;

public class Zombieland extends Game{
	
    private static final int GAME_WIDTH = 640;
	private static final int GAME_HEIGHT = 480;

	private Sprite hero;
	private Background background;
	
	public void initResources(){
	}
	
	public void update(long elapsedTime) {
    }

    public void render(Graphics2D g) {
    }
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new Zombieland(), new Dimension(GAME_WIDTH,GAME_HEIGHT), false);
        game.start();
    }

}
