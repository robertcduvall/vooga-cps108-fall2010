package engine.player.action;

public interface Controller {

	public void initializeMappings();
	
	public void addInput(String listen, String method, String classname, Object... paramVals);
	
    public void setParams(Class<?>... parameterTypes);
	
	public void update();
	
}
