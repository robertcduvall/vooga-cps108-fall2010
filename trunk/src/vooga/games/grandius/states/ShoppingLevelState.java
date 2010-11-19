package vooga.games.grandius.states;

import vooga.engine.core.Game;
import vooga.engine.state.MenuGameState;
import vooga.games.grandius.buttons.BuyBlackHoleButton;
import vooga.games.grandius.buttons.BuyMissileButton;
import vooga.games.grandius.buttons.NextLevelButton;
//TODO this GameState should be implemented using a MenuGameState
//private void buildShoppingLevelState() {
//	shoppingLevelState = new GameState();
//	int displayX = Resources.getInt("shoppingLevelX");
//	int displayY = Resources.getInt("shoppingLevelY");
//
//	//shoppingLevel1 is an OverlayStat vs. String (displays Stat)
//	OverlayStat shoppingLevel1 = new OverlayStat("CASH: ", statCash);
//	shoppingLevel1.setFont(font);
//	shoppingLevel1.setLocation(displayX,displayY);
//	shoppingLevelGroup.add(shoppingLevel1);
//
//	for(int i=2;i<5;i++){
//		displayY=displayY*i;
//		OverlayString shoppingLevel = new OverlayString(Resources.getString("shoppingLevel"+i), font);
//		shoppingLevel.setLocation(displayX, displayY);
//		shoppingLevelGroup.add(shoppingLevel);
//	}
//	shoppingLevelState.addGroup(backgroundGroup);
//	shoppingLevelState.addGroup(shoppingLevelGroup);
//}
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
