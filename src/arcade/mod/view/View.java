package arcade.mod.view;

import javax.swing.*;

import arcade.mod.controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class View extends JFrame {

    private static final long serialVersionUID = 1L;
   
    Controller myController;
   
    JPanel centralPanel;
    Collection<String> myCategories = new ArrayList<String>();
   
    
    public View(Controller controller) {
        myController = controller;
    	initializeOnStart();
    }
    
   
    public void initializeOnStart() {
       
    	setSize(800,600);
    	
        JPanel contentPane = new JPanel(new BorderLayout());
       
        contentPane.add(makeCategoryBox(), BorderLayout.NORTH);
        contentPane.add(makeButton(), BorderLayout.SOUTH);
       
        //JScrollPane scrollPane = new JScrollPane();
        
        centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.PAGE_AXIS));
       
        centralPanel.setBackground(Color.GREEN);
        
        
        //scrollPane.add(centralPanel);
        
        contentPane.add(centralPanel, BorderLayout.CENTER);
       
       
        add(contentPane);
        
        setVisible(true);
        
    }
    
    public void createFrames(String category) {
    	    	
    	centralPanel.removeAll();
    	    	
    	centralPanel.add(new ImageListFrame("lol","/Users/brentsodman/Pictures/photo1.jpg"));

    	centralPanel.validate();

    	
    	/*Collection<AbstractListFrame> frames = myController.getFrames(category);
    	
    	for (AbstractListFrame alf : frames){
    		centralPanel.add(alf);
    	}*/
    	
    }

    private JButton makeButton() {
        JButton saveButton = new JButton("Save All");
       
        saveButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e){
                //handle saving
            }
           
        });
       
        return saveButton;
    }

    private JComboBox makeCategoryBox() {
        //myCategories = myController.getCategories();
        myCategories.add("placeholder");
        myCategories.add("placeholder2");
       
        JComboBox categoryBox = new JComboBox(myCategories.toArray());
        categoryBox.setSelectedIndex(0);
        categoryBox.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e)
            {
            	JComboBox comboBox = (JComboBox)e.getSource();
            	View.this.createFrames((String)comboBox.getSelectedItem());
            }
           
        });
       
        return categoryBox;
       
    }
   
}