package arcade.store.control;

import javax.swing.JOptionPane;

import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;
import arcade.store.gui.pages.AreYouSureAddToCartView;
import arcade.store.gui.pages.AreYouSureDemoView;
import arcade.store.gui.pages.GamePurchaseView;
import arcade.store.items.IItemInfo;

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
		view.setGameIcon(item.getImages().get(IItemInfo.COVER_IMAGE));
	}

	@Override
	public void addModel(IModel model) {
		storeModel = (StoreModel) model;
	}

	@Override
	public void addViewer(IViewer viewer) {
		view = (GamePurchaseView) viewer;

	}

	/**
	 * This method processes the button press for adding a game to to the user's
	 * shopping list
	 * 
	 */
	public void processConfirmAddToCart() {
		// new AreYouSureAddToCartView(this);
		JOptionPane.showConfirmDialog(null,
				"Are You Sure You Want to Add This Item To Cart?",
				"Add to Cart", JOptionPane.YES_NO_OPTION);

	}

	/**
	 * This method processes the button press for demoing a game on the Game
	 * Purchase View
	 */
	public void processConfirmDemoGame() {

		new AreYouSureDemoView(this);
	}

	/**
	 * This method specifies what will happened after the user confirms to buy
	 * the item
	 */
	public void processAddToCart() {

		String gameName = view.getTitleTextField().getText();
		storeModel.addToCart(gameName);
	}

	/**
	 * This method specifies what will happened after the user confirms to demo
	 * the game
	 */
	public void processDemoGame() {
		// TODO Auto-generated method stub

	}

}
