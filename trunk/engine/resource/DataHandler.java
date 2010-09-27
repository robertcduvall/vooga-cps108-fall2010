package engine.resource;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

/**
 * The DataHandler class is an abstract class that allows for the import and access of static data from external files.
 * @author David Herzka
 * @date September 26, 2010
 *
 */
public abstract class DataHandler {
	DocumentBuilder db;
	Map<String, Object> Data;
	

	protected DataHandler() {
		Data = new HashMap<String, Object>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This constructor calls the default constructor for DataHandler and loads data from the indicated file.
	 * @param filename The location of the data file to be loaded.
	 */
	protected DataHandler(String filename) {
		this();
		load(filename);
	}

	/**
	 * An abstract method that contains the procedure for importing data.  
	 * Within load(...), data fields should be added to the DataHandler using CreateDataField(...) 
	 * @param filename The location of the data file to be loaded.
	 */
	public abstract void load(String filename);

	/**
	 * A method to help parse an XML input file.
	 * @param filename The location of the data file to be loaded.
	 * @return dom Document parsed from XML file.
	 */
	protected Document loadFromXML(String filename) {
		try {
			System.out.println(System.getProperty("user.dir"));
			InputStream is = getResourceAsStream(filename);
			Document doc = db.parse(is);
			return doc;
		} catch (Exception e) {
			System.out.println(filename + " does not exist!");
			return null;
		}
	}

	protected InputStream getResourceAsStream(String filename) {
		filename = "data/" + filename;
		return getClass().getClassLoader().getResourceAsStream(filename);
	}

	protected void CreateDataField(String fieldName, Object fieldData) {
		Data.put(fieldName.trim().toUpperCase(), fieldData);
	}

	/**
	 * Fetches data field object corresponding to supplied name.
	 * @param fieldName The name of the data object to be retrieved.
	 */
	public Object getData(String fieldName) {
		return Data.get(fieldName.toUpperCase().trim());
	}

	protected Object[][] loadFromSpreadSheet(String filename, String delimiter) {
		try {
			System.out.println(System.getProperty("user.dir"));
			InputStream is = getResourceAsStream(filename);
			Scanner s = new Scanner(is);
			int curLine = 1;
			String line = s.nextLine();
			String[] splitLine = line.split(delimiter);
			while (s.hasNextLine()) {
				curLine++;
			}
			Object[][] outArray = new Object[curLine][splitLine.length];
			s.reset();
			curLine = 0;
			while (s.hasNextLine()) {
				line = s.nextLine();
				splitLine = line.split(delimiter);
				outArray[curLine++] = splitLine;
			}
			return outArray;
		} catch (Exception e) {
			System.out.println(filename + " does not exist!");
			return null;
		}
	}

	// create methods for getting fields from XML (e.g., return arraylist of
	// entries for a given field)
	// create method for getting CSV data (returns static array)
	// SQLite data retrieval/storage

}
