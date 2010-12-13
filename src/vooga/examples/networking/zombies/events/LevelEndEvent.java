package vooga.examples.networking.zombies.events;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.examples.networking.zombies.Shooter;
import vooga.examples.networking.zombies.Zombie;
import vooga.examples.networking.zombies.gamestates.PlayState;

public class LevelEndEvent implements IEventHandler {

	private Shooter target;
	private PlayState state;

	private AddZombieEvent addZombies;
	private AddRandomItemEvent addItems;
	private long seed;

	private double levelDeaths;

	public LevelEndEvent(Shooter player, PlayState currentState,
			IEventHandler addzombies, AddRandomItemEvent additems, long seed) {
		state = currentState;
		levelDeaths = levelZombies();
		target = player;
		addZombies = (AddZombieEvent) addzombies;
		addItems = additems;
		this.seed = seed;
		// createZombies();
	}

	@Override
	public boolean isTriggered() {
		return target.getScore().getStat() == (int) levelDeaths;
	}

	@Override
	public void actionPerformed() {
		createZombies();
		state.setNewDelay();
		target.resetLevelScore();

	}

	public void updateDeaths(int deaths) {
		levelDeaths += deaths;
	}

	public void createZombies() {

		int level = state.getLevel();
		state.getLevelStatOverlay().setActive(true);
		state.getStatLevel().setStat(level + 1);
		state.setLevel(level + 1);

		// overlayLevelStat.setActive(true);
		// statLevel.setStat(level + 1);
		// level++;

		int size = 0;
		while (size < levelZombies()) {
			Zombie zombie = new Zombie("New", state.getLevel(), target, state, seed);
			zombie.setDropItemListener(addItems);
			addZombies.addEnemy(zombie);
			size++;
		}
		updateDeaths(levelZombies());
	}

	public int levelZombies() {
		return (int) (Zombie.zombiesPerLevel() * state.getLevel() * (Double) Resources
				.getDouble("zombieLimitingFactor"));
	}
}
