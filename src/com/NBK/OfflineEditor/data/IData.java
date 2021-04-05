package com.NBK.OfflineEditor.data;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.NBK.OfflineEditor.Inventory.IOfflinePlayerInventory;
import com.NBK.OfflineEditor.util.GenericAttribute;

public interface IData {

	void setAbsorbation(OfflinePlayer player, float absorbation);
	
	float getAbsorbation(OfflinePlayer player);
	
	void setAttribute(OfflinePlayer player, GenericAttribute attribute, double value);
	
	//getAttributes
	
	void setEnderItems(OfflinePlayer player, IOfflinePlayerInventory inventory);
	
	void setHealth(OfflinePlayer player, float health);
	
	float getHealth(OfflinePlayer player);
	
	void setInventory(OfflinePlayer player, IOfflinePlayerInventory inventory);
	
	void setPosition(OfflinePlayer player, Location pos);

	Location getPosition(OfflinePlayer player);
	
	void setRatation(OfflinePlayer player, float yaw, float pitch);
	
	void setItemInHand(OfflinePlayer player, ItemStack item);
	
	void setWorld(OfflinePlayer player, World world);
	
	String getWorld(OfflinePlayer player);
	
	void setLevel(OfflinePlayer player, int level);
	
	int getLevel(OfflinePlayer player);
	
	void setFoodLevel(OfflinePlayer player, int food);
	
	int getFoodLevel(OfflinePlayer player);
	
	void setGameMode(OfflinePlayer player, GameMode gm);
	
	GameMode getGameMode(OfflinePlayer player);
	
	void setPotionEffects(OfflinePlayer player, List<PotionEffect> effects);
	
	List<PotionEffect> getPotionEffects(OfflinePlayer player);
}
