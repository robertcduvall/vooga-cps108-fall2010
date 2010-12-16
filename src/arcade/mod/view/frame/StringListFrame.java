package arcade.mod.view.frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import arcade.mod.model.IResourceNode;
import arcade.mod.model.XMLNode;

public class StringListFrame extends ListFrame {

	private String myString;
	private JTextField myInput;

	public StringListFrame() {

	}

	public StringListFrame(IResourceNode node) {
		super(node);

		restrictSize(HEIGHT);
		
	}

	@Override
	public ListFrame newInstance(IResourceNode node) {
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

		myDescriptionLabel.setText(myDescription);
		myNameLabel.setText("String name: " + myName);
		myStringLabel.setText("Value: " + myString);
		myNewValue.setText("New String: ");
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets=  new Insets(3,3,3,3);
		gbc.ipadx = 2;
		gbc.ipady = 2;
		
		gbc.gridy = 0;
		gbc.gridwidth=2;
		add(myDescriptionLabel, gbc);
		gbc.gridwidth=1;
		gbc.gridy=1;
		gbc.gridx = 0;
		add(myNameLabel, gbc);
		gbc.gridx = 1;
		add(myStringLabel, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		add(myNewValue, gbc);
		gbc.gridx = 1;
		add(myInput, gbc);

	}

	@Override
	public void initializeComponents() {
		myInput = new JTextField(30);
		myDescriptionLabel = new JLabel();
		myNameLabel = new JLabel();
		myStringLabel = new JLabel();
		myNewValue = new JLabel();

		makeComponents();
	}

	@Override
	public boolean confirmValidity() {
		// TODO Auto-generated method stub
		return true;
	}
}
