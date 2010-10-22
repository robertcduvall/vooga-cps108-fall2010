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
	private boolean onGround;
	private boolean killed;
	private double myMaxX;

	private Queue<Character> myCheatText;
	private static final int MAX_CHEAT_LENGTH = 10;
	private char lastCheatChar;

	public MarioSprite(String name, String stateName, BufferedImage[] left,
			BufferedImage[] right) {
		super(name, stateName, left, right);
		setMaxHealth(3);

		addStat("Score", new Stat<Integer>(0));

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

	public boolean isKilled() {
		return killed;
	}

	public void setKilled(boolean b) {
		killed = b;
	}

	@Override
	public void update(long elapsedTime) {
		if (!this.isOnScreen()) {
			setHealth(getHealth() - 1);
			killed = true;
		}

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

	public void setMaxX(double maxX) {
		myMaxX = maxX;
	}

	public void actOnItem(ItemSprite item) {
		if (item instanceof GravityItem) {
			setGravityCoef(((GravityItem) item).getGravity());
		}
	}

	@SuppressWarnings("unchecked")
	public void incScore(int i) {
		Stat<Integer> stat = (Stat<Integer>) getStat("Score");
		stat.setStat(stat.getStat() + i);
		System.out.println(stat.getStat());
		if((stat.getStat()%100) == 0){
			System.out.println("incrementing health");
			setHealth(getHealth()+1);
		}
	}

	@SuppressWarnings("unchecked")
	public void setScore(int i) {
		Stat<Integer> stat = ((Stat<Integer>) getStat("Score"));
		stat.setStat(i);
	}

	public Integer getScore() {
		return (Integer) getStat("Score").getStat();
	}

	private boolean checkCheat(String s) {
		String curCheat = "";
		for (char i : myCheatText)
			curCheat += i;
		if (myCheatText.size() - s.length() < 0)
			return false;
		System.out.println(curCheat.substring(myCheatText.size() - s.length()));
		if (s.equals(curCheat.substring(myCheatText.size() - s.length())))
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
		if (checkCheat("GRVTY"))
			setGravityCoef(.2);
		else if (checkCheat("NORM"))
			setGravityCoef(1);
		else if (checkCheat("MORE"))
			speed += .1;
		else if (checkCheat("EROM"))
			speed -= .1;

	}

}
