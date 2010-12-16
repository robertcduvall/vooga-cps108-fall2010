package arcade.store.control;

import javax.swing.JOptionPane;

import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;

import arcade.store.gui.pages.GamePurchaseView;
import arcade.store.items.IItemInfo;
import arcade.store.privileges.PrivilegeManager;

public class PurchaseItemController implements IController {

	private StoreModel storeModel;
	private GamePurchaseView view;

	public PurchaseItemController(IModel model) {
		storeModel = (StoreModel) model;
	}

	@Override
	public void initialize() {

	}

	public void initializeViewerFromItemInfo(IItemInfo item) {
		if (item == null)
			return;

		view.getDescriptionTextArea().setText(item.getDescription());
		view.getTitleTextField().setText(item.getTitle());
		view.getPriceTextField().setText(item.getPrice());
		view.getGameIcon().setIcon(
				(item.getImages().get(IItemInfo.COVER_IMAGE)));
		view.getGameIcon().setSize(150, 150);

		view.setAddToCartButtonClickable(storeModel.checkPrivileges("purchase"));
		
		int currentUserId = Integer.parseInt(storeModel.getCurrentUserAccount().getId());
		for(String s : StoreModel.getUserOwnedGamesAsStrings(currentUserId)) {
			if(s.equals(getItemName())) {
				view.setAddToCartButtonClickable(false);
			}
		}
	}


	@Override
	public void addModel(IModel model) {
		storeModel = (StoreModel) model;
	}

	@Override
	public void addViewer(IViewer viewer) {
		view = (GamePurchaseView) viewer;
	}

	private String getItemName() {
		return view.getTitleTextField().getText();
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
			storeModel.getCurrentUserAccount().addToCart(getItemName());
			
		}
	}

	/**
	 * This method processes the button press for demoing a game on the Game
	 * Purchase View
	 */
	public void processConfirmDemoGame() {
		// TODO: demo functionality?
		JOptionPane.showConfirmDialog(null,
				"Are You Sure You Want to Demo This Game?", "Demo Game",
				JOptionPane.YES_NO_OPTION);
	}

	/**
	 * This method specifies what will happened after the user confirms to demo
	 * the game
	 */
	public void processDemoGame() {
		// TODO Auto-generated method stub

	}

}
