package arcade.mod.view;


import arcade.mod.model.IResourceNode;

public class DoubleListFrame extends SliderListFrame {

	private int MIN = -100000;
	private int MAX = 100000;
	private double INIT = 0;
	
	public DoubleListFrame() {
	}

	public DoubleListFrame(IResourceNode node) {
		super(node);
	}

	@Override
	public AbstractListFrame newInstance(IResourceNode node) {
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

	@Override
	public boolean confirmValidity() {
		return true;
	}

}
