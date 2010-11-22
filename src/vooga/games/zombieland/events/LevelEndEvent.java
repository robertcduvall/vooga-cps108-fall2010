package vooga.games.zombieland.events;

import java.util.Stack;

import com.golden.gamedev.object.Timer;

import vooga.engine.event.IEventHandler;
import vooga.games.zombieland.Shooter;
import vooga.games.zombieland.Zombie;
import vooga.games.zombieland.gamestates.PlayState;

public class LevelEndEvent implements IEventHandler{

	private double levelDeaths;
	private Shooter target;
	private PlayState state;
	
	public LevelEndEvent(Shooter player, PlayState currentState, double properDeaths)
	{
		levelDeaths = properDeaths;
		target = player;
		state = currentState;
	}
	
	@Override
	public boolean isTriggered() {
		return target.getScore().getStat() == (int)levelDeaths;
	}

	@Override
	public void actionPerformed() {
		state.createZombies();
		state.setNewDelay();
		target.resetLevelScore();
		
	}
	public void updateDeaths(double deaths)
	{
		levelDeaths += deaths;
	}

}
