package arcade.store.account;

import javax.swing.JOptionPane;

public class GuestUser extends StoreUser {
	
	public void updateToCreddits(double creddits) {
		JOptionPane.showMessageDialog(null, "Guest users cannot add creddits");
	}
	
	public void addToCart(String name) {
		JOptionPane.showMessageDialog(null, "Guest users cannot purchase games");
	}
	

}
