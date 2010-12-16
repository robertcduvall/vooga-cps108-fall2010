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

		IItemInfo a = new ItemInfo(0, "abc", "8", "A", "3", null, "Action", null);
		IItemInfo b = new ItemInfo(0,"abc", "3", "X", "20", null, "Shooter", null);
		IItemInfo c = new ItemInfo(0,"abc", "0", "Y", "9", null, "Racing", null);
		IItemInfo d = new ItemInfo(0,"abc", "5", "B", "14", null, "Racing", null);
		IItemInfo e = new ItemInfo(0,"abc", "1", "H", "5", null, "Role Playing", null);
		IItemInfo f = new ItemInfo(0,"abc", "9", "W", "16", null, "Adventure", null);
		IItemInfo g = new ItemInfo(0,"abc", "4", "M", "1", null, "Action", null);
		IItemInfo h = new ItemInfo(0,"abc", "2", "N", "4", null, "Role Playing", null);
		IItemInfo i = new ItemInfo(0,"abc", "6", "J", "2", null, "Music", null);

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
