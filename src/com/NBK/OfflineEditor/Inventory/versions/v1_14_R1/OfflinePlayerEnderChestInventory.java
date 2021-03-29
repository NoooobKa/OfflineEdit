package com.NBK.OfflineEditor.Inventory.versions.v1_14_R1;

import java.io.File;
import java.io.FileInputStream;

import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftInventoryCustom;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.inventory.Inventory;

import com.NBK.OfflineEditor.Inventory.IOfflinePlayerInventory;
import com.NBK.OfflineEditor.util.Util;

import net.minecraft.server.v1_14_R1.ItemStack;
import net.minecraft.server.v1_14_R1.NBTCompressedStreamTools;
import net.minecraft.server.v1_14_R1.NBTTagCompound;
import net.minecraft.server.v1_14_R1.NBTTagList;

public class OfflinePlayerEnderChestInventory extends CraftInventoryCustom implements IOfflinePlayerInventory{

	public OfflinePlayerEnderChestInventory(OfflinePlayer player) {
		super(null, 36, "§cOffline§2Edit §9§l| §5" + player.getName() + " §9§l| §2EnderChest");
		setItemsFromNBT(getPlayerNBT(player).getList("EnderItems", 10));
	}

	@Override
	public void setItemsFromNBT(Object o) {
		NBTTagList nbtList = (NBTTagList) o;
	    int i;
	    for (i = 0; i < 27; i++)
	      setItem(i, null); 
	    for (i = 0; i < nbtList.size(); i++) {
	      NBTTagCompound nbttagcompound = nbtList.getCompound(i);
	      int j = nbttagcompound.getByte("Slot") & 0xFF;
	      if (j >= 0 && j < getSize())
	        setItem(j, CraftItemStack.asBukkitCopy(ItemStack.a(nbttagcompound))); 
	    }
	}

	@Override
	public Object getNBTTagList(Object o) {
	    NBTTagList nbttaglist = new NBTTagList();
	    for (int i = 0; i < 27; i++) {
	      ItemStack itemstack = CraftItemStack.asNMSCopy(getItem(i));
	      if (itemstack != null) {
	        NBTTagCompound nbttagcompound = new NBTTagCompound();
	        nbttagcompound.setByte("Slot", (byte)i);
	        itemstack.save(nbttagcompound);
	        nbttaglist.add(nbttagcompound);
	      } 
	    } 
	    return nbttaglist;
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
