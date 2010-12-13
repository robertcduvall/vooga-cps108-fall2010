package arcade.store.test;

import java.util.List;
import java.util.ArrayList;

import arcade.store.items.IItemInfo;
import arcade.store.items.ItemInfo;
import arcade.store.organizer.FilterByTitleOrganizer;
import arcade.store.organizer.FilterOrganizer;
import arcade.store.organizer.SortByTitleOrganizer;
import arcade.store.organizer.SortOrganizer;

public class SortItemInfoTest {

	public static void main(String[] args) {

		IItemInfo a = new ItemInfo("abc", "8", "A", "3", null, "Action");
		IItemInfo b = new ItemInfo("abc", "3", "X", "20", null, "Shooter");
		IItemInfo c = new ItemInfo("abc", "0", "Y", "9", null, "Racing");
		IItemInfo d = new ItemInfo("abc", "5", "B", "14", null, "Racing");
		IItemInfo e = new ItemInfo("abc", "1", "H", "5", null, "Role Playing");
		IItemInfo f = new ItemInfo("abc", "9", "W", "16", null, "Adventure");
		IItemInfo g = new ItemInfo("abc", "4", "M", "1", null, "Action");
		IItemInfo h = new ItemInfo("abc", "2", "N", "4", null, "Role Playing");
		IItemInfo i = new ItemInfo("abc", "6", "J", "2", null, "Music");

		ArrayList<IItemInfo> itemInfoList = new ArrayList<IItemInfo>();
		SortOrganizer sortOrganizer = new SortByTitleOrganizer();
		FilterOrganizer filterOrganizer = new FilterByTitleOrganizer();
		
		itemInfoList.add(a);
		itemInfoList.add(b);
		itemInfoList.add(c);
		itemInfoList.add(d);
		itemInfoList.add(e);
		itemInfoList.add(f);
		itemInfoList.add(g);
		itemInfoList.add(h);
		itemInfoList.add(i);
		
		List<IItemInfo> sortedItemInfoList = sortOrganizer.organize(itemInfoList, "x");
		
		for (IItemInfo info : sortedItemInfoList) {
			System.out.println(info.getTitle());
		}
		
		System.out.println("\n");
		
		List<IItemInfo> filteredItemInfoList = filterOrganizer.organize(itemInfoList, "W");

		for (IItemInfo info : filteredItemInfoList) {
			System.out.println(info.getTitle());
		}
	}
}
