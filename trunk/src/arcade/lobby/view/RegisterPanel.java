package arcade.lobby.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.text.DateFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

public class RegisterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel thisPanel = this;
	private JLabel title = null;
	private JPanel informationLabelPanel = null;  
	private JLabel userNameLabel = null;
	private JLabel firstNameLabel = null;
	private JLabel lastNameLabel = null;
	private JLabel emailPanal = null;
	private JLabel passWordLabel = null;
	private JLabel passWordReEnterLabel = null;
	private Panel informationTextPanel = null;  
	private Map<String, JTextField> textMap;  //  @jve:decl-index=0:
	private String[] fieldNames = {"userName" , "firstName", "lastName" , "email", "birthday"};
	private JButton submitButton = null;
	private JLabel birthdayLabel = null;
	/**
	 * This is the default constructor
	 */
	public RegisterPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		textMap = new TreeMap<String , JTextField>();
		title = new JLabel();
		title.setText("Register");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("anyThing", Font.PLAIN, 32));
		this.setSize(338, 239);
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(getInformationLabelPanel(), BorderLayout.WEST);
		this.add(getInformationTextPanel(), BorderLayout.CENTER);
		this.add(getSubmitButton(), BorderLayout.SOUTH);
		
	}

	/**
	 * This method initializes informationLabelPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getInformationLabelPanel() {
		if (informationLabelPanel == null) {
			birthdayLabel = new JLabel();
			birthdayLabel.setText("Birthday:");
			passWordReEnterLabel = new JLabel();
			passWordReEnterLabel.setText("Enter Password Again:     ");
			passWordLabel = new JLabel();
			passWordLabel.setText("Password:");
			emailPanal = new JLabel();
			emailPanal.setText("Email: ");
			lastNameLabel = new JLabel();
			lastNameLabel.setText("Last Name:");
			firstNameLabel = new JLabel();
			firstNameLabel.setText("Fist Name: ");
			userNameLabel = new JLabel();
			userNameLabel.setText("User Name: ");
			GridLayout gridLayout = new GridLayout(0,1);
			informationLabelPanel = new JPanel();
			informationLabelPanel.setLayout(gridLayout);			
			informationLabelPanel.add(userNameLabel, null);
			informationLabelPanel.add(firstNameLabel, null);
			informationLabelPanel.add(lastNameLabel, null);
			informationLabelPanel.add(emailPanal, null);
			informationLabelPanel.add(birthdayLabel, null);
			informationLabelPanel.add(passWordLabel, null);
			informationLabelPanel.add(passWordReEnterLabel, null);
		}
		return informationLabelPanel;
	}

	/**
	 * This method initializes informationTextPanal	
	 * 	
	 * @return java.awt.Panel	
	 */
	private Panel getInformationTextPanel() {
		if (informationTextPanel == null) {
			informationTextPanel = new Panel();
			informationTextPanel.setLayout(new GridLayout(0,1));
			for(int i = 0; i<fieldNames.length; i++){
				JTextField temp = new JTextField();
				informationTextPanel.add(temp, null);
				textMap.put(fieldNames[i] , temp);
			}
			for(int i = 0; i<2; i++){
				JPasswordField passTemp = new JPasswordField();
				informationTextPanel.add(passTemp, null);
				textMap.put("password" + (i + 1), passTemp);
			}
			textMap.get("birthday").setText("mm/dd/yyyy");
		}
		return informationTextPanel;
	}

	

	/**
	 * This method initializes submitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton();
			submitButton.setText("Submit");
			submitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					checkEmpty();
					checkEmailFormat();
					checkPasswords();
					checkBirthDay();
				}

				private boolean checkBirthDay() {
					String date = textMap.get("birthday").getText();
					String[] parts = date.split("/");
					if(parts.length != 3){
						return false;
					}
					String mm = parts[0];
					String dd = parts[1];
					String yy = parts[2];
					
					if(mm.length() != 2){
						return false;
					}
					if(dd.length() != 2){
						return false;
					}
					if(yy.length() != 4){
						return false;
					}
					for(String str:parts){
						if(!allDigits(str)){
							return false;
						}
					}
					
					int month = Integer.valueOf(parts[0]);
					int day = Integer.valueOf(parts[1]);
					int year = Integer.valueOf(parts[2]);
					
					if(month > 12 || month < 1){
						return false;
					}
					
					if(day > 31 || day < 1){
						return false;
					}
					
					return true;
				}
			
				private boolean allDigits(String str){
					for(char c: str.toCharArray()){
						if(!Character.isDigit(c)){
							return false;
						}
					}
					return true;
				}
				

				private boolean checkPasswords() {
					String pass1 = textMap.get("password1").getText();
					String pass2 = textMap.get("password2").getText();
					boolean toReturn = pass1.equals(pass2);
					if(!toReturn){
						JOptionPane.showMessageDialog(thisPanel, "The Passwords are not the Same");
					}
					return toReturn;
				}

				private boolean checkEmailFormat() {
					boolean failed = true;
					String input = textMap.get("email").getText();
					 //Checks for email addresses starting with
				      //inappropriate symbols like dots or @ signs.
				      Pattern p = Pattern.compile("^\\.|^\\@");
				      Matcher m = p.matcher(input);
				      if (m.find())
				         failed =  false;
				      //Checks for email addresses that start with
				      //www. and prints a message if it does.
				      p = Pattern.compile("^www\\.");
				      m = p.matcher(input);
				      if (m.find()) {
				    	  failed =  false;
				      }
				      p = Pattern.compile("[^A-Za-z0-9\\.\\@_\\-~#]+");
				      m = p.matcher(input);
				      StringBuffer sb = new StringBuffer();
				      boolean result = m.find();
				      boolean deletedIllegalChars = false;

				      while(result) {
				         deletedIllegalChars = true;
				         m.appendReplacement(sb, "");
				         result = m.find();
				      }

				      // Add the last segment of input to the new String
				      m.appendTail(sb);

				      input = sb.toString();

				      if (deletedIllegalChars) {
				    	  failed =  false;
				      }
				      if(!failed){
				    	  JOptionPane.showMessageDialog(thisPanel, "Email Address Not Valid");
				      }
				   return failed;
				}

				private boolean checkEmpty() {
					for(String str : textMap.keySet()){
						JTextField temp = textMap.get(str);
						if(temp.getText().equals("")){
							JOptionPane.showMessageDialog(thisPanel, "No Fields can be left blank.");
							return false;
						}
					}
					return true;
					
				}
			});
		}
		return submitButton;
	}

	
}  //  @jve:decl-index=0:visual-constraint="1,5"
