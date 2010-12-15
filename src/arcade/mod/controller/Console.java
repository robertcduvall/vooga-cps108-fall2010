package arcade.mod.controller;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import arcade.mod.model.XMLModel;
import arcade.mod.view.ConsoleView;

public class Console {

	public XMLModel myModel;
	public ConsoleView myView;

	public static void main(String args[]) {
		new Console();
	}
	public Console() {
		myView = new ConsoleView();

	}

}
