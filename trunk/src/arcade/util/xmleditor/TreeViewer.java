package arcade.util.xmleditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class TreeViewer extends JPanel {
	
  JTree tree;
  
  public TreeViewer(XMLNode root) {
    setLayout(new BorderLayout());
    tree = new JTree(root);
    
    add(new JScrollPane((JTree)tree),"Center");
    }

  public Dimension getPreferredSize(){
    return new Dimension(200, 120);
    }
  
  public void addTreeSelectionListener(TreeSelectionListener listener){
	  tree.addTreeSelectionListener(listener);
  }

  public static void main(String s[]) throws ParserConfigurationException, SAXException, IOException{
    MyJFrame frame = new MyJFrame("Tree Collapse Expand");
    }
  }


class MyJFrame extends JFrame implements ActionListener {
  JButton b1, b2, b3;
  TreeViewer panel;
  MyJFrame(String s) throws ParserConfigurationException, SAXException, IOException {
    super(s);
    setForeground(Color.black);
    setBackground(Color.lightGray);
    
    
    File file = new File("resources.xml");
    XMLDocumentCreator xmlCreator = new XMLFileParser(file);
	Document document = xmlCreator.getDocument();
    
    
    panel = new TreeViewer(new XMLNode((Element)document.getFirstChild(), null));
    expandAll(panel.tree);
    getContentPane().add(panel,"Center");

    b1 = new JButton("Expand");
    b3 = new JButton("Expand to last");
    b2 = new JButton("Collapse");

    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    getContentPane().add(b1,"West");
    getContentPane().add(b3,"North");
    getContentPane().add(b2,"East");
    setSize(300,300);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowCloser());
    }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == b1) expandAll(panel.tree);
    if (ae.getSource() == b3) expandToLast(panel.tree);
    if (ae.getSource() == b2) collapseAll(panel.tree);
    }


  public void expandAll(JTree tree) {
    int row = 0;
    while (row < tree.getRowCount()) {
      tree.expandRow(row);
      row++;
      }
    }


  public void expandToLast(JTree tree) {
    // expand to the last leaf from the root
    DefaultMutableTreeNode  root;
    root = (DefaultMutableTreeNode) tree.getModel().getRoot();
    tree.scrollPathToVisible(new TreePath(root.getLastLeaf().getPath()));
    }


  /*
  // alternate version, suggested by C.Kaufhold
  public void expandToLast(JTree tree) {
    TreeModel data = tree.getModel();
    Object node = data.getRoot();

    if (node == null) return;

    TreePath p = new TreePath(node);
    while (true) {
         int count = data.getChildCount(node);
         if (count == 0) break;
         node = data.getChild(node, count - 1);
         p = p.pathByAddingChild(node);
    }
    tree.scrollPathToVisible(p);
  }
  */


  public void collapseAll(JTree tree) {
    int row = tree.getRowCount() - 1;
    while (row >= 0) {
      tree.collapseRow(row);
      row--;
      }
    }
 }
