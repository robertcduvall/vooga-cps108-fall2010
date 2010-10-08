package vooga.games.zombieland;

import vooga.engine.player.control.GameEntitySprite;
import com.golden.gamedev.object.Sprite;

public abstract class Item extends GameEntitySprite {
	
	private Shooter player;
	
	public Item(Shooter shooter, Sprite s, double x, double y){
		super("Item", "Weapon", s);
		setX(x);
		setY(y);
		player=shooter;
	}
	public Shooter getPlayer(){
		return player;
	}
	public abstract void act();
}
