package com.NBK.OfflineEditor.Inventory.versions.v1_9_R2;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;

import net.minecraft.server.v1_9_R2.*;

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
	    private final ItemStack[] items;
	    
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

	      this.items = new ItemStack[size];
	      this.title = title;
	      this.viewers = new ArrayList<HumanEntity>();
	      this.owner = owner;
	      this.type = InventoryType.CHEST;
	    }
	    
	    public int getSize() {
	      return this.items.length;
	    }
	    
	    public ItemStack getItem(int i) {
	      return this.items[i];
	    }
	    
	    public ItemStack splitStack(int i, int j) {
	      ItemStack result, stack = getItem(i);
	      if (stack == null)
	        return null; 
	      if (stack.count <= j) {
	        setItem(i, null);
	        result = stack;
	      } else {
	        result = CraftItemStack.copyNMSStack(stack, j);
	        stack.count -= j;
	      } 
	      update();
	      return result;
	    }
	    
	    public ItemStack splitWithoutUpdate(int i) {
	      ItemStack result, stack = getItem(i);
	      if (stack == null)
	        return null; 
	      if (stack.count <= 1) {
	        setItem(i, null);
	        result = stack;
	      } else {
	        result = CraftItemStack.copyNMSStack(stack, 1);
	        stack.count--;
	      } 
	      return result;
	    }
	    
	    public void setItem(int i, ItemStack itemstack) {
	      this.items[i] = itemstack;
	      if (itemstack != null && getMaxStackSize() > 0 && itemstack.count > getMaxStackSize())
	        itemstack.count = getMaxStackSize(); 
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
	    
	    public ItemStack[] getContents() {
	      return this.items;
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
	    
	    public void b(int i, int i1) {}
	    
	    public int g() {
	      return 0;
	    }
	    
	    public void l() {}
	    
	    public String getName() {
	      return this.title;
	    }
	    
	    public boolean hasCustomName() {
	      return (this.title != null);
	    }
	    
	    public IChatBaseComponent getScoreboardDisplayName() {
	      return (IChatBaseComponent)new ChatComponentText(this.title);
	    }

		@Override
		public Location getLocation() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setProperty(int arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
	  }
	}