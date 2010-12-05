package arcade.mod.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import arcade.mod.view.AbstractListFrame;

public class FrameFactory {
	
	private final static String categoriesPropertyFile = "src/arcade/mod/categoryType.properties";
	
	public static AbstractListFrame createFrame(String resourceType) throws FileNotFoundException, IOException{
		Properties props = new Properties();
		props.load(new FileInputStream(categoriesPropertyFile));
		Set<Object> set = props.keySet();
		
		
		return null;	
	}
}
