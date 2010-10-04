package vooga.engine.player.control;

/*
 * @author Choi, Cue, Hawthorne
 * @version 1.0
 */

public interface Controller {

	public void initializeMappings();
	
	public void addInput(String listen, String method, String classname, Object... paramVals);
	
    public void setParams(Class<?>... parameterTypes);
	
	public void update();
	
}
