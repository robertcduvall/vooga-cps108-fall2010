package arcade.mod.model;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * An interface specifying a model for use in the 
 * mod environment. The model is designed according to 
 * the Model-View-Presenter paradigm. Capable of 
 * producing a list of resource types and returning 
 * resources of the selected type as ResourceNodes. Also
 * capable of writing the current ResouceNodes held in the 
 * model to a file.
 * 
 * @author Daniel Koverman
 *
 */
public interface IModel {
	
	/**
	 * Get available categories or types of resources held 
	 * by the model
	 * @return The categories of resources in the model
	 */
	public Collection<String> getCategories();
	
	/**
	 * Returns all ResourceNodes of a given type specified by a 
	 * String. To get a Collection of valid Strings, call the 
	 * getCategories() method first. These ResourceNodes will 
	 * contain all of the information and behavior required 
	 * to get and edit mod-able information in the Model.
	 * @param category
	 * @return
	 */
	public List<IResourceNode> getResourcesFromCategory(String category);
	
	/**
	 * Write the resources to a file for use when running a game mod 
	 * or to edit later. Format of the resources depends on model 
	 * implementation.
	 * @param file File which the resources should be written to.
	 */
	public void writeResources(File file);

	
}
