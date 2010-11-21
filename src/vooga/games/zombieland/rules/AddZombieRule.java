package vooga.games.zombieland.rules;

import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

import vooga.engine.level.Rule;
import vooga.games.zombieland.Shooter;
import vooga.games.zombieland.Zombie;

public class AddZombieRule implements Rule{

	private static final int ONE_SECOND_IN_MILLI = 1000;
	private Timer timer; 
	
	public AddZombieRule()
	{
		timer = new Timer(ONE_SECOND_IN_MILLI);
		
	}
	
	
	@Override
	public void enforce(SpriteGroup... groups) {
		
		SpriteGroup humangroup = groups[0];
		Shooter human = (Shooter) groups[0].getActiveSprite();
		
		SpriteGroup zombiegroup = groups[1];
		Zombie newZombie = new Zombie();
		newZombie.setHumanTarget(human);
		zombiegroup.add(newZombie);
	}

	@Override
	public boolean isSatisfied(SpriteGroup... groups) {
		
		return (timer.action(ONE_SECOND_IN_MILLI));
		
	}

}
