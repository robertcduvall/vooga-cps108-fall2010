package vooga.games.towerdefense.events;

import vooga.engine.level.GameLostConditions;
import vooga.games.towerdefense.actors.Player;

public class GameLost extends GameLostConditions {

	private Player player;

	public GameLost(Player p)
	{
		player = p;
	}
	public boolean GameLost() {
		return player.getSelfEsteem()!=0;
	}
}
