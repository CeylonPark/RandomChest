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
            sender.sendMessage("플레이어 전용 명령어");
            return true;
        }

        if(args.length < 1) {
            sender.sendMessage("도움말");
            return false;
        }

        Player player = (Player) sender;

        switch (args[0]) {
            case "wand":
                player.getInventory().addItem(this.chestManager.getWand());
                sender.sendMessage(RandomChest.prefix+"지급되었습니다.");
                break;
            case "setitem":
                player.getInventory().getItemInMainHand();
                break;
            case "start":
                this.chestManager.run();
                break;
            default:
                sender.sendMessage("도움말");
                //도움말
                break;
        }
        return true;
    }
}

/*
    /rc wand
    /rc run
    /rc reset

    /rc setitem <amount>
    /rc list
    /rc remove <number>
    /rc resetitem
    /rc setblank
 */