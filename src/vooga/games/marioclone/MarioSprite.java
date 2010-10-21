package vooga.games.marioclone;

import java.awt.image.BufferedImage;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import vooga.engine.overlay.Stat;
import vooga.engine.player.control.ItemSprite;
import vooga.games.marioclone.items.GravityItem;

@SuppressWarnings("serial")
public class MarioSprite extends CharacterSprite {

	private double jumpSpeed = 1;
	private double speed = .5;
	private boolean onGround = false;
	private double myMaxX;
	private Stat<Integer> myEnemiesKilled;

	private Queue<Character> myCheatText;
	private static final int MAX_CHEAT_LENGTH = 10;
	private char lastCheatChar;

	public Stat<Integer> getEnemiesKilled() {
		return myEnemiesKilled;
	}

	public MarioSprite(String name, String stateName, BufferedImage[] left,
			BufferedImage[] right, Stat<Integer> enemiesKilled) {
		super(name, stateName, left, right);
		myEnemiesKilled = enemiesKilled;
		setMaxHealth(3);

		myCheatText = new ArrayBlockingQueue<Character>(MAX_CHEAT_LENGTH);
	}

	public void moveRight() {
		setHorizontalSpeed(speed);
	}

	public void moveLeft() {
		setHorizontalSpeed(-speed);
	}

	public void jump(boolean force) {
		if (isOnScreen()) {
			if (onGround || force) {
				setVerticalSpeed(-jumpSpeed);
				onGround = false;
			}
		}
	}

	@Override
	public void setY(double y) {
		// check if falling
		if (Math.round(y) > Math.round(getY())) {
			onGround = false;
		}
		super.setY(y);
	}

	public void jumpCmd() {
		jump(false);
	}

	public void stop() {
		setHorizontalSpeed(0);
	}

	public void setOnGround(boolean b) {
		onGround = b;
	}

	@Override
	public void update(long elapsedTime) {
		setImage(getCurrentSprite().getImage());
		double x = getX();
		if (x > myMaxX) {
			myMaxX = x;
		}

		super.update(elapsedTime);
		stop();
		if (getX() <= 0) {
			setX(0);
		}
		int halfScreen = getBackground().getWidth() / 2;
		if ((myMaxX - halfScreen) >= getX()) {
			setX(myMaxX - halfScreen);
		}
	}

	public double getMaxX() {
		return myMaxX;
	}

	public void actOnItem(ItemSprite item) {
		if (item.getClass().equals(GravityItem.class)) {
			setGravityCoef(((GravityItem) item).getGravity());
		}
	}

	@SuppressWarnings("unchecked")
	public void incScore(int i) {
		Stat<Integer> stat = (Stat<Integer>) getStat("Kills");
		stat.setStat(stat.getStat() + i);
	}
	
	private boolean checkCheat(String s) {
		String curCheat = "";
		for (char i : myCheatText)
			curCheat += i;
		if(myCheatText.size()-s.length() < 0) return false;
		System.out.println(curCheat.substring(myCheatText.size()-s.length()));
		if(s.equals(curCheat.substring(myCheatText.size()-s.length())))
			return true;
		return false;
	}

	public void cheat(char c) {
		if (c != lastCheatChar) {
			if (myCheatText.size() == MAX_CHEAT_LENGTH)
				myCheatText.poll();
			myCheatText.add(c);
			lastCheatChar = c;
		}
		if(checkCheat("GRVTY")) 
			setGravityCoef(.2);
		else if(checkCheat("NORM")) 
			setGravityCoef(1);
		
		
	}
	
}
