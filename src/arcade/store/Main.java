package arcade.store;

import arcade.store.control.Control;
import arcade.store.gui.MainPageView;

public class Main {
	
	public static void main(String[] args) {
		Control controller = new Control();
		MainPageView view = new MainPageView(controller);
		StoreModel model = new StoreModel(controller);
		controller.setModel(model);
		controller.setView(view);
	}

}
