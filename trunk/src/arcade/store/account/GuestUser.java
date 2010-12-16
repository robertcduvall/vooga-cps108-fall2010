package arcade.store.account;

import javax.swing.JOptionPane;

/**
 * GuestUser is a subclass of StoreUser and defines the difference between a
 * regular store user and a guest. GuestUser overrides the methods
 * updateToCreddits and addToCart making it so that a guest cannot perform these
 * actions.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class GuestUser extends StoreUser {

	private static final String GUEST_USER_TYPE = "0";
	
	public GuestUser(String id, double creddits, String cart)
	{
		super(id, creddits, cart);
	}
	
	
	@Override
	public void addToCart(String name) {
	
	}
	
	/**
	 * This method returns the guest user type
	 * @return
	 */
	public String getType()
	{
		return GUEST_USER_TYPE;
	}
	
	
}
