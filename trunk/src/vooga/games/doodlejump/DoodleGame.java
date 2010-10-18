package vooga.games.doodlejump;

import java.awt.*;

import vooga.engine.player.control.KeyboardControl;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.overlay.*;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;

import com.golden.gamedev.object.background.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The DoodleGame class creates a game based on the popular iPhone app Doodle
 * Jump
 * 
 * @author Adam Cue, Marcus Molchany, Nick Straub
 * 
 */
public class DoodleGame extends Game {

	// Background
	private Background background;

	// Playfield
	protected PlayField playField;
	
	private OverlayString scoreString;
	private Stat<Integer> score;
	private int currentLevel;
	private int passScore;
	private int nextLevel;
	
	{distribute = true;}

	// Doodle (main player)
	private DoodleSprite doodle;
	private KeyboardControl doodle_keyboard_control;

	protected SpriteGroup doodleGroup, ballGroup;

	// Collision Manager
	protected CollisionManager doodleToGreenPlatform, doodleToMonster,
			ballToMonster, doodleToBrownPlatform, doodleToWhitePlatform,
			doodleToSpring, doodleToTrampoline, doodleToJetpack;
	
	// Game States (menus)
	private int menuInt;
	private boolean isActive;
	
	public DoodleGame(){
		super();
		currentLevel = 1;
		score = new Stat<Integer>(0);
		scoreString = new OverlayString("0");
		scoreString.setX(450);
		scoreString.setY(50);
	}

	@Override
	public void initResources() {
		DoodleLevel level = new DoodleLevel();
		
		// playfield
		playField = level.getPlayfield(new File("src/vooga/games/doodlejump/levels/level" + Integer.toString(currentLevel) + ".txt"));
		
		// background
		background = level.getBackground();
		
		passScore += level.getScore();
		nextLevel = level.getNextLevel();

		// spritegroups
		ballGroup = playField.addGroup(new SpriteGroup("Ball Group"));
		doodleGroup = playField.addGroup(new SpriteGroup("Doodle Group"));

		// doodle (main player)
		doodle = new DoodleSprite("doodle", "normal", new Sprite(
				getImage("images/doodle_right.png"), 325, 550), this);
		doodle.setVerticalSpeed(0.5);
		doodleGroup.add(doodle);
		doodle_keyboard_control = new KeyboardControl(doodle, this);
		doodle_keyboard_control.addInput(KeyEvent.VK_LEFT, "moveLeft",
				"vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_RIGHT, "moveRight",
				"vooga.games.doodlejump.DoodleSprite", null);
		doodle_keyboard_control.addInput(KeyEvent.VK_SPACE, "shoot",
				"vooga.games.doodlejump.DoodleSprite", null);
		
		// Collision
		doodleToGreenPlatform = new DoodleToGreenPlatformCollision();
		doodleToMonster = new DoodleToMonsterCollision();
		ballToMonster = new BallToMonsterCollision();
		doodleToBrownPlatform = new DoodleToBrownPlatformCollision();
		doodleToWhitePlatform = new DoodleToWhitePlatformCollision();
		doodleToSpring = new DoodleToSpringCollision();
		doodleToTrampoline = new DoodleToTrampolineCollision();

		playField.addCollisionGroup(doodleGroup, level.getPlatformGroup(),
				doodleToGreenPlatform);
		playField.addCollisionGroup(doodleGroup, level.getMonsterGroup(), doodleToMonster);
		playField.addCollisionGroup(ballGroup, level.getMonsterGroup(), ballToMonster);
		playField.addCollisionGroup(doodleGroup, level.getBrownPlatformGroup(),
				doodleToBrownPlatform);
		playField.addCollisionGroup(doodleGroup, level.getWhitePlatformGroup(),
				doodleToWhitePlatform);
		playField.addCollisionGroup(doodleGroup, level.getSpringGroup(), doodleToSpring);
		playField.addCollisionGroup(doodleGroup, level.getTrampolineGroup(),
				doodleToTrampoline);
		
		//game state
		menuInt = 1;
		isActive = false;
		setFPS(100);
	}

	@Override
	public void update(long elapsedTime) {
				
		doodle_keyboard_control.update();
		if (menuInt ==1){
			if(keyPressed(KeyEvent.VK_ENTER)){
				menuInt++;
				isActive = true;
			}
			playField.setBackground(new ImageBackground(
					getImage("images/default-play.png")));
		}
		else if(menuInt%2 == 0){
			playField.setBackground(background);
			for(SpriteGroup group : playField.getGroups()){
				for(Sprite sprite : group.getSprites()){
					if(doodle.getY() < 400 && sprite != null){
						if(group.getName().equals("Doodle Group"))
							score.setStat(score.getStat()+5);
						sprite.moveY(400 - doodle.getY());
					}
				}
			}
			playField.update(elapsedTime);
			scoreString.setString(Integer.toString(score.getStat()));
			if(Integer.parseInt(scoreString.getString()) >= passScore){
				if(nextLevel != 0){
					currentLevel = nextLevel;
					initResources();
				}
				else{
					stop();
				}
			}
			if(keyPressed(KeyEvent.VK_P)){
				menuInt++;
				isActive = false;
			}
		}
		else {
			if(keyPressed(KeyEvent.VK_P)){
				menuInt--;
				isActive = true;
			}
			playField.setBackground(new ImageBackground(
					getImage("images/pause-cover-resume.png")));
		}
	}

	@Override
	public void render(Graphics2D g) {
		playField.render(g);
		
		if (menuInt == 1) {
			playField.getBackground().render(g);
		}
		else if (menuInt %2 != 0) {
			playField.getBackground().render(g);
		}
		
		scoreString.render(g);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new DoodleGame(), new Dimension(532, 850), false);
		game.start();
	}

}
