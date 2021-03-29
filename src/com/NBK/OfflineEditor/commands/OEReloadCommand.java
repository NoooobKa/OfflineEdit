package com.NBK.OfflineEditor.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.NBK.OfflineEditor.database.Database;
import com.NBK.OfflineEditor.database.PlayersDataBase;

public class OEReloadCommand implements CommandExecutor{

	public OEReloadCommand(JavaPlugin p) {
		p.getCommand("OEreload").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		PlayersDataBase.reload();
		Database.reload();
		return true;
	}

}
