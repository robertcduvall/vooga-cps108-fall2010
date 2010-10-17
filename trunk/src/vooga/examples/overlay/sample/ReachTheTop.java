package vooga.examples.overlay.sample;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;

import vooga.engine.overlay.OverlayCreator;
import vooga.engine.overlay.OverlayIcon;
import vooga.engine.overlay.OverlayStat;
import vooga.engine.overlay.OverlayStatImage;
import vooga.engine.overlay.OverlayTracker;
import vooga.engine.overlay.Stat;
import vooga.engine.overlay.StatInt;
import vooga.engine.resource.Resources;

// GTGE
import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.util.*;

/**
 * Objective:Reach the finish line as many times as you can. Get 10 points every
 * time the bomb reaches the finish line. You get 4 lives.
 * Use the up , down, right and left arrow keys to move.
 */
public class ReachTheTop extends Game {

	PlayField myPlayfield; // the game playfield

	Background myBackground;
	SpriteGroup myPlayerGroup;
	SpriteGroup myEnemyGroup;
	/*SpriteGroup sg;
	OverlayStat os;*/

	AnimatedSprite myBall;

	BombBallCollision myCollision;

	GameFont myFont;

	Stat<Integer> myLives;
	Stat<Integer> myScore;

	static int myStartX;
	static int myStartY;

	/****************************************************************************/
	/**************************** GAME SKELETON *********************************/
	/****************************************************************************/

	public void initResources() {

		// set number of lives and initial score
		/*myLives = new StatInt(4);
		myScore = new StatInt(0);
		os = new OverlayStat("SCORE     : ", myScore);
		OverlayStatImage osi = new OverlayStatImage(getImage("resources/transBall.gif"), 10, 10);
		OverlayIcon oi = new OverlayIcon(myLives, osi, "LIVES     : ");
		os.setLocation(10, 50);
		oi.setLocation(10, 30);
		sg = new SpriteGroup(null);
		sg.add(os);
		sg.add(oi);*/
		Resources.setGame(this);
		Resources.loadImage("ball", "resources/transBall.gif");
		
		
		OverlayCreator.setGame(this);
		OverlayTracker track = OverlayCreator.createOverlays("src/vooga/examples/overlay/sample/resources/myOverlays.xml");
		myLives = track.getStats().get(2);
		myScore = track.getStats().get(3);

		// create the game playfield
		myPlayfield = new PlayField();
		myPlayfield.addGroup(track.getOverlayGroups().get(0));
		myPlayfield.addGroup(track.getOverlayGroups().get(1));
		myPlayfield.addGroup(track.getOverlayGroups().get(2));
		
				

		// associate the playfield with a background
		myBackground = new ImageBackground(
				getImage("resources/background_clouds2.jpg"), 640, 480);
		myPlayfield.setBackground(myBackground);

		// set starting point for ball
		myStartX = myBackground.getWidth() / 2;
		myStartY = myBackground.getHeight() - 100;

		// create our player sprite
		myBall = new AnimatedSprite(getImages("resources/transBomb2.gif", 1, 1),
				287.5, 390);
		myBall.setAnimate(false);
		myBall.setLoopAnim(false);

		// create the player sprite group
		myPlayerGroup = new SpriteGroup("Player");
		myEnemyGroup = new SpriteGroup("Enemy");

		// add all groups into our playfield
		myPlayfield.addGroup(myPlayerGroup);
		myPlayfield.addGroup(myEnemyGroup);

		// insert sprites into the player sprite group
		myPlayerGroup.add(myBall);

		// inserts sprites in rows to ENEMY_GROUP
		BufferedImage image1 = getImage("resources/transBall.gif");
		BufferedImage image = ImageUtil.resize(image1, 30, 30);
		int startX = 10, startY = 30; // starting coordinate
		for (int j = 0; j < 4; j += 2) { // 4 rows
			for (int i = 0; i < 7; i += 2) { // 7 sprites in a row
				Sprite enemy = new Sprite(image, startX + (i * 80), startY
						+ (j * 70));
				Random val = new Random();
				double horizontalSpeed = val.nextDouble();
				double verticalSpeed = val.nextDouble();
				enemy.setHorizontalSpeed((horizontalSpeed - 0.5) / 4);
				enemy.setVerticalSpeed((verticalSpeed - 0.5) / 4);

				myEnemyGroup.add(enemy);
			}
		}

		// register collision
		myCollision = new BombBallCollision(this);

		// register collision to playfield
		myPlayfield.addCollisionGroup(myPlayerGroup, myEnemyGroup, myCollision);

		myFont = fontManager.getFont(getImages("resources/font.png", 20, 3),
				" !            .,0123" + "456789:   -? ABCDEFG"
						+ "HIJKLMNOPQRSTUVWXYZ ");
		/*os.setFont(new Font("hello", Font.ITALIC, 22));
		os.setColor(Color.GREEN);*/
	}

	public void update(long elapsedTime) {

		// playfield updates all the groups and check for collision
		myPlayfield.update(elapsedTime);

		// enemy sprite wall bounce
		Sprite[] sprites = myEnemyGroup.getSprites();
		int size = myEnemyGroup.getSize();

		// iterate over the enemy sprites group and adjust the speed
		for (int i = 0; i < size; i++) {
			Sprite thisSprite = sprites[i];
			if (thisSprite.getY() + thisSprite.getHeight() > myBackground.getHeight()
					|| thisSprite.getY() < 0) {
				thisSprite.setVerticalSpeed(-thisSprite.getVerticalSpeed());
			}
			if ((thisSprite.getX() + thisSprite.getWidth() > myBackground.getWidth())
					|| thisSprite.getX() < 0) {
				thisSprite.setHorizontalSpeed(-thisSprite.getHorizontalSpeed());
			}
		}

		// control the sprite with arrow key
		double speedX = 0;
		double speedY = 0;

		if (keyDown(KeyEvent.VK_LEFT))
			speedX = -0.1;
		if (keyDown(KeyEvent.VK_RIGHT))
			speedX = 0.1;
		if (keyDown(KeyEvent.VK_DOWN))
			speedY = 0.1;
		if (keyDown(KeyEvent.VK_UP))
			speedY = -0.1;

		myBall.setHorizontalSpeed(speedX);
		myBall.setVerticalSpeed(speedY);

		// check if ball has crossed finish line
		if (myBall.getY() < 50) {
			myScore.setStat(myScore.getStat() + 10);
			myBall.setX(myStartX);
			myBall.setY(myStartY);
		}
		/*sg.update(elapsedTime);*/
	}

	public void render(Graphics2D g) {

		// render the background and the sprite groups
		myPlayfield.render(g);
		/*sg.render(g);*/

		if (myLives.getStat() < 1) {
			// game over
			myFont.drawString(g, "GAME OVER!!!", myBackground.getWidth() / 3,
					myBackground.getHeight() / 2);
			myFont.drawString(g, "FINAL SCORE:" + myScore, myBackground
					.getWidth() / 3, myBackground.getHeight() / 2 + 20);

		} else {
			// draw info text
			myFont.drawString(g, "ARROW KEY : MOVE", 10, 10);
			//myFont.drawString(g, "LIVES     : " + myLives, 10, 30);
			//myFont.drawString(g, "SCORE     : " + myScore.getStat(), 10, 50);
		}
	}

	/****************************************************************************/
	/***************************** START-POINT **********************************/
	/****************************************************************************/

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new ReachTheTop(), new Dimension(640, 480), false);
		game.start();
	}

	public void deductLife() {
		myLives.setStat(myLives.getStat()-1);
	}

	public boolean livesLeft() {
		if (myLives.getStat() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
