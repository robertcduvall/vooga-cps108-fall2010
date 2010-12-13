package arcade.util.xmleditor.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.w3c.dom.Node;

public class AttributeFrame extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private AttributeFrameController controller;
	
	public AttributeFrame(Node attribute){
		controller = new AttributeFrameController(attribute, this);
		
		JLabel label = new JLabel(attribute.getNodeName());
		JTextField textField = new JTextField(attribute.getNodeValue());
		textField.addFocusListener(controller);
		add(label);
		add(textField);
	}

}
