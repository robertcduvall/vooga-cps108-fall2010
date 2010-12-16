package arcade.store.organizer;

import java.util.List;

import arcade.store.items.IItemInfo;

/**
 * IOrganizer is an interface for all class that perform the organize method.
 * Classes that implement this interface must have a method that takes in a List
 * of IItemInfo objects and a String of criteria and returns an organized list.
 * 
 * @author Drew Sternesky, Jimmy Mu, Marcus Molchany
 * 
 */
public interface IOrganizer {

	public List<IItemInfo> organize(List<IItemInfo> list, String criteria);

}
