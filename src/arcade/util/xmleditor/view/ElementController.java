package arcade.util.xmleditor.view;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import arcade.util.xmleditor.Controller;
import arcade.util.xmleditor.model.XMLNode;
import arcade.util.xmleditor.view.toolbar.ElementToolBar;

public class ElementController implements TreeSelectionListener{
	
	Controller parent;
	ElementPanel view;
	Element element;
	AttributeController attributeController;
	ElementNameController nameController;
	
	public ElementController(Controller parent){
		this.parent = parent;
		attributeController = new AttributeController();
		nameController = new ElementNameController();
		
		view = new ElementPanel(this, attributeController.getView(), nameController.getView(), new ElementToolBar());
	}
	
	public ElementPanel getView(){
		return view;
	}
	
	public void addAttribute(Node attributeNode){
		element.setAttribute(attributeNode.getNodeName(), attributeNode.getNodeValue());
	}
	

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		System.out.println("selection changed");
		element = ((XMLNode) arg0.getPath().getLastPathComponent()).getElement();
		NamedNodeMap attributeMap = element.getAttributes();
		attributeController.setAttributeMap(attributeMap);
		nameController.setElementName(element.getNodeName());
	}

}
