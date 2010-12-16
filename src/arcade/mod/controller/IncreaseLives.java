package arcade.mod.controller;

import vooga.engine.core.BetterSprite;
import vooga.games.cyberion.sprites.playership.PlayerShip;

public class IncreaseLives implements IConsoleCommand {

	private static final String PLAYER_GROUP = "playerGroup";

	@Override
	public void performCommand(String myInput) {
		for (BetterSprite bs : GameConsole.allSprites) {
			if (bs.getLabel().equals(PLAYER_GROUP)) {

				System.out.println(bs.getX());

				// ((PlayerShip) bs.getCurrentSprite())
				// .setX(((PlayerShip) bs.getCurrentSprite())
				// .getLife() + 1);

				((PlayerShip) bs.getCurrentSprite()).setX(0);
				GameConsole.setModified(true);

			}

		}

	}
}
