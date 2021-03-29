package com.NBK.OfflineEditor.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.NBK.OfflineEditor.database.Database;
import com.NBK.OfflineEditor.database.InventoryBook;

public class OfflineEditCommand implements CommandExecutor{

	public OfflineEditCommand(JavaPlugin plugin) {
		plugin.getCommand("OfflineEdit").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (!p.hasPermission("oe.use")) {
				return true;
			}
			if (args.length == 0) {
				p.openInventory(Database.getPlayersHeadGUI().getFirstPage());
				return true;
			}else {
				if (args.length == 1) {
					InventoryBook ib = Database.createBook(args[0]);
					if (ib.size() != 0) {
						p.openInventory(ib.getFirstPage());
					}else {
						p.sendMessage(ChatColor.RED + "Players with " + ChatColor.GOLD + args[0] + ChatColor.RED + " in name not founded");
					}
					return true;
				}
			}
		}
		return false;
	}
	
}
