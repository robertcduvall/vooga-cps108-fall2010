package arcade.store.account;

import java.util.ArrayList;
import java.util.List;

import arcade.store.page.GamePage;

/**
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date 11-04-10
 * @description This is a developer Shop account. A developer should be able to
 * 				1) remove third party extensions if desired
 * 				2) certify third party (other developers) mods
 * 				3) provide their own mods (should be certified on the go)
 * 				5) NOT have to pay for their own game
 * 				6) Upload games/ mods/ update
 * 				7) Change the content (access to a different GUI)
 * 			
 * 				8) Perks: 	get an amount from developed games...!!! 	
 *							some other perks: if developers sell enough game to customers
 *												they get extra bonus points
 *				//TODO: How do you set up the requirements to become a developer									
 */

public class DeveloperShopAccount extends UserShopAccount{

	private int numberOfClients;
	List<GamePage> developedGames;
	
	public DeveloperShopAccount(String user, String id) {
		super(user, id);
		
		numberOfClients = 0;
		developedGames = new ArrayList<GamePage>();
	}

	@Override
	public void purchaseGame(GamePage page) {
		// TODO Auto-generated method stub
		//checkReward() here!
	}

	@Override
	public void transactCredits(String creditname, double change) {
		// TODO Auto-generated method stub
		//checkReward() here!
	}

	@Override
	public void transferInformation(PremiumShopAccount newAccount) {
		// TODO Auto-generated method stub
		
	}

	public void registerDevelopedGame(GamePage page)
	{
		if(!developedGames.contains(page))
			developedGames.add(page);
	}
	
	/**
	 * Removing an unliked mod
	 * @param modName
	 */
	public void removeMod(String modName, GamePage page)
	{
		
	}
	
	/**
	 * This method allows the user to 
	 * @param modName
	 * @param page
	 */
	public void certifyMod(String modName, GamePage page)
	{
		
	}
	
	/**
	 * This method allows the developer to uncertify mods
	 * @param modName
	 * @param page
	 */
	public void uncertifyMod(String modName, GamePage page)
	{
		
	}
	
	/**
	 * This method allows the developer to upload game to the library
	 */
	public void uploadGame()
	{
		//TODO: how do developers upload games?
		//check for getRewarded() here
	}
	
	/**
	 * This method specifies the specific perks the developer has
	 */
	private boolean getRewarded()
	{
		//TODO: write in the means by which developers get rewarded 
		// check the number of downloads
		return false;
	}
	
	
	/**
	 * This method returns the number of downloads for a game
	 * based on the title name
	 * @param gameTitle
	 */
	public void getNumberOfDownloads(String gameTitle)
	{
		//TODO: get this done
		//do you want to keep 
	}
	
	public int getTotalNumberOfDownloads()
	{
		int total = 0;
		
		for(GamePage page : developedGames)
			total += page.getNumberOfBuyers();
		
		return total;
	}
	
	private int getNumberOfDownloads(GamePage page)
	{
		return page.getNumberOfBuyers();
	}
}