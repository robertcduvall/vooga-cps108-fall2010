package arcade.store.account;

import java.util.List;

/**
 * DeveloperUser extends StoreUser and describes what a developer can do in the
 * store.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public class DeveloperUser extends StoreUser {
	
	private static final String DEVELOPER_TYPE = "2";
	
	@Override
	public String getAccountType() {
		return DEVELOPER_TYPE; 
	}
	
	@Override
	public void setCreddits(double newCreddits) {
		setCreddits(0);
	}

}
