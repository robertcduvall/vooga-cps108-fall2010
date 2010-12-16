package arcade.store.control;

import java.util.*;

import javax.swing.table.DefaultTableModel;

import arcade.core.mvc.Controller;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;
import arcade.store.account.StoreUser;
import arcade.store.database.StoreDbConstants;
import arcade.store.database.StoreSqlAdapter;
import arcade.store.gui.pages.CredditPurchaseView;
import arcade.store.gui.tabs.ProfileTab;

public class ProfileController extends Controller {

	public ProfileController(String filepath) {
		super(filepath);
	}

	private StoreModel storeModel;
	private ProfileTab profileTab;
	private static final String[] columnNames = { "Item Name", "Date", "Price" };
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
		profileTab.setUsernameTextField(getUser().getId());
		profileTab.setAvailableCredditsTextField("" + getUser().getCreddits());
		profileTab.checkPurchaseCredditsButtonPriviliges(storeModel
				.checkPrivileges("addCreddits"));
		populatePurchaseHistory();
	}

	/**
	 * 
	 * Source:
	 * http://www.experts-exchange.com/Programming/Languages/Java/Q_23255872
	 * .html
	 */
	public void populatePurchaseHistory() {
		List<Map<String, String>> data = dbAdapter.getRows(
				StoreDbConstants.PURCHASE_HISTORY_TABLE,
				StoreDbConstants.PURCHASE_HISTORY_USERID_FIELD, getUser()
						.getId());
		String[][] tableData = new String[data.size()][3];
		for (int k = 0; k < data.size(); k++) {
			tableData[k][0] = data.get(k).get(StoreDbConstants.ITEMNAME_FIELD);
			tableData[k][1] = data.get(k).get(StoreDbConstants.DATE_FIELD);
			tableData[k][2] = data.get(k).get(StoreDbConstants.PRICE_FIELD);
		}
		profileTab.setPurchasedItemsTableModel(new DefaultTableModel(tableData,
				columnNames) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		});
	}

	public void openCredditPurchaseView() {
		new CredditPurchaseView(this);
	}

	public StoreUser getUser() {
		return storeModel.getCurrentUserAccount();
	}

	public void processCredditPurchase(String creddits) {
		double amount;
		try {
			amount = Double.parseDouble(creddits);
		} catch (Exception e) {
			amount = 0;
		}
		storeModel.addCredditsToUser(amount);
	}

}
