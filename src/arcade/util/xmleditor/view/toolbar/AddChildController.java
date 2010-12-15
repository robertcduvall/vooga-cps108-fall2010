package arcade.util.xmleditor.view.toolbar;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.w3c.dom.Element;

public class AddChildController extends ElementToolBarButton{
	
	
	public AddChildController(){
		super("Add Child");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = (String) JOptionPane.showInputDialog("Element Tag Name");
		Element child = getElement().getOwnerDocument().createElement(s);
		
		getElement().appendChild(child);
	}

}
