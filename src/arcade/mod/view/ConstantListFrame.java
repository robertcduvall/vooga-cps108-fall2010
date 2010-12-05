package arcade.mod.view;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import arcade.mod.model.ResourceNode;

public class ConstantListFrame extends AbstractListFrame {

	private String myName;
	private String myConstant;
	private JSlider myInput;
	private JLabel stringLabel;

	private int MIN = -1000;
	private int MAX = 1000;
	private int INIT = 0;

	public ConstantListFrame() {

	}

	public ConstantListFrame(ResourceNode node) {

		super(node);

		restrictSize(DEFAULT_HEIGHT);

		makeComponents();
	}

	@Override
	public AbstractListFrame newInstance(ResourceNode node) {

		return new ConstantListFrame(node);
	}

	@Override
	public void handleNode(ResourceNode node) {
		myName = node.getAttribute("name");
		myConstant = node.getAttribute("value");
		if (node.getAttributes().contains("max")) {
			MAX = Integer.parseInt(node.getAttribute("max"));
		}
		if (node.getAttributes().contains("min")) {
			MIN = Integer.parseInt(node.getAttribute("min"));
		}
		INIT = Integer.parseInt(myConstant);
	}

	@Override
	public void makeComponents() {
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
					currentValue.setText(value.toString());
				}
			}
		});
		currentValue.setText(value.toString());
		stringName.setText("Constant name: " + myName);
		stringLabel.setText("Value: " + myConstant);
		newValue.setText("New Constant: ");

		add(stringName);
		add(stringLabel);
		add(newValue);
		add(myInput);
		add(currentValue);

	}
}
