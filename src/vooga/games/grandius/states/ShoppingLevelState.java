package vooga.games.grandius.states;

import vooga.engine.core.Game;
import vooga.engine.state.MenuGameState;
import vooga.games.grandius.sprites.buttons.BuyBlackHoleButton;
import vooga.games.grandius.sprites.buttons.BuyMissileButton;
import vooga.games.grandius.sprites.buttons.NextLevelButton;

public class ShoppingLevelState extends MenuGameState {

	private BuyMissileButton myBuyMissileButton;
	private BuyBlackHoleButton myBuyBlackHoleButton;
	private NextLevelButton myNextLevelButton;
	private Game myGame;
	 //TODO distinguish between initialize() and constructor in terms of what they need to do
	public ShoppingLevelState(Game game) {
		this.myGame = game;
	}

	@Override
	public void initialize() {
		this.myBuyMissileButton = new BuyMissileButton(myGame);
		this.myBuyBlackHoleButton = new BuyBlackHoleButton(myGame);
		this.myNextLevelButton = new NextLevelButton(myGame);
		addButton(myBuyMissileButton);
		addButton(myBuyBlackHoleButton);
		addButton(myNextLevelButton);
	}
	
}
