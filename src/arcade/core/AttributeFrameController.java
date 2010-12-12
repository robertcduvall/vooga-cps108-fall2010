package arcade.core;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import org.w3c.dom.Node;

public class AttributeFrameController implements FocusListener{
	
	AttributeFrame view;
	private Node attribute;
	
	public AttributeFrameController(Node attribute, AttributeFrame view){
		this.attribute = attribute;
		this.view = view;
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
