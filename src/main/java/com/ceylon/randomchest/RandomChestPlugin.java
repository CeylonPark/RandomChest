package com.ceylon.randomchest;

import com.ceylon.randomchest.command.RandomChestCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomChestPlugin extends JavaPlugin {
    public static final String prefix = "§f[ §6랜덤상자 §f] ";
    private final RandomChestManager chestManager;

    public RandomChestPlugin() {
        this.chestManager = new RandomChestManager(this);
    }

    @Override
    public void onEnable() {
        getCommand("랜덤상자").setExecutor(new RandomChestCommand(this, "랜덤상자", this.chestManager));
        getServer().getPluginManager().registerEvents(new RandomChestListener(this.chestManager), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
