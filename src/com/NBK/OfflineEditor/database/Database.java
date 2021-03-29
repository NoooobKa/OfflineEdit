package com.NBK.OfflineEditor.database;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.NBK.OfflineEditor.Inventory.InventoryManager;
import com.NBK.OfflineEditor.util.CustomStack;

public class Database {

	private static Database database;
	private InventoryBook playersBook;
	
	private Database() {
		this.playersBook = createBook();
	}
	
	static {
		database = new Database();
	}
	
	public static InventoryBook getPlayersHeadGUI() {
		return Database.database.playersBook;
	}
	
	private InventoryBook createBook() {
		InventoryBook ib = new InventoryBook();
		OfflinePlayer[] players = Bukkit.getOfflinePlayers();
		ItemStack NEXT =  new CustomStack(Material.ARROW).setName("§2§lNEXT").getItemStack();
		ItemStack BACK = new CustomStack(Material.ARROW).setName("§2§lBACK").getItemStack();
		int pages = (int) Math.ceil(players.length / 45.0);
		for (int i = 0; i < pages; i++) {
			Inventory inv = InventoryManager.createInventory(54, "§cOffline§2Edit §9§l| §cDatabase §7§l[§2" + (i + 1) + "§5/§2" + pages + "§7§l]");
			if (i == 0) {
				if (pages > 1)inv.setItem(53, NEXT);
			}else {
				if (i == pages - 1) {
					inv.setItem(45, BACK);
				}else {
					inv.setItem(45, BACK);
					inv.setItem(53, NEXT);
				}
			}
			int to = players.length - i * 45;
			to = to > 45 ? 45 : to;
			for (int j = 0; j < to; j++) {
				OfflinePlayer p = players[45 * i + j];
				inv.setItem(j, CustomStack.getSkull(p.getName()).setName(ChatColor.GOLD + p.getName()).addStringInLore("§aUUID: §c" + p.getUniqueId()).getItemStack());
			}
			ib.addPage(inv);
		}
		return ib;
	}
	
	public static InventoryBook createBook(String chars) {
		String soughtChars = chars.toLowerCase();
		InventoryBook ib = new InventoryBook();
		List<OfflinePlayer> soughtPlayers = new ArrayList<>();
		for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			if (player.getName().toLowerCase().contains(soughtChars)) {
				soughtPlayers.add(player);
			}
		}
		ItemStack NEXT =  new CustomStack(Material.ARROW).setName("§2§lNEXT").getItemStack();
		ItemStack BACK = new CustomStack(Material.ARROW).setName("§2§lBACK").getItemStack();
		int pages = (int) Math.ceil(soughtPlayers.size() / 45.0);
		for (int i = 0; i < pages; i++) {
			Inventory inv = InventoryManager.createInventory(54, "§cOffline§2Edit §9§l| §cDatabase §7§l[§2" + (i + 1) + "§5/§2" + pages + "§7§l]");
			if (i == 0) {
				if (pages > 1)inv.setItem(53, NEXT);
			}else {
				if (i == pages - 1) {
					inv.setItem(45, BACK);
				}else {
					inv.setItem(45, BACK);
					inv.setItem(53, NEXT);
				}
			}
			int to = soughtPlayers.size() - i * 45;
			to = to > 45 ? 45 : to;
			for (int j = 0; j < to; j++) {
				OfflinePlayer p = soughtPlayers.get(45 * i + j);
				inv.setItem(j, CustomStack.getSkull(p.getName()).setName(ChatColor.GOLD + p.getName()).addStringInLore("§aUUID: §c" + p.getUniqueId()).getItemStack());
			}
			ib.addPage(inv);
		}
		return ib;
	}
	
	public static void reload() {
		Database.database = new Database();
	}
	
}
