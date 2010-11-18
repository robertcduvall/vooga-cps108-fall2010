package vooga.games.galaxyinvaders;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.core.Sprite;
import vooga.engine.resource.Resources;

public class Ship extends Sprite{

	private static final double PLAYER_BOMB_SPEED = 0.8;
	private static final int X = 350;
	private static final int Y = 700;
	
	private SpriteGroup torpedos;
	
	public Ship(SpriteGroup t){
		super(Resources.getImage("ship"), X, Y);
		torpedos = t;
	}
	
	private void fire() {
		Sprite temp = new Sprite(Resources.getImage("torpedo"), getX()+25, getY()-35);
		temp.setSpeed(0, -PLAYER_BOMB_SPEED);
		torpedos.add(temp);
	}

	private void moveLeft(int distance) {
		if(getX()>0-15)  
			move(distance, 0);
	}

	private void moveRight(int distance) {
		if(getX()<getWidth()-45)  
			move(distance, 0);
	}
	
}
