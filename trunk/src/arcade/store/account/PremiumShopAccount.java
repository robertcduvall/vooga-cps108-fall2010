package arcade.store.account;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import arcade.store.page.AccountShopPage;
import arcade.store.page.GamePage;

/**
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date 12-04-10
 * @description: Here is an user account
 * 				 The user should be able to:
 * 					1) write comments
 *					2) process balances
 *					3) 
 *			
 */

public class PremiumShopAccount extends UserShopAccount{

	private static final String GAME_CREDIT_NAME = "gamecredit";
	
	private Map<String, Double> shopCredits;
	private Set<String> boughtGames;
	
	public PremiumShopAccount(String user, String id) {
		super(user, id);
		
		boughtGames = new HashSet<String>();
		shopCredits = new HashMap<String, Double>();
	}
		
	/**
	 * Premium user should have additional perks
	 * @param page
	 * @param comment
	 */
	public void commentOnGamePage(GamePage page, String comment){
		
		page.paintUserComment(comment);	
	}
	
	@Override
	public void purchaseGame(GamePage page)
	{
		if(hasGame(page))
			return;
		
		addGame(page);
		transactCredits(-page.getPrice());
	}
	
	
	/**
	 * This method processes an amount change to (assumed) 
	 * game credits
	 * @param change
	 */
	public void transactCredits(double change)
	{
		shopCredits.put(GAME_CREDIT_NAME, change);
			
		if(hasSubZeroCredit(GAME_CREDIT_NAME))
				resetCredit(GAME_CREDIT_NAME);
	}
	
	/**
	 * This method processes an amount of change to the credit
	 * @param creditname
	 * @param change
	 */
	@Override
	public void transactCredits(String creditname, double change) {

		if(!shopCredits.containsValue(creditname))
		{
			shopCredits.put(creditname, change);
			if(hasSubZeroCredit(creditname))
			{
				resetCredit(creditname);
			}
		}
		else
		{
			double creditvalue = shopCredits.get(creditname);
			shopCredits.put(creditname, change + creditvalue);
		}
	}

	private boolean hasSubZeroCredit(String creditname) {
		return shopCredits.get(creditname) < 0;
	}

	private void resetCredit(String creditname) {
		shopCredits.put(creditname, 0.0);
	}

	private boolean hasCreditType(String creditname){

		return shopCredits.containsKey(creditname);
	}

	public double getCredits(String creditname) {

		if(!hasCreditType(creditname))
		{
			return 0;
		}
		else
		{
			return shopCredits.get(creditname);
		}
	}

	private void addGame(GamePage page) {
		boughtGames.add(page.getGameTitle());
	}

	private void addGame(String title) {
		boughtGames.add(title);
	}

	public boolean hasGame(GamePage game) {
		return boughtGames.contains(game.getGameTitle());
	}

	public boolean hasGame(String title){
		return boughtGames.contains(title);
	}

	
	/**
	 * Give this account's information to another. 
	 * Adds current information the new account
	 * 
	 * @return
	 */
	@Override
	public void transferInformation(PremiumShopAccount newAccount)
	{
		//transfer credits
		for(String credit : shopCredits.keySet())
			newAccount.transactCredits(credit, shopCredits.get(credit));

		//transfer games owned
		for(String game: boughtGames)
			newAccount.addGame(game);
	}

	
	
	
	
	

}
