package arcade.util.xmleditor.mainmenu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import arcade.util.xmleditor.Controller;
import arcade.util.xmleditor.IBaseController;
import arcade.util.xmleditor.mainmenu.file.CloseEventListener;
import arcade.util.xmleditor.mainmenu.file.NewModelEventListener;
import arcade.util.xmleditor.mainmenu.file.OpenFileEventListener;
import arcade.util.xmleditor.mainmenu.file.OpenTemplateEventListener;
import arcade.util.xmleditor.mainmenu.file.SaveAsEventListener;
import arcade.util.xmleditor.mainmenu.file.SaveEventListener;

public class MenuBar extends JMenuBar{

	private static final long serialVersionUID = 1L;
	
	public MenuBar(IBaseController controller){
		
		add(createFileMenu(controller));
		add(createEditMenu());
	}
	
	public JMenu createFileMenu(IBaseController controller){
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem newFile = new JMenuItem("New");
		newFile.setMnemonic(KeyEvent.VK_N);
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		newFile.addActionListener(new NewModelEventListener(controller));
		file.add(newFile);
		
		JMenuItem open = new JMenuItem("Open");
		open.setMnemonic(KeyEvent.VK_O);
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		open.addActionListener(new OpenFileEventListener(controller));
		file.add(open);
		
		JMenuItem openTemplate = new JMenuItem("Open Template");
		openTemplate.setMnemonic(KeyEvent.VK_T);
		openTemplate.addActionListener(new OpenTemplateEventListener(controller));
		file.add(openTemplate);
		
		JMenuItem save = new JMenuItem("Save");
		save.setMnemonic(KeyEvent.VK_S);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		save.addActionListener(new SaveEventListener(controller));
		file.add(save);
		
		JMenuItem saveAs = new JMenuItem("Save As");
		saveAs.setMnemonic(KeyEvent.VK_A);
		saveAs.addActionListener(new SaveAsEventListener(controller));
		file.add(saveAs);
		
		JMenuItem close = new JMenuItem("Exit");
		close.setMnemonic(KeyEvent.VK_X);
		close.addActionListener(new CloseEventListener());
		file.add(close);
		
		return file;
	}
	
	public JMenu createEditMenu(){
		JMenu edit = new JMenu("Edit");
		
		JMenuItem cutNode = new JMenuItem("Cut Node");
		edit.add(cutNode);
		
		JMenuItem copyNode = new JMenuItem("Copy Node");
		edit.add(copyNode);
		
		JMenuItem deleteNode = new JMenuItem("Delete Node");
		edit.add(deleteNode);
		
		JMenuItem pasteNodeAbove = new JMenuItem("Paste Node Above");
		edit.add(pasteNodeAbove);
		
		JMenuItem pasteNodeBelow = new JMenuItem("Paste Node Below");
		edit.add(pasteNodeBelow);
		
		JMenuItem newNodeAbove = new JMenuItem("New Node Above");
		edit.add(newNodeAbove);
		
		JMenuItem newNodeBelow = new JMenuItem("New Node Below");
		edit.add(newNodeBelow);
		
		return edit;
	}

}
