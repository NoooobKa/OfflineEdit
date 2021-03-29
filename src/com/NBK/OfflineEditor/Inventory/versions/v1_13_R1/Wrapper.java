package com.NBK.OfflineEditor.Inventory.versions.v1_13_R1;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;

import com.NBK.OfflineEditor.Inventory.IInventoryWrapper;
import com.NBK.OfflineEditor.Inventory.IOfflinePlayerInventory;
import com.NBK.OfflineEditor.util.CustomStack;

public class Wrapper implements IInventoryWrapper{

	@Override
	public IOfflinePlayerInventory createOfflineInventory(OfflinePlayer player) {
		IOfflinePlayerInventory oi =  new OfflinePlayerInventory(player);
		Inventory inv = oi.getOfflineInventory();
		for (int i = 36; i < 45; i++) {
			inv.setItem(i, new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 5).setName("§c§lBARRIER").getItemStack());
		}
		for (int i = 50; i < 53; i++) {
			inv.setItem(i, new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 5).setName("§c§lBARRIER").getItemStack());
		}
		inv.setItem(53, new CustomStack(Material.EMERALD).setName("§a§lSAVE INVENTORY").getItemStack());
		return oi;
	}

	@Override
	public IOfflinePlayerInventory createOfflineEnderChestInventory(OfflinePlayer player) {
		IOfflinePlayerInventory oi = new OfflinePlayerEnderChestInventory(player);
		Inventory inv = oi.getOfflineInventory();
		for (int i = 27; i < 35; i++) {
			inv.setItem(i, new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 5).setName("§c§lBARRIER").getItemStack());
		}
		inv.setItem(35, new CustomStack(Material.EMERALD).setName("§a§lSAVE INVENTORY").getItemStack());
		return oi;
	}

	@Override
	public Inventory createInventory(int size, String title) {
		return Bukkit.createInventory(null, size, title);
	}
	
}
