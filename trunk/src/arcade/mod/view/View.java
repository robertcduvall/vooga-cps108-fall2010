package arcade.mod.view;

import javax.swing.*;

import arcade.mod.controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

public class View extends JFrame {

    private static final long serialVersionUID = 1L;
   
    Controller myController;
   
    JPanel centralPanel;
    JScrollPane centralScrollPane;
    Collection<String> myCategories;
    String currentCategory;
   
    
    public View(Controller controller) {
        myController = controller;
    	initializeOnStart();
    }
    
   
    public void initializeOnStart() {
       
    	setSize(800,600);
    	
        JPanel contentPane = new JPanel(new BorderLayout());
       
        contentPane.add(makeCategoryBox(), BorderLayout.NORTH);
        contentPane.add(makeButton(), BorderLayout.SOUTH);
       
        
        centralPanel = new JPanel();
                
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.PAGE_AXIS));
       
        centralScrollPane = new JScrollPane(centralPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);        
        
        contentPane.add(centralScrollPane, BorderLayout.CENTER);
       
        add(contentPane);
        
        setVisible(true);
        
    }
    
    
    public void changeFrames(Collection<AbstractListFrame> frames){
    	
    	centralPanel.removeAll();
    	
    	for (AbstractListFrame alf : frames){
    		centralPanel.add(alf);
    	}
    	
    	centralPanel.validate();
    }
    
    public String getCurrentCategory() {
    	return currentCategory;
    }

    private JButton makeButton() {
        JButton saveButton = new JButton("Save All");
       
        saveButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e){
                myController.save();
            }
           
        });
       
        return saveButton;
    }
    
    public File requestSaveLocation () {
    	
    	JFileChooser saveChooser = new JFileChooser(); 
    	
    	saveChooser.showSaveDialog(this);
    	
    	return saveChooser.getSelectedFile();
    	
    }

    private JComboBox makeCategoryBox() {
    	
    	Collection<String> myCategories = myController.getCategories();
       
        JComboBox categoryBox = new JComboBox(myCategories.toArray());
        categoryBox.setSelectedIndex(0);
        categoryBox.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e)
            {
            	JComboBox comboBox = (JComboBox)e.getSource();
            	currentCategory = (String)comboBox.getSelectedItem();
            	
            	myController.framesHaveChanged();
            }
           
        });
       
        return categoryBox;
       
    }
   
}