package arcade.mod.view;

import java.io.File;
import java.util.Collection;

import arcade.mod.view.frame.ListFrame;

/**
 * The front end GUI of the mod environment. This acts
 * as the view in the model-view-presenter paradigm.
 * This interface provides the presenter with methods 
 * for requesting information and updating the view. 
 * Generally views should not handle user events or 
 * program logic not directly related the interface,
 * but should instead inform the presenter so the presenter
 * may take the proper action.
 * 
 * @author Daniel Koverman
 *
 */
public interface IViewer {
	
	/**
	 * Initialize the viewer after construction. This is 
	 * necessary because the viewer may be required to select 
	 * a file in order to create the model, but it also may need 
	 * data from the model to initialize properly. This method 
	 * allows partial initialization on construction and full 
	 * initialization after the model is created.
	 */
	public void initialize();
	
	/**
	 * Allows the presenter a graphical means of selecting 
	 * a file to open.
	 * @return File to open
	 */
	public File openFileSelect();
	
	/**
	 * Allows the presenter a graphical means of selecting
	 * a file to close.
	 * @return File to save to
	 */
	public File saveFileSelect();
	
	/**
	 * Allows the presenter to determine which category of 
	 * resources the viewer currently has selected
	 * @return category of resources selected in viewer
	 */
	public String getCurrentCategory();
	
	public void changeCategories(Collection<String> categories);
	
	/**
	 * Removes all ListFrames currently in the center panel and 
	 * replaces them with new ones which are automatically 
	 * displayed.
	 * @param frames ListFrames to populate the center panel with
	 */
	public void changeFrames(Collection<ListFrame> frames);

}
