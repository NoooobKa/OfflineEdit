package com.NBK.OfflineEditor.util;

import org.bukkit.Bukkit;

public final class VersionUtils {

	public static String getVersion() {
		String version = null;
		try {
			version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return version;
	}
	
	public static boolean isOldVersion() {
		return Integer.valueOf(getVersion().split("\\_")[1]) == 8;
	}
	
	public static boolean isAboveThirteen() {
		return Integer.valueOf(getVersion().split("\\_")[1]) > 13;
	}
	
}
