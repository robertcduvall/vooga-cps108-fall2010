package arcade.store.control;

import java.util.List;

import javax.swing.JPanel;

import arcade.core.mvc.Controller;
import arcade.core.mvc.IController;
import arcade.core.mvc.IModel;
import arcade.core.mvc.IViewer;
import arcade.store.StoreModel;
import arcade.store.gui.ItemThumbnailPanel;
import arcade.store.gui.pages.GamePurchaseView;
import arcade.store.gui.tabs.MainPageTab;
import arcade.store.items.IItemInfo;

public class MainPageController extends Controller{

	
	private StoreModel storeModel;
	private MainPageTab viewer;
	
	public MainPageController(String filepath)
	{
		super(filepath);
	}
	
	@Override
	public void addModel(IModel currentmodel) {
		storeModel = (StoreModel) currentmodel;
		
	}
	

	@Override
	public void addViewer(IViewer currentviewer) {
		viewer = (MainPageTab) currentviewer;

	}

	public void processFilter()
	{
		filter((String) viewer.getGenreList().getSelectedValue());
	}
	
	@Override
	public void initialize() {
		populateMainPage(storeModel.getAllItems());
		populateGenreList(storeModel.getGenres());
	}


	public void populateGenreList(String[] list) {
		viewer.getGenreList().setListData(list);
	}


	public void populateMainPage(List<IItemInfo> list) {

		viewer.getGameList().removeAll();
		JPanel gamePanel = viewer.getGameList();
		viewer.remove(gamePanel);

		for (IItemInfo i : list) {
			ItemThumbnailPanel itemPanel = new ItemThumbnailPanel(i.getTitle(), i.getPrice(), i.getGenre(), i
					.getImages().get(0), this);
			gamePanel.add(itemPanel);
		}

		viewer.getJScrollPane().setViewportView(gamePanel);

	}

	public void filter(String label) {
		List<IItemInfo> list = storeModel.filter(label);
		populateMainPage(list);
	}

	public void openGamePurchasePage(String tagName) {
		
		IItemInfo item = StoreModel.getItemInfo(tagName);
		IController purchaseItemController = (IController) new PurchaseItemController(storeModel);
		
		IViewer view = new GamePurchaseView(purchaseItemController);
		purchaseItemController.addViewer(view);
		
		((PurchaseItemController) purchaseItemController).initializeViewerFromItemInfo(item);
	}

}
