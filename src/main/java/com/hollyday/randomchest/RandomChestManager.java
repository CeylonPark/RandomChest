package com.hollyday.randomchest;

import com.hollyday.randomchest.util.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RandomChestManager {
    private final Plugin plugin;
    private final ItemStack wand;
    private final Location[] poses;
    private final Map<ItemStack, Integer> items;
    private ItemStack blank;

    public RandomChestManager(Plugin plugin) {
        this.plugin = plugin;
        this.wand = new ItemBuilder(Material.STICK).setDisplayName("§d훌륭한 막대기").build();
        this.poses = new Location[2];
        this.items = new HashMap<>();
    }

    public ItemStack getWand() {
        return new ItemStack(this.wand);
    }
    public void setPos(Location location, int pos) {
        if(location != null && (pos == 0 || pos == 1)) {
            this.poses[pos] = location;
        }
    }
    public void setItems(ItemStack itemStack, int amount) {
        this.items.put(itemStack, amount);
    }
    public void remove(String displayName) {

    }
    public String[] getItemsToString() {
        return null;
    }

    public void runRandomChest() {
        if(this.poses[0] == null || this.poses[1] == null) {
            return;
        }
        if(!this.poses[0].getWorld().equals(this.poses[1].getWorld())) {
            return;
        }
        Set<Inventory> inventories = this.getChestInventories();
        if(inventories.size() < this.getMaxItemAmount()) {
            return;
        }

        System.out.println("시작");
    }
    private int getMaxItemAmount() {
        int amount = 0;
        for(ItemStack itemStack : this.items.keySet()) {
            amount += this.items.get(itemStack);
        }
        return amount;
    }
    private Set<Inventory> getChestInventories() {
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
        Set<Inventory> inventories = new HashSet<>();
        for(Location location : inventoryMap.keySet()) {
            inventories.add(inventoryMap.get(location));
        }
        return inventories;
    }
}
