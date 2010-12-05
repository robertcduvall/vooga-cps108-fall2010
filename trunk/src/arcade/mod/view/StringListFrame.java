package arcade.mod.view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import arcade.mod.model.ResourceNode;

public class StringListFrame extends AbstractListFrame {

	private String myName;
	private String myString;
	private JTextField myInput;
	private JLabel stringLabel;

	public StringListFrame() {

	}

	public StringListFrame(ResourceNode node) {

		super(node);

		myInput = new JTextField(30);

		restrictSize(DEFAULT_HEIGHT);

		makeComponents();
	}

	@Override
	public AbstractListFrame newInstance(ResourceNode node) {

		return new StringListFrame(node);
	}

	@Override
	public void handleNode(ResourceNode node) {
		myName = node.getAttribute("name");
		myString = node.getAttribute("value");

	}

	@Override
	public void makeComponents() {
		JLabel stringName = new JLabel();
		stringLabel = new JLabel();
		JLabel newValue = new JLabel();

		stringName.setText("String name: " + myName);
		stringLabel.setText("Value: " + myString);
		newValue.setText("New String: ");

		add(stringName);
		add(stringLabel);
		add(newValue);
		add(myInput);

	}
}
