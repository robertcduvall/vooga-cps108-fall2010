package vooga.games.zombieland;

import vooga.engine.player.control.GameEntitySprite;
import com.golden.gamedev.object.Sprite;

public abstract class Item extends GameEntitySprite {
	
	private Shooter myPlayer;
	
	public Item(Shooter shooter, Sprite s, double x, double y){
		super("Item", "Weapon", s);
		setX(x);
		setY(y);
		myPlayer= shooter;
	}
	public Shooter getPlayer(){
		return myPlayer;
	}
	public abstract void act();
}
