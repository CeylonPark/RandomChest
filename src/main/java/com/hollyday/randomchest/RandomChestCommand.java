package com.hollyday.randomchest;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RandomChestCommand implements CommandExecutor {
    private static final String[] help = {
            RandomChest.prefix+"§6Usage: /rc ...",
            "  §d-> §f/rc wand",
            "  §d-> §f/rc run",
            "  §d-> §f/rc clear",
            "  §6Usage: /rc item ...",
            "    §d-> §f/rc item set <name> <amount>",
            "    §d-> §f/rc item remove <name>",
            "    §d-> §f/rc item list",
            "    §d-> §f/rc item blank",
            "    §d-> §f/rc item clear"
    };
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
                sender.sendMessage(RandomChest.prefix+"§dLeft click: select pos #1: Right click: select pos #2");
                return true;
            case "run":
                if(this.chestManager.runRandomChest()) {
                    sender.sendMessage(RandomChest.prefix+"§aSuccess");
                } else {
                    sender.sendMessage(RandomChest.prefix+"§cFailure");
                }
                return true;
            case "clear":
                if(this.chestManager.clearRandomChest()) {
                    sender.sendMessage(RandomChest.prefix+"§aSuccess");
                } else {
                    sender.sendMessage(RandomChest.prefix+"§cFailure");
                }
                return true;
            case "help":
                sender.sendMessage(RandomChestCommand.help);
                return true;
            case "item":
                break;
            default:
                return false;
        }
        if(args.length < 2) {
            return false;
        }
        switch(args[1]) {
            case "set":
                if(args.length != 4) {
                    sender.sendMessage(RandomChest.prefix+"Usage: /randomchest item set <name> <amount>");
                    return true;
                }
                ItemStack itemStack = this.getPlayerTool(player);
                if(itemStack.getType() == Material.AIR) {
                    sender.sendMessage(RandomChest.prefix+"§cYou are not holding an item!");
                    return true;
                }
                try {
                    this.chestManager.setItems(args[2], itemStack, Math.max(Integer.parseInt(args[3]), 0));
                    sender.sendMessage(RandomChest.prefix+"§aSet Item");
                } catch(NumberFormatException e) {
                    sender.sendMessage(RandomChest.prefix+"Usage: /randomchest item set <name> <amount>");
                    return true;
                }
                return true;
            case "remove":
                if(args.length != 3) {
                    sender.sendMessage(RandomChest.prefix+"Usage: /randomchest item remove <name>");
                    return true;
                }
                if(this.chestManager.removeItems(args[2])) {
                    sender.sendMessage(RandomChest.prefix+"§aRemove item");
                } else {
                    sender.sendMessage(RandomChest.prefix+"§cNot Found");
                }
                return true;
            case "list":
                sender.sendMessage(this.chestManager.getItemsToStrings());
                return true;
            case "blank":
                ItemStack blank = this.getPlayerTool(player);
                this.chestManager.setBlank(blank);
                sender.sendMessage(RandomChest.prefix+"§aSet Blank Item");
                return true;
            case "clear":
                this.chestManager.clearItems();
                sender.sendMessage(RandomChest.prefix+"§aClear!");
                return true;
            default:
                return false;
        }
    }

    private ItemStack getPlayerTool(Player player) {
        return player.getInventory().getItemInMainHand();
    }
}