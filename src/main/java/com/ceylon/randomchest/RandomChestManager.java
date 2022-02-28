package com.ceylon.randomchest;

import com.ceylon.randomchest.util.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.*;

public class RandomChestManager {
    private final Plugin plugin;
    private final ItemStack wand;
    private final Location[] poses;
    private final Map<String, ItemStack> items;
    private final Map<String, Integer> itemsCounts;
    private ItemStack blank;

    public RandomChestManager(Plugin plugin) {
        this.plugin = plugin;
        this.wand = new ItemBuilder(Material.STICK).setDisplayName("§dRandom Chest Wand").build();
        this.poses = new Location[2];
        this.items = new HashMap<>();
        this.itemsCounts = new HashMap<>();
    }

    public ItemStack getWand() {
        return new ItemStack(this.wand);
    }
    public void setPos(Location location, int pos) {
        if(location != null && (pos == 0 || pos == 1)) {
            this.poses[pos] = location;
        }
    }
    public void setItems(String name, ItemStack itemStack, int amount) {
        this.items.put(name, new ItemBuilder(itemStack, 1).build());
        this.itemsCounts.put(name, amount);
    }
    public boolean removeItems(String name) {
        return this.items.remove(name) != null | this.itemsCounts.remove(name) != null;
    }
    public void clearItems() {
        this.items.clear();
        this.itemsCounts.clear();
        this.blank = null;
    }
    public String[] getItemsToStrings() {
        String[] items = new String[this.items.size()];
        int i = 0;
        for(String name : this.items.keySet()) {
            items[i] = name + " : " + this.itemsCounts.get(name);
            i++;
        }
        return items;
    }
    public void setBlank(ItemStack itemStack) {
        this.blank = itemStack;
    }
    public boolean runRandomChest() {
        List<Inventory> inventories = new ArrayList<>(this.getChestInventories());
        if(inventories.size() < this.getMaxItemAmount()) {
            return false;
        }
        this.clearRandomChest();
        Collections.shuffle(inventories);
        int count = 0;
        for(String string : this.itemsCounts.keySet()) {
            for(int i = 0; i < this.itemsCounts.get(string); i++) {
                inventories.get(count++).addItem(this.items.get(string));
            }
        }
        if(this.blank == null || this.blank.getType() == Material.AIR) {
            return true;
        }
        for(int i = count; i < inventories.size(); i++) {
            inventories.get(i).addItem(this.blank);
        }
        return true;
    }
    public boolean clearRandomChest() {
        Set<Inventory> inventories = this.getChestInventories();
        for(Inventory inventory : inventories) {
            inventory.clear();
        }
        return true;
    }
    private boolean isSetPoses() {
        return this.poses[0] != null && this.poses[1] != null
                && this.poses[0].getWorld().equals(this.poses[1].getWorld());
    }
    private int getMaxItemAmount() {
        int amount = 0;
        for(String string : this.itemsCounts.keySet()) {
            amount += this.itemsCounts.get(string);
        }
        return amount;
    }
    private Set<Inventory> getChestInventories() {
        Set<Inventory> inventories = new HashSet<>();
        if(!isSetPoses()) {
            return inventories;
        }
        Map<Location, Inventory> inventoryMap = new HashMap<>();
        World world = this.poses[0].getWorld();
        Vector max = Vector.getMaximum(this.poses[0].toVector(), this.poses[1].toVector());
        Vector min = Vector.getMinimum(this.poses[0].toVector(), this.poses[1].toVector());
        for (int i = min.getBlockX(); i <= max.getBlockX();i++) {
            for (int j = min.getBlockY(); j <= max.getBlockY(); j++) {
                for (int k = min.getBlockZ(); k <= max.getBlockZ();k++) {
                    Block block = world.getBlockAt(i,j,k);
                    if(block.getState() instanceof Chest) {
                        Inventory inventory =((Chest) block.getState()).getInventory();
                        inventoryMap.put(inventory.getLocation(), inventory);
                    }
                }
            }
        }
        for(Location location : inventoryMap.keySet()) {
            inventories.add(inventoryMap.get(location));
        }
        return inventories;
    }
}
