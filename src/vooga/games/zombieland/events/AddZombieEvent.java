package vooga.games.zombieland.events;

import java.util.Stack;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEventHandler;
import vooga.games.zombieland.Zombie;

public class AddZombieEvent implements IEventHandler{

	private Stack<Zombie> zombieQueue = new Stack<Zombie>();
	private SpriteGroup zombies;
	private boolean timer;
	
	public AddZombieEvent(SpriteGroup group)
	{
		zombies = group;
		timer = false;
	}
	
	@Override
	public boolean isTriggered() {
		
		return !zombieQueue.isEmpty() && timer;
	}

	@Override
	public void actionPerformed() {
		timer = false;
		buildEnemy(zombieQueue.pop());
		
	}

	private void buildEnemy(Zombie enemy){
		zombies.add(enemy);
	}
	
	public void addEnemy(Zombie enemy){
		zombieQueue.add(enemy);
	}
	
	public void timeUp()
	{
		timer = true;
	}
	
}
