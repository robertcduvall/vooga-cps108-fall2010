package arcade.util.xmleditor.controllers.toolbar;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;


public class AddAttributeController extends ElementToolBarButton {
	
	private static final String LABEL_TEXT = "Add Attribute";
	
	public AddAttributeController(){
		super(LABEL_TEXT);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = (String) JOptionPane.showInputDialog("Attribute Name");
		getNode().addAttribute(s);
	}

}
