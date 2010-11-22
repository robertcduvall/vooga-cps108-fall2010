package vooga.games.mariogame.sprites;

import java.util.Queue;

import com.golden.gamedev.object.Sprite;

import vooga.engine.overlay.Stat;
import vooga.engine.core.BetterSprite;
import vooga.games.mariogame.items.GravityItem;

@SuppressWarnings("serial")
public class MarioSprite extends BetterSprite {

	private double jumpSpeed = 1;
	private double speed = .5;
	private boolean onGround;
	private boolean killed;
	private double myMaxX;
	private Stat<Integer> myHealth;
	private int myMaxHealth = 3;
	private double myGravity;

	private Queue<Character> myCheatText;
	private static final int MAX_CHEAT_LENGTH = 10;
	private char lastCheatChar;

	public MarioSprite(){
		myHealth = new Stat<Integer>(100);
		
	}
	
	/*
	public MarioSprite(String name, BufferedImage[] left) {
		super(name, left);
		setStat("Score", new Stat<Integer>(0));

		myCheatText = new ArrayBlockingQueue<Character>(MAX_CHEAT_LENGTH);
	}
	*/

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
	
	public void respawn(){
		setLocation(0, 0);
		//getBackground().setLocation(0, 0);
		setKilled(false);
		setMaxX(0);
		//setGravityCoef(1);
	}

	public boolean isKilled() {
		return killed;
	}

	public void setKilled(boolean b) {
		killed = b;
	}

	@Override
	public void update(long elapsedTime) {
		if (getY() > this.getBackground().getHeight()) {
			setHealth(myHealth.getStat() - 1);
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

	public void actOnItem(Sprite item) {
		if (item.getClass().equals(GravityItem.class)) {
			setGravity(((GravityItem) item).getGravity());
		}
	}

	@SuppressWarnings("unchecked")
	public void incScore(int i) {
		Stat<Integer> stat = (Stat<Integer>) getStat("Score");
		stat.setStat(stat.getStat() + i);
		if((stat.getStat()%100) == 0){
			myHealth.setStat(myHealth.getStat() + 1);
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
			setGravity(.2);
		else if (checkCheat("NORM"))
			setGravity(1.0);
		else if (checkCheat("MORE"))
			speed += .1;
		else if (checkCheat("EROM"))
			speed -= .1;

	}
	
	public double getGravity(){
		return myGravity;
	}
	
	public void setGravity(Double d){
		myGravity = d;
	}
	
	public int getMaxHealth(){
		return myMaxHealth;
	}
	
	public int getHealth(){
		return myHealth.getStat();
	}
	
	public void setHealth(int i){
		myHealth.setStat(i);
	}

}
