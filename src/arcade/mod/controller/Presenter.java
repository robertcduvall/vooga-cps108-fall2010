package arcade.mod.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;
import arcade.core.GameView;
import arcade.mod.model.IModel;
import arcade.mod.model.IResourceNode;
import arcade.mod.model.ModException;
import arcade.mod.model.XMLModel;
import arcade.mod.view.IViewer;
import arcade.mod.view.frame.ListFrame;
import arcade.util.filehandling.FileHandler;

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

	@Override
	public void modificationRequest(File file,String className, String interfaceName, String modName) {		
			
			String currentGame = GameView.getGame().toLowerCase().replace(" ", "");
			
			//this is because digger is currently the only compliant game
			currentGame="digger";
			
			String destFilepath = System.getProperty("user.dir");
		
			destFilepath = destFilepath + File.separatorChar + "src" + File.separatorChar + "vooga" + File.separatorChar + "games" + File.separatorChar + currentGame + File.separatorChar + "mod" + File.separatorChar + file.getName();
		
			File destFile = new File(destFilepath);
			
			System.out.println(destFile);
		
		try {
			
			FileHandler.copyFile(file, destFile);
			
		} catch (IOException e) {
			System.out.println("Failed to copy file" + file);
		}
		
		//TODO:Modify the XML
		XMLDocumentCreator xmlCreator = new XMLFileParser("entityMap.xml");
		try {
			Document doc = xmlCreator.getDocument();
			Element element = doc.createElement("Mapping");
			element.setAttribute("callingClass", className);
			element.setAttribute("requestedClass", interfaceName);
			element.setAttribute("modName", modName);
			element.setAttribute("resolveTo", "vooga.games." + currentGame + ".mod." + file.getName().substring(0, file.getName().lastIndexOf('.')) );
			doc.getFirstChild().appendChild(element);

			// Prepare the DOM document for writing
			Source source = new DOMSource(doc);

			// Prepare the output file
			Result result = new StreamResult("entityMap.xml");

			// Write the DOM document to the file
			Transformer xformer = TransformerFactory.newInstance().newTransformer();
			xformer.transform(source, result);

		} catch (Throwable e) {
			e.printStackTrace();
		}
        
	}



}