package com.NBK.OfflineEditor.Listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

import com.NBK.OfflineEditor.Inventory.InventoryManager;
import com.NBK.OfflineEditor.database.Database;
import com.NBK.OfflineEditor.database.PlayersDataBase;
import com.NBK.OfflineEditor.player.OfflineEditPlayer;
import com.NBK.OfflineEditor.util.CustomStack;
import com.NBK.OfflineEditor.util.VersionUtils;


public class OEListener implements Listener{

	public OEListener(Plugin p) {
		Bukkit.getPluginManager().registerEvents(this, p);
	}
	
	@EventHandler
	public void ice(InventoryClickEvent e) {
		if (e.getClickedInventory() != null && (VersionUtils.isAboveThirteen() ? ChatColor.stripColor(e.getView().getTitle()) : ChatColor.stripColor(e.getInventory().getTitle())) != null) {
			String title = VersionUtils.isAboveThirteen() ? ChatColor.stripColor(e.getView().getTitle()) : ChatColor.stripColor(e.getInventory().getTitle());
			if (title.contains("OfflineEdit")) {
				String[] words = title.split("\\|");
				Player changer = (Player) e.getWhoClicked();
				if (words[1].contains("Database")) {
					e.setCancelled(true);
					if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
						if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
							OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(ChatColor.stripColor(new CustomStack(e.getCurrentItem()).getFirstLoreWhere("UUID")).split("\\s")[1]));
							OfflineEditPlayer p = OfflineEditPlayer.getPlayer(player);
							changer.openInventory(p.getMainGUI());
						}else {
							switch (e.getSlot()) {
							case 45:
								changer.openInventory(Database.getPlayersHeadGUI().back(e.getClickedInventory()));
								break;
							case 53:
								changer.openInventory(Database.getPlayersHeadGUI().next(e.getClickedInventory()));
								break;
							default:
								break;
							}
						}
					}
				}
				if (words.length == 1) {
					
				}else {
					if (words.length == 2) {
						if (PlayersDataBase.contains(words[1].replace(" ", ""))) {
							e.setCancelled(true);
							OfflineEditPlayer p = OfflineEditPlayer.getPlayer(words[1].replace(" ", ""));
							switch (e.getSlot()) {
							case 3:
								if (hasPermission(changer, "oe.editHealth"))changer.openInventory(InventoryManager.createPlayerHealthChanger(p.getOfflinePlayer()));
								break;
							case 5:
								if (hasPermission(changer, "oe.editAbsorbation"))changer.openInventory(InventoryManager.createPlayerAbsorbationChanger(p.getOfflinePlayer()));
								break;
							case 9:
								if (hasPermission(changer, "oe.editGameMode"))changer.openInventory(InventoryManager.createPlayerGameModeChanger(p.getOfflinePlayer()));
								break;
							case 13:
								if (hasPermission(changer, "oe.editFoodLevel"))changer.openInventory(InventoryManager.createPlayerFoodLevelChanger(p.getOfflinePlayer()));
								break;
							case 17:
								if (hasPermission(changer, "oe.editInventory"))changer.openInventory(p.getOfflineInventory().getOfflineInventory());
								break;
							case 18:
								if (hasPermission(changer, "oe.editLocation"))changer.openInventory(InventoryManager.createPlayerLocationChanger(p.getOfflinePlayer()));
								break;
							case 22:
								if (hasPermission(changer, "oe.editLevel"))changer.openInventory(InventoryManager.createPlayerLevelChanger(p.getOfflinePlayer()));
								break;
							case 26:
								if (hasPermission(changer, "oe.editEnderChest"))changer.openInventory(p.getOfflineEnderInventory().getOfflineInventory());
								break;
							default:
								break;
							}
						}
					}else {
						if (words.length == 3) {
							String invName = words[2].replace(" ", "");
							if (invName.equals("Inventory")) {
								if (VersionUtils.isOldVersion()) {
									if (e.getSlot() > 35 && e.getSlot() < 45 || e.getSlot() > 48) {
										e.setCancelled(true);
										if (e.getSlot() == 53) {
											OfflineEditPlayer p = OfflineEditPlayer.getPlayer(words[1].replace(" ", ""));
											p.setInventory(p.getOfflineInventory());
											changer.sendMessage(p.getOfflinePlayer().getName() + "'s inventory saved!");
										}
									}
								}else {
									if (e.getSlot() > 35 && e.getSlot() < 45 || e.getSlot() > 49) {
										e.setCancelled(true);
										if (e.getSlot() == 53) {
											OfflineEditPlayer p = OfflineEditPlayer.getPlayer(words[1].replace(" ", ""));
											p.setInventory(p.getOfflineInventory());
											changer.sendMessage(p.getOfflinePlayer().getName() + "'s inventory saved!");
										}
									}
								}
								return;
							}
							if (invName.equals("EnderChest")) {
								if (e.getSlot() > 26) {
									e.setCancelled(true);
									if (e.getSlot() == 35) {
										OfflineEditPlayer p = OfflineEditPlayer.getPlayer(words[1].replace(" ", ""));
										p.setEnderItems(p.getOfflineEnderInventory());
										changer.sendMessage(p.getOfflinePlayer().getName() + "'s EnderChest saved!");
									}
								}
								return;
							}
							if (invName.equals("AbsorbationChanger")) {
								e.setCancelled(true);
								OfflineEditPlayer p = OfflineEditPlayer.getPlayer(words[1].replace(" ", ""));
								int absorbation = Integer.valueOf(ChatColor.stripColor(e.getClickedInventory().getItem(13).getItemMeta().getDisplayName()).split("\\s")[1]);
								switch (e.getSlot()) {
								case 4:
									e.getClickedInventory().setItem(13, new CustomStack(Material.GLOWSTONE_DUST).setName("§eAbsorbation§f: §6" + (absorbation + 1)).getItemStack());
									p.setAbsorbation(absorbation + 1);
									changer.sendMessage("Absorbation added (" + (absorbation + 1) + ")");
									break;
								case 22:
									e.getClickedInventory().setItem(13, new CustomStack(Material.GLOWSTONE_DUST).setName("§eAbsorbation§f: §6" + (absorbation - 1 > 0 ? absorbation - 1 : 0)).getItemStack());
									p.setAbsorbation(absorbation - 1 > 0 ? absorbation - 1 : 0);
									changer.sendMessage("Absorbation subtracted (" + (absorbation - 1 > 0 ? absorbation - 1 : 0) + ")");
									break;
								default:
									break;
								}
								return;
							}
							if (invName.equals("HealthChanger")) {
								e.setCancelled(true);
								OfflineEditPlayer p = OfflineEditPlayer.getPlayer(words[1].replace(" ", ""));
								int health = Integer.valueOf(ChatColor.stripColor(e.getClickedInventory().getItem(13).getItemMeta().getDisplayName()).split("\\s")[1]);
								switch (e.getSlot()) {
								case 4:
									e.getClickedInventory().setItem(13, new CustomStack(Material.REDSTONE).setName("§cHealth§f: §6" + (health + 1 < 20 ? health + 1 : 20)).getItemStack());
									p.setHealth(health + 1 < 20 ? health + 1 : 20);
									changer.sendMessage("Health added (" + (health + 1 < 20 ? health + 1 : 20) + ")");
									break;
								case 22:
									e.getClickedInventory().setItem(13, new CustomStack(Material.REDSTONE).setName("§cHealth§f: §6" + (health - 1 > 0 ? health - 1 : 0)).getItemStack());
									p.setHealth(health - 1 > 0 ? health - 1 : 0);
									changer.sendMessage("Absorbation subtracted (" + (health - 1 > 0 ? health - 1 : 0) + ")");
									break;
								default:
									break;
								}
								return;
							}
							if (invName.equals("FoodLevelChanger")) {
								e.setCancelled(true);
								OfflineEditPlayer p = OfflineEditPlayer.getPlayer(words[1].replace(" ", ""));
								int food = Integer.valueOf(ChatColor.stripColor(e.getClickedInventory().getItem(13).getItemMeta().getDisplayName()).split("\\s")[1]);
								switch (e.getSlot()) {
								case 4:
									e.getClickedInventory().setItem(13, new CustomStack(Material.COOKED_BEEF).setName("§eFoodLevel§f: §6" + (food + 1 < 20 ? food + 1 : 20)).getItemStack());
									p.setFoodLevel(food + 1 < 20 ? food + 1 : 20);
									changer.sendMessage("FoodLevel added (" + (food + 1 < 20 ? food + 1 : 20) + ")");
									break;
								case 22:
									e.getClickedInventory().setItem(13, new CustomStack(Material.COOKED_BEEF).setName("§cFoodLevel§f: §6" + (food - 1 > 0 ? food - 1 : 0)).getItemStack());
									p.setFoodLevel(food - 1 > 0 ? food - 1 : 0);
									changer.sendMessage("FoodLevel subtracted (" + (food - 1 > 0 ? food - 1 : 0) + ")");
									break;
								default:
									break;
								}
								return;
							}
							if (invName.equals("LevelChanger")) {
								e.setCancelled(true);
								OfflineEditPlayer p = OfflineEditPlayer.getPlayer(words[1].replace(" ", ""));
								int level = Integer.valueOf(ChatColor.stripColor(e.getClickedInventory().getItem(13).getItemMeta().getDisplayName()).split("\\s")[1]);
								switch (e.getSlot()) {
								case 4:
									e.getClickedInventory().setItem(13, new CustomStack(Material.EXP_BOTTLE).setName("§aLevel§f: §6" + (level + 1)).getItemStack());
									p.setLevel(level + 1);
									changer.sendMessage("FoodLevel added (" + (level + 1) + ")");
									break;
								case 22:
									e.getClickedInventory().setItem(13, new CustomStack(Material.EXP_BOTTLE).setName("§aLevel§f: §6" + (level - 1 > 0 ? level - 1 : 0)).getItemStack());
									p.setLevel(level - 1 > 0 ? level - 1 : 0);
									changer.sendMessage("FoodLevel subtracted (" + (level - 1 > 0 ? level - 1 : 0) + ")");
									break;
								default:
									break;
								}
								return;
							}
							if (invName.equals("GameModeChanger")) {
								e.setCancelled(true);
								OfflineEditPlayer p = OfflineEditPlayer.getPlayer(words[1].replace(" ", ""));
								switch (e.getSlot()) {
								case 4:
									e.getClickedInventory().setItem(13, new CustomStack(Material.BRICK).setName("§aCurrent GM§f: §e§lCREATIVE").getItemStack());
									p.setGameMode(GameMode.CREATIVE);
									changer.sendMessage("GameMode changed §7§l[§eCREATIVE§7§l]");
									break;
								case 12:
									e.getClickedInventory().setItem(13, new CustomStack(Material.GOLD_SWORD).setName("§aCurrent GM§f: §2§lSURVIVAL").getItemStack());
									p.setGameMode(GameMode.SURVIVAL);
									changer.sendMessage("GameMode changed §7§l[§2SURVIVAL§7§l]");
									break;
								case 14:
									e.getClickedInventory().setItem(13, new CustomStack(Material.STONE_PICKAXE).setName("§aCurrent GM§f: §b§lADVENTURE").getItemStack());
									p.setGameMode(GameMode.ADVENTURE);
									changer.sendMessage("GameMode changed §7§l[§bADVENTURE§7§l]");
									break;
								case 22:
									e.getClickedInventory().setItem(13, new CustomStack(Material.EYE_OF_ENDER).setName("§aCurrent GM§f: §5§lSPECTATOR").getItemStack());
									p.setGameMode(GameMode.SPECTATOR);
									changer.sendMessage("GameMode changed §7§l[§5SPECTATOR§7§l]");
									break;
								default:
									break;
								}
								return;
							}
							if (invName.equals("LocationChanger")) {
								e.setCancelled(true);
								if (e.getSlot() == 13) {
									OfflineEditPlayer p = OfflineEditPlayer.getPlayer(words[1].replace(" ", ""));
									p.setPosition(changer.getLocation());
									changer.sendMessage("§dnow §6" + p.getOfflinePlayer().getName() + "§f'§6s §dlocation§f: §7§l(§a" + changer.getWorld().getName() + "§f, §6" + changer.getLocation().getX() + "§f, §6" + changer.getLocation().getY() + "§f, §6" + changer.getLocation().getZ() + "§7§l)");
									Location loc = changer.getLocation();
									e.getClickedInventory().setItem(13, new CustomStack(Material.COMPASS).setName("§Click to set ur position").addStringInLore("§aCurrent Postition§f:").addStringInLore("§eX§f: §6" + (int)loc.getX()).addStringInLore("§eY§f: §6" + (int)loc.getY()).addStringInLore("§eZ§f: §6" + (int)loc.getZ()).getItemStack());
								}
								return;
							}
						}
					}
				}
			}
		}
	}
	
	private boolean hasPermission(Player p, String permission) {
		if (!p.hasPermission(permission)) {
			p.sendMessage(ChatColor.RED + "You do not have permission to use this");
			return false;
		}
		return true;
	}
	
}
