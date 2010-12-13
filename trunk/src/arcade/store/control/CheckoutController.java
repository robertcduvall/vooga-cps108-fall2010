package arcade.store.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;
import arcade.store.account.StoreUser;
import arcade.store.gui.pages.ItemHasBeenDroppedView;
import arcade.store.gui.pages.NoDropSelectedView;
import arcade.store.gui.pages.SorryYouCantDoThisView;
import arcade.store.gui.pages.ThankYouForBuyingView;
import arcade.store.gui.tabs.CheckoutTab;
import arcade.store.items.IItemInfo;

public class CheckoutController implements IController {

	private StoreModel storeModel;
	private CheckoutTab viewer;
	private String currentSelected;

	public CheckoutController() {
		currentSelected = null;
	}

	@Override
	public void addModel(IModel model) {
		storeModel = (StoreModel) model;
	}

	@Override
	public void addViewer(IViewer view) {

		viewer = (CheckoutTab) view;
	}

	@Override
	public void initialize() {

		List<String> currentCart = storeModel.getCurrentUserAccount().getCart();

		JList displayList = viewer.getItemsList();
		displayList.setListData(currentCart.toArray(new String[currentCart
				.size()]));

		// The current user creddits;
		String currentUserCreddits = storeModel.getCurrentUserAccount()
				.getCreddits();
		viewer.getAvailableCredditsTextField().setText(currentUserCreddits);
		double currCredits = Double.parseDouble(currentUserCreddits);

		// The total cart cost
		String totalCartPrice = "" + storeModel.getTotalUserCartCost();
		viewer.getTotalCostTextField().setText(totalCartPrice);
		double totalCost = Double.parseDouble(totalCartPrice);

		double remainingCreddits = (currCredits - totalCost);
		setRemainingCreditField(remainingCreddits);

		currentSelected = null;
	}

	private void setRemainingCreditField(double balance) {

		if (balance < 0) {
			viewer.getRemainingCredditsTextField().setText("0");
		} else {
			viewer.getRemainingCredditsTextField().setText("" + balance);
		}

	}

	/**
	 * This method checks whether the use really wants to buy the items on his
	 * cart
	 */
	public void processConfirmBuyCart() {

		if (storeModel.getCurrentUserAccount().getCart().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Your Cart Is Empty!");
		} else {
			int ret = JOptionPane.showConfirmDialog(null,
					"Are You Sure You Want to Buy This Cart?", "Buy Cart",
					JOptionPane.YES_NO_OPTION);
			if (ret == JOptionPane.YES_OPTION) {
				processBuyCart();
			}
		}
	}

	public void verifyCreddits() {

		// if no elements on cart...throw an error
		// if no credits are less than cost throw an error
		// else go ahead and buy the cart

		if (userHasNoItems() || userHasNoCreddits()) {
			// new SorryYouCantDoThisView();

			JOptionPane.showMessageDialog(null,
					"Sorry, We Cannot Proceed This Cart Purchase");
		} else {
			processBuyCart();
			// new ThankYouForBuyingView();
			// throw a message about "Hey! thank you!"

			JOptionPane.showMessageDialog(null,
					"Thank You For Buying At the Store!");
		}
	}

	private boolean userHasNoCreddits() {

		return !storeModel.userHasEnoughCredditsToBuyWishList();
	}

	private boolean userHasNoItems() {

		StoreUser user = storeModel.getCurrentUserAccount();
		return user.getCart().isEmpty();

	}

	/**
	 * This method process the buy cart step!
	 */
	public void processBuyCart() {
		StoreUser user = storeModel.getCurrentUserAccount();
		double userCreddits = Double.parseDouble(user.getCreddits());
		ArrayList<String> gamesToBuy = new ArrayList<String>();

		for (String title : user.getCart()) {
			IItemInfo item = storeModel.getItemInfo(title);
			double price = Double.parseDouble(item.getPrice());
			userCreddits -= price;
			gamesToBuy.add(title);
		}

		user.addGames(gamesToBuy);

		// initialize a new array for the cart!
		user.emptyCart();

		// put the creddits back!
		user.updateToCreddits(userCreddits);

		// refresh the content of your inventory
		initialize();
	}

	public void processConfirmDropItem() {

		if (currentSelected == null) {
			JOptionPane.showMessageDialog(null, "No Item Has Been Selected");
		} else {

			int ret = JOptionPane.showConfirmDialog(null,
					"Are You Sure You Want to Drop This Cart?", "Drop Cart",
					JOptionPane.YES_NO_OPTION);
			if (ret == JOptionPane.YES_OPTION) {
				storeModel.getCurrentUserAccount().emptyCart();
			}
		}
	}

	public void processDropItem() {
		// need something else here!
		// like another pop up

		if (currentSelected == null) {
			JOptionPane.showMessageDialog(null, "No Item Has Been Selected");
		} else {
			StoreUser user = storeModel.getCurrentUserAccount();
			user.removeTitleFromCart(currentSelected);

			JOptionPane.showMessageDialog(null, "Item Has Been Dropped");

			initialize();
		}
	}

	public void processSaveCart() {
		storeModel.getCurrentUserAccount().saveCart();
		JOptionPane.showMessageDialog(null, "Cart Successfully Saved!");
	}

	public void registerCurrentElement() {

		currentSelected = (String) viewer.getItemsList().getSelectedValue();
	}

	public void processConfirmDropCart() {
		if (storeModel.getCurrentUserAccount().getCart().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Your Cart Is Empty!");
		} else {
			int ret = JOptionPane.showConfirmDialog(null,
					"Are You Sure You Want to Drop This Cart?", "Drop Cart",
					JOptionPane.YES_NO_OPTION);
			if (ret == JOptionPane.YES_OPTION) {
				storeModel.getCurrentUserAccount().emptyCart();
			}
		}
	}

}