package arcade.mod.controller;

import java.io.File;
import java.util.Collection;

/**
 * Presenter interfaces acts as the presenter
 * in the model-view-presenter GUI paradigm. Designed 
 * to handle events which occur in the view. This 
 * interface provides the means for the view to 
 * inform the presenter of new events. Ideally, they 
 * should all have no parameters and no return value.
 * 
 * Implementations of this interface are responsible for 
 * most of the logic for editing and reading a model which 
 * is not specific to a concrete model implementation.
 * 
 * @author Daniel Koverman
 *
 */
public interface IPresenter {
	
	/**
	 * Inform the presenter that the user is 
	 * attempting to save.
	 */
	public void save();
	
	/**
	 * Inform the presenter that the user is
	 * attempting to load
	 */
	public void load();
	
	
	/**
	 * Inform the presenter that the user is 
	 * attempting to view a new category of 
	 * resources
	 */
	public void newCategorySelected();

	/**
	 * Confirm that a given file is valid
	 * @param saveFile - file to be saved
	 * @return true if a file is valid
	 */
	public boolean errorCheck(File saveFile);
}
