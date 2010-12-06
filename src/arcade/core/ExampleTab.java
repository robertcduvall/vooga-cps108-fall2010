package arcade.core;

import javax.swing.JButton;

public class ExampleTab extends Tab {
	public ExampleTab(){
		JButton a=new JButton("sdf");
		
		add(a);
	}

	@Override
	public String getComponentName() {
		return "ExampleTab";
	}

	@Override
	public String getComponentDescription() {
		return "This is an example";
	}
}
