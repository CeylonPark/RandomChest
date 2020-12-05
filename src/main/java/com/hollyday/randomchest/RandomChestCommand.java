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
        if(!(sender instanceof Player)) {
            sender.sendMessage(RandomChest.prefix+"§cThis command is only available to player.");
            return true;
        }
        if(args.length < 1) {
            return false;
        }
        Player player = (Player) sender;
        switch(args[0]) {
            case "wand":
                player.getInventory().addItem(this.chestManager.getWand());
                sender.sendMessage(RandomChest.prefix+"지급되었습니다.");
                return true;
            case "run":
                this.chestManager.runRandomChest();
                sender.sendMessage("랜덤");
                return true;
            case "reset":

                return true;
            case "help":
                sender.sendMessage("");
                return true;
            case "item":
                break;
            default:
                return false;
        }
        if(args.length < 2) {
            return true;
            //help item
        }
        switch(args[1]) {
            case "set":
                sender.sendMessage("");
                break;
            case "":
                sender.sendMessage("d");
                break;
            default:
                break;
        }
        return true;
    }
}

/*
    /rc wand
    /rc run
    /rc reset
    /rc item

    /rc item set <amount>
    /rc item list
    /rc item remove <name>
    /rc item reset
    /rc item blank
 */