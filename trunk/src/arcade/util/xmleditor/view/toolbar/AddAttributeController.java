package arcade.util.xmleditor.view.toolbar;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.w3c.dom.Element;

import arcade.util.xmleditor.model.XMLNode;

public class AddAttributeController extends ElementToolBarButton {
	
	public AddAttributeController(){
		super("Add Attribute");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = (String) JOptionPane.showInputDialog("Attribute Name");
		getNode().addAttribute(s);
	}

}
