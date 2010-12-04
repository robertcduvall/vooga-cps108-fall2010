package arcade.store.account;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the store's shop account;
 * @author Drew Strenesky, Jimmy Mu, Marcus Molchany
 * @date 11-04-10
 * @description: This class represents the store's shop account
 */

public class StoreShopAccount {

	//TODO: relocate these variables to somewhere else
	private final static String GAME_CREDITS_KEY= "gamecredits";
	private final static String TIME_CREDITS_KEY = "timecredits";
	
	Map<String, UserShopAccount> storeClients;
	Map<String, Double> storeCredits;
	
	public StoreShopAccount()
	{
		//how do you relay old information from a database?
		storeClients = new HashMap<String, UserShopAccount>();
		storeCredits = new HashMap<String, Double>();
	}
	
	public int getNumberOfClients()
	{
		return storeClients.size();
	}
	
	public void addShopAccount(UserShopAccount account)
	{
		String name = account.getUser();
		storeClients.put(name, account);
	}
	
	
	public UserShopAccount getShopAccount(String userName)
	{
		//here are guaranteeing that all the keys are valid 
		//simply because the storeShopAccount is inside the Store
		return storeClients.get(userName);
	}
	
	public boolean hasUserName(String name)
	{
		return storeClients.containsKey(name);
	}
	
	public double getTotalStoreGameCredits()
	{
		return storeCredits.get(GAME_CREDITS_KEY);
	}
	
	public double getTotalStoreTimeCredits()
	{
		return storeCredits.get(TIME_CREDITS_KEY);
	}
	
	/**
	 * When the user has effected a transaction
	 */
	public void updateStoreAccount()
	{
		// is that not all clients have a particular credit name!!
		//but this problem is already solved inside the userShopAccount
		//method getCredit!
		
		//TODO: how do you insure that all the keys for credits
		//are registered in the parser...so that you won't
		//miss a credit key?
		for(String credit : storeCredits.keySet())
		{
			double storecredit = storeCredits.get(credit);
			
			for(String client: storeClients.keySet())
			{
				UserShopAccount account = storeClients.get(client);
				storecredit += account.getCredits(credit);
			}
			
			storeCredits.put(credit, storecredit);
		}		
	}
	
}
