package arcade.security.gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.miginfocom.swing.MigLayout;
import arcade.security.exceptions.UserConfigurationNotFoundException;
import arcade.security.resourcesbundle.LabelResources;
import arcade.security.resourcesbundle.StaticFileResources;
/**
 * 
 * @author Meng Li
 *
 */
@SuppressWarnings("serial")
public class LogInFrame extends JFrame {


	private JButton submitButton;
	private JButton cancelButton;
	private String username;
	private char[] password;
	JTextField text1;
	JPasswordField text_pass;
	//JProgressBar progressbar;

	public LogInFrame(){
		super();
		this.setAlwaysOnTop(true);
		MouseDragListener m1=new MouseDragListener(this); //inner class
		this.addMouseListener(m1);
		this.addMouseMotionListener(m1);
		setSize(270, 150);
		this.setLayout(new MigLayout());
		this.setBackground(Color.white);
		JLabel image=new JLabel(new ImageIcon(StaticFileResources.getPath("loginimage")));
		JLabel label1=new JLabel(LabelResources.getLabel("UserId"));		
		JLabel label2=new JLabel(LabelResources.getLabel("Password"));
		text1=new JTextField(5);	
		text_pass=new JPasswordField(5);
		add(image,"cell 0 0 2 1");
		add(label1,"cell 0 1");
		add(text1,"cell 1 1,wrap");
		add(label2,"cell 0 2");
		add(text_pass,"wrap");
		submitButton=new SecurityButton(LabelResources.getLabel("LoginframeSubmit"),new ImageIcon(StaticFileResources.getPath("loginsubmit")),"Log in");
		cancelButton=new SecurityButton(LabelResources.getLabel("LoginframeCancel"),new ImageIcon(StaticFileResources.getPath("logincancel")),"Cancel");
		submitButton.requestFocus(true);
		add(submitButton,"");
		add(cancelButton,"wrap");
		this.setLocationRelativeTo(this);
		this.setUndecorated(true);   //no frameworks		
		pack();
		addListener();		
		setVisible(true);
	}

	class MouseDragListener extends MouseAdapter{
		JFrame frame;
		Point origin;
		Point current;
		Point frame_start_location;

		public MouseDragListener(JFrame frame){
			this.frame=frame;	

		}

		Point getabsoluteScreenLocation(MouseEvent e) {
			Point cursor=e.getPoint(); 
			Point target_location=frame.getLocationOnScreen();
			return new Point((int) (target_location.getX()) + (int)(cursor.getX()),
					(int) (target_location.getY()) + (int)(cursor.getY()));
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			current=getabsoluteScreenLocation(e);
			Point offset=new Point((int)(current.getX())-(int)frame_start_location.getX(),(int)(current.getY())-(int)frame_start_location.getY());
			this.frame.setLocation( new Point((int)(origin.getX())+(int)(offset.getX()),(int)(origin.getY())+(int)(offset.getY())));

		}

		@Override
		public void mousePressed(MouseEvent e) {
			frame_start_location = getabsoluteScreenLocation(e);
			origin=frame.getLocation();
		}

	}

	public class SubmitEvent implements ActionListener{
		JFrame jf;
		public SubmitEvent(JFrame frame){
			jf = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			username=text1.getText();
			password=text_pass.getPassword();
			if(checkPassword(null)){
				
				try {
					MainFrame main = new MainFrame();
					jf.dispose();					
				} catch (UserConfigurationNotFoundException e1) {
					e1.printStackTrace();
				}

			}
			else{			
				text_pass.selectAll();
				text_pass.requestFocus(true);
			}
		}

	}
		
	

	public void addListener(){
		submitButton.addActionListener(new SubmitEvent(this));//{
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				username=text1.getText();
//				password=text_pass.getPassword();
//				if(checkPassword(null)){
//					
//					try {
//						MainFrame main = new MainFrame();
//						
//					} catch (UserConfigurationNotFoundException e1) {
//						e1.printStackTrace();
//					}
//	
//				}
//				else{			
//					text_pass.selectAll();
//					text_pass.requestFocus(true);
//				}
//			}
//
//		}
//		);


		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);					
			}				
		});
	}

	public boolean checkPassword(char[] input){

		return true;			
	}

	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new LogInFrame();
	}
}
