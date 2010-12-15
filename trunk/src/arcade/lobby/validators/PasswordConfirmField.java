package arcade.lobby.validators;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import net.miginfocom.swing.MigLayout;
/**
 * 
 * No Longer Used
 *
 */
@SuppressWarnings("serial")
public class PasswordConfirmField extends JComponent {
	private static final int DEFAULT_LENGTH = 10;
	private JPasswordField passwordField1, passwordField2;
	private JLabel label1,label2;
	
	public PasswordConfirmField() {
		this(DEFAULT_LENGTH);
	}
	
	public PasswordConfirmField(int columns) {
		this(columns,null,null);
	}
	
	public PasswordConfirmField(String labelText1, String labelText2) {
		this(DEFAULT_LENGTH,labelText1,labelText2);
	}
	
	public PasswordConfirmField(int columns, String labelText1, String labelText2) {
		this.setLayout(new MigLayout("wrap 2, hidemode 3"));
		passwordField1 = new JPasswordField(columns);
		passwordField2 = new JPasswordField(columns);
		label1 = labelText1==null?null:new JLabel(labelText1);
		label2 = labelText2==null?null:new JLabel(labelText2);
		addAllToContainer();
	}
	
	private void addAllToContainer() {
		if(label1!=null) add(label1);
		add(passwordField1,"wrap");
		if(label2!=null) add(label2);
		add(passwordField2,"wrap");
		
	}
	
	public char[] getPassword1() {
		return passwordField1.getPassword();
	}
	
	public char[] getPassword2() {
		return passwordField2.getPassword();
	}
}
