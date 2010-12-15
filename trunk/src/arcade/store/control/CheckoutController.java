package arcade.store.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import arcade.core.mvc.Controller;
import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;
import arcade.store.account.StoreUser;
import arcade.store.gui.tabs.CheckoutTab;
import arcade.store.items.IItemInfo;

/**
 * CheckoutController is a subclass of Controller. It connects the StoreModel
 * with the CheckoutTab.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class CheckoutController extends Controller {

	private StoreModel storeModel;
	private CheckoutTab viewer;
	private String currentSelected;

	/**
	 * Default constructor for CheckoutController sets the currentSelected to
	 * null;
	 */
	public CheckoutController(String filepath) {
		super(filepath);
		currentSelected = null;
	}

	/**
	 * addModel is an override of the method from IController. It casts the
	 * IModel parameter to a StoreModel and sets the instance variable
	 * storeModel to this object.
	 */
	@Override
	public void addModel(IModel model) {
		storeModel = (StoreModel) model;
	}

	/**
	 * addViewer is an override of the method from IController. It casts the
	 * IViewer parameter to a CheckoutTab and sets the instance variable viewer
	 * to this object.
	 */
	@Override
	public void addViewer(IViewer view) {

		viewer = (CheckoutTab) view;
		currentSelected = null;
	}

	/**
	 * initialize is an override of the method from IController.
	 */
	@Override
	public void initialize() {

		processEventString("initialize");
	}

	public void resetSelected() {

		currentSelected = null;
	}

	/**
	 * Sets the remaining creddits TextField to the cost of the user's cart
	 * subtracted from the user's current total creddits.
	 */
	public void setUpRemainingCreddits() {

		double currCredits = storeModel.getCurrentUserAccount().getCreddits();
		double totalCost = storeModel.getTotalUserCartCost();

		double remainingCreddits = (currCredits - totalCost);
		setRemainingCredditField(remainingCreddits);
	}

	/**
	 * TODO: comment
	 */
	public void setUpUserCost() {

		String totalCartPrice = "" + storeModel.getTotalUserCartCost();
		viewer.setTotalCostTextField(totalCartPrice);
	}

	/**
	 * TODO: comment
	 */
	public void setUpCurrentCredditsField() {

		String currentUserCreddits = ""
				+ storeModel.getCurrentUserAccount().getCreddits();
		viewer.setAvailableCredditsTextField(currentUserCreddits);
	}

	/**
	 * TODO: comment
	 */
	public void setUpUserCart() {
		List<String> currentCart = storeModel.getCurrentUserAccount().getCart();

		viewer.setItemsList(currentCart.toArray(new String[currentCart.size()]));
	}

	/**
	 * setRemainingCreditField takes in the parameter balance and puts this
	 * value in a JTextField.
	 * 
	 * @param balance
	 *            the remaining creddit balance of the user
	 */
	private void setRemainingCredditField(double balance) {

		if (balance < 0) {
			viewer.setRemainigCredditsTextField("0");
		} else {
			viewer.setRemainigCredditsTextField("" + balance);
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

	/**
	 * Displays a message to the user if they do not have sufficient funds or
	 * they have no items in their cart, otherwise processBuyCart is called and
	 * a thank you message is displayed.
	 */
	public void verifyCreddits() {

		// if no elements on cart...throw an error
		// if no credits are less than cost throw an error
		// else go ahead and buy the cart

		if (userHasNoItems() || userHasEnoughCreddits()) {
			JOptionPane.showMessageDialog(null,
					"Sorry, We Cannot Proceed This Cart Purchase");
		} else {
			processBuyCart();
			JOptionPane.showMessageDialog(null,
					"Thank You For Buying At the Store!");
		}
	}

	/**
	 * Returns the boolean for whether or not the user has enough creddits to
	 * proceed with their purchase.
	 * 
	 * @return true if the user has enough creddits, false otherwise.
	 */
	private boolean userHasEnoughCreddits() {

		return !storeModel.userHasEnoughCredditsToBuyWishList();
	}

	/**
	 * TODO: comment
	 * 
	 * @return
	 */
	private boolean userHasNoItems() {

		StoreUser user = storeModel.getCurrentUserAccount();
		return user.getCart().isEmpty();

	}

	/**
	 * This method process the buy cart step!
	 */
	public void processBuyCart() {
		StoreUser user = storeModel.getCurrentUserAccount();
		double userCreddits = user.getCreddits();
		ArrayList<IItemInfo> gamesToBuy = new ArrayList<IItemInfo>();

		for (String title : user.getCart()) {
			IItemInfo item = storeModel.getItemInfo(title);
			double price = Double.parseDouble(item.getPrice());
			userCreddits -= price;
			gamesToBuy.add(item);
		}

		user.addGames(gamesToBuy);

		// initialize a new array for the cart!
		user.emptyCart();

		// put the creddits back!
		user.updateToCreddits(userCreddits);

		// refresh the content of your inventory
		initialize();
	}

	/**
	 * TODO: comment
	 */
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

	/**
	 * TODO: comment
	 */
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

	/**
	 * TODO: comment
	 */
	public void processSaveCart() {
		storeModel.getCurrentUserAccount().saveCart();
		JOptionPane.showMessageDialog(null, "Cart Successfully Saved!");
	}

	/**
	 * TODO: comment
	 */
	public void registerCurrentElement() {

		currentSelected = (String) viewer.getSelectedItem();

	}

	/**
	 * TODO: comment
	 */
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
