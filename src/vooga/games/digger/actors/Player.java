package vooga.games.digger.actors;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.digger.EntityKey;
import vooga.games.digger.EntityMap;
import vooga.games.digger.movers.Mover;
import vooga.games.digger.shooters.Shooter;

public class Player extends BetterSprite {
	
	private static final long serialVersionUID = 1L;
	Mover mover;
	Shooter shooter;
	
	public Player(){
		try {
			mover = (Mover) EntityMap.resolve(new EntityKey(this.getClass(),Mover.class, Resources.getGame().getMod()));
			shooter = (Shooter) EntityMap.resolve(new EntityKey(this.getClass(),Shooter.class, Resources.getGame().getMod()));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mover.setSprite(this);
		shooter.setSprite(this);
	}
	
	@Override
	public void update(long elapsedTime){
		mover.move(elapsedTime);
		shooter.shoot(elapsedTime);
	}

}
