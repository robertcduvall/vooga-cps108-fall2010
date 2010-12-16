package arcade.util.xmleditor.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import arcade.util.xmleditor.controllers.Controller;
import arcade.util.xmleditor.controllers.IBaseController;
import arcade.util.xmleditor.mainmenu.MenuBar;
import arcade.util.xmleditor.model.XMLNode;
import arcade.util.xmleditor.views.element.ElementPanel;

/**
 * Concrete implementation of IBaseView contained in a JPanel
 * 
 * @author Daniel Koverman
 * 
 */
public class View extends JPanel implements IBaseView, TreeSelectionListener {

	private static final long serialVersionUID = 1L;
	private TreeViewer treeViewer;
	private IBaseController controller;

	public View(Controller controller, ElementPanel elementPanel) {

		initializePanel();
		this.controller = controller;

		treeViewer = new TreeViewer();

		add(treeViewer, BorderLayout.WEST);
		add(elementPanel, BorderLayout.CENTER);

	}

	private void initializePanel() {

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800, 600));

	}

	@Override
	public void reloadModel(XMLNode root) {
		remove(treeViewer);
		updateUI();
		treeViewer = new TreeViewer(root);
		treeViewer.addTreeSelectionListener(this);
		add(treeViewer, BorderLayout.WEST);

	}

	@Override
	public void updateModel(XMLNode node) {

		treeViewer.reload(node);
		validate();

	}

	@Override
	public void showError(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	@Override
	public void showView() {
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
