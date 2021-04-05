package com.NBK.OfflineEditor.util;

import java.util.LinkedList;

import org.bukkit.inventory.Inventory;

public class InventoryBook {

	private LinkedList<Inventory> pages;
	
	public InventoryBook() {
		this.pages = new LinkedList<>();
	}
	
	public void addPage(Inventory inv) {
		pages.add(inv);
	}
	
	public Inventory getFirstPage() {
		return pages.getFirst();
	}
	
	public int getPageIndex(Inventory page) {
		return pages.lastIndexOf(page);
	}
	
	public Inventory getPage(int index) {
		return pages.get(index);
	}
	
	public Inventory next(int index) {
		return getPage(index + 1);
	}
	
	public Inventory next(Inventory page) {
		return getPage(getPageIndex(page) + 1);
	}
	
	public Inventory back(int index) {
		return getPage(index - 1);
	}
	
	public Inventory back(Inventory page) {
		return getPage(getPageIndex(page) - 1);
	}
	
	public int size() {
		return pages.size();
	}
	
}
