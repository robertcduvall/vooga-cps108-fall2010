package arcade.store.organizer;

import java.util.List;

import arcade.store.items.IItemInfo;

public interface IOrganizer {

	public List<IItemInfo> organize(List<IItemInfo> list, String criteria);

}
