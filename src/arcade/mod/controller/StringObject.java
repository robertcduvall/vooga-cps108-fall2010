package arcade.mod.controller;

public class StringObject {
	private String myString;
	
	public StringObject(String s){
		myString = s;
	}
	
	@Override
	public String toString(){
		return myString;
	}
}
