package arcade.util.xmleditor.model;

import org.w3c.dom.Element;


public interface ModelListener {
	
	public void modelChanged(XMLNode root);
	
	public void nodeSelected(XMLNode node);	
	
	public void nodeUpdated(XMLNode node);

}
