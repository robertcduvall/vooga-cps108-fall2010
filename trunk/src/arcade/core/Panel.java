package arcade.core;

import java.awt.*;
import java.awt.Event.*;

import javax.swing.*;

/**
 * @author Derek Zhou, Yang Su, Aaron Choi
 * 
 */
public abstract class Panel extends JPanel{
	private String name;
	
	public Panel(){
		
	}
	
	public Panel(String title){
		name=title;
	}
}
