package com.hollyday.randomchest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomChestCommand implements CommandExecutor {
    private final RandomChestManager chestManager;

    public RandomChestCommand(RandomChestManager chestManager) {
        this.chestManager = chestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equals("setitem")) {
            Player player = (Player) sender;
            player.getInventory().getItemInMainHand();
        }
        return true;
    }
}
