package arcade.util.xmleditor.view;

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

import arcade.util.xmleditor.model.XMLNode;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class TreeViewer extends JPanel {
	
  JTree tree;
  
  public TreeViewer(){
	  tree = new JTree();
  }
  
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

 }

