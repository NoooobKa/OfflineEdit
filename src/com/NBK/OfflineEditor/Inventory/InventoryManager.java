package com.NBK.OfflineEditor.Inventory;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.NBK.OfflineEditor.player.OfflineEditPlayer;
import com.NBK.OfflineEditor.util.CustomStack;
import com.NBK.OfflineEditor.util.VersionUtils;

public class InventoryManager {

	private static final IInventoryWrapper wrapper;
	
	static {
		wrapper = loadInvWrapper();
	}
	
	private static IInventoryWrapper loadInvWrapper() {
		switch (VersionUtils.getVersion()) {
		case "v1_8_R1":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_8_R1.Wrapper();
		case "v1_8_R2":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_8_R2.Wrapper();
		case "v1_8_R3":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_8_R3.Wrapper();
		case "v1_9_R1":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_9_R1.Wrapper();
		case "v1_9_R2":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_9_R2.Wrapper();
		case "v1_10_R1":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_10_R1.Wrapper();
		case "v1_11_R1":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_11_R1.Wrapper();
		case "v1_12_R1":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_12_R1.Wrapper();
		case "v1_13_R1":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_13_R1.Wrapper();
		case "v1_14_R1":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_14_R1.Wrapper();
		case "v1_15_R1":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_15_R1.Wrapper();
		case "v1_16_R1":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_16_R1.Wrapper();
		case "v1_16_R2":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_16_R2.Wrapper();
		case "v1_16_R3":
			return new com.NBK.OfflineEditor.Inventory.versions.v1_16_R3.Wrapper();
		default:
			return null;
		}
	}
	
	public static IOfflinePlayerInventory createInventory(OfflinePlayer player) {
		return InventoryManager.wrapper.createOfflineInventory(player);	
	}
	
	public static IOfflinePlayerInventory createEnderInventory(OfflinePlayer player) {
		return InventoryManager.wrapper.createOfflineEnderChestInventory(player);
	}
	
	public static Inventory createInventory(int size, String title) {
		return InventoryManager.wrapper.createInventory(size, title);
	}
	
	public static Inventory createPlayerGUI(OfflinePlayer player) {
		Inventory inv = InventoryManager.createInventory(27, "§cOffline§2Edit §9§l| §5" + player.getName());
		//inv.setItem(0, new CustomStack(Material.GRASS).setName("Edit World").getItemStack());
		inv.setItem(3, new CustomStack(Material.REDSTONE).setName("Edit Health").getItemStack());
		inv.setItem(5, new CustomStack(Material.GLOWSTONE_DUST).setName("Edit Absorbation Hearts").getItemStack());
		inv.setItem(9, new CustomStack(Material.NETHER_STAR).setName("Edit GameMode").getItemStack());
		inv.setItem(13, new CustomStack(Material.COOKED_BEEF).setName("Edit FoodLevel").getItemStack());
		inv.setItem(17, new CustomStack(Material.CHEST).setName("Edit Inventory").getItemStack());
		inv.setItem(18, new CustomStack(Material.COMPASS).setName("Edit Location").getItemStack());
		inv.setItem(22, new CustomStack(Material.EXP_BOTTLE).setName("Edit Level").getItemStack());
		inv.setItem(26, new CustomStack(Material.ENDER_CHEST).setName("Edit EnderInventory").getItemStack());
		return inv;
	}
	
	public static Inventory createPlayerWorldChanger(OfflinePlayer player) {
		Inventory inv = InventoryManager.createInventory(27, "§cOffline§2Edit §9§l| §5" + player.getName() + " §9§l| §aWorld§2Changer");
		return inv;
	}
	
	public static Inventory createPlayerAbsorbationChanger(OfflinePlayer player) {
		Inventory inv = InventoryManager.createInventory(27, "§cOffline§2Edit §9§l| §5" + player.getName() + " §9§l| §aAbsorbation§2Changer");
		ItemStack BARRIER = new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 0).setName("§c§lBARRIER").getItemStack();
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, BARRIER);
		}
		inv.setItem(4, new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 5).setName("§aAdd Absorbation").getItemStack());
		inv.setItem(13, new CustomStack(Material.GLOWSTONE_DUST).setName("§eAbsorbation§f: §6" + OfflineEditPlayer.getPlayer(player).getAbsorbation()).getItemStack());
		inv.setItem(22, new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 14).setName("§cSubtract Absorbation").getItemStack());
		return inv;
	}
	
	public static Inventory createPlayerGameModeChanger(OfflinePlayer player) {
		Inventory inv = InventoryManager.createInventory(27, "§cOffline§2Edit §9§l| §5" + player.getName() + " §9§l| §aGameMode§2Changer");
		GameMode gm = OfflineEditPlayer.getPlayer(player).getGameMode();
		ItemStack BARRIER = new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 0).setName("§c§lBARRIER").getItemStack();
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, BARRIER);
		}
		inv.setItem(4, new CustomStack(Material.BRICK).setName("§aSET §e§lCREATIVE").getItemStack());
		inv.setItem(12, new CustomStack(Material.GOLD_SWORD).setName("§aSET §2§lSURVIVAL").getItemStack());
		inv.setItem(13, new CustomStack(gm == GameMode.SURVIVAL ? Material.GOLD_SWORD : gm == GameMode.CREATIVE ? Material.BRICK : gm == GameMode.SPECTATOR ? Material.EYE_OF_ENDER : gm == GameMode.ADVENTURE ? Material.STONE_PICKAXE : Material.BARRIER).setName("§aCurrent GM§f: §6§l" + gm.name()).getItemStack());
		inv.setItem(14, new CustomStack(Material.STONE_PICKAXE).setName("§aSET §b§lADVENTURE").getItemStack());
		inv.setItem(22, new CustomStack(Material.EYE_OF_ENDER).setName("§aSET §5§lSPECTATOR").getItemStack());
		return inv;
	}
	
	public static Inventory createPlayerFoodLevelChanger(OfflinePlayer player) {
		Inventory inv = InventoryManager.createInventory(27, "§cOffline§2Edit §9§l| §5" + player.getName() + " §9§l| §aFoodLevel§2Changer");
		ItemStack BARRIER = new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 0).setName("§c§lBARRIER").getItemStack();
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, BARRIER);
		}
		inv.setItem(4, new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 5).setName("§aAdd Food").getItemStack());
		inv.setItem(13, new CustomStack(Material.COOKED_BEEF).setName("§eFoodLevel§f: §6" + OfflineEditPlayer.getPlayer(player).getFoodLevel()).getItemStack());
		inv.setItem(22, new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 14).setName("§cSubtract Food").getItemStack());
		return inv;
	}
	
	public static Inventory createPlayerHealthChanger(OfflinePlayer player) {
		Inventory inv = InventoryManager.createInventory(27, "§cOffline§2Edit §9§l| §5" + player.getName() + " §9§l| §aHealth§2Changer");
		ItemStack BARRIER = new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 0).setName("§c§lBARRIER").getItemStack();
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, BARRIER);
		}
		inv.setItem(4, new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 5).setName("§aAdd Health").getItemStack());
		inv.setItem(13, new CustomStack(Material.REDSTONE).setName("§cHealth§f: §6" + OfflineEditPlayer.getPlayer(player).getHealth()).getItemStack());
		inv.setItem(22, new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 14).setName("§cSubtract Health").getItemStack());
		return inv;
	}
	
	public static Inventory createPlayerLocationChanger(OfflinePlayer player) {
		Inventory inv = InventoryManager.createInventory(27, "§cOffline§2Edit §9§l| §5" + player.getName() + " §9§l| §aLocation§2Changer");
		ItemStack BARRIER = new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 0).setName("§c§lBARRIER").getItemStack();
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, BARRIER);
		}
		Location loc = OfflineEditPlayer.getPlayer(player).getPosition();
		inv.setItem(13, new CustomStack(Material.COMPASS).setName("§Click to set ur position").addStringInLore("§aCurrent Postition§f:").addStringInLore("§eX§f: §6" + (int)loc.getX()).addStringInLore("§eY§f: §6" + (int)loc.getY()).addStringInLore("§eZ§f: §6" + (int)loc.getZ()).getItemStack());
		return inv;
	}
	
	public static Inventory createPlayerLevelChanger(OfflinePlayer player) {
		Inventory inv = InventoryManager.createInventory(27, "§cOffline§2Edit §9§l| §5" + player.getName() + " §9§l| §aLevel§2Changer");
		ItemStack BARRIER = new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 0).setName("§c§lBARRIER").getItemStack();
		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, BARRIER);
		}
		inv.setItem(4, new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 5).setName("§aAdd Level").getItemStack());
		inv.setItem(13, new CustomStack(Material.EXP_BOTTLE).setName("§aLevel§f: §6" + OfflineEditPlayer.getPlayer(player).getLevel()).getItemStack());
		inv.setItem(22, new CustomStack(Material.STAINED_GLASS_PANE).setDurablity((short) 14).setName("§cSubtract Level").getItemStack());
		return inv;
	}
	
}
