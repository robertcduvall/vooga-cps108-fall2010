package arcade.store.page;

import java.util.List;

public interface ISearchablePage {

	public String getGameName();
	public List<String> getTags();
	public String getGenre();
}
