package arcade.store;

import arcade.store.account.GuestShopAccount;
import arcade.store.account.StoreShopAccount;
import arcade.store.account.UserShopAccount;
import arcade.store.logic.StoreLogic;

public class Store {

	private static final String GUEST_USER_NAME = "GENERIC_USER";
	private static final String GUEST_USER_ID = "000000";
	
	private StoreLibrary storeLibrary;
	private StoreLogic storeLogic;
	private StoreShopAccount storeAccount;
	
	private UserShopAccount currentActiveAccount;
	
	
	public Store()
	{
		//TODO how do you save previous information?
		storeLibrary = new StoreLibrary();	
		storeLogic = new StoreLogic();
		storeAccount = new StoreShopAccount();
		
		//by default, a GUEST user is registered with the store
		currentActiveAccount = new GuestShopAccount(GUEST_USER_NAME, GUEST_USER_ID);
	}
	
	/**
	 * This method is used to initialize the game library information 
	 * from a database by a parser;
	 * @param library
	 */
	public void registerCurrentLibrary(StoreLibrary library)
	{
		storeLibrary = library;
	}
	
	/**
	 * This method is used to initialize the store logic information
	 * from a database by a parser
	 * @param logic
	 */
	public void registerCurrentLogic(StoreLogic logic)
	{
		storeLogic = logic;
	}
	
	/**
	 * This method is used to initalize the store account information
	 * from a database by a parser
	 * @param account
	 */
	public void registerCurrentStoreAccount(StoreShopAccount account)
	{
		storeAccount = account;
	}
	
	/**
	 * Registers the user when an actual user logs in
	 * @param newAccount
	 */
	public void registerLoggedInShopAccount(UserShopAccount account)
	{
		currentActiveAccount = account;
	}
	
	/**
	 * Returns the shop account of the user that is logged in
	 * at the current time
	 * @return
	 */
	public UserShopAccount getCurrentShopAccount()
	{
		return currentActiveAccount;
	}
	
	/**
	 * This store needs to be updated in its transactions
	 * before it returns the total game credits
	 * @return
	 */
	public double getTotalStoreGameCredits()
	{
		storeAccount.updateStoreAccount();
		return storeAccount.getTotalStoreGameCredits();
	}
	
	/**
	 * The store needs to be updated in its transactions
	 * before it returns the total time credits
	 * @return
	 */
	public double getTotalStoreTimeCredits()
	{
		storeAccount.updateStoreAccount();
		return storeAccount.getTotalStoreTimeCredits();
	}
	
	//how do you ensure that one user won't override another?
	public void registerNewShopAccount(UserShopAccount newAccount)
	{
		if(userNameAlreadyExist(newAccount.getUser()))
		{
			//TODO: fill this in!!
			//do something here! 
			//paint a GUI
			//and DO NOT register the user!
		}
		else
		{
			storeAccount.addShopAccount(newAccount);
		}
	}
	
	private boolean userNameAlreadyExist(String name)
	{
		return storeAccount.hasUserName(name);
	}
	
	/**
	 * Returns the number of current clients in the store
	 * @return
	 */
	public int getNumberOfClients()
	{
		return storeAccount.getNumberOfClients();
	}
	
	/**
	 * TODO: figure out how the store logic 
	 * is going to be processed and also 
	 * figure out a override mechanism 
	 * to allow special accesses etc.
	 */
	public void overrideStoreLogic()
	{
		
	}
	
	/**
	 * TODO: give a user account a special privilege
	 * that it previously does not have
	 * @param account
	 */
	public void overrideStoreLogic(UserShopAccount account)
	{
		
	}
	
}
