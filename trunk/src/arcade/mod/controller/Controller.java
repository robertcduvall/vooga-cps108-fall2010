package arcade.mod.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JComponent;

import arcade.core.Tab;
import arcade.mod.model.IModel;
import arcade.mod.model.IResourceNode;
import arcade.mod.model.XMLModel;
import arcade.mod.view.AbstractListFrame;
import arcade.mod.view.IViewer;
import arcade.mod.view.View;

/**
 * 
 * Controls and manages interactions between the Model and View. In keeping with
 * the Model-View-Presenter framework objectives, we tried to restrict the
 * Controller to a thin translator that carries out the requirements of the View
 * while keeping the View 'dumb'.
 * 
 * @author Brian
 * @author Brent
 * @author Daniel
 * @author Vitor
 * 
 */
public class Controller extends Tab implements IPresenter {

	public static final String TAB_NAME = "Mod";
	IModel myModel;
	IViewer myView;
	FrameFactory myFactory;
	Collection<AbstractListFrame> myFrames;
	
	/**
	 * Creates the instance of Controller when the Mod Environment is launched
	 */
	public Controller() {
		setName(TAB_NAME);
		myFactory = new FrameFactory();
		myView = new View(this);
		myView.initialize();
//		File xmlFile = myView.openFileSelect();
//		if (xmlFile != null) {
//			try {
//				myModel = new XMLModel(xmlFile);
//			} catch (Exception e) {
//				// TODO show dialog in view
//				e.printStackTrace();
//			}
//		} else {
//			System.exit(1);
//		}
		
	}

	public void load(){
		File xmlFile = myView.openFileSelect();
		if (xmlFile != null) {
			try {
				myModel = new XMLModel(xmlFile);
			} catch (Exception e) {
				// TODO show dialog in view
				e.printStackTrace();
			}
		}
		myView.changeCategories(myModel.getCategories());
	}
	
	/**
	 * Retrieve the collection of categories from the Model
	 * 
	 * @return a Collection of Strings representing all category types
	 *         (i.e.-Images, Sounds, etc.)
	 */
	public Collection<String> getCategories() {
		return myModel.getCategories();
	}

	/**
	 * Tell the model to get resources of a specified category. Tell the View to
	 * change the frames.
	 */
	public void newCategorySelected() {

		List<IResourceNode> nodes = myModel.getResourcesFromCategory(myView
				.getCurrentCategory());
		myView.changeFrames(convertToView(nodes));
	}

	/**
	 * Translate between the ResourceNodes that the Model works with to the
	 * AbstractListFrames that the View works with
	 * 
	 * @param nodes
	 *            list of all ResourceNodes in XML file
	 * @return collection of all AbstractListFrames from nodes
	 */
	private Collection<AbstractListFrame> convertToView(List<IResourceNode> nodes) {
		List<AbstractListFrame> frames = new ArrayList<AbstractListFrame>();
		for (IResourceNode node : nodes) {

			frames.add(myFactory.createFrame(myView.getCurrentCategory(), node));
		}
		myFrames = frames;
		return frames;
	}

	/**
	 * Save an XML file
	 */
	public void save() {
		File saveFile = myView.saveFileSelect();
		if (errorCheck()) {
			myModel.writeResources(saveFile);
		}
	}

	/**
	 * Temporary main method until arcade adds a button to call us
	 * 
	 * @param args
	 *            command line input
	 */
	public static void main(String[] args) {
		Controller mod = new Controller();
	}

	/**
	 * Get the content from the View
	 * @return JComponent - View cast to a JComponent object
	 */
	@Override
	public JComponent getContent() {
		return (JComponent) myView;
	}

	/**
	 * Do error checking here through Frame hierarchy
	 */
	@Override
	public boolean errorCheck() {
		for (AbstractListFrame frame: myFrames){
			if (!frame.confirmValidity()){
				return false;
			}
		}
		return true;
	}
}