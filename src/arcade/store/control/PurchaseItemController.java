package arcade.store.control;

import javax.swing.JOptionPane;

import arcade.core.api.Tab;
import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.lobby.model.Profile;
import arcade.lobby.model.ProfileSet;
import arcade.store.StoreModel;
import arcade.store.gui.pages.GamePurchaseView;
import arcade.store.items.IItemInfo;
import arcade.wall.models.data.review.Review;
import arcade.wall.models.data.review.ReviewSet;

/**
 * 
 * @author: 		Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date:			12-16-10
 * @description:	This is the controller for the StoreModel and the 
 * 					GamePurchaseView class. This class specifically 
 * 					handles user purchases and the necessary 
 * 					processing, and privilege checking that go
 * 					in between.
 */

public class PurchaseItemController implements IController {

	private StoreModel storeModel;
	private GamePurchaseView view;

	public PurchaseItemController(IModel model) {
		storeModel = (StoreModel) model;
	}

	/**
	 * This method initializes the controller given an IITemInfo object
	 * @param item
	 */
	public void initializeViewerFromItemInfo(IItemInfo item) {
		if (item == null)
			return;

		setTextField(item);
		setButtonPrivilege();
	}

	private void setButtonPrivilege() {
		view.setAddToCartButtonClickable(storeModel.checkPrivileges("purchase"));
		
		int currentUserId = Integer.parseInt(storeModel.getCurrentUserAccount().getId());
		for(String s : StoreModel.getUserOwnedGamesAsStrings(currentUserId)) {
			if(s.equals(view.getItemName())) {
				view.setAddToCartButtonClickable(false);
			}
		}
	}

	/**
	 * This method sets up the textfields that are used to populate the page.
	 */
	private void setTextField(IItemInfo item) {
		view.getDescriptionTextArea().setText(item.getDescription());
		view.getTitleTextField().setText(item.getTitle());
		view.getPriceTextField().setText(item.getPrice());
		view.getGameIcon().setIcon(
				(item.getImages().get(IItemInfo.COVER_IMAGE)));
		view.getGameIcon().setSize(150, 150);
	}

	@Override
	public void addModel(IModel model) {
		storeModel = (StoreModel) model;
	}

	@Override
	public void addViewer(Tab viewer) {
		view = (GamePurchaseView) viewer;
	}

	/**
	 * This method processes the button press for adding a game to to the user's
	 * shopping list
	 * 
	 */
	public void processConfirmAddToCart() {
		int ret = JOptionPane.showConfirmDialog(null,
				"Are You Sure You Want to Add This Item To Cart?",
				"Add to Cart", JOptionPane.YES_NO_OPTION);
		if (ret == JOptionPane.YES_OPTION) {
			storeModel.getCurrentUserAccount().addToCart(view.getItemName());
			
		}
	}

	/**
	 * This method processes the button press for demoing a game on the Game
	 * Purchase View
	 */
	public void processConfirmDemoGame() {
	
		JOptionPane.showConfirmDialog(null,
				"Are You Sure You Want to Demo This Game?", "Demo Game",
				JOptionPane.YES_NO_OPTION);
	}

	/**
	 * This method specifies what will happened after the user confirms to demo
	 * the game
	 */
	public void processDemoGame() {
		
	}


	@Override
	public void initialize() {
		
	}


}
