package arcade.store.account;

import arcade.store.page.GamePage;

/**
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date 12-05-10
 * @description Here's a guest shop account.
 * 				1) A guest should not be able to add credits
 *				2) A guest should only be able to browse games
 */

public class GuestShopAccount extends UserShopAccount{

	public GuestShopAccount(String user, String id) {
		super(user, id);
	
	}

	@Override
	public void purchaseGame(GamePage page) {
		//TODO: should a guest even know about this mechanism?
	}

	@Override
	public void transactCredits(String creditname, double change) {
		
	}

	@Override
	public void transferInformation(PremiumShopAccount newAccount) {
		
		String username = getUserName();
		String userID = getAccountID();
		
		newAccount.resetUserID(userID);
		newAccount.resetUserName(username);	
	}

	
		
	
	
}
