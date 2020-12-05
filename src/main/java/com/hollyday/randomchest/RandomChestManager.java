package com.hollyday.randomchest;

import com.hollyday.randomchest.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class RandomChestManager {
    private final Plugin plugin;
    private final ItemStack wand;
    private final Location[] poses;

    public RandomChestManager(Plugin plugin) {
        this.plugin = plugin;
        this.wand = new ItemBuilder(Material.STICK).setDisplayName("§d훌륭한 막대기").build();
        this.poses = new Location[2];
    }

    public void setPos(Location location, int pos) {
        if(location != null && (pos == 0 || pos == 1)) {
            this.poses[pos] = location;
        }
    }

    public void run() {
        Vector max = Vector.getMaximum(this.poses[0].toVector(), this.poses[1].toVector());
        Vector min = Vector.getMinimum(this.poses[0].toVector(), this.poses[1].toVector());
        int amount = 0;
        for (int i = min.getBlockX(); i <= max.getBlockX();i++) {
            for (int j = min.getBlockY(); j <= max.getBlockY(); j++) {
                for (int k = min.getBlockZ(); k <= max.getBlockZ();k++) {
                    Block block = this.poses[0].getWorld().getBlockAt(i,j,k);
                    if(block.getState() instanceof Chest) {
                        amount++;
                        Bukkit.getServer().broadcastMessage(((Chest) block.getState()).getInventory().getLocation().toString()+"");
                    }
                }
            }
        }
        Bukkit.getServer().broadcastMessage(""+amount);
    }

    public ItemStack getWand() {
        return new ItemStack(this.wand);
    }
}
