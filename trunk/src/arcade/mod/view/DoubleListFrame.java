package arcade.mod.view;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import arcade.mod.model.ResourceNode;

public class DoubleListFrame extends SliderListFrame {

	private int MIN = -100000;
	private int MAX = 100000;
	private double INIT = 0;
	
	public DoubleListFrame() {
	}

	public DoubleListFrame(ResourceNode node) {
		super(node);
	}

	@Override
	public AbstractListFrame newInstance(ResourceNode node) {
		return new DoubleListFrame(node);
	}


	@Override
	public void handleInitialParsing() {
		
		INIT = Double.parseDouble(myValue);
		
	}

	@Override
	public String parseChange(Integer value) {
		Double toDouble = new Double(((double) value) / 1000);
		return toDouble.toString();
	}
}
