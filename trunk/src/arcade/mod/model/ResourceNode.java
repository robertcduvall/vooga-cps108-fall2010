package arcade.mod.model;

import java.util.Collection;
import java.util.List;

public interface ResourceNode {
	
	public List<ResourceNode> getChildren();	
	
	public Collection<String> getAttributes();
	
	public String getAttribute(String attributeName);
	
	public void setAttribute(String attributeName, String value);
	
	public String getDescription();

}
