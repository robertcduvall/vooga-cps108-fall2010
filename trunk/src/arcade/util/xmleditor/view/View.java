package arcade.util.xmleditor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import arcade.util.xmleditor.Controller;
import arcade.util.xmleditor.IBaseController;
import arcade.util.xmleditor.mainmenu.MenuBar;
import arcade.util.xmleditor.model.XMLNode;

public class View extends JPanel implements IBaseView, TreeSelectionListener{
	
	private static final long serialVersionUID = 1L;
	TreeViewer treeViewer;
	ElementPanel elementPanel;
	IBaseController controller;

	public View(Controller controller, ElementPanel elementPanel){
		
		this.initializePanel();
		this.controller = controller;
		
		
		treeViewer = new TreeViewer();
		
		
		this.elementPanel = elementPanel;
		treeViewer.addTreeSelectionListener(elementPanel.getController());

		add(treeViewer, BorderLayout.WEST);
		add(elementPanel, BorderLayout.CENTER);

	}

	private void initializePanel() {
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(500, 600));
		
	}

	@Override
	public void reloadModel(XMLNode root) {
		remove(treeViewer);
		updateUI();
		treeViewer = new TreeViewer(root);
		treeViewer.addTreeSelectionListener(elementPanel.getController());
		treeViewer.addTreeSelectionListener(this);
		add(treeViewer, BorderLayout.WEST);
		
	}

	@Override
	public void updateModel(XMLNode node) {
		
		treeViewer.reload(node);
		validate();
		
	}
	
	@Override
	public void showError(String message){
		JOptionPane.showMessageDialog(this, message);
	}
	
	@Override
	public void showView(){
		JFrame frame = new JFrame();
		frame.add(this);
		frame.addWindowListener(new WindowCloser());
		frame.setJMenuBar(new MenuBar(controller));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		controller.newNodeSelected((XMLNode) arg0.getPath()
				.getLastPathComponent());
		
	}

}
