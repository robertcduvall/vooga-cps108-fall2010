package vooga.engine.resource;

public class Entry<T> {
	private String myColumnName;
	private T myData;
	
	public Entry(String columnName,T data) {
		myColumnName = columnName;
		myData = data;
	}
	
	public T getData() {
		return myData;
	}
	
	public String getColumnName() {
		return myColumnName;
	}
}
