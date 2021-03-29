package com.NBK.OfflineEditor.util;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public final class Util {
	
    public static File getPlayerFile(final OfflinePlayer player) {
        final File wDir = Bukkit.getWorlds().get(0).getWorldFolder();
        final UUID uuid = player.getUniqueId();
        final File playerDir = new File(wDir, "playerdata");
        return new File(playerDir, uuid + ".dat");
    }
    
}
