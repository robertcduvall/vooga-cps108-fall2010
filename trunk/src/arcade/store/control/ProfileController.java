package arcade.store.control;

import java.util.*;

import javax.swing.table.DefaultTableModel;

import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;
import arcade.store.StoreSqlAdapter;
import arcade.store.account.StoreUser;
import arcade.store.gui.pages.CredditPurchaseView;
import arcade.store.gui.tabs.ProfileTab;

public class ProfileController implements IController{

	private StoreModel storeModel;
	private ProfileTab profileTab;
	private static final String[] columnNames = {"Item Name", "Date", "Price"};
	private static StoreSqlAdapter dbAdapter = new StoreSqlAdapter();

	
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
		profileTab.getUsernameTextField().setText(getUser().getName());
		profileTab.getAvailableCredditsTextField().setText(getUser().getCreddits());
		List<Map<String,String>> data = dbAdapter.getRows("SELECT * FROM PurchaseHistory WHERE User_Id='"+storeModel.getCurrentUserAccount().getId()+"'");
		String[][] tableData = new String[data.size()][3];
		for(int k=0; k<data.size(); k++) {
			tableData[k][0] = data.get(k).get("ItemName");
			tableData[k][1] = data.get(k).get("Date");
			tableData[k][2] = data.get(k).get("Price");
		}
		profileTab.getPurchasedGamesTable().setModel(new DefaultTableModel(tableData, columnNames));
	}
	
	public void openCredditPurchaseView() {
		new CredditPurchaseView(this);
	}
	
	public StoreUser getUser() {
		return storeModel.getCurrentUserAccount();
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
