package arcade.mod.controller;

public class StringObject implements ControllerObject {
	private String myString;
	
	public StringObject(String s){
		myString = s;
	}
	
	public String toString(){
		return myString;
	}
}
