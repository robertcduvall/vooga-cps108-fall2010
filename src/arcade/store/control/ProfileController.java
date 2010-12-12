package arcade.store.control;

import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;
import arcade.store.account.StoreUser;
import arcade.store.gui.pages.CredditPurchaseView;
import arcade.store.gui.tabs.ProfileTab;

public class ProfileController implements IController{

	private StoreModel storeModel;
	private ProfileTab profileTab;
	
	
	@Override
	public void addModel(IModel model) {
		storeModel = (StoreModel) model;
	}

	@Override
	public void addViewer(IViewer viewer) {
		profileTab = (ProfileTab) viewer;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	public void openCredditPurchaseView() {
		new CredditPurchaseView(this);
	}
	
	public void processCredditPurchase(String creddits) {
		double amount = 0;
		try {
			amount = Double.parseDouble(creddits);
		}
		catch (Exception e) {
			System.out.println("You did not enter a number");
		}
		StoreUser user = storeModel.getCurrentUserAccount();
		user.addCreddits(amount);
		System.out.println(user.getCreddits());
	}

}
