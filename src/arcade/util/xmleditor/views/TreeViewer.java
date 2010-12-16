package arcade.util.xmleditor.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import arcade.util.xmleditor.model.XMLNode;

public class TreeViewer extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTree tree;

	public TreeViewer() {
		tree = new JTree();
	}

	public TreeViewer(XMLNode root) {
		setLayout(new BorderLayout());

		DefaultTreeModel model = new DefaultTreeModel(root);

		tree = new JTree(model);

		add(new JScrollPane((JTree) tree), "Center");
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 120);
	}

	public void reload(TreeNode node) {
		((DefaultTreeModel) tree.getModel()).reload(node);
	}

	public void addTreeSelectionListener(TreeSelectionListener listener) {
		tree.addTreeSelectionListener(listener);
	}

}
