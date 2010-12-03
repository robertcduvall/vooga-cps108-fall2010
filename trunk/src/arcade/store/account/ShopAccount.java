package arcade.store.account;

import java.util.*;

import arcade.store.page.GamePage;

/**
 * 
 * @author Drew Sternsky, Jimmy Mu, Marcus Molchany
 * @date 11-02-10
 * @description: This is a shop account that a player has. 
 */
public class ShopAccount {

	private String accountID;
	private String userName;

	private Map<String, Double> shopCredits;
	private Set<String> shopPrivileges;
	private Set<String> boughtGames;
	private Stack<GamePage> visitedPages;

	public ShopAccount(String user, String id) {
		userName = user;
		accountID = id;

		boughtGames = new HashSet<String>();
		shopCredits = new HashMap<String, Double>();
		shopPrivileges = new HashSet<String>();
		visitedPages = new Stack<GamePage>();
	}

	public void update()
	{

	}

	/**
	 * Overrides all the information from one account to another.
	 * This method copies information about the user's
	 * privileges, games, and credits.
	 * @param newAccount
	 */
	public void transferInformation(ShopAccount newAccount)
	{
		pasteAccountInformation(newAccount);
	}

	/**
	 * Give this account's information to another. 
	 * Adds current information the new account
	 * 
	 * @return
	 */
	private void pasteAccountInformation(ShopAccount newAccount)
	{
		//transfer credits
		for(String credit : shopCredits.keySet())
			newAccount.addCredits(credit, shopCredits.get(credit));

		//transfer privileges
		for(String privilege: shopPrivileges)
			newAccount.addPrivilege(privilege);

		//transfer games owned
		for(String game: boughtGames)
			newAccount.addGame(game);
	}


	public void recordGamePage(GamePage currentPage)
	{
		visitedPages.add(currentPage);
	}

	public GamePage goToPreviousGamePage()
	{
		return visitedPages.pop();
	}

	public String getAccountID(){
		return accountID;
	}

	public String getUser()	{
		return userName;
	}


	public void addCredits(String creditname, double amount) {

		if(!shopCredits.containsValue(creditname))
		{
			shopCredits.put(creditname, amount);
		}
		else
		{
			double creditvalue = shopCredits.get(creditname);
			shopCredits.put(creditname, amount + creditvalue);
		}
	}

	public boolean hasCredit(String creditname){

		return shopCredits.containsKey(creditname);
	}

	public double getCredits(String creditname) {

		if(!shopCredits.containsKey(creditname))
		{
			return 0;
		}
		else
		{
			return shopCredits.get(creditname);
		}
	}

	public void addGame(GamePage game) {

		boughtGames.add(game.getGameTitle());

	}

	public void addGame(String title) {

		boughtGames.add(title);
	}

	public boolean hasGame(GamePage game) {

		return boughtGames.contains(game.getGameTitle());
	}

	public boolean hasGame(String title){

		return boughtGames.contains(title);
	}

	public void addPrivilege(String privilege){

		shopPrivileges.add(privilege);
	}

	public boolean hasPrivilege(String privilege)
	{
		return shopPrivileges.contains(privilege);
	}






}
