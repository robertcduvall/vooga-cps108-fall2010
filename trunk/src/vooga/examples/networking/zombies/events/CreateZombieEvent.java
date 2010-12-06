package vooga.examples.networking.zombies.events;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.examples.networking.zombies.Shooter;
import vooga.examples.networking.zombies.Zombie;
import vooga.examples.networking.zombies.gamestates.PlayState;

public class CreateZombieEvent implements IEventHandler {

	private boolean isUsedOnce;
	private LevelEndEvent endLevel;
	private AddZombieEvent addZombies;
	private PlayState currentPlayState;
	private Shooter player;
	private int level;

	public CreateZombieEvent(PlayState current, Shooter shooter,
			IEventHandler endLevel, IEventHandler addZombies) {
		isUsedOnce = false;
		level = current.getLevel();
		createZombies();
		player = shooter;
		currentPlayState = current;
		this.endLevel = (LevelEndEvent) endLevel;
		this.addZombies = (AddZombieEvent) addZombies;
	}

	@Override
	public boolean isTriggered() {
		return !isUsedOnce;
	}

	@Override
	public void actionPerformed() {
		isUsedOnce = true;
		createZombies();
	}

	public void createZombies() {
		int size = 0;
		while (size < levelZombies()) {
			addZombies.addEnemy(new Zombie("New", level, player,
					currentPlayState));
			size++;
		}
		endLevel.updateDeaths(levelZombies());
	}

	public int levelZombies() {
		return (int) (Zombie.zombiesPerLevel() * level * (Double) Resources
				.getDouble("zombieLimitingFactor"));
	}
}
