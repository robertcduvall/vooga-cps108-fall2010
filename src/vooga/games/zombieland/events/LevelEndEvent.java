package vooga.games.zombieland.events;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.zombieland.Shooter;
import vooga.games.zombieland.Zombie;
import vooga.games.zombieland.gamestates.PlayState;

public class LevelEndEvent implements IEventHandler {

	private Shooter target;
	private PlayState state;

	private AddZombieEvent addZombies;
	private AddRandomItemEvent addItems;

	private double levelDeaths;

	public LevelEndEvent(Shooter player, PlayState currentState,
			IEventHandler addzombies, AddRandomItemEvent additems) {
		state = currentState;
		levelDeaths = levelZombies();
		target = player;
		addZombies = (AddZombieEvent) addzombies;
		addItems = additems;
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

			Zombie zombie = new Zombie("New", state.getLevel(), target, state);
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
