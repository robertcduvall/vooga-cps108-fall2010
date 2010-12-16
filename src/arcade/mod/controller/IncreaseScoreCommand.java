package arcade.mod.controller;

import vooga.engine.overlay.Stat;
import vooga.games.cyberion.sprites.enemyship.EnemyShip;
import vooga.games.cyberion.states.PlayState;

/**
 * Simple implementation of a console command increases score by 10;
 * @author vitorolivier
 * 
 */
public class IncreaseScoreCommand implements IConsoleCommand {

	@Override
	public void performCommand(String myInput) {
		PlayState ps = (PlayState) GameConsole.playState;
		EnemyShip ship = ps.getEnemy();

		Stat<Integer> stat = (Stat<Integer>) ship.getStat("scoreStat");

		stat.setStat(stat.getStat() + 10);

		GameConsole.playState = ps;
	}

}
