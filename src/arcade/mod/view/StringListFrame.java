package arcade.mod.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import arcade.mod.model.ResourceNode;
import arcade.mod.model.XMLNode;

public class StringListFrame extends AbstractListFrame {

	private String myName;
	private String myString;
	private String myDescription;
	private JTextField myInput;
	private JLabel stringLabel;
	private JLabel descriptionLabel;

	public StringListFrame() {

	}

	public StringListFrame(ResourceNode node) {
		super(node);

		myInput = new JTextField(30);

		restrictSize(HEIGHT);

		makeComponents();
	}

	@Override
	public AbstractListFrame newInstance(ResourceNode node) {
		if (!node.getNode().getNodeName().equals(XMLNode.DESCRIPTION_TAG))
			return new StringListFrame(node);
		else
			return new StringListFrame();
	}

	@Override
	public void handleNode(ResourceNode node) {
		myName = node.getAttribute("name");
		myString = node.getAttribute("value");
		myDescription = node.getDescription();

	}

	@Override
	public void makeComponents() {
		descriptionLabel = new JLabel();
		JLabel stringName = new JLabel();
		stringLabel = new JLabel();
		JLabel newValue = new JLabel();

		descriptionLabel.setText(myDescription);
		stringName.setText("String name: " + myName);
		stringLabel.setText("Value: " + myString);
		newValue.setText("New String: ");

		add(stringName);
		add(descriptionLabel);
		add(stringLabel);
		add(newValue);
		add(myInput);

	}
}
