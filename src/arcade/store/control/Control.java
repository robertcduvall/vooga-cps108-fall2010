package arcade.store.control;

import java.util.List;

import javax.swing.ImageIcon;

import arcade.store.StoreModel;
import arcade.store.gui.GamePurchaseView;
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
	}
	
	public void processPurchaseButton()
	{
//		model.processPurchase(item);
	}
	
	
	public void populateMainPage(List<IItemInfo> list)
	{
		

		
		
	}
	
	public void organize(String label)
	{
		List<IItemInfo> list = model.organize(label);
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
		view.getCoverArt().setIcon( item.getImages().get(item.COVER_IMAGE));
	
		view.getTitleField().setText(item.getTitle());
		view.getPriceField().setText(item.getPrice());
	}
	
	
	
	
	
	
	

	
	
	
}
