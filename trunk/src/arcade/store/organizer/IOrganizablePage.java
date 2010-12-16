package arcade.store.organizer;

import java.util.List;

/**
 * IOrganizablePage is an interface for all pages that need to be organizable.
 * All classes that implement this interface must include a the method
 * getGameName which returns a string, getTags which returns a List of Strings
 * and getGenre which returns a String.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public interface IOrganizablePage {

	public String getGameName();

	public List<String> getTags();

	public String getGenre();
}
