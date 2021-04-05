package com.NBK.OfflineEditor.player;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import com.NBK.OfflineEditor.Inventory.IOfflinePlayerInventory;
import com.NBK.OfflineEditor.Inventory.InventoryManager;
import com.NBK.OfflineEditor.data.DataManager;
import com.NBK.OfflineEditor.database.PlayersDataBase;
import com.NBK.OfflineEditor.main.OfflineEditor;
import com.NBK.OfflineEditor.util.GenericAttribute;

public class OfflineEditPlayer{

	private static final Map<UUID, OfflineEditPlayer> players;
	private UUID id;
	private Long lastPlayed;
	private Inventory mainInv;
	private IOfflinePlayerInventory lastInv;
	private IOfflinePlayerInventory lastEnderInv;
	
	static {
		players = new ConcurrentHashMap<>();
	}
	
	public OfflineEditPlayer(UUID id) {
		OfflinePlayer op = Bukkit.getOfflinePlayer(id);
		if (op != null) {
			this.id = id;
			this.lastInv = null;
			this.lastPlayed = op.getLastPlayed();
			this.mainInv = InventoryManager.createPlayerGUI(op);
			players.put(id, this);
		}else {
			throw new NullPointerException("Player with this id not founded!");
		}
	}

	public OfflinePlayer getOfflinePlayer() {
		return Bukkit.getOfflinePlayer(id);
	}
	
	public IOfflinePlayerInventory getOfflineInventory() {
		if (lastInv == null) {
			return lastInv = InventoryManager.createInventory(getOfflinePlayer());
		}else {
			if (lastPlayed == getOfflinePlayer().getLastPlayed()) {
				return lastInv;
			}else {
				lastPlayed = getOfflinePlayer().getLastPlayed();
				return lastInv = InventoryManager.createInventory(getOfflinePlayer());
			}
		}
	}

	public IOfflinePlayerInventory getOfflineEnderInventory() {
		if (lastEnderInv == null) {
			return lastEnderInv = InventoryManager.createEnderInventory(getOfflinePlayer());
		}else {
			if (lastPlayed == getOfflinePlayer().getLastPlayed()) {
				return lastEnderInv;
			}else {
				lastPlayed = getOfflinePlayer().getLastPlayed();
				return lastEnderInv = InventoryManager.createEnderInventory(getOfflinePlayer());
			}
		}
	}
	
	public Inventory getMainGUI() {
		return mainInv;
	}
	
	public void setAbsorbation(float absorbation) {
		new BukkitRunnable() {
			@Override
			public void run() {
				DataManager.getManager().setAbsorbation(getOfflinePlayer(), absorbation);
			}
		}.runTaskAsynchronously(OfflineEditor.INSTANCE);
	}

	public int getAbsorbation() {
		return (int) DataManager.getManager().getAbsorbation(getOfflinePlayer());
	}
	
	public void setAttribute(GenericAttribute attribute, double value) {
		DataManager.getManager().setAttribute(getOfflinePlayer(), attribute, value);
	}

	public void setEnderItems(IOfflinePlayerInventory inventory) {
		DataManager.getManager().setEnderItems(getOfflinePlayer(), inventory);
	}

	public void setHealth(float health) {
		DataManager.getManager().setHealth(getOfflinePlayer(), health);
	}

	public double getHealth() {
		return DataManager.getManager().getHealth(getOfflinePlayer());
	}
	
	public void setInventory(IOfflinePlayerInventory inventory) {
		DataManager.getManager().setInventory(getOfflinePlayer(), getOfflineInventory());
	}

	public void setPosition(Location pos) {
		DataManager.getManager().setPosition(getOfflinePlayer(), pos);
	}

	public Location getPosition() {
		return DataManager.getManager().getPosition(getOfflinePlayer());
	}
	
	public void setRatation(float yaw, float pitch) {
		DataManager.getManager().setRatation(getOfflinePlayer(), yaw, pitch);
	}

	public void setItemInHand(ItemStack item) {
		DataManager.getManager().setItemInHand(getOfflinePlayer(), item);
	}

	public void setWorld(World world) {
		DataManager.getManager().setWorld(getOfflinePlayer(), world);
	}

	public void setLevel(int level) {
		DataManager.getManager().setLevel(getOfflinePlayer(), level);
	}

	public int getLevel() {
		return DataManager.getManager().getLevel(getOfflinePlayer());
	}
	
	public void setFoodLevel(int food) {
		DataManager.getManager().setFoodLevel(getOfflinePlayer(), food);
	}

	public int getFoodLevel() {
		return DataManager.getManager().getFoodLevel(getOfflinePlayer());
	}
	
	public void setGameMode(GameMode gm) {
		DataManager.getManager().setGameMode(getOfflinePlayer(), gm);
	}
	
	public GameMode getGameMode() {
		return DataManager.getManager().getGameMode(getOfflinePlayer());
	}
	
	public void setPotionEffects(List<PotionEffect> effects) {
		DataManager.getManager().setPotionEffects(getOfflinePlayer(), effects);
	}
	
	public List<PotionEffect> getPotionEffects(){
		return DataManager.getManager().getPotionEffects(getOfflinePlayer());
	}
	
	public static OfflineEditPlayer getPlayer(UUID id) {
		if (players.containsKey(id)) {
			return players.get(id);
		}
		return new OfflineEditPlayer(id);
	}
	
	public static OfflineEditPlayer getPlayer(String name) {
		if (PlayersDataBase.contains(name)) {
			return getPlayer(PlayersDataBase.getPlayers().get(name));
		}
		return null;
	}
	
	public static OfflineEditPlayer getPlayer(OfflinePlayer op) {
		if (op != null) {
			return getPlayer(op.getUniqueId());
		}
		return null;
	}
	
}
