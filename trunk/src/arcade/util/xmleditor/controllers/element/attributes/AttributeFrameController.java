package arcade.util.xmleditor.controllers.element.attributes;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import org.w3c.dom.Node;

import arcade.util.xmleditor.views.element.attributes.AttributeFrame;

/**
 * Keeps track of one attribute from the attributes of the 
 * currently selected XMLNode and responds when the view 
 * informs it that the user has changed its value.
 * 
 * @author Daniel Koverman
 *
 */
public class AttributeFrameController implements FocusListener {

	private Node attribute;
	private AttributeFrame view;

	public AttributeFrameController(Node attribute) {
		this.attribute = attribute;
		view = new AttributeFrame(this,attribute);
	}

	public AttributeFrame getView(){
		return view;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// Do nothing
	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField textField = (JTextField) e.getSource();
		attribute.setNodeValue(textField.getText());
	}

}
