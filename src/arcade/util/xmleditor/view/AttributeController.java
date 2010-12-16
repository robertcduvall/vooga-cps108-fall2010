package arcade.util.xmleditor.view;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import arcade.util.xmleditor.model.ModelListener;
import arcade.util.xmleditor.model.XMLNode;

public class AttributeController implements ModelListener{
	
	private NamedNodeMap attributeMap;
	private AttributePanel view;
	
	public AttributeController(){	
		view = new AttributePanel();
	}
	
	public AttributeController(NamedNodeMap attributeMap){
		this();		
		this.attributeMap = attributeMap;
	}
	
	public AttributePanel getView(){
		return view;
	}
	
	public void setAttributeMap(NamedNodeMap attributeMap){
		this.attributeMap = attributeMap;
		view.update(attributeMap);
	}

	@Override
	public void nodeSelected(XMLNode node) {
		setAttributeMap(node.getAttributes());
	}

	@Override
	public void nodeUpdated(XMLNode node) {
		setAttributeMap(node.getAttributes());
		
	}

	@Override
	public void modelChanged(XMLNode root) {
		// Do nothing
		
	}
}
