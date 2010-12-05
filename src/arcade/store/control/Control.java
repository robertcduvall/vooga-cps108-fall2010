package arcade.store.control;

import java.util.List;

import arcade.store.StoreModel;
import arcade.store.gui.GamePurchaseView;
import arcade.store.gui.MainPageView;
import arcade.store.items.IItemInfo;

public class Control {

	private StoreModel model;
	private MainPageView page;
	
	public Control(StoreModel model, MainPageView page)
	{
		this.model = model;
		this.page = page;
		
	}
	
	public void processPurchaseButton()
	{
		// TODO
	}
	
	
	public void populateMainPage(List<IItemInfo> list)
	{
		
		//** TODO
		
	}
	
	public void organize(String label)
	{
		//TODO
	}
	
	public void showDialogBox()
	{
	
		//** TODO
	}
	
	public void openGamePage(String tagName)
	{
		IItemInfo item = model.getItemInfo(tagName);
		
		GamePurchaseView view = new GamePurchasView(this);
		
		view.getDescriptionField().setText(item.getDescription(());
		
		
	}
	
	
	
	
	
	
	

	
	
	
}
