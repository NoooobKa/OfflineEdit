package com.NBK.OfflineEditor.Inventory;

import org.bukkit.inventory.Inventory;

public interface IOfflinePlayerInventory {
	
	void setItemsFromNBT(Object o);
	
	Object getNBTTagList(Object o);
	
	Inventory getOfflineInventory();
}
