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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import arcade.security.resourcesbundle.LabelResources;
import arcade.security.resourcesbundle.StaticFileResources;

@SuppressWarnings("serial")
public class GuestConfigurationFrame extends UserConfigurationFrame {

	//private boolean initiated;
	//private ApplicationLaunchStatusEvent status=new ApplicationLaunchStatusEvent("InterMilan");
	private Container currentContentPane;
	//private JTabbedPane tabpane;
	//private boolean isTabPaneCreated=false;
	//private Set<String> listOfClickedPlayer =new TreeSet<String>();
	//private HashMap<Integer,String> mapOfClickedPlayer =new HashMap<Integer,String>();
	//private Integer tabIndex=0;

	public GuestConfigurationFrame(){
		super(LabelResources.getLabel("GuestInternalFrame"),true,true,true,true);
		Dimension a=new Dimension(200,200);
		this.setPreferredSize(a);
		this.setLocation(200, 200);
		this.setJMenuBar(createMenuBar());	
		this.setFrameIcon(new ImageIcon(StaticFileResources.getPath("guestinternalframeicon")));	//to do
		this.setLayout(new BorderLayout());  
		currentContentPane=this.getContentPane();	 //get the current container,so new component can be added on when the mouse clicked  	

		//add(new JScrollPane(createNameList()),BorderLayout.WEST);  //scrollpane for Jlist
		pack();
		//initiated=true;
		//status.setInitiatedStatus(initiated);   //if status.initiated, the instance variable initiated must be public	
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//addInternalFrameListener(new InternalFrameListener());	
		setVisible(true);
		//EventBus.publish(status);
	}

//	class InternalFrameListener extends InternalFrameAdapter{
//		public void internalFrameClosing(InternalFrameEvent e) {  //when extends, the InternalFrameEvent e must be written,otherwise it is another unknown(or user created) method
//			initiated=false;			
//			status.setInitiatedStatus(initiated);
//			EventBus.publish(status);
//
//		}
//	}


	public JMenuBar createMenuBar() {
		JMenuBar menubar=new JMenuBar();
		JMenu menu=new JMenu("Options");
		JMenuItem item=new JMenuItem("clear all the selection");
		item.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){				
//				if(!(isTabPaneCreated==false)){
//					tabpane.removeAll();//when remove,tabpane can not be empty
//				}
//				tabIndex=0;
//				mapOfClickedPlayer =new HashMap<Integer,String>();
//				//listOfClickedPlayer =new TreeSet<String>();
			}	
		});
		menu.add(item);
		menubar.add(menu);
		return menubar;
	}


//	public JList createNameList() {
//		//get data from database
//		String[] c={"Pandev","Milito","Eto","Materazzi","Cesar"};
//		DefaultListModel model = new DefaultListModel();
//		for(String a:c){
//			model.addElement(a);
//		}
//		JList list=new JList(model);
//		list.setLayoutOrientation(JList.VERTICAL_WRAP);
//		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//		list.addMouseListener(new MouseHandler());
//		return list;
//	}



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
//				if(isTabPaneCreated==false){//first time clicked on player name	
//					createTabPanelandAddTab(selection);						    
//				}
//				else {if(!mapOfClickedPlayer.containsValue(selection)){					   				
//					   addTab(selection); //if the player is not chosen yet,crate the new tab
//				     } 
//				     else{
//					   selectExistingTab(selection);//if the player is already chosen, just select it again,not create another one!		    
//				     }
//				}
			//	tabIndex++;
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
