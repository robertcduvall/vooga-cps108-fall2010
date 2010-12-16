package arcade.mod.controller;

import vooga.engine.overlay.Stat;
import vooga.games.cyberion.sprites.enemyship.EnemyShip;
import vooga.games.cyberion.states.PlayState;

public class IncreaseScore implements IConsoleCommand {

	@Override
	public void performCommand(String myInput) {
		PlayState ps = (PlayState) GameConsole.playState;
		EnemyShip ship = ps.getEnemy();
		ship.setLife(ship.getLife() + 1);
		Stat<Integer> stat = (Stat<Integer>) ship.getStat("scoreStat");

		stat.setStat(stat.getStat() + 10);

		GameConsole.playState = ps;
	}

}
