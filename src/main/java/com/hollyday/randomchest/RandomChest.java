package com.hollyday.randomchest;

import org.bukkit.plugin.java.JavaPlugin;

public final class RandomChest extends JavaPlugin {
    public static final String prefix = "§f[ §6RandomChest §f] ";
    private final RandomChestManager chestManager;

    public RandomChest() {
        this.chestManager = new RandomChestManager(this);
    }

    @Override
    public void onEnable() {
        getCommand("RandomChest").setExecutor(new RandomChestCommand(this.chestManager));
        getServer().getPluginManager().registerEvents(new RandomChestListener(this.chestManager), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
