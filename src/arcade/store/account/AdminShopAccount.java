package arcade.store.account;

import arcade.store.page.GamePage;

/**
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * @date 12-05-10
 * @description 
 *
 */

public class AdminShopAccount extends UserShopAccount{

	public AdminShopAccount(String user, String id) {
		super(user, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void purchaseGame(GamePage page) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transactCredits(String creditname, double change) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferInformation(PremiumShopAccount newAccount) {
		// TODO Auto-generated method stub
		
	}

}
