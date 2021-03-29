package com.NBK.OfflineEditor.Inventory;

import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;

public interface IInventoryWrapper {

	IOfflinePlayerInventory createOfflineInventory(OfflinePlayer player);
	
	IOfflinePlayerInventory createOfflineEnderChestInventory(OfflinePlayer player);
	
	Inventory createInventory(int size, String title);
}
