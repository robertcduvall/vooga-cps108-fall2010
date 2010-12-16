package arcade.util.xmleditor.views.element.attributes;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class AttributePanel extends JPanel implements IAttributeView {

	private static final long serialVersionUID = 1L;

	public final int DEFAULT_TEXT_FIELD_COL = 15;

	public AttributePanel() {

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	}

	@Override
	public void clearAttributes() {
		removeAll();
		updateUI();
	}

	@Override
	public void addAttributeFrame(AttributeFrame frame) {
		add(frame);
	}

}
