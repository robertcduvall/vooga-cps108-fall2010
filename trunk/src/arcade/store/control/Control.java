package arcade.store.control;

import java.util.List;
import java.util.Vector;

import javax.swing.table.*;
import javax.swing.*;

import arcade.store.StoreModel;
import arcade.store.gui.GamePurchaseView;
import arcade.store.gui.MainPageView;
import arcade.store.items.IItemInfo;

public class Control {

	private static final String[] COLUMN_NAMES = {"Game Title", "Price", "Genre"};
	
	
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
	
	public void processPurchaseButton(String gameName)
	{
//		model.processPurchase(gameName);
	}
	
	
	public void populateMainPage(List<IItemInfo> list)
	{
//		String[][] data = new String[list.size()][COLUMN_NAMES.length];
//		for(int k=0; k<list.size(); k++) {
//			IItemInfo item = list.get(k);
//			data[k][0] = item.getTitle();
//			data[k][1] = item.getPrice();
//			data[k][2] = item.getGenre();
//		}
//		TableModel tm = new DefaultTableModel(data, COLUMN_NAMES);
//		view.getGameListTable().setModel(tm);

		for(IItemInfo i : list) {
			view.addGameToList(i.getTitle(), i.getPrice(), i.getGenre(), i.getImages().get(0));
		}
		
	}
	
	public void filter(String label)
	{
		List<IItemInfo> list = model.filter(label);
		populateMainPage(list);
	}
	
	public void showDialogBox()
	{
		
		//** TODO
	}
	
	public void openGamePage(String tagName)
	{
		IItemInfo item = model.getItemInfo(tagName);
		GamePurchaseView view = new GamePurchaseView(this);
		view.getDescriptionField().setText(item.getDescription());
		view.getTitleField().setText(item.getTitle());
		view.getPriceField().setText(item.getPrice());
		System.out.println(item.getImages().get(item.COVER_IMAGE));
		view.getCoverArt().setIcon( item.getImages().get(item.COVER_IMAGE));
		view.getCoverArt().setText("Coverart holder frame");
		
	}
	
	
	
	
	
	
	

	
	
	
}
