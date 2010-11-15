package vooga.engine.resource;

/**
 * This is a class representing a column of an SQLite table
 * @author David G. Herzka
 */
public class Column {
	private String myName;
	private String myType;
	private String[] myArgs;
	
	// Type constants
	public static final String INT = "INTEGER";
	public static final String NONE = "";
	public static final String BOOLEAN = "BOOLEAN";
	public static final String REAL = "REAL";
	public static final String TEXT = "TEXT";

	/**
	 * Constructs a Column with the indicated name, type, and arguments (e.g., PRIMARY KEY)
	 */
	public Column(String name,String type,String... args) {
		myName = name;
		myType = type;
		myArgs = args;
	}

	/**
	 * Generates the approprate declaration for the column
	 */
	public String getDeclaration() {
		StringBuilder sb = new StringBuilder("");
		sb.append(myName+" "+myType);
		for(String arg : myArgs) {
			sb.append(" " + arg);
		}
		return sb.toString();
	}
}
