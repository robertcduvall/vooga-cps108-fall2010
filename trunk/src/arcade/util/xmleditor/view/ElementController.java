package arcade.util.xmleditor.view;

import javax.swing.JToolBar;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import arcade.util.xmleditor.Controller;
import arcade.util.xmleditor.model.XMLNode;

public class ElementController implements TreeSelectionListener{
	
	Controller parent;
	ElementPanel view;
	Element element;
	AttributeController attributeController;
	ElementNameController nameController;
	
	public ElementController(Controller parent, JToolBar toolbar, AttributeController attributeController){
		this.parent = parent;
		this.attributeController = attributeController;
		nameController = new ElementNameController();
		
		view = new ElementPanel(this, attributeController.getView(), nameController.getView(), toolbar);
	}
	
	public ElementPanel getView(){
		return view;
	}
	
	public void addAttribute(Node attributeNode){
		element.setAttribute(attributeNode.getNodeName(), attributeNode.getNodeValue());
	}
	

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		element = ((XMLNode) arg0.getPath().getLastPathComponent()).getElement();
		NamedNodeMap attributeMap = element.getAttributes();
		attributeController.setAttributeMap(attributeMap);
		nameController.setElementName(element.getNodeName());
	}

}
