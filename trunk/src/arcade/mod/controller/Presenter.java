package arcade.mod.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import arcade.core.Tab;
import arcade.core.mvc.IController;
import arcade.mod.model.IModel;
import arcade.mod.model.IResourceNode;
import arcade.mod.model.ModException;
import arcade.mod.model.XMLModel;
import arcade.mod.view.IViewer;
import arcade.mod.view.View;
import arcade.mod.view.frame.ListFrame;

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
public class Presenter implements IPresenter {

	IModel myModel;
	IViewer myView;
	FrameFactory myFactory;
	Collection<ListFrame> myFrames;
	
	/**
	 * Creates the instance of Controller when the Mod Environment is launched
	 */
	public Presenter(IViewer view) {
		myFactory = new FrameFactory();
		myView = view;
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
	private Collection<ListFrame> convertToView(List<IResourceNode> nodes) {
		List<ListFrame> frames = new ArrayList<ListFrame>();
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
	 * Do error checking here through Frame hierarchy
	 */
	@Override
	public boolean errorCheck() {
		for (ListFrame frame: myFrames){
			if (!frame.confirmValidity()){
				throw ModException.BAD_INPUT;
			}
		}
		return true;
	}


}