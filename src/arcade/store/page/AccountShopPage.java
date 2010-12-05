package arcade.store.page;

import java.awt.event.InputEvent;

import javax.swing.JFrame;

import arcade.store.account.StoreShopAccount;

public class AccountShopPage extends StorePage{

	private StoreShopAccount userAccount;
	
	public AccountShopPage(JFrame gui) {
		super(gui);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processOnEvent(InputEvent event) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method registers the current User's shopaccount
	 * TODO: Where do I need to put this?
	 * @param account
	 */
	public void registerShopAccount(StoreShopAccount account)
	{
		userAccount = account;
	}
}
