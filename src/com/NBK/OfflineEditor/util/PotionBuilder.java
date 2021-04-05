package com.NBK.OfflineEditor.util;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("deprecation")
public class PotionBuilder {

	private int id;
	private int duration;
	private int amplifier;
	private boolean ambient;
	private boolean particle;
	
	public PotionBuilder(PotionEffect effect) {
		this.id = effect.getType().getId();
		this.duration = effect.getDuration();
		this.amplifier = effect.getAmplifier();
		this.ambient = effect.isAmbient();
		this.particle = effect.hasParticles();
	}
	
	public PotionBuilder(int id, int duration, int amplifier, boolean ambient, boolean particle) {
		this.id = id;
		this.duration = duration;
		this.amplifier = amplifier;
		this.ambient = ambient;
		this.setParticle(particle);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getAmplifier() {
		return amplifier;
	}

	public void setAmplifier(int amplifier) {
		this.amplifier = amplifier;
	}

	public boolean isAmbient() {
		return ambient;
	}

	public void setAmbient(boolean ambient) {
		this.ambient = ambient;
	}
	
	public boolean isParticle() {
		return particle;
	}

	public void setParticle(boolean particle) {
		this.particle = particle;
	}
	
	public ItemStack toItemStack() {
		return new CustomStack(Material.POTION).setName("OfflinePotion").
				addStringInLore("§fID: " + id + " §c§l" + PotionEffectType.getById(id).getName()).
				addStringInLore("§fDuration: " + (duration / 20)).
				addStringInLore("§fLevel: " + (amplifier + 1)).
				addStringInLore("§fAmbient: " + ambient).
				addStringInLore("§fParticles: " + particle).
				getItemStack();
	}
	
	public Object toMobEffect() {
		switch (VersionUtils.getVersion()) {
		case "v1_8_R1":
			return new net.minecraft.server.v1_8_R1.MobEffect(id, duration, amplifier, particle, ambient);
		case "v1_8_R2":
			return new net.minecraft.server.v1_8_R2.MobEffect(id, duration, amplifier, particle, ambient);
		case "v1_8_R3":
			return new net.minecraft.server.v1_8_R3.MobEffect(id, duration, amplifier, particle, ambient);
		case "v1_9_R1":
			return new net.minecraft.server.v1_9_R1.MobEffect(net.minecraft.server.v1_9_R1.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		case "v1_9_R2":
			return new net.minecraft.server.v1_9_R2.MobEffect(net.minecraft.server.v1_9_R2.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		case "v1_10_R1":
			return new net.minecraft.server.v1_10_R1.MobEffect(net.minecraft.server.v1_10_R1.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		case "v1_11_R1":
			return new net.minecraft.server.v1_11_R1.MobEffect(net.minecraft.server.v1_11_R1.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		case "v1_12_R1":
			return new net.minecraft.server.v1_12_R1.MobEffect(net.minecraft.server.v1_12_R1.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		case "v1_13_R1":
			return new net.minecraft.server.v1_13_R1.MobEffect(net.minecraft.server.v1_13_R1.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		case "v1_13_R2":
			return new net.minecraft.server.v1_13_R2.MobEffect(net.minecraft.server.v1_13_R2.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		case "v1_14_R1":
			return new net.minecraft.server.v1_14_R1.MobEffect(net.minecraft.server.v1_14_R1.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		case "v1_15_R1":
			return new net.minecraft.server.v1_15_R1.MobEffect(net.minecraft.server.v1_15_R1.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		case "v1_16_R1":
			return new net.minecraft.server.v1_16_R1.MobEffect(net.minecraft.server.v1_16_R1.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		case "v1_16_R2":
			return new net.minecraft.server.v1_16_R2.MobEffect(net.minecraft.server.v1_16_R2.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		case "v1_16_R3":
			return new net.minecraft.server.v1_16_R3.MobEffect(net.minecraft.server.v1_16_R3.MobEffectList.fromId(id), duration, amplifier, particle, ambient);
		default:
			return null;
		}
	}
	
	public PotionEffect toPotionEffect() {
		return new PotionEffect(PotionEffectType.getById(id), duration, amplifier, ambient, particle);
	}
	
	public static PotionBuilder fromItemStack(ItemStack item) {
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("OfflinePotion")) {
			List<String> lore = item.getItemMeta().getLore();
			int id = 0;
			int duration = 0;
			int level = 0;
			boolean ambient = false;
			boolean particles = false;
			for (String s : lore) {
				String[] ss = s.split("\\s");
				String field = ChatColor.stripColor(ss[0].replace(":", ""));
				switch (field) {
				case "ID":
					id = Integer.valueOf(ss[1]);
					break;
				case "Duration":
					duration = Integer.valueOf(ss[1]) * 20;
					break;
				case "Level":
					level = Integer.valueOf(ss[1]) - 1;
					break;
				case "Ambient":
					ambient = Boolean.valueOf(ss[1]);
					break;
				case "Particles":
					particles = Boolean.valueOf(ss[1]);
					break;
				default:
					break;
				}
			}
			return new PotionBuilder(id, duration, level, ambient, particles);
		}
		return null;
	}
	
}
