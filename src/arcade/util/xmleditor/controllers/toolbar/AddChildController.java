package arcade.util.xmleditor.controllers.toolbar;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.w3c.dom.Element;

import arcade.util.xmleditor.model.XMLNode;

public class AddChildController extends ElementToolBarButton {

	private static final String LABEL_TEXT = "Add Child";

	public AddChildController() {
		super(LABEL_TEXT);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s = (String) JOptionPane.showInputDialog("Element Tag Name");
		Element child = getNode().getElement().getOwnerDocument()
				.createElement(s);

		getNode().appendChild(
				new XMLNode(child, getNode(), getNode().getObserver()));
	}

}
