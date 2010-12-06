package arcade.mod.view;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import arcade.mod.model.ResourceNode;

public class DoubleListFrame extends AbstractListFrame {

	private String myName;
	private String myDouble;
	private String myDescription;
	private JSlider myInput;
	private JLabel stringLabel;
	private JLabel descriptionLabel;

	private int MIN = -100000;
	private int MAX = 100000;
	private int INIT = 0;

	public DoubleListFrame() {

	}

	public DoubleListFrame(ResourceNode node) {

		super(node);

		restrictSize(HEIGHT);

		makeComponents();
	}

	@Override
	public AbstractListFrame newInstance(ResourceNode node) {

		return new DoubleListFrame(node);
	}

	@Override
	public void handleNode(ResourceNode node) {
		myDescription = node.getDescription();
		myName = node.getAttribute("name");
		myDouble = node.getAttribute("value");
		if (node.getAttributes().contains("max")) {
			MAX = Integer.parseInt(node.getAttribute("max"));
		}
		if (node.getAttributes().contains("min")) {
			MIN = Integer.parseInt(node.getAttribute("min"));
		}
		INIT = (int) Double.parseDouble(myDouble);
	}

	@Override
	public void makeComponents() {
		descriptionLabel = new JLabel();
		descriptionLabel.setText(myDescription);
		JLabel stringName = new JLabel();
		stringLabel = new JLabel();
		JLabel newValue = new JLabel();
		final JLabel currentValue = new JLabel();
		myInput = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
		Integer value = INIT;
		myInput.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {

				JSlider source = (JSlider) e.getSource();

				if (!source.getValueIsAdjusting()) {
					Integer value = (int) source.getValue();
					Double toDouble = new Double(((double) value) / 1000);
					currentValue.setText(toDouble.toString());
					myDouble = toDouble.toString();
				}
			}
		});
		currentValue.setText(value.toString());
		stringName.setText("Constant name: " + myName);
		stringLabel.setText("Value: " + myDouble);
		newValue.setText("New Constant: ");

		add(stringName);
		add(descriptionLabel);
		add(stringLabel);
		add(newValue);
		add(myInput);
		add(currentValue);

	}
}
