package arcade.mod.view;

import arcade.mod.model.IResourceNode;

public class IntegerListFrame extends SliderListFrame {

	public IntegerListFrame() {
	}

	public IntegerListFrame(IResourceNode node) {
		super(node);
	}

	@Override
	public AbstractListFrame newInstance(IResourceNode node) {

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
		//TODO: if we eventually come up with something that we need to check on the slider than we should add it here
		return true;
	}
}
