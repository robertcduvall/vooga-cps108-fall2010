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
		String[][] data = new String[list.size()][COLUMN_NAMES.length];
		for(int k=0; k<list.size(); k++) {
			IItemInfo item = list.get(k);
			data[k][0] = item.getTitle();
			data[k][1] = item.getPrice();
			data[k][2] = item.getGenre();
		}
		TableModel tm = new DefaultTableModel(data, COLUMN_NAMES);
		view.getGameListTable().setModel(tm);

		
		
	}
	
	public void organize(String label)
	{
//		List<IItemInfo> list = model.organize(label);
//		populateMainPage(list);
	}
	
	public void showDialogBox()
	{
		
		//** TODO
	}
	
	public void openGamePage(String tagName)
	{
		System.out.println(tagName);
		IItemInfo item = model.getItemInfo(tagName);
		System.out.println(item.getTitle());
		GamePurchaseView view = new GamePurchaseView(this);
		
		view.getDescriptionField().setText(item.getDescription());

	

		view.getTitleField().setSize(200, 20);
		view.getTitleField().setText(item.getTitle());
		view.getPriceField().setText(item.getPrice());
		view.getCoverArt().setIcon( item.getImages().get(item.COVER_IMAGE));
		
	}
	
	
	
	
	
	
	

	
	
	
}
