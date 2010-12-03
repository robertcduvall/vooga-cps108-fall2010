package arcade.store;

import arcade.store.account.ShopAccount;

public class Store {

	StoreLibrary storeLibrary;
	
	
	public Store()
	{
		//figure out the parser in here!
		storeLibrary = new StoreLibrary();
		
		
	}
	
	/**
	 * Potential problem when you have multiple users trying to access
	 * the store at the same time...how do you set up a unique internet
	 * connection?
	 * @param newAccount
	 */
	public void registerCurrentUser(ShopAccount newAccount)
	{
		
	}
	
	/**
	 * Provide a write out to an XML file to keep the current history.
	 * 
	 */
	public void recordToHistory()
	{
		
		
	}
	
	
	
	
	
	
	
	
	
}
