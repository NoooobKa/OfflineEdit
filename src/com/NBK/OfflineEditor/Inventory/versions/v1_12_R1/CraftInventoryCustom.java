package com.NBK.OfflineEditor.Inventory.versions.v1_12_R1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;

import net.minecraft.server.v1_12_R1.*;

public class CraftInventoryCustom extends CraftInventory {
	  public CraftInventoryCustom(InventoryHolder owner, InventoryType type) {
	    super(new MinecraftInventory(owner, type));
	  }
	  
	  public CraftInventoryCustom(InventoryHolder owner, InventoryType type, String title) {
	    super(new MinecraftInventory(owner, type, title));
	  }
	  
	  public CraftInventoryCustom(InventoryHolder owner, int size) {
	    super(new MinecraftInventory(owner, size));
	  }
	  
	  public CraftInventoryCustom(InventoryHolder owner, int size, String title) {
	    super(new MinecraftInventory(owner, size, title));
	  }
	  
	  static class MinecraftInventory implements IInventory {
	    private final NonNullList<ItemStack> items;
	    
	    private int maxStack = 64;
	    
	    private final List<HumanEntity> viewers;
	    
	    private final String title;
	    
	    private InventoryType type;
	    
	    private final InventoryHolder owner;
	    
	    public MinecraftInventory(InventoryHolder owner, InventoryType type) {
	      this(owner, type.getDefaultSize(), type.getDefaultTitle());
	      this.type = type;
	    }
	    
	    public MinecraftInventory(InventoryHolder owner, InventoryType type, String title) {
	      this(owner, type.getDefaultSize(), title);
	      this.type = type;
	    }
	    
	    public MinecraftInventory(InventoryHolder owner, int size) {
	      this(owner, size, "Chest");
	    }
	    
	    public MinecraftInventory(InventoryHolder owner, int size, String title) {
	      Validate.notNull(title, "Title cannot be null");
	      this.items = NonNullList.a(size, ItemStack.a);
	      this.title = title;
	      this.viewers = new ArrayList<>();
	      this.owner = owner;
	      this.type = InventoryType.CHEST;
	    }
	    
	    public int getSize() {
	      return this.items.size();
	    }
	    
	    public ItemStack getItem(int i) {
	      return (ItemStack)this.items.get(i);
	    }
	    
	    public ItemStack splitStack(int i, int j) {
	      ItemStack result, stack = getItem(i);
	      if (stack == ItemStack.a)
	        return stack; 
	      if (stack.getCount() <= j) {
	        setItem(i, ItemStack.a);
	        result = stack;
	      } else {
	        result = CraftItemStack.copyNMSStack(stack, j);
	        stack.subtract(j);
	      } 
	      update();
	      return result;
	    }
	    
	    public ItemStack splitWithoutUpdate(int i) {
	      ItemStack result, stack = getItem(i);
	      if (stack == ItemStack.a)
	        return stack; 
	      if (stack.getCount() <= 1) {
	        setItem(i, null);
	        result = stack;
	      } else {
	        result = CraftItemStack.copyNMSStack(stack, 1);
	        stack.subtract(1);
	      } 
	      return result;
	    }
	    
	    public void setItem(int i, ItemStack itemstack) {
	      this.items.set(i, itemstack);
	      if (itemstack != ItemStack.a && getMaxStackSize() > 0 && itemstack.getCount() > getMaxStackSize())
	        itemstack.setCount(getMaxStackSize()); 
	    }
	    
	    public int getMaxStackSize() {
	      return this.maxStack;
	    }
	    
	    public void setMaxStackSize(int size) {
	      this.maxStack = size;
	    }
	    
	    public void update() {}
	    
	    public boolean a(EntityHuman entityhuman) {
	      return true;
	    }
	    
	    public List<ItemStack> getContents() {
	      return (List<ItemStack>)this.items;
	    }
	    
	    public void onOpen(CraftHumanEntity who) {
	      this.viewers.add(who);
	    }
	    
	    public void onClose(CraftHumanEntity who) {
	      this.viewers.remove(who);
	    }
	    
	    public List<HumanEntity> getViewers() {
	      return this.viewers;
	    }
	    
	    public InventoryType getType() {
	      return this.type;
	    }
	    
	    public InventoryHolder getOwner() {
	      return this.owner;
	    }
	    
	    public boolean b(int i, ItemStack itemstack) {
	      return true;
	    }
	    
	    public void startOpen(EntityHuman entityHuman) {}
	    
	    public void closeContainer(EntityHuman entityHuman) {}
	    
	    public int getProperty(int i) {
	      return 0;
	    }
	    
	    public void setProperty(int i, int j) {}
	    
	    public int h() {
	      return 0;
	    }
	    
	    public void clear() {
	      this.items.clear();
	    }
	    
	    public String getName() {
	      return this.title;
	    }
	    
	    public boolean hasCustomName() {
	      return (this.title != null);
	    }
	    
	    public IChatBaseComponent getScoreboardDisplayName() {
	      return (IChatBaseComponent)new ChatComponentText(this.title);
	    }
	    
	    public Location getLocation() {
	      return null;
	    }
	    
	    public boolean x_() {
	      ItemStack itemstack;
	      Iterator<ItemStack> iterator = this.items.iterator();
	      do {
	        if (!iterator.hasNext())
	          return true; 
	        itemstack = iterator.next();
	      } while (itemstack.isEmpty());
	      return false;
	    }
	  }
	}