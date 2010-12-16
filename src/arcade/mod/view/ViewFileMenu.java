package arcade.mod.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import arcade.mod.controller.IPresenter;
import arcade.mod.view.file.CurrentFileOpener;
import arcade.mod.view.file.RecentFileOpener;
import arcade.mod.view.file.SystemFileOpener;
import arcade.util.xmleditor.controllers.Controller;

/**
 * MenuBar which includes a File category. File category has open, save, and
 * close. Based on vooga.arcade.lobby.Menu by Andrew Brown
 * 
 * @author Daniel Koverman
 * 
 */

public class ViewFileMenu extends JMenuBar {

	IPresenter presenter;
	View parent;

	private static final long serialVersionUID = 1L;

	public ViewFileMenu(View parent, IPresenter presenter) {
		super();
		this.parent = parent;
		this.presenter = presenter;
		initialize();
	}

	private void initialize() {
		JMenu menu = buildFileMenu();
		
		JMenu behaviorMenu = buildBehaviorMenu();
		
		JMenu xmlMenu = buildXMLMenu();
		
		this.add(menu);
		this.add(behaviorMenu);
		this.add(xmlMenu);
	}
	
	private JMenu buildXMLMenu(){
		JMenu XMLMenu = new JMenu("XML");
		XMLMenu.setMnemonic(KeyEvent.VK_X);
		XMLMenu.getAccessibleContext().setAccessibleDescription("For modifying generic XML");
		
		JMenuItem openItem = new JMenuItem("Edit XML", KeyEvent.VK_E);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.CTRL_MASK));
		openItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Controller controller = new Controller();
			}
		});
		
		XMLMenu.add(openItem);
		
		return XMLMenu;
		
	}
	
	private JMenu buildBehaviorMenu(){
		
		//Build the second menu.
		JMenu behaviorMenu = new JMenu("Behavior");
		behaviorMenu.setMnemonic(KeyEvent.VK_B);
		behaviorMenu.getAccessibleContext().setAccessibleDescription("For modifying behavior");

		JMenuItem openItem = new JMenuItem("Modify", KeyEvent.VK_M);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				ActionEvent.CTRL_MASK));
		openItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				parent.displayBehaviorDialog();
			}
		});
		behaviorMenu.add(openItem);
		
		return behaviorMenu;
	}
	
	private JMenu buildFileMenu(){
		
		// Build the first menu.
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(
				"For saving and loading");
		

		// a group of JMenuItems
		JMenuItem openItem = new JMenuItem("Open", KeyEvent.VK_O);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		openItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				parent.setFileOpener(new SystemFileOpener(parent));
				presenter.load();
			}
		});
		menu.add(openItem);

		JMenuItem openRecentItem = new JMenuItem("Open Recent", KeyEvent.VK_R);
		openRecentItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.CTRL_MASK));
		openRecentItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				parent.setFileOpener(new RecentFileOpener());
				presenter.load();
			}
		});
		menu.add(openRecentItem);
		
		JMenuItem openCurrentItem = new JMenuItem("Open Current", KeyEvent.VK_T);
		openCurrentItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		openCurrentItem.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				parent.setFileOpener(new CurrentFileOpener());
				presenter.load();
				
			}
			
		});
		menu.add(openCurrentItem);
		
		JMenuItem saveItem = new JMenuItem("Save All", KeyEvent.VK_S);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		saveItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				presenter.save();
			}
		});
		menu.add(saveItem);

		JMenuItem closeItem = new JMenuItem("Close", KeyEvent.VK_C);
		closeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		closeItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// TODO
			}
		});
		menu.add(closeItem);
		
		return menu;
	}
	

}