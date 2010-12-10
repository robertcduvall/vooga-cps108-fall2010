package arcade.store.control;

import java.util.List;

import javax.swing.JPanel;

import arcade.store.StoreModel;
import arcade.store.gui.GamePurchaseView;
import arcade.store.gui.ItemThumbnailPanel;
import arcade.store.gui.MainPageView;
import arcade.store.items.IItemInfo;

public class Control {

	private StoreModel model;
	private MainPageView view;

	public void setModel(StoreModel model) {
		this.model = model;
	}

	public void setView(MainPageView view) {
		this.view = view;
		populateMainPage(model.getAllItems());
		populateGenreList(model.getGenres());
	}

	public void populateGenreList(String[] list) {
		view.getGenreList().setListData(list);
	}

	public void processPurchaseButton(String gameName) {
		model.processPurchase(gameName);
	}

	public void populateMainPage(List<IItemInfo> list) {

		view.getGameList().removeAll();
		view.getGameList().revalidate();
		JPanel gamePanel = view.getGameList();
		view.remove(gamePanel);

		for (IItemInfo i : list) {
			ItemThumbnailPanel itemPanel = new ItemThumbnailPanel(i.getTitle(), i.getPrice(), i.getGenre(), i
					.getImages().get(0), this);
			gamePanel.add(itemPanel);
		}
		
		view.getJScrollPane().setViewportView(gamePanel);

	}

	public void filter(String label) {
		List<IItemInfo> list = model.filter(label);
		populateMainPage(list);
	}

	public void showDialogBox() {

		// ** TODO
	}

	public void openGamePage(String tagName) {
		IItemInfo item = model.getItemInfo(tagName);
		GamePurchaseView view = new GamePurchaseView(this);
		view.getDescriptionField().setText(item.getDescription());
		view.getTitleField().setText(item.getTitle());
		view.getPriceField().setText(item.getPrice());
		view.getCoverArt().setIcon(item.getImages().get(item.COVER_IMAGE));
	}

}
