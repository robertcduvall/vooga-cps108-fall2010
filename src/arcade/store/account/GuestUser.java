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

	//TODO: Who did this?
	public void setCreddits(double creddits) {
		JOptionPane.showMessageDialog(null, "Guest users cannot add creddits");
	}

	@Override
	public void addToCart(String name) {
		JOptionPane
				.showMessageDialog(null, "Guest users cannot purchase games");
	}

}
