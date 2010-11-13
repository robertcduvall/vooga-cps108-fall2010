package vooga.engine.factory;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.golden.gamedev.object.PlayField;

public class LevelParser implements LevelFactory{

	@Override
	public PlayField getPlayfield(String filepath) {
		
		return makePlayFieldFromFile(filepath);
	
	}

	/**
	 * 
	 * @param levelFactoryFile
	 * @param newplayfield
	 * @return
	 */
	private PlayField makePlayFieldFromFile(String xmlLevelFile) {
	
		PlayField newplayfield = new PlayField();
		
		File file = new File(xmlLevelFile);
		DocumentBuilderFactory documentfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = documentfactory.newDocumentBuilder();
			
			
			
		} catch (ParserConfigurationException e) {
			
			throw LevelException.LEVEL_PARSING_EXCEPTION;
		}
		

		
		
		
		
		
		
		
		
		return newplayfield;
		
	}

}
