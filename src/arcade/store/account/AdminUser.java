package arcade.store.account;

/**
 * AdminUser extends StoreUser and describes what an administrator can do in the
 * store.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class AdminUser extends StoreUser {

	private static final String ADMIN_TYPE = "3";
	private static final int ADMIN_CREDDITS = 1000000;
	
	@Override
	public String getAccountType() {
		return ADMIN_TYPE; 
	}

	@Override
	public void setCreddits(double newCreddits) {
		setCreddits(newCreddits + ADMIN_CREDDITS);
	}
}
