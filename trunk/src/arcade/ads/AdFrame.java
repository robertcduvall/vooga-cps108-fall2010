package arcade.ads;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * 
 * @author Hao He (hh89@duke.edu)
 * @author Nick Straub (njs7@duke.edu)
 * @author Scott Winkleman (saw26@duke.edu)
 * @author Kate Yang (kly2@duke.edu)
 * 
 * @version 1.0
 */
public class AdFrame extends JFrame{
	
	AdsManager myManager;
	
	public AdFrame(AdsManager manager){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		myManager = manager;
		
		setTitle("Ads");
		
    	JPanel panel = (JPanel) getContentPane();
    	panel.setLayout(new FlowLayout());
		makeButtons(panel);
    	panel.setPreferredSize(new Dimension(500, 200));
		
		pack();
    	setVisible(true);
    	
    	myManager.setGraphics(panel.getGraphics());
		myManager.update();
		myManager.render();
	}
	
	/**
	 * Create the 
	 * 
	 */
	public void makeButtons(JPanel panel){
		panel.setLayout(new FlowLayout());
		
    	JButton prev = new JButton("Previous Ad");
    	panel.add(prev);
    	prev.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			getPrevious();
    		}		
    	});
    	
    	JButton next = new JButton("Next Ad");
    	panel.add(next);
    	next.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			getNext();
    		}
    	});
	}
	
	public void getPrevious(){
		myManager.prevAds();
	}
	
	public void getNext(){
		myManager.nextAds();
	}
	
	public static void main(String args[]){
		AdsManager manager = new AdsManager();
		new AdFrame(manager);
	}

}
