package arcade.util.xmleditor.views;

import arcade.util.xmleditor.model.XMLNode;

/**
 * The lowest structure on the view which contains all other elements of the
 * view. Should be combined with an IBaseController. Must have capacity to
 * display itself, show errors in applicable environment, reload a model when a
 * new model is being displayed, and updating a model when something about the
 * model has changed.
 * 
 * @author Daniel Koverman
 * 
 */
public interface IBaseView {

	/**
	 * Have the view display itself. In a stand alone context a frame should be
	 * created and made visible here. The view can either be an extension of the
	 * frame or a panel which is placed in that frame.
	 */
	public void showView();

	/**
	 * Display an error with a message designated by the controller in, for
	 * instance, a dialog box.
	 * 
	 * @param message
	 *            The error message to be displayed
	 */
	public void showError(String message);

	/**
	 * This method is called by the controller when a new model is replacing the
	 * current model being displayed. The model should be loaded by traversing
	 * the tree given the root of the tree
	 * 
	 * @param node
	 *            The root of the XML model tree
	 */
	public void reloadModel(XMLNode root);

	/**
	 * This method is called by the controller when the current models tree
	 * structure has been revised. For instance, a new node may have been added
	 * or a node may have been deleted and the view should reflect this change.
	 * All changes will have occurred at or below the node parameter.
	 * 
	 * @param node
	 *            Top level at which model needs to be checked for changes to
	 *            tree structure
	 */
	public void updateModel(XMLNode node);

}
