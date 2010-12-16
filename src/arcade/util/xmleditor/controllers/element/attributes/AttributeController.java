package arcade.util.xmleditor.controllers.element.attributes;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JComponent;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import arcade.util.xmleditor.views.element.attributes.AttributePanel;
import arcade.util.xmleditor.views.element.attributes.IAttributeView;

public class AttributeController implements IAttributeController {

	private IAttributeView view;

	public AttributeController() {
		view = new AttributePanel();
	}

	public AttributeController(NamedNodeMap attributeMap) {
		this();
		setAttributeMap(attributeMap);
	}

	public JComponent getView() {
		return (JComponent) view;
	}

	@Override
	public void setAttributeMap(NamedNodeMap attributeMap) {
		Collection<AttributeFrameController> frames = new ArrayList<AttributeFrameController>();
		for (int i = 0; i < attributeMap.getLength(); i++) {
			Node attribute = attributeMap.item(i);
			frames.add(new AttributeFrameController(attribute));
		}
		updateView(frames);
	}

	private void updateView(Collection<AttributeFrameController> frames) {
		view.clearAttributes();
		for (AttributeFrameController frame : frames) {
			view.addAttributeFrame(frame.getView());
		}
	}
}
