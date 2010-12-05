package arcade.mod.model;

import java.io.File;
import java.util.Collection;
import java.util.List;

public interface Model {
	
	public Collection<String> getCategories();
	
	public List<ResourceNode> getResourcesFromCategory(String category);
	
	public void writeResources(File file);

	
}
