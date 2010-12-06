package arcade.security.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import arcade.security.resourcesbundle.LabelResources;
import arcade.security.resourcesbundle.StaticFileResources;
/**
 * 
 * @author Meng Li
 *
 */
@SuppressWarnings("serial")
public class DeveloperConfigurationFrame extends UserConfigurationFrame {

	private Container currentContentPane;


	public DeveloperConfigurationFrame(){
		super(LabelResources.getLabel("DeveloperInternalFrame"),true,true,true,true);
		Dimension a=new Dimension(200,200);
		this.setPreferredSize(a);
		this.setLocation(200, 200);
		this.setJMenuBar(createMenuBar());	
		this.setFrameIcon(new ImageIcon(StaticFileResources.getPath("developerinternalframeicon")));	//to do
		this.setLayout(new BorderLayout());  
		currentContentPane=this.getContentPane();	 //get the current container,so new component can be added on when the mouse clicked  	
     	pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}



	public JMenuBar createMenuBar() {
		JMenuBar menubar=new JMenuBar();
		JMenu menu=new JMenu("Options");
		JMenuItem item=new JMenuItem("clear all the selection");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
				//TODO:depends on your application
			}	
		});
		menu.add(item);
		menubar.add(menu);
		return menubar;
	}

	/**
	 * create tabs, no duplicates and select the already chosen one 
	 * @author ml146
	 *
	 */
	class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount()==2){
				JList target = (JList) e.getSource();
				String selection=(String) target.getSelectedValue();	
//TODO:Depends on how you wanna use this mouse event
				currentContentPane.validate();								
			}
		}		

//		private void createTabPanelandAddTab(String selection) {
//			tabpane=createTabPanel(selection);
//			tabpane.add(selection,new PlayerPanel(selection));					
//			mapOfClickedPlayer.put(tabIndex, selection);
//			currentContentPane.add(tabpane);
//			isTabPaneCreated=true;   //first clicked the player		
//		}
		
//		private JTabbedPane createTabPanel(String playername){
//			tabpane=new JTabbedPane();
//			tabpane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
//			return tabpane;
//		}
//		
//		private void addTab(String selection) {
//			mapOfClickedPlayer.put(tabIndex,selection);										
//			tabpane.add(selection,new PlayerPanel(selection));					
//
//		}		
//
//		private void selectExistingTab(String selection) {
//			for(Integer a:mapOfClickedPlayer.keySet()){
//				if(mapOfClickedPlayer.get(a).equals(selection)){
//					tabIndex=a;
//					break;
//				}
//			    }
//			    tabpane.setSelectedIndex(tabIndex);	
//		}
	}


	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}
}
