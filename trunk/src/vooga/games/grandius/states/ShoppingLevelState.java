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
	
	public ShoppingLevelState(Game game) {
		this.myBuyMissileButton = new BuyMissileButton(game);
		this.myBuyBlackHoleButton = new BuyBlackHoleButton(game);
		this.myNextLevelButton = new NextLevelButton(game);
	}

	@Override
	public void initialize() {
		addButton(myBuyMissileButton);
		addButton(myBuyBlackHoleButton);
		addButton(myNextLevelButton);
	}
	
}
