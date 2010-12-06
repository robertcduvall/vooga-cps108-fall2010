package arcade.mod.view;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import arcade.mod.model.ResourceNode;

public class IntegerListFrame extends SliderListFrame {

	public IntegerListFrame() {
	}

	public IntegerListFrame(ResourceNode node) {
		super(node);
	}

	@Override
	public AbstractListFrame newInstance(ResourceNode node) {

		return new IntegerListFrame(node);
	}

	
	public String parseChange(Integer value){
		return value.toString();
	}

	@Override
	public void handleInitialParsing() {

		INIT = Integer.parseInt(myValue);
		
	}
}
