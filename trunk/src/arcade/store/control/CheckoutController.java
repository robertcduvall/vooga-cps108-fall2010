package arcade.store.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;
import arcade.store.account.StoreUser;
import arcade.store.gui.pages.AreYouSureBuyCartView;
import arcade.store.gui.pages.AreYouSureDropCart;
import arcade.store.gui.pages.AreYouSureDropItem;
import arcade.store.gui.pages.ItemHasBeenDroppedView;
import arcade.store.gui.pages.NoDropSelectedView;
import arcade.store.gui.pages.SorryYouCantDoThisView;
import arcade.store.gui.pages.ThankYouForBuyingView;
import arcade.store.gui.tabs.CheckoutTab;
import arcade.store.items.IItemInfo;

public class CheckoutController implements IController{

	private StoreModel storeModel;
	private CheckoutTab viewer;
	private String currentSelected;
	
	public CheckoutController()
	{
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
	
		StoreUser account = storeModel.getCurrentUserAccount();
		List<String> currentCart = account.getCart();
		
		JList displayList = viewer.getItemsList();
		displayList.setListData(currentCart.toArray(new String[currentCart.size()]));
		
		// The current user creddits;
		String currentUserCreddits = account.getCreddits();
		viewer.getAvailableCredditsTextField().setText(currentUserCreddits);		
		double currCredits = Double.parseDouble(currentUserCreddits);
		
		//	The total cart cost
		String totalCartPrice = "" + storeModel.getTotalUserCartCost();
		viewer.getTotalCostTextField().setText(totalCartPrice);
		double totalCost = Double.parseDouble(totalCartPrice);
		
		double remainingCreddits = (currCredits - totalCost);
		setRemainingCreditField(remainingCreddits);
		
		currentSelected= null;
	}

	private void setRemainingCreditField(double balance) {
		
		if(balance < 0)
		{
			viewer.getRemainingCredditsTextField().setText("0");	
		}
		else
		{
			viewer.getRemainingCredditsTextField().setText("" + balance);
		}
		
	}

	/**
	 * This method checks whether the use really wants to buy the items on his cart
	 */
	public void processConfirmBuyCart() {
		
		new AreYouSureBuyCartView(this);
	}

	public void verifyCreddits() {
		
		// if no elements on cart...throw an error
		// if no credits are less than cost throw an error
		// else go ahead and buy the cart
				
		if(userHasNoItems() || userHasNoCreddits())
		{
			new SorryYouCantDoThisView();
		}
		else
		{
			processBuyCart();
			new ThankYouForBuyingView();
			//throw a message about "Hey! thank you!"
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
	public void processBuyCart()
	{
		StoreUser user = storeModel.getCurrentUserAccount();
		double userCreddits = Double.parseDouble(user.getCreddits());
		
		for(String title : user.getCart())
		{
			IItemInfo item = storeModel.getItemInfo(title);
			double price = Double.parseDouble(item.getPrice());
			userCreddits -= price;
			user.addGame(title);
		}
		
		//initialize a new array for the cart!
		user.emptyCart();
		
		//put the creddits back!
		user.updateToCreddits(userCreddits);
		
		//refresh the content of your inventory
		initialize();
	}

	public void processConfirmDropItem() {
	
		if(currentSelected == null)
		{
			new NoDropSelectedView();
		}
		else
		{
			new AreYouSureDropItem(this);
		}
	}

	public void processDropItem() {
		//need something else here!
		//like another pop up
		StoreUser user = storeModel.getCurrentUserAccount();
		user.removeTitleFromCart(currentSelected);
		new ItemHasBeenDroppedView();
		initialize();
	}

	public void registerCurrentElement() {
	
		currentSelected = (String) viewer.getItemsList().getSelectedValue();
	}

	public void processConfirmDropCart() {
		
		new AreYouSureDropCart(this);
	}
	
	public void processDropCart(){
		
		StoreUser user = storeModel.getCurrentUserAccount();
		user.emptyCart();
		initialize();
	}
	
	
}
