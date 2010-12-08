package arcade.mod.view;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import arcade.mod.model.IResourceNode;

public abstract class SliderListFrame extends AbstractListFrame {

	protected String myValue;
	protected JSlider myInput;

	protected int MIN = -1000;
	protected int MAX = 1000;
	protected int INIT = 0;

	public SliderListFrame() {

	}

	public SliderListFrame(IResourceNode node) {
		super(node);
		handleNode(node);
		initializeComponents();
	}
	


	@Override
	public void initializeComponents() {
		descriptionLabel = new JLabel();
		myNameLabel = new JLabel();
		newValue = new JLabel();
		currentValue = new JLabel();
		System.out.println(MIN + " " + MAX + " " +INIT);
		myInput = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
		stringLabel = new JLabel();

		makeComponents();
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
		
		handleInitialParsing();

		if (node.getAttributes().contains("max")) {
			MAX = Integer.parseInt(node.getAttribute("max"));
		} else {
			MAX = INIT + 1000;
		}
		if (node.getAttributes().contains("min")) {
			MIN = Integer.parseInt(node.getAttribute("min"));
		} else {
			MIN = INIT - 1000;
		}

	}

	public abstract void handleInitialParsing();

	public abstract String parseChange(Integer value);

	@Override
	public void makeComponents() {

		Integer value = INIT;
		myInput.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {

				JSlider source = (JSlider) e.getSource();

				if (!source.getValueIsAdjusting()) {
					Integer value = (int) source.getValue();

					String updatedString = parseChange(value);

					currentValue.setText(updatedString);
					myValue = updatedString;
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
		add(myInput);
		add(currentValue);

	}

}
