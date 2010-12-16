package arcade.util.xmleditor.controllers;

import java.io.File;

import arcade.util.xmleditor.model.XMLNode;

/**
 * The controller which launches the main view and handles the 
 * lowest level interactions with the model. Other controllers 
 * are created at least indirectly by this controller. Has the 
 * capacity to set the model, save the model, and can be notified 
 * that a new node has been selected by the view so that observers 
 * can in turn be notified.
 * 
 * @author Daniel Koverman
 *
 */
public interface IBaseController{
	
	public void setModel(File file);
	
	public void save();
	
	public void save(File file);
	
	public void newNodeSelected(XMLNode node);

}
