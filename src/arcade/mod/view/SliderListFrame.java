package arcade.mod.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import arcade.mod.model.IResourceNode;

public abstract class SliderListFrame extends AbstractListFrame {

	protected String myValue;
	protected JSlider mySliderInput;
	protected JTextField myTextInput;

	protected int MIN;
	protected int MAX;
	protected int INIT;

	public SliderListFrame() {

	}

	public SliderListFrame(IResourceNode node) {
		super(node);

	}

	@Override
	public void initializeComponents() {
		descriptionLabel = new JLabel();
		myNameLabel = new JLabel();
		newValue = new JLabel();
		currentValue = new JLabel();
		stringLabel = new JLabel();
		myTextInput = new JTextField();

	}

	@Override
	public AbstractListFrame newInstance(IResourceNode node) {

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

				}
				mySliderInput.setValue(value);
				currentValue.setText(myValue);

			}

		});
		mySliderInput.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {

				JSlider source = (JSlider) e.getSource();

				if (!source.getValueIsAdjusting()) {
					Integer value = (int) source.getValue();

					String updatedString = parseChange(value);

					currentValue.setText(updatedString);
					myValue = updatedString;
					myTextInput.setText(myValue);
				}
			}
		});

		descriptionLabel.setText(myDescription);
		currentValue.setText(value.toString());
		myNameLabel.setText("Constant name: " + myName);
		stringLabel.setText("Value: " + myValue);
		newValue.setText("New Constant: ");

		add(myNameLabel);
		add(descriptionLabel);
		add(stringLabel);
		add(newValue);
		add(mySliderInput);
		add(currentValue);
		add(myTextInput);

	}
}
