package vooga.engine.factory;

public class LevelException extends RuntimeException{

	public final static RuntimeException LEVEL_PARSING_EXCEPTION = 
		new RuntimeException("Level format is incorrect");


}
