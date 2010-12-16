package arcade.util.xmleditor.controllers.toolbar;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.w3c.dom.Element;

/**
 * Adds a new child to the current XML tree. Element tag name 
 * is prompted. It will start with no attributes.
 * @author Daniel Koverman
 *
 */
public class AddChildController extends ElementToolBarButton {

	private static final String LABEL_TEXT = "Add Child";

	public AddChildController() {
		super(LABEL_TEXT);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = (String) JOptionPane.showInputDialog("Element Tag Name");
		Element child = getNode().getDocument().createElement(s);

		getNode().appendChild(child);
	}

}
