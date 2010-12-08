package arcade.mod.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

		restrictSize(HEIGHT);
		
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

		descriptionLabel.setText(myDescription);
		myNameLabel.setText("String name: " + myName);
		stringLabel.setText("Value: " + myString);
		newValue.setText("New String: ");

		add(myNameLabel);
		add(descriptionLabel);
		add(stringLabel);
		add(newValue);
		add(myInput);

	}

	@Override
	public boolean confirmValidity(File saveFile) {
		// TODO: do actual error checking here
		return true;
	}

	@Override
	public void initializeComponents() {
		myInput = new JTextField(30);
		descriptionLabel = new JLabel();
		myNameLabel = new JLabel();
		stringLabel = new JLabel();
		newValue = new JLabel();

		makeComponents();
	}
}
