package arcade.store.control;

import java.util.List;

import javax.swing.JPanel;

import arcade.core.api.Tab;
import arcade.core.mvc.Controller;
import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.store.StoreModel;
import arcade.store.gui.StoreItemThumbnailPanel;
import arcade.store.gui.pages.GamePurchaseView;
import arcade.store.gui.tabs.MainPageTab;
import arcade.store.items.IItemInfo;

/**
 * 
 * @author 			Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date 			12-15-10
 * @description		The MainPageController is the controller for the StoreModel
 * 					and the MainPageTab. This controller manages action events from
 * 					ItemThumbnailPanel and transmit information to StoreModel 
 *
 */

public class MainPageController extends Controller{

	private StoreModel storeModel;
	private MainPageTab viewer;
	
	/**
	 * Constructs MainPageController
	 * @param filepath location of the MainPageController class.
	 * Necessary for the method call processing feature of the
	 * Controller superclass. 
	 */
	public MainPageController(String filepath)
	{
		super(filepath);
	}
	
	@Override
	public void addModel(IModel currentmodel) {
		storeModel = (StoreModel) currentmodel;
	}
	

	@Override
	public void addViewer(Tab mainPageTab) {
		viewer = (MainPageTab) mainPageTab;

	}

	/**
	 * Gets the currently-selected genre in the genre list
	 * and passes this information to the filter method 
	 * for processing.
	 */
	public void processFilter()
	{
		filter((String) viewer.getSelectedGenre());
	}
	
	@Override
	public void initialize() {
		storeModel.refreshUser();
		populateMainPage(storeModel.getAllItems());
		populateGenreList(storeModel.getGenres());
	}

	/**
	 * Takes an array of Strings and populates the genre JList
	 * in the viewer with each item.
	 * 
	 * @param list String array of genres. 
	 */
	public void populateGenreList(String[] list) {
		viewer.setGenreList(list);
	}

	/**
	 * Populates the main game listing panel in the MainPageTab with 
	 * new StoreItemThumbnailPanels constructed from the list of IItemInfo
	 * objects. This method removes the panel entirely, paints the
	 * new ThumbnailPanels onto the container panel, and re-adds the container
	 * panel back to the view. Done this way to avoid refreshing problem.
	 * 
	 * @param list IItemInfo objects that should be used to create
	 * StoreItemThumbnailPanels for viewing.
	 */
	public void populateMainPage(List<IItemInfo> list) {

		viewer.getGameList().removeAll();
		JPanel gamePanel = viewer.getGameList();
		viewer.remove(gamePanel);

		for (IItemInfo item : list) {
			StoreItemThumbnailPanel itemPanel 
					= new StoreItemThumbnailPanel(item, this);
			gamePanel.add(itemPanel);
		}

		viewer.getJScrollPane().setViewportView(gamePanel);
	}

	
	/**
	 * Gets a list of only requested IItemInfo objects from the 
	 * StoreModel and populates them into the viewer.
	 * 
	 * @param label the String to filter on (StoreModel is currently
	 * filtering on Genre)
	 */
	public void filter(String label) {
		List<IItemInfo> list = storeModel.filter(label);
		populateMainPage(list);
	}

	/**
	 * Opens a game purchase window given the String title of 
	 * a game.
	 * 
	 * @param tagName title of game to be displayed in the 
	 * purchasePage.
	 */
	public void openGamePurchasePage(String tagName) {
		
		IItemInfo item = StoreModel.getItemInfo(tagName);
		IController purchaseItemController = (IController) new PurchaseItemController(storeModel);

		Tab viewer = new GamePurchaseView(purchaseItemController);
		purchaseItemController.addViewer(viewer);
		
		((PurchaseItemController) purchaseItemController).initializeViewerFromItemInfo(item);
	}

}
