package vooga.games.galaxyinvaders;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;

public class Ship extends BetterSprite{

	private static final double PLAYER_BOMB_SPEED = 0.8;
	private static final int INITIAL_SHIP_X = 350;
	private static final int INITIAL_SHIP_Y = 700;
	
	private SpriteGroup torpedos;
	
	public Ship(){
		super(Resources.getImage("ship"), INITIAL_SHIP_X, INITIAL_SHIP_Y);
		torpedos = new SpriteGroup("shots");
	}
	
	public void update(long elapsedTime){
		torpedos.update(elapsedTime);
	}
	
	private void fire() {
		BetterSprite temp = new BetterSprite(Resources.getImage("torpedo"), getX()+25, getY()-35);
		temp.setSpeed(0, -PLAYER_BOMB_SPEED);
		torpedos.add(temp);
	}

	private void moveLeft(int distance) {
		if(getX()>0-15)  
			move(-distance, 0);
	}

	private void moveRight(int distance) {
		if(getX()<getWidth()-45)  
			move(distance, 0);
	}
	
	public SpriteGroup getTorpedoGroup(){
		return torpedos;
	}
	
}
