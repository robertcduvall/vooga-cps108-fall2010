package arcade.util.xmleditor.view.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.w3c.dom.Element;

import arcade.util.xmleditor.model.ModelListener;
import arcade.util.xmleditor.model.XMLNode;

public class AddAttributeController implements ActionListener, ModelListener {
	
	private Element element;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = (String) JOptionPane.showInputDialog("Attribute Name");
		element.setAttribute(s, "");
	}

	@Override
	public void modelChanged(XMLNode root) {
		element = null;		
	}

	@Override
	public void elementSelected(Element element) {
		this.element = element;		
	}

}
