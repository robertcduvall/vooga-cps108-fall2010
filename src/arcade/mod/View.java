package arcade.mod;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class View extends JFrame {

    private static final long serialVersionUID = 1L;
   
    Controller myController;
   
    JPanel centralPane;
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
       
       
        centralPane = new JPanel();
        centralPane.setLayout(new BoxLayout(centralPane, BoxLayout.PAGE_AXIS));
               
        
        contentPane.add(centralPane, BorderLayout.CENTER);
       
       
        add(contentPane);
        
        setVisible(true);
        
    }
    
    public void createFrames(String category) {
    	
    	centralPane.removeAll();
    	
    	Collection<AbstractListFrame> frames = myController.getFrames(category);
    	
    	for (AbstractListFrame alf : frames){
    		centralPane.add(alf);
    	}
    	
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