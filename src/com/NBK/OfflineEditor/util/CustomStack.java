package com.NBK.OfflineEditor.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CustomStack {

	private ItemStack is;
	
	public CustomStack(ItemStack is) {
		this.is = is;
	}
	
	public CustomStack(Material m) {
		this.is = new ItemStack(m);
	}
	
	public ItemStack getItemStack() {
		return is;
	}
	
	public CustomStack setType(Material m) {
		getItemStack().setType(m);
		return this;
	}
	
	public CustomStack setName(String name) {
		ItemMeta m = getMeta();
		m.setDisplayName(name);
		setMeta(m);
		return this;
		
	}
	
	public CustomStack setLore(List<String> lore) {
		ItemMeta m = getMeta();
		m.setLore(lore);
		getItemStack().setItemMeta(m);
		return this;
	}
	
	public CustomStack addStringInLoreAbove(String string) {
		List<String> lore = getLore();
		lore.add(0, string);
		setLore(lore);
		return this;
	}
	
	public CustomStack addStringInLore(String string) {
		List<String> lore = getLore();
		lore.add(string);
		setLore(lore);
		return this;
	}
	
	private List<String> getLore() {
		return getMeta().getLore() == null ? new ArrayList<>() : getMeta().getLore();
	}
	
	private ItemMeta getMeta() {
		return getItemStack().getItemMeta();
	}
	
	private void setMeta(ItemMeta meta) {
		getItemStack().setItemMeta(meta);
	}
	
	public CustomStack enchant(Enchantment ench, int level) {
		getItemStack().addEnchantment(ench, level);
		return this;
	}
	
	public CustomStack setAmount(int amount) {
		getItemStack().setAmount(amount);
		return this;
	}
	
	public CustomStack setDurablity(short d) {
		getItemStack().setDurability(d);
		return this;
	}
	
	public CustomStack addItemFlag(ItemFlag flag) {
		ItemMeta m = getMeta();
		m.addItemFlags(flag);
		setMeta(m);
		return this;
	}

	public String getFirstLoreWhere(String arg) {
		for (String s : getLore()) {
			if (s.contains(arg)) {
				return s;
			}
		}
		return null;
	}
	
    @Nonnull
    public static CustomStack getSkull(String name) {
        CustomStack head = new CustomStack(Material.SKULL_ITEM).setDurablity((short)3);
        SkullMeta meta = (SkullMeta) head.getMeta();
        meta.setOwner(name);
        head.getItemStack().setItemMeta(meta);
        return head;
    }
	
}
