package arcade.store.control;

import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import arcade.core.api.Tab;
import arcade.core.mvc.Controller;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;
import arcade.store.account.StoreUser;
import arcade.store.database.StoreDbConstants;
import arcade.store.database.StoreSqlAdapter;
import arcade.store.gui.pages.CredditPurchaseView;
import arcade.store.gui.tabs.ProfileTab;

/**
 * 
 * @author:			Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date:			12-16-10
 * @description:	The ProfileController is the controller class for
 *					the StoreModel and the ProfileTab. The ProfileController
 *					relays information from the database to get the user's 
 *					purchase history and process event such as edit user profile
 *					and add credits to StoreUser account.
 */

public class ProfileController extends Controller {

	private static final String[] columnNames = { "Item Name", "Date", "Price" };
	private static StoreSqlAdapter dbAdapter;
	
	private StoreModel storeModel;
	private ProfileTab profileTab;
	
	
	/**
	 * This is a basic constructor for the ProfileController
	 * @param filepath
	 */
	public ProfileController(String filepath) {
		super(filepath);
		dbAdapter = new StoreSqlAdapter();
	}


	@Override
	public void addModel(IModel model) {
		storeModel = (StoreModel) model;
	}

	@Override
	public void addViewer(Tab viewer) {
		profileTab = (ProfileTab) viewer;
	}

	/**
	 * initialize relays information from the Lobby group, fetches the user creddits
	 * and sets up buttons based on the type of user and their type of privileges
	 */
	@Override
	public void initialize() {
		populateProfileFields();
		setUpUserImage();
		populatePurchaseHistory();
	}


	/**
	 * This method populates the JFieldTexts that are displayed on the Profile Tab.
	 */
	private void populateProfileFields() {
		profileTab.setUsernameTextField(storeModel.getCurrentLobbyProfile().getFullName());
		profileTab.setAvailableCredditsTextField("" + storeModel.getUserCreddits());
		profileTab.checkPurchaseCredditsButtonPriviliges(storeModel
				.checkPrivileges("addCreddits"));
	}


	/**
	 * setUpUserImage polls the avatar image from the Lobby Profile
	 */
	private void setUpUserImage() {
		ImageIcon image;
		
		try {
			image = new ImageIcon(storeModel.getCurrentLobbyProfile().getAvatar());
		}
		catch (Exception e) {
			image = new ImageIcon("");
		}
		profileTab.setUserImage(image);
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
				StoreDbConstants.PURCHASE_HISTORY_USERID_FIELD, storeModel.getCurrentUserAccount().getId());
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

	/**
	 * openCredditPurchaseView pops up the credit purchase view
	 */
	public void openCredditPurchaseView() {
		new CredditPurchaseView(this);
	}

	
//	public StoreUser getUser() {
//		return storeModel.getCurrentUserAccount();
//	}

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
