package arcade.util.xmleditor.view;

import org.w3c.dom.NamedNodeMap;

public class AttributeController{
	
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
}
