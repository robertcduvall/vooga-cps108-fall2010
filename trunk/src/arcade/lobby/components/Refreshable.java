package arcade.lobby.components;


/**
 * All components in the profile must implement this interface,
 * so when the user changes the panels can be refreshed.
 */
public interface Refreshable {
	
	public void refresh();

}
