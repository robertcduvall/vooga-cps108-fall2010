package arcade.util.xmleditor.controllers.element.name;

import javax.swing.JComponent;

import arcade.util.xmleditor.views.element.name.ElementNamePanel;
import arcade.util.xmleditor.views.element.name.StringPanel;


public class ElementNameController implements IElementNameController{
	
	private StringPanel view;
	
	public ElementNameController(){
		view = new ElementNamePanel();
	}
	
	public ElementNameController(String elementName){
		this();
		setElementName(elementName);
	}
	
	@Override
	public JComponent getView(){
		return view;
	}
	
	@Override
	public void setElementName(String elementName){
		view.updateString(elementName);
	}

}
