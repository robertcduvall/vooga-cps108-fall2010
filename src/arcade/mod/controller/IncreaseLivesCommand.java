package arcade.mod.controller;

import vooga.engine.overlay.Stat;
import vooga.games.cyberion.sprites.playership.PlayerShip;
import vooga.games.cyberion.states.PlayState;

public class IncreaseLivesCommand implements IConsoleCommand {

	@Override
	public void performCommand(String myInput) {

		PlayState ps = (PlayState) GameConsole.playState;
		PlayerShip ship = ps.getPlayer();
		ship.setLife(ship.getLife() + 1);
		Stat<Integer> stat = (Stat<Integer>) ship.getStat("livesStat");
		System.out.println(ship.getStat("livesStat").getStat());
		stat.setStat(ship.getLife());
		ship.setWeaponPower(3);

		GameConsole.playState = ps;
	}

}
