package arcade.mod.controller;

import java.util.Collection;

/**
 * Presenter is an interface which dictates the basic logic we need for a Presenter in the MVP design structure
 * 
 */
public interface Presenter {
	
	/**
	 * Save the current XML file in the Model
	 */
	public void save();
	
	/**
	 * Retrieve the categories of resource type (i.e.-images, sounds, etc.)
	 * @return Collection of Strings that represent all available categories in the current XML file
	 */
	//TODO remove necessity to return Collection
	public Collection<String> getCategories();
	
	/**
	 * Do something when once a category is selected
	 */
	public void newCategorySelected();

}
