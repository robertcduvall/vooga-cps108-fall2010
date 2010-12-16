package arcade.mod.controller;

import com.golden.gamedev.object.Sprite;

import vooga.engine.core.BetterSprite;
import vooga.engine.overlay.Overlay;
import vooga.engine.overlay.Stat;
import vooga.engine.state.GameStateManager;
import vooga.games.cyberion.sprites.playership.PlayerShip;
import vooga.games.cyberion.states.PlayState;

public class IncreaseLives implements IConsoleCommand {

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
