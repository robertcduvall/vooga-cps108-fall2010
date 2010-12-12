package vooga.games.digger.actors;

import vooga.engine.core.BetterSprite;
import vooga.engine.resource.Resources;
import vooga.games.digger.EntityKey;
import vooga.games.digger.EntityMap;
import vooga.games.digger.movers.Mover;

public class Player extends BetterSprite {
	
	Mover mover;
	
	public Player(){
		try {
			this.mover = (Mover) EntityMap.resolve(new EntityKey(this.getClass(),Mover.class, Resources.getGame().getMod()));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.mover.setSprite(this);
	}
	
	@Override
	public void update(long elapsedTime){
		mover.move(elapsedTime);
	}

}
