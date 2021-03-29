package com.NBK.OfflineEditor.Inventory.versions.v1_9_R1;

import java.io.File;
import java.io.FileInputStream;

import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.inventory.Inventory;

import com.NBK.OfflineEditor.Inventory.IOfflinePlayerInventory;
import com.NBK.OfflineEditor.util.Util;

import net.minecraft.server.v1_9_R1.ItemStack;
import net.minecraft.server.v1_9_R1.NBTCompressedStreamTools;
import net.minecraft.server.v1_9_R1.NBTTagCompound;
import net.minecraft.server.v1_9_R1.NBTTagList;

public class OfflinePlayerInventory extends CraftInventoryCustom implements IOfflinePlayerInventory{

	public OfflinePlayerInventory(OfflinePlayer player) {
		super(null, 54, "§cOffline§2Edit §9§l| §5" + player.getName() + " §9§l| §2Inventory");
		setItemsFromNBT(getPlayerNBT(player).getList("Inventory", 10));
	}

	@Override
	public void setItemsFromNBT(Object o) {
		NBTTagList nbttaglist = (NBTTagList) o;
		for (int i = 0; i < nbttaglist.size(); i++) {
			NBTTagCompound nbttagcompound = nbttaglist.get(i);
			int j = nbttagcompound.getByte("Slot") & 0xFF;
			ItemStack itemstack = ItemStack.createStack(nbttagcompound);
			if (itemstack != null) {
				if (j >= 0 && j < 36)setItem(j, CraftItemStack.asBukkitCopy(itemstack)); 
				if (j >= 100 && j < 104)setItem(45 + (j - 100), CraftItemStack.asBukkitCopy(itemstack)); 
				if (j == 150)setItem(49, CraftItemStack.asBukkitCopy(itemstack));
			} 
		}
	}
	
	@Override
	public NBTTagList getNBTTagList(Object o) {
		NBTTagList nbtList = (NBTTagList) o;
	    int i;
	    for (i = 0; i < 36; i++) {
	      if (getItem(i) != null) {
	        NBTTagCompound nbttagcompound = new NBTTagCompound();
	        nbttagcompound.setByte("Slot", (byte)i);
	        CraftItemStack.asNMSCopy(getItem(i)).save(nbttagcompound);
	        nbtList.add(nbttagcompound);
	      } 
	    } 
	    for (i = 45; i < 49; i++) {
	      if (getItem(i) != null) {
	        NBTTagCompound nbttagcompound = new NBTTagCompound();
	        nbttagcompound.setByte("Slot", (byte)(100 + i - 45));
	        CraftItemStack.asNMSCopy(getItem(i)).save(nbttagcompound);
	        nbtList.add(nbttagcompound);
	      } 
	    }
	    if (getItem(49) != null) {
	        NBTTagCompound nbttagcompound = new NBTTagCompound();
	        nbttagcompound.setByte("Slot", (byte)(150));
	        CraftItemStack.asNMSCopy(getItem(i)).save(nbttagcompound);
	        nbtList.add(nbttagcompound);
	    }
	    return nbtList;
	}
	
    @SuppressWarnings({ "resource" })
	private NBTTagCompound getPlayerNBT(OfflinePlayer player) {
        try {
          File file1 = Util.getPlayerFile(player);
          if (file1.exists())return NBTCompressedStreamTools.a(new FileInputStream(file1)); 
        } catch (Exception exception) {}
        return null;
    }
	
	@Override
	public Inventory getOfflineInventory() {
		return this;
	}
    
}
