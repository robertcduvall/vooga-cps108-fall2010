package arcade.util.xmleditor.controllers.element;

import javax.swing.JComponent;

import org.w3c.dom.NamedNodeMap;

import arcade.util.xmleditor.controllers.element.attributes.AttributeController;
import arcade.util.xmleditor.controllers.element.attributes.IAttributeController;
import arcade.util.xmleditor.controllers.element.name.ElementNameController;
import arcade.util.xmleditor.controllers.element.name.IElementNameController;
import arcade.util.xmleditor.model.ModelListener;
import arcade.util.xmleditor.model.XMLNode;
import arcade.util.xmleditor.views.element.ElementPanel;

public class ElementController implements ModelListener{
	
	private ElementPanel view;
	private IAttributeController attributeController;
	private IElementNameController nameController;
	private XMLNode currentNode;
	
	public ElementController(JComponent toolbar){
		this.attributeController = new AttributeController();
		nameController = new ElementNameController();
		
		view = new ElementPanel(attributeController.getView(), nameController.getView(), toolbar);
	}
	
	public ElementPanel getView(){
		return view;
	}

	@Override
	public void modelChanged(XMLNode root) {
		//Do nothing
	}

	@Override
	public void nodeSelected(XMLNode node) {
		currentNode = node;
		updateDependents();
	}

	@Override
	public void nodeUpdated(XMLNode node) {
		if(currentNode==node){
			updateDependents();
		}
	}
	
	private void updateDependents(){
		NamedNodeMap attributeMap = currentNode.getAttributes();
		attributeController.setAttributeMap(attributeMap);
		nameController.setElementName(currentNode.getName());
	}

}
