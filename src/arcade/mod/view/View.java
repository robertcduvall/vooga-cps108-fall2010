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
    JScrollPane centralScrollPane;
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
       
        
        centralPanel = new JPanel();
                
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.PAGE_AXIS));
       
        centralScrollPane = new JScrollPane(centralPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        centralScrollPane.setBackground(Color.GREEN);
        
        
        contentPane.add(centralScrollPane, BorderLayout.CENTER);
       
       
        add(contentPane);
        
        setVisible(true);
        
    }
    
    public void createFrames(String category) {
    	    	
    	centralPanel.removeAll();
    	    	
    	ImageListFrame temp = new ImageListFrame("lol","/vooga/src/vooga/games/cyberion/resources/images/enemyShip.PNG", centralPanel.getWidth(), 200);
    	ImageListFrame temp2 = new ImageListFrame("lol","/vooga/src/vooga/games/cyberion/resources/images/enemyShip.PNG", centralPanel.getWidth(), 200);
    	ImageListFrame temp3 = new ImageListFrame("lol","/vooga/src/vooga/games/cyberion/resources/images/enemyShip.PNG", centralPanel.getWidth(), 200);
    	ImageListFrame temp4 = new ImageListFrame("lol","/vooga/src/vooga/games/cyberion/resources/images/enemyShip.PNG", centralPanel.getWidth(), 200);
    	ImageListFrame temp5 = new ImageListFrame("lol","/vooga/src/vooga/games/cyberion/resources/images/enemyShip.PNG", centralPanel.getWidth(), 200);

    	
    	centralPanel.add(temp);
    	centralPanel.add(temp2);
    	centralPanel.add(temp3);
    	centralPanel.add(temp4);
    	centralPanel.add(temp5);
    	
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
    	
    	Collection<Object> categoryObjects = myController.getCategories();
    	
    	for (Object obj : categoryObjects){
    		myCategories.add(obj.toString());
    	}
    	
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