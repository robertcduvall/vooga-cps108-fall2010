package arcade.security.gui.reusablejcomponent.demo;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Example demo demonstrates how to use reusable privilege GUI component.
 * @author Meng Li
 *
 */
public class DemoView extends JFrame {


	private static final long serialVersionUID = 1L;
	
	public DemoView(String name){
		super(name);
		setSize(600,600);
		setLocationRelativeTo(this.getParent());
		
		/***********************************
		 * JButton example
		 ************************************/
		//JButton button = new JButton(new ButtonAction("demo button","admin"));  
		JButton button = new JButton(new ButtonAction("demo button","developer"));  //comment out this line and above line to see the effect.
		//JButton button = new JButton(new ButtonAction("demo button","developer","admin"));  //You only want administrator and developer to use this button. 
	
		/***********************************
		 * JButton example
		 ************************************/
		/*
		 * if you are using ArcadeAction for privilege issue, you should not add the ButtonAction as below.
		 * if you need to do it as below, you need to put if in actionPerformed method rather than
		 * in the ButtonAction class.
		 */
		//JButton button = new JButton("demobutton");
		//button.addActionListener(new ButtonAction("demo button","developer"));
		this.getContentPane().add(button);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	
	public static void main(String[] args){
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI demo.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new DemoView("Demo for resuable privilege GUI components");
            }
        });
		
	}

}
