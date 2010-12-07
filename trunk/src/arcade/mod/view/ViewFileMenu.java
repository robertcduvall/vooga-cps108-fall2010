package arcade.mod.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import arcade.mod.controller.IPresenter;

/**
 * MenuBar which includes a File category.
 * File category has open, save, and close.
 * Based on vooga.arcade.lobby.Menu by Andrew Brown
 * @author Daniel Koverman
 *
 */

public class ViewFileMenu extends JMenuBar{
	
	IPresenter presenter;
    
	private static final long serialVersionUID = 1L;

    public ViewFileMenu(IPresenter presenter){
    	super();
    	this.presenter = presenter;
    	initialize();
    }
    
    private void initialize(){
    	//Build the first menu.
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(
		        "For saving and loading");
		this.add(menu);

		//a group of JMenuItems
		JMenuItem openItem = new JMenuItem("Open",
		                         KeyEvent.VK_O);
		openItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				presenter.load();
			}
		});
		menu.add(openItem);
		
		JMenuItem saveItem = new JMenuItem("Save All", KeyEvent.VK_S);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				presenter.save();
			}
		});
		menu.add(saveItem);
		
		JMenuItem closeItem = new JMenuItem("Close", KeyEvent.VK_C);
		closeItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		closeItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				//TODO 
			}
		});
		menu.add(closeItem);
    }
	
}