package arcade.mod;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import arcade.mod.model.Model;
import arcade.mod.model.XMLModel;

public class Controller {

	Model myModel;
	
	public Controller() {
		View modView = new View(this);
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
	
	//temporary main method until arcade adds a button to call us
	public static void main(String[] args){
		Controller mod = new Controller();
	}	
}
