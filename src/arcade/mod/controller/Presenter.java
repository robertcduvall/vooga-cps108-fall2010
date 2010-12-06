package arcade.mod.controller;

import java.util.Collection;

public interface Presenter {
	
	public void save();
	
	//TODO remove necessity to return Collection
	public Collection<String> getCategories();
	
	public void newCategorySelected();

}
