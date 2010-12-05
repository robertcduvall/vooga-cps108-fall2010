package arcade.mod.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import arcade.mod.model.Model;
import arcade.mod.model.XMLModel;
import arcade.mod.view.View;

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
	
	public Collection<Object> getCategories(){
		Collection<String> strings = myModel.getCategories();
		Collection<Object> objects = new ArrayList<Object>();
		for (String s: strings){
			objects.add(new StringObject(s));
		}
		return objects;
	}
	
	//temporary main method until arcade adds a button to call us
	public static void main(String[] args){
		Controller mod = new Controller();
	}	
}
