package vooga.engine.resource;

/**
 * Entry represents a single database cell, containing field and data information.
 * @author David G. Herzka
 *
 * @param <T>
 */
public class Entry<T> {
	private String myFieldName;
	private T myData;
	
	/**
	 * Constructs an Entry with the indicated field name and data
	 */
	public Entry(String fieldName,T data) {
		myFieldName = fieldName;
		myData = data;
	}
	
	/**
	 * @return Encapsulated data object
	 */
	public T getData() {
		return myData;
	}
	
	/**
	 * @return Field name
	 */
	public String getFieldName() {
		return myFieldName;
	}
}
