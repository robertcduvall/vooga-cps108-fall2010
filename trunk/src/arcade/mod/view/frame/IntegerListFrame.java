package arcade.mod.view.frame;

import arcade.mod.model.IResourceNode;

public class IntegerListFrame extends SliderListFrame {

	public IntegerListFrame() {
	}

	public IntegerListFrame(IResourceNode node) {
		super(node);
	}

	@Override
	public ListFrame newInstance(IResourceNode node) {

		return new IntegerListFrame(node);
	}

	
	public String parseChange(Integer value){
		return value.toString();
	}

	@Override
	public void handleInitialParsing() {

		INIT = Integer.parseInt(myValue);
		
	}

	@Override
	public boolean confirmValidity() {
		// TODO:if we ever need to confirm something on integers, put it here
		return true;
	}

}
