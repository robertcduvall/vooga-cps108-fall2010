package arcade.mod.view.frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import arcade.mod.model.IResourceNode;
import arcade.mod.model.ModException;

public abstract class SliderListFrame extends ListFrame {

	protected String myValue;
	protected JSlider mySliderInput;
	protected JTextField myTextInput;

	protected int MIN;
	protected int MAX;
	protected int INIT;
	
	private final int HEIGHT = 100;
	
	protected GridBagConstraints gbConstraints;

	public SliderListFrame() {

	}

	public SliderListFrame(IResourceNode node) {
		
		super(node);
		
				
		restrictSize(HEIGHT);

	}

	@Override
	public void initializeComponents() {
		myDescriptionLabel = new JLabel();
		myNameLabel = new JLabel();
		myNewValue = new JLabel();
		myCurrentValue = new JLabel();
		myStringLabel = new JLabel();
		myTextInput = new JTextField();

	}

	@Override
	public ListFrame newInstance(IResourceNode node) {

		return new IntegerListFrame(node);
	}

	@Override
	public void handleNode(IResourceNode node) {

		myDescription = node.getDescription();
		myName = node.getAttribute("name");
		myValue = node.getAttribute("value");
		myTextInput.setText(myValue);
		if (node.getAttributes().contains("max")) {
			MAX = Integer.parseInt(node.getAttribute("max"));
		} else {
			MAX = 1000;
		}
		if (node.getAttributes().contains("min")) {
			MIN = Integer.parseInt(node.getAttribute("min"));
		} else {
			MIN = -1000;
		}
		mySliderInput = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);

		handleInitialParsing();
	}

	public abstract void handleInitialParsing();

	public abstract String parseChange(Integer value);

	@Override
	public void makeComponents() {

		Integer value = INIT;
		myTextInput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				int value = Integer.parseInt(myValue);
				try {
					value = Integer.parseInt(myTextInput.getText());
					myValue = myTextInput.getText();
				} catch (Exception e) {
					throw ModException.BAD_INPUT;
				}
				mySliderInput.setValue(value);
				myCurrentValue.setText(myValue);

			}

		});
		mySliderInput.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {

				JSlider source = (JSlider) e.getSource();

				if (!source.getValueIsAdjusting()) {
					Integer value = (int) source.getValue();

					String updatedString = parseChange(value);

					myCurrentValue.setText(updatedString);
					myValue = updatedString;
					myTextInput.setText(myValue);
				}
			}
		});

		myDescriptionLabel.setText(myDescription);
		myCurrentValue.setText(value.toString());
		myNameLabel.setText("Name: " + myName);
		myStringLabel.setText("Current value: " + myValue);
		myNewValue.setText("New value: ");

		this.setLayout(new GridBagLayout());
		
		gbConstraints = new GridBagConstraints();
		
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.weightx = 0;
		gbConstraints.weighty = 0;
		
		gbConstraints.insets = new Insets(3,3,3,3);
		gbConstraints.ipadx = 5;
		gbConstraints.ipady = 5;
		
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 1;
		add(myNameLabel, gbConstraints);
		gbConstraints.gridx = 2;
		add(myStringLabel, gbConstraints);
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 0;
		add(myDescriptionLabel, gbConstraints);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 2;
		add(myNewValue, gbConstraints);
		gbConstraints.gridx = 1;
		add(mySliderInput, gbConstraints);
		gbConstraints.gridx = 2;
		add(myTextInput, gbConstraints);

	}
}
