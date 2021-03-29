package com.NBK.OfflineEditor.data.versions.v1_16_R2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import com.NBK.OfflineEditor.Inventory.IOfflinePlayerInventory;
import com.NBK.OfflineEditor.data.IData;
import com.NBK.OfflineEditor.util.GenericAttribute;

import net.minecraft.server.v1_16_R2.*;

public class Manager implements IData{

	@Override
	public void setAbsorbation(OfflinePlayer player, float absorbation) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		tagCompound.setFloat("AbsorptionAmount", absorbation);
		save(tagCompound, player);
	}

	@Override
	public float getAbsorbation(OfflinePlayer player) {
		return getPlayerNBT(player).getFloat("AbsorptionAmount");
	}
	
	@Override
	public void setAttribute(OfflinePlayer player, GenericAttribute attribute, double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnderItems(OfflinePlayer player, IOfflinePlayerInventory inventory) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		tagCompound.set("EnderItems", (NBTBase) inventory.getNBTTagList(new NBTTagList()));
		save(tagCompound, player);
	}

	@Override
	public void setHealth(OfflinePlayer player, float health) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		tagCompound.setFloat("HealF", health);
		tagCompound.setShort("Health", (short)(int)Math.ceil(health));
		save(tagCompound, player);
	}

	@Override
	public float getHealth(OfflinePlayer player) {
		return getPlayerNBT(player).getFloat("HealF");
	}
	
	@Override
	public void setInventory(OfflinePlayer player, IOfflinePlayerInventory inventory) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		tagCompound.set("Inventory", (NBTBase) inventory.getNBTTagList(new NBTTagList()));
		save(tagCompound, player);
	}

	@Override
	public void setPosition(OfflinePlayer player, Location pos) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		NBTTagList tagList = tagCompound.getList("Pos", 6);
		tagList.a(0, NBTTagDouble.a(pos.getX()));
		tagList.a(1, NBTTagDouble.a(pos.getY()));
		tagList.a(2, NBTTagDouble.a(pos.getZ()));
		tagCompound.set("Pos", tagList);
		save(tagCompound, player);
		setRatation(player, pos.getYaw(), pos.getPitch());
	}

	@Override
	public Location getPosition(OfflinePlayer player) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		NBTTagList tagList = tagCompound.getList("Pos", 6);
		return new Location(Bukkit.getWorld(getWorld(player)), tagList.e(0), tagList.e(1), tagList.e(2));
	}
	
	@Override
	public void setRatation(OfflinePlayer player, float yaw, float pitch) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		NBTTagList tagList = tagCompound.getList("Rotation", 5);
		tagList.a(0, NBTTagFloat.a(yaw));
		tagList.a(1, NBTTagFloat.a(pitch));
		tagCompound.set("Rotation", tagList);
		save(tagCompound, player);
	}

	@Override
	public void setItemInHand(OfflinePlayer player, ItemStack item) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		net.minecraft.server.v1_16_R2.ItemStack is = CraftItemStack.asNMSCopy(item);
		if (is != null && is.getItem() != null) {
			tagCompound.set("SelectedItem", is.save(new NBTTagCompound()));
			save(tagCompound, player);
		}
	}

	@Override
	public void setWorld(OfflinePlayer player, World world) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		tagCompound.setString("SpawnWorld", world.getName());
		save(tagCompound, player);
	}

	@Override
	public String getWorld(OfflinePlayer player) {
		return getPlayerNBT(player).getString("SpawnWorld");
	}
	
	@Override
	public void setLevel(OfflinePlayer player, int level) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		tagCompound.setInt("XpLevel", level);
		save(tagCompound, player);
	}

	@Override
	public int getLevel(OfflinePlayer player) {
		return getPlayerNBT(player).getInt("XpLevel");
	}
	
	@Override
	public void setFoodLevel(OfflinePlayer player, int food) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		tagCompound.setInt("foodLevel", food);
		save(tagCompound, player);
	}

	@Override
	public int getFoodLevel(OfflinePlayer player) {
		return getPlayerNBT(player).getInt("foodLevel");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void setGameMode(OfflinePlayer player, GameMode gm) {
		NBTTagCompound tagCompound = getPlayerNBT(player);
		tagCompound.setInt("playerGameType", gm.getValue());
		save(tagCompound, player);
	}

	@SuppressWarnings("deprecation")
	@Override
	public GameMode getGameMode(OfflinePlayer player) {
		return GameMode.getByValue(getPlayerNBT(player).getInt("playerGameType"));
	}
	
    public static File getPlayerFile(final OfflinePlayer player) {
        final File wDir = Bukkit.getWorlds().get(0).getWorldFolder();
        final UUID uuid = player.getUniqueId();
        final File playerDir = new File(wDir, "playerdata");
        return new File(playerDir, uuid + ".dat");
    }
	
    public static File getPlayerTempFile(OfflinePlayer player) {
        File wDir = Bukkit.getWorlds().get(0).getWorldFolder();
        UUID uuid = player.getUniqueId();
        File playerDir = new File(wDir, "playerdata");
        return new File(playerDir, uuid + ".dat.tmp");
    }
    
    @SuppressWarnings({ "resource" })
	public static NBTTagCompound getPlayerNBT(OfflinePlayer player) {
        try {
          File file1 = getPlayerFile(player);
          if (file1.exists())return NBTCompressedStreamTools.a(new FileInputStream(file1)); 
        } catch (Exception exception) {}
        return null;
    }
    
    public static void save(NBTTagCompound nbt, OfflinePlayer op) {
        try {
          NBTTagCompound nbttagcompound = nbt;
          File file = getPlayerTempFile(op);
          File file2 = getPlayerFile(op);
          NBTCompressedStreamTools.a(nbttagcompound, new FileOutputStream(file));
          if (file2.exists())file2.delete(); 
          file.renameTo(file2);
        } catch (Exception ex) {
          ex.printStackTrace();
        } 
    }
	
}
