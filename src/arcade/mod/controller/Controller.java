package arcade.mod.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import arcade.mod.model.Model;
import arcade.mod.model.ResourceNode;
import arcade.mod.model.XMLModel;
import arcade.mod.view.AbstractListFrame;
import arcade.mod.view.View;

/**
 * 
 * Controls and manages interactions between the Model and View.  In keeping with the Model-View-Presenter
 * framework objectives, we tried to restrict the Controller to a thin translator that carries out the requirements
 * of the View while keeping the View 'dumb'.
 * 
 * @author Brian
 * @author Brent
 * @author Daniel
 * @author Vitor
 *
 */
public class Controller {

	Model myModel;
	View myView;
	FrameFactory myFactory;
	
	
	/**
	 * Creates the instance of Controller when the Mod Environment is launched
	 */
	public Controller() {
		myFactory = new FrameFactory();
		myView = new View(this);
		File xmlFile = myView.loadFile(); //TODO replace null with file
		try {
			myModel = new XMLModel(xmlFile);
		} catch (Exception e) {
			//TODO show dialogue in view
			e.printStackTrace();
		}
		myView.initializeOnStart();
	}
	
	/**
	 * Retrieve the collection of categories from the Model
	 * @return a Collection of Strings representing all category types (i.e.-Images, Sounds, etc.)
	 */
	public Collection<String> getCategories(){
		return myModel.getCategories();
	}
	
	/**
	 * Ask the model to get resources of a specified category.  Tell the View to change the frames.
	 */
	public void framesHaveChanged(){
		List<ResourceNode> nodes = myModel.getResourcesFromCategory(myView.getCurrentCategory());
		myView.changeFrames(convertToView(nodes));
	}
	
	/**
	 * Translate between the ResourceNodes that the Model works with to the AbstractListFrames that the View works with
	 * @param nodes list of all ResourceNodes in XML file
	 * @return collection of all AbstractListFrames from nodes
	 */
	private Collection<AbstractListFrame> convertToView(List<ResourceNode> nodes){
		List<AbstractListFrame> frames = new ArrayList<AbstractListFrame>();
		for (ResourceNode node: nodes){
			frames.add(myFactory.createFrame(myView.getCurrentCategory(), node));
		}
		return frames;
	}
	
	/**
	 * Save an XML file
	 */
	public void save(){
		File saveFile = myView.requestSaveLocation();
		myModel.writeResources(saveFile);
	}
	
	/**
	 * Temporary main method until arcade adds a button to call us
	 * @param args command line input
	 */
	public static void main(String[] args){
		Controller mod = new Controller();
	}	
}
