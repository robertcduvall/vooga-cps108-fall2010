package com.brackeen.javagamebook.resources;

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
	Map<String, Object> Data;
	

	protected DataHandler() {
		Data = new HashMap<String, Object>();
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
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
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
	
	/**
	 * 
	 * @param s Scanner for spreadsheet that is to be parsed.
	 * @param delimiter Spreadsheet delimiter.
	 * @return 2-D array of strings from spreadsheet cells.
	 */
	protected String[][] loadFromSpreadsheet(Scanner s, String delimiter) {
		try {
			System.out.println(System.getProperty("user.dir"));
			int curLine = 1;
			String line = s.nextLine();
			String[] splitLine = line.split(delimiter);
			while (s.hasNextLine()) {
				curLine++;
			}
			int sheetWidth = splitLine.length;
			String[][] outArray = new String[curLine][sheetWidth];
			s.reset();
			curLine = 0;
			while (s.hasNextLine()) {
				line = s.nextLine();
				splitLine = line.split(delimiter);
				for(int i = 0; i < sheetWidth; i++) {
					outArray[curLine][i] = splitLine[i];
				}
				curLine++;
			}
			return outArray;
		} catch (Exception e) {
			System.out.println("File does not exist!");
			return null;
		}
	}
	
	/**
	 * 
	 * @param filename A string containing the file path of the spreadsheet to be parsed.
	 * @param delimiter Spreadsheet delimiter.
	 * @return 2-D array of strings from spreadsheet cells.
	 */
	protected String[][] loadFromSpreadsheet(String filename, String delimiter) {
		return loadFromSpreadsheet(new Scanner(filename), delimiter);
	}
	
	/**
	 * 
	 * @param s Scanner for spreadsheet that is to be parsed.
	 * @param delimiter Spreadsheet delimiter.
	 * @return 2-D array of strings from spreadsheet cells.
	 */
	protected String[][] loadFromSpreadsheet(Scanner s, Character delimiter) {
		return loadFromSpreadsheet(s, ""+delimiter);
	}
	
	/**
	 * 
	 * @param filename A string containing the file path of the spreadsheet to be parsed.
	 * @param delimiter Spreadsheet delimiter.
	 * @return 2-D array of strings from spreadsheet cells.
	 */
	protected String[][] loadFromSpreadsheet(String filename, Character delimiter) {
		return loadFromSpreadsheet(new Scanner(filename), ""+delimiter);
	}

	// create methods for getting fields from XML (e.g., return arraylist of
	// entries for a given field)
	// create method for getting CSV data (returns static array)
	// SQLite data retrieval/storage

}
