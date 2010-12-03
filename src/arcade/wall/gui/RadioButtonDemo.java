package arcade.wall.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RadioButtonDemo extends JPanel {
    static JFrame frame;

    static String jumper = "Jumper";
    static String mario = "Mario";
    static String grandius = "Grandius";
    static String zombieLand = "ZombieLand";
    static String towerDefense = "Tower Defense";

    JLabel picture;

    public RadioButtonDemo() {
        // Create the radio buttons.
        JRadioButton jumperButton = new JRadioButton(jumper);
        jumperButton.setMnemonic(KeyEvent.VK_J);
        jumperButton.setActionCommand(jumper);
        jumperButton.setSelected(true);

        JRadioButton marioButton = new JRadioButton(mario);
        marioButton.setMnemonic(KeyEvent.VK_M);
        marioButton.setActionCommand(mario);

        JRadioButton grandiusButton = new JRadioButton(grandius);
        grandiusButton.setMnemonic(KeyEvent.VK_G);
        grandiusButton.setActionCommand(grandius);

        JRadioButton zombieLandButton = new JRadioButton(zombieLand);
        zombieLandButton.setMnemonic(KeyEvent.VK_Z);
        zombieLandButton.setActionCommand(zombieLand);

        JRadioButton towerDefenseButton = new JRadioButton(towerDefense);
        towerDefenseButton.setMnemonic(KeyEvent.VK_T);
        towerDefenseButton.setActionCommand(towerDefense);

        // Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(jumperButton);
        group.add(marioButton);
        group.add(grandiusButton);
        group.add(zombieLandButton);
        group.add(towerDefenseButton);

        // Register a listener for the radio buttons.
        RadioListener myListener = new RadioListener();
        jumperButton.addActionListener(myListener);
        marioButton.addActionListener(myListener);
        grandiusButton.addActionListener(myListener);
        zombieLandButton.addActionListener(myListener);
        towerDefenseButton.addActionListener(myListener);

        // Set up the picture label
        
//        picture = new JLabel(new ImageIcon("images/" 
//                                           + jumper 
//                                           + ".gif"));
        picture = new JLabel();

        // The preferred size is hard-coded to be the width of the
        // widest image and the height of the tallest image.
        // A real program would compute this.
        
        picture.setPreferredSize(new Dimension(177, 122));


        // Put the radio buttons in a column in a panel
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(0, 1));
        radioPanel.add(jumperButton);
        radioPanel.add(marioButton);
        radioPanel.add(grandiusButton);
        radioPanel.add(zombieLandButton);
        radioPanel.add(towerDefenseButton);

        setLayout(new BorderLayout());
        add(radioPanel, BorderLayout.WEST);
        add(picture, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    /** Listens to the radio buttons. */
    class RadioListener implements ActionListener { 
        public void actionPerformed(ActionEvent e) {
//            picture.setIcon(new ImageIcon("images/" 
//                                          + e.getActionCommand() 
//                                          + ".gif"));
        	
        }
    }

    public static void main(String s[]) {
         frame = new JFrame("RadioButtonDemo");
         frame.addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent e) {System.exit(0);}
         });
 
         frame.getContentPane().add(new RadioButtonDemo(), BorderLayout.CENTER);
         frame.pack();
         frame.setVisible(true);
    }
}
