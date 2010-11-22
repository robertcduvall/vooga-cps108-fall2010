package vooga.games.galaxyinvaders.sprites;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.galaxyinvaders.states.PlayState;

public class Ship extends BetterSprite{
		
	public Ship(){
		super(Resources.getImage("ship"), Resources.getInt("initialShipX"), Resources.getInt("initialShipY"));
	}
	
	public void fire() {
		BetterSprite temp = new BetterSprite(Resources.getImage("torpedo"), getX()+25, getY()-35);
		temp.setSpeed(0, -(Resources.getDouble("playerBombSpeed")));
		PlayState.getPlayField().getGroup("shipTorpedos").add(temp);
	}

	public void moveLeft() {
		if(getX()>0-15)  
			move(-(Resources.getInt("shipMoveDistance")), 0);
	}
	
	public void moveRight() {
		if(getX()<Resources.getGame().getWidth()-45)  
			move(Resources.getInt("shipMoveDistance"), 0);
	}
	
}
