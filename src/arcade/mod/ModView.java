package arcade.mod;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ModView extends JFrame {

    private static final long serialVersionUID = 1L;
   
    ModController myController;
   
    Collection<String> myCategories = new ArrayList<String>();
   
    public ModView() {
       
    }
   
    
    public ModView(ModController controller) {
        myController = controller;
    }
    
   
    public void initializeOnStart() {
       
        JPanel contentPane = new JPanel(new BorderLayout());
       
        contentPane.add(makeCategoryBox(), BorderLayout.NORTH);
        contentPane.add(makeButton(), BorderLayout.SOUTH);
       
        //handle central jpanel
       
        JPanel centralPane = new JPanel(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
       
        //JList
       
       
        contentPane.add(centralPane, BorderLayout.CENTER);
       
        //end central jpanel
       
        pack();
        setVisible(true);
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
       
        JComboBox categoryBox = new JComboBox((String[])myCategories.toArray());
        categoryBox.setSelectedIndex(0);
        categoryBox.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e)
            {
                //change based on box
            }
           
        });
       
        return categoryBox;
       
    }
   
}