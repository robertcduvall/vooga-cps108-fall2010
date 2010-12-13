package arcade.util.xmleditor.view;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class TestListener implements TreeSelectionListener{

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		System.out.println("+ test");
		
	}

}
