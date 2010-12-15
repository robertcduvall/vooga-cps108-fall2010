package arcade.store.control;

import java.util.List;

import javax.swing.JOptionPane;

import arcade.core.mvc.Controller;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;
import arcade.store.account.StoreUser;
import arcade.store.gui.tabs.CheckoutTab;

/**
 * 
 * @author: 		Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date: 			12-10-15
 * @description: 	This class represents the controller for the the CheckoutTab and the 
 * 					the StoreModel. The CheckoutTab extends the Controller class and maintain
 * 					all the communications between the the StoreModel and the Viewer.
 * 					These interactions include displaying StoreUser creddits in the 
 * 					JTextFields, calculating the the purchase cost of the cart, and 
 * 					processing user's shopping requests. 
 *
 */

public class CheckoutController extends Controller {

	private StoreModel storeModel;
	private CheckoutTab viewer;
	private String currentSelected;

	/**
	 * default constructor for CheckoutController that takes in
	 * a filepath for the resource bundle that this class
	 * uses with the processEvent and processEventString methods.
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
	}

	/**
	 * initialize is an override of the method from IController.
	 */
	@Override
	public void initialize() {
		processEventString("initialize");
	}

	/**
	 * sets the currently element selected on the list to be null.
	 */
	public void resetSelected() {
		currentSelected = null;
	}

	/**
	 * sets up the GUI JTextField for the remaining creddits. 
	 */
	private void setUpRemainingCreddits() {

		double remaining = storeModel.getUserWishListBalance();
		setRemainingCreditField(remaining);
	}

	/**
	 * setRemainingCreditField takes in the parameter balance and puts this
	 * value in a JTextField. If the user has has a balance of less than 0,
	 * 0 is displayed. If the user has a balance of greater than or equal to 0,
	 * then their credits are displayed.
	 */
	private void setRemainingCreditField(double balance) {
		if (balance < 0) {
			viewer.setRemainigCredditsTextField("0");
		} else {
			viewer.setRemainigCredditsTextField("" + balance);
		}
	}
	
	/**
	 * setUpUserCostField gets the total cost of the user's 
	 * shopping cart and display this information in the 
	 * cost JTextField of the CheckoutTab.
	 */
	private void setUpUserCostField() {
		// The total cart cost
		String totalCartPrice = "" + storeModel.getTotalUserCartCost();
		viewer.setTotalCostTextField(totalCartPrice);
	}

	/**
	 * setUpCurrentCredditsField gets the user's current creddits
	 * and displays this information on the CheckoutTab. 
	 */
	private void setUpCurrentCredditsField() {
		// The current user creddits;
		String currentUserCreddits = ""
				+ storeModel.getCurrentUserAccount().getCreddits();
		viewer.setAvailableCredditsTextField(currentUserCreddits);
	}

	/**
	 * setUpUserCart makes 
	 */
	private void setUpUserCart() {
		List<String> currentCart = storeModel.getCurrentUserAccount().getCart();
		viewer.setItemsList(currentCart.toArray(new String[currentCart.size()]));
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

		if (storeModel.userHasNoItems() || storeModel.userHasEnoughCreddits()) {
			JOptionPane.showMessageDialog(null,
					"Sorry, We Cannot Proceed This Cart Purchase");
		} else {
			processBuyCart();
			JOptionPane.showMessageDialog(null,
					"Thank You For Buying At the Store!");
		}
	}


	/**
	 * This method process the buy cart step!
	 */
	public void processBuyCart() {
	
		storeModel.processBuyCart();
		
		// refresh the content of your inventory
		initialize();
	}

	/**
	 * 
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
	 * 
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
	 * 
	 */
	public void processSaveCart() {
		storeModel.getCurrentUserAccount().saveCart();
		JOptionPane.showMessageDialog(null, "Cart Successfully Saved!");
	}

	/**
	 * 
	 */
	public void registerCurrentElement() {
		currentSelected = (String) viewer.getSelectedItem();

	}

	/**
	 * 
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
