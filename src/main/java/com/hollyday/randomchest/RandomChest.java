package com.hollyday.randomchest;

import org.bukkit.plugin.java.JavaPlugin;

public final class RandomChest extends JavaPlugin {
    private final RandomChestManager chestManager;

    public RandomChest() {
        this.chestManager = new RandomChestManager(this);
    }

    @Override
    public void onEnable() {
        getCommand("RandomChest").setExecutor(new RandomChestCommand(this.chestManager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
