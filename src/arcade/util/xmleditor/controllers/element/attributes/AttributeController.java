package arcade.util.xmleditor.controllers.element.attributes;

import javax.swing.JComponent;

import org.w3c.dom.NamedNodeMap;

import arcade.util.xmleditor.views.element.attributes.AttributePanel;
import arcade.util.xmleditor.views.element.attributes.IAttributeView;


public class AttributeController implements IAttributeController{
	
	private IAttributeView view;
	
	public AttributeController(){	
		view = new AttributePanel();
	}
	
	public AttributeController(NamedNodeMap attributeMap){
		this();		
		setAttributeMap(attributeMap);
	}
	
	public JComponent getView(){
		return (JComponent) view;
	}
	
	@Override
	public void setAttributeMap(NamedNodeMap attributeMap){
		view.updateAttributes(attributeMap);
	}
}
