package vooga.games.mariogame.sprites;

import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.Stat;
import vooga.games.mariogame.items.Item;
import vooga.games.mariogame.rules.Gravity;

import com.golden.gamedev.object.AnimatedSprite;

@SuppressWarnings("serial")
public class MarioSprite extends BetterSprite {

	private double jumpSpeed = 1;
	private double speed = .5;
	private boolean onGround;
	private double myMaxX;
	private int myMaxHealth = 3;
	private boolean myLevelFinished = false;

//	private Queue<Character> myCheatText;
//	private static final int MAX_CHEAT_LENGTH = 10;
	private static final long BIG_TIME = 10000L;
//	private char lastCheatChar;
	private boolean isBig;
	private long bigStart;

	public MarioSprite(){		
	}
	
	/*
	public MarioSprite(String name, BufferedImage[] left) {
		super(name, left);
		setStat("Score", new Stat<Integer>(0));

		myCheatText = new ArrayBlockingQueue<Character>(MAX_CHEAT_LENGTH);
	}
	*/

	private void showAnimation(String direction) {
		setAsRenderedSprite(direction);
		((AnimatedSprite) getCurrentSprite()).setAnimate(true);
	}
	
	public void moveRight() {
		if(isBig) showAnimation("marioRightBig");
		else showAnimation("marioRight");
		setHorizontalSpeed(speed);
	}

	public void moveLeft() {
		if(isBig) showAnimation("marioLeftBig");
		else showAnimation("marioLeft");
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
		setHealth(getHealth() - 1);
		setMaxX(0);
		Gravity.reset();
		isBig = false;
	}



	@Override
	public void update(long elapsedTime) {
		if(isBig && System.currentTimeMillis()-bigStart > BIG_TIME) {
			isBig = false;
		}
		
		if (getY() > this.getBackground().getHeight()) {
			setHealth(getHealth() - 1);
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
			System.out.println("hit left wall");
			System.out.println("maxX: "+myMaxX);
			System.out.println("width: "+getBackground().getWidth());
			System.out.println("current x: "+x);
			setX(myMaxX - halfScreen);
		}
		setOnGround(false);
	}

	public double getMaxX() {
		return myMaxX;
	}

	public void setMaxX(double maxX) {
		myMaxX = maxX;
	}

	public void actOnItem(Item item) {
		if (item.getType().equals("Gravity")) {
			Gravity.setGravityCoef(.5);
		} else if(item.getType().equals("Big")) {
			isBig = true;
			bigStart = System.currentTimeMillis();
		} else if(item.getType().equals("Life")) {
			setHealth(getHealth()+1);
		}
	}

	@SuppressWarnings("unchecked")
	public void incScore(int i) {
		Stat<Integer> stat = (Stat<Integer>) getStat("score");
		stat.setStat(stat.getStat() + i);
		if((stat.getStat()%100) == 0){
			setHealth(getHealth() + 1);
		}
	}

	@SuppressWarnings("unchecked")
	public void setScore(int i) {
		Stat<Integer> stat = ((Stat<Integer>) getStat("score"));
		stat.setStat(i);
	}

	public Integer getScore() {
		return (Integer) getStat("score").getStat();
	}

//	private boolean checkCheat(String s) {
//		String curCheat = "";
//		for (char i : myCheatText)
//			curCheat += i;
//		if (myCheatText.size() - s.length() < 0)
//			return false;
//		if (s.equals(curCheat.substring(myCheatText.size() - s.length())))
//			return true;
//		return false;
//	}

	/*
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
	*/
	
	public int getMaxHealth(){
		return myMaxHealth;
	}
	
	public int getHealth(){
		return (Integer) this.getStat("health").getStat();
	}
	
	@SuppressWarnings("unchecked")
	public void setHealth(int i){
		Stat<Integer> stat = ((Stat<Integer>) getStat("health"));
		stat.setStat(i);
	}
	
	public void setLevelFinsihed(boolean value){
		myLevelFinished = value;
	}
	
	public boolean levelFinished(){
		return myLevelFinished;
	}

}
