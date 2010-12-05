package arcade.store.account;

import java.util.*;

import arcade.store.page.GamePage;
import arcade.store.page.StorePage;

/**
 * 
 * @author Drew Sternsky, Jimmy Mu, Marcus Molchany
 * @date 11-02-10
 * @description: This is a shop account that a player has. 
 */
public abstract class UserShopAccount {

	private String accountID;
	private String userName;
	private Stack<StorePage> visitedPages;


	public UserShopAccount(String user, String id) {
		userName = user;
		accountID = id;

		visitedPages = new Stack<StorePage>();
	}

	/**
	 * Tthis method overrides how a game is purchased
	 * @param page
	 */
	public abstract void purchaseGame(GamePage page);

	/**
	 * This method transact a change on a specific credit name
	 * @param creditname
	 * @param change
	 */
	public abstract void transactCredits(String creditname, double change);


	/**
	 * Overrides all the information from one account to another.
	 * This method copies information about the user's
	 * privileges, games, and credits.
	 * @param newAccount
	 */
	public abstract void transferInformation(PremiumShopAccount newAccount);
	


	public void recordGamePage(GamePage currentPage)
	{
		visitedPages.add(currentPage);
	}

	public StorePage goToPreviousGamePage()
	{
		return visitedPages.pop();
	}

	public String getAccountID(){
		return accountID;
	}

	public String getUserName()	{
		return userName;
	}

	public void resetUserName(String name)
	{
		userName = name;
	}
	
	public void resetUserID(String userID)
	{
		accountID = userID;
	}




}
