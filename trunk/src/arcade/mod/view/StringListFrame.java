package arcade.mod.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import arcade.mod.model.IResourceNode;
import arcade.mod.model.XMLNode;

public class StringListFrame extends AbstractListFrame {

	private String myString;
	private JTextField myInput;
	

	public StringListFrame() {

	}

	public StringListFrame(IResourceNode node) {
		super(node);

		myInput = new JTextField(30);

		restrictSize(HEIGHT);

		makeComponents();
	}

	@Override
	public AbstractListFrame newInstance(IResourceNode node) {
		if (!node.getNode().getNodeName().equals(XMLNode.DESCRIPTION_TAG))
			return new StringListFrame(node);
		else
			return new StringListFrame();
	}

	@Override
	public void handleNode(IResourceNode node) {
		myName = node.getAttribute("name");
		myString = node.getAttribute("value");
		myDescription = node.getDescription();

	}

	@Override
	public void makeComponents() {
		myInput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				myString = myInput.getText();

			}

		});
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
