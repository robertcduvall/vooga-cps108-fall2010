package vooga.games.towerdefense;

import java.awt.*;
import java.awt.event.*;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.engine.BaseInput;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;

import vooga.engine.core.Game;
import vooga.engine.player.control.Control;
import vooga.engine.player.control.PlayerSprite;


public class TowerDefense extends Game{
	
	public BaseInput bsInput;
	private PlayerSprite playerCursor;
	private Control playerCursorControl;
	private Sprite duvall;
	private Background background;
	private PlayField playfield;
	private SpriteGroup towerGroup, enemyGroup, towerShotGroup;
	
	public void initResources(){
		duvall = new Sprite(getImage("images/duvallFace.png"), 100, 200);
		background = new ColorBackground(Color.BLUE, 800, 600);
		playfield = new PlayField(background);
		initPlayer();
		
		towerGroup = playfield.addGroup(new SpriteGroup("Player Group"));
		enemyGroup = playfield.addGroup(new SpriteGroup("Enemy Group"));
		towerShotGroup = playfield.addGroup(new SpriteGroup("Tower Shot Group"));
		
		playfield.addCollisionGroup(towerShotGroup, enemyGroup, new TowerShotEnemyCollision());
		
		
		
		duvall.setBackground(background);
	}
	
	private void initPlayer(){
		Sprite playerSprite =  new Sprite(getImage("images/duvallFace.png"), 20, 50);
		playerCursor = new PlayerSprite("player", "playerCursor", playerSprite);
		playerCursorControl = new PlayerCursorControl(playerCursor, this);
	}
	
	public void update(long elapsedTime) {
		playfield.update(elapsedTime);
		playerCursorControl.update();
    }

	public void render(Graphics2D g) {
		playfield.render(g);
		playerCursor.render(g);
    }
	
	public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new TowerDefense(), new Dimension(640,480), false);
        game.start();
    }
	
	
}
