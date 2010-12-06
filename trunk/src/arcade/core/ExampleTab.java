package arcade.core;

import javax.swing.JButton;

public class ExampleTab extends Tab {
	public ExampleTab(){
		JButton a=new JButton("sdf");
		
		add(a);
		setToolTipText("This is an example");
		setName("ExampleTab");
	}
}
