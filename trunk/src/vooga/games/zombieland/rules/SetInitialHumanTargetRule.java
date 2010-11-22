package vooga.games.zombieland.rules;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.level.Rule;
import vooga.games.zombieland.Shooter;
import vooga.games.zombieland.Zombie;

public class SetInitialHumanTargetRule implements Rule{

	
	private static final int ZOMBIE_GROUP_LOCATION = 1;
	private static final int HUMAN_GROUP_LOCATION = 0;

	@Override
	public void enforce(SpriteGroup... groups) {
		
		SpriteGroup humanGroup = groups[HUMAN_GROUP_LOCATION];
		Shooter human = (Shooter) humanGroup.getActiveSprite();
		
		SpriteGroup zombieGroup = groups[ZOMBIE_GROUP_LOCATION];
		
		for(Sprite zombie: zombieGroup.getSprites())
		{
			if(zombie != null)
			{
				((Zombie) zombie).setHumanTarget(human);
			}
		}
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		
		SpriteGroup humanGroup = groups[HUMAN_GROUP_LOCATION];
		Shooter human = (Shooter) humanGroup.getActiveSprite();
		
		SpriteGroup zombieGroup = groups[ZOMBIE_GROUP_LOCATION];
		
		if(human.getTimeInExistence() < 5)
		{	
			for(Sprite zombie : zombieGroup.getSprites())
			{
				if( ((Zombie) zombie).getTarget() == human )
				{
					return true;
				}	
			}
			return false;
		}
		else
		{
			return true;
		}
	}

}
