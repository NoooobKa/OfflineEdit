package com.NBK.OfflineEditor.database;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import com.NBK.OfflineEditor.util.CustomStack;

public class PlayersDataBase {

	private static Map<String, UUID> players;
	private static Map<UUID, ItemStack> heads;
	
	
	static {
		players = loadPlayers();
		heads = loadHeads();
	}
	
	public static boolean contains(String name) {
		return PlayersDataBase.players.containsKey(name);
	}
	
	public static Map<String, UUID> getPlayers() {
		return PlayersDataBase.players;
	}
	
	public static Map<UUID, ItemStack> getHeads() {
		return PlayersDataBase.heads;
	}
	
	private static ConcurrentHashMap<String, UUID> loadPlayers(){
		ConcurrentHashMap<String, UUID> players = new ConcurrentHashMap<String, UUID>();
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			players.put(p.getName(), p.getUniqueId());
		}
		return players;
	}
	
	private static ConcurrentHashMap<UUID, ItemStack> loadHeads() {
		ConcurrentHashMap<UUID, ItemStack> heads = new ConcurrentHashMap<UUID, ItemStack>();
		PlayersDataBase.players.forEach((name, id) -> heads.put(id, CustomStack.getSkull(name).setName(ChatColor.GOLD + name).addStringInLore("§cUUID§f: " + id).getItemStack()));
		return heads;
	}
	
	public static void reload() {
		players = loadPlayers();
		heads = loadHeads();
		
	}
}
