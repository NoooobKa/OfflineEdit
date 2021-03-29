package com.NBK.OfflineEditor.main;

import org.bukkit.plugin.java.JavaPlugin;

import com.NBK.OfflineEditor.Listeners.OEListener;
import com.NBK.OfflineEditor.commands.OEReloadCommand;
import com.NBK.OfflineEditor.commands.OfflineEditCommand;

public class OfflineEditor extends JavaPlugin{

	public static OfflineEditor INSTANCE;
	
	public void onEnable() {
		INSTANCE = this;
		new OEListener(this);
		new OfflineEditCommand(this);
		new OEReloadCommand(this);
	}
	
	public void onDisable() {
		
	}
	
}
