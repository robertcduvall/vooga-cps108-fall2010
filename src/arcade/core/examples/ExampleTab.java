package arcade.core.examples;

import java.text.DateFormat;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.DateFormatter;

import arcade.core.api.Tab;
import arcade.core.mvc.IController;

@SuppressWarnings("serial")
public class ExampleTab extends JPanel implements Tab {
	private JLabel time;
	DateFormatter df = new DateFormatter();
	
	public ExampleTab() {
		setName("Tab Example");
		setToolTipText("This is an example of how to use the Tab interface.");
		
		add(new JLabel("You switched to this tab at:"));
		add(time = new JLabel());
		
		refresh();
	}

	@Override
	public IController getController() {
		return null;
	}

	@Override
	public void refresh() {
		setTimeString(System.currentTimeMillis());
	}
	
	private void setTimeString(long timeMillis) {
		time.setText(DateFormat.getDateTimeInstance().format(timeMillis));
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}
}
