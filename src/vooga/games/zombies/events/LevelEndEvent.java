package vooga.games.zombies.events;

import java.util.Random;

import vooga.engine.event.IEventHandler;
import vooga.engine.resource.Resources;
import vooga.games.zombies.Shooter;
import vooga.games.zombies.Zombie;
import vooga.games.zombies.gamestates.PlayState;

public class LevelEndEvent implements IEventHandler {

	private Shooter[] targets;
	private Shooter target;
	private PlayState state;

	private AddZombieEvent addZombies;
	private AddRandomItemEvent addItems;
	private long seed;

	private double levelDeaths;

	public LevelEndEvent(Shooter[] players, PlayState currentState,
			IEventHandler addzombies, AddRandomItemEvent additems, long seed) {
		state = currentState;
		levelDeaths = levelZombies();
		targets = players;
		addZombies = (AddZombieEvent) addzombies;
		addItems = additems;
		this.seed = seed;
		// createZombies();
	}
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
		int score = 0;
		for(Shooter target : targets){
			if(target != null)
				score += target.getScore().getStat();
		}
		return score == (int) levelDeaths;
	}

	@Override
	public void actionPerformed() {
		createZombies();
		state.setNewDelay();
		for(Shooter target : targets){
			if(target != null)
				target.resetLevelScore();
		}
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
		Random random = new Random(seed);
		while (size < levelZombies()) {
			Zombie zombie = new Zombie("New", state.getLevel(), targets, state, random);
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
