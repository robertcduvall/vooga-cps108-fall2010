package arcade.mod.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import arcade.mod.model.Model;
import arcade.mod.model.ResourceNode;
import arcade.mod.model.XMLModel;
import arcade.mod.view.AbstractListFrame;
import arcade.mod.view.View;

public class Controller {

	Model myModel;
	View myView;
	
	public Controller() {
		myView = new View(this);
		File xmlFile = null; //TODO replace null with file
		try {
			myModel = new XMLModel(xmlFile);
		} catch (Exception e) {
			//TODO show dialogue in view
			e.printStackTrace();
		}
	}
	
	public Collection<String> getCategories(){
		return myModel.getCategories();
	}
	
	
	public void framesHaveChanged(){
		List<ResourceNode> nodes = myModel.getResourcesFromCategory(myView.getCurrentCategory());
		myView.changeFrames(convertToView(nodes));
	}
	
	private Collection<AbstractListFrame> convertToView(List<ResourceNode> nodes){
		List<AbstractListFrame> frames = new ArrayList<AbstractListFrame>();
		for (ResourceNode node: nodes){
			frames.add(FrameFactory.createFrame(myView.getCurrentCategory()));
		}
		return frames;
	}
	
	public void save(){
		File saveFile = myView.requestSaveLocation();
		myModel.writeResources(saveFile);
	}
	
	//temporary main method until arcade adds a button to call us
	public static void main(String[] args){
		Controller mod = new Controller();
	}	
}
