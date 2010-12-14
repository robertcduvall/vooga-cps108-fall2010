package vooga.games.zombies.events;

import java.util.Stack;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEventHandler;
import vooga.games.zombies.Zombie;

public class AddZombieEvent implements IEventHandler {

	private Stack<Zombie> zombieStack = new Stack<Zombie>();
	private SpriteGroup zombies;
	private boolean timer;

	public AddZombieEvent(SpriteGroup group) {
		zombies = group;
		timer = false;
	}

	@Override
	public boolean isTriggered() {
		return !zombieStack.isEmpty() && timer;
	}

	@Override
	public void actionPerformed() {
		timer = false;
		buildEnemy(zombieStack.pop());

	}

	private void buildEnemy(Zombie enemy) {
		zombies.add(enemy);
	}

	public void addEnemy(Zombie enemy) {
		zombieStack.add(enemy);
	}

	public void timeUp() {
		timer = true;
	}

}
