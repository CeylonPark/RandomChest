package com.ceylon.randomchest.command;

import com.ceylon.randomchest.RandomChestManager;
import com.ceylon.randomchest.RandomChestPlugin;
import com.ceylon.randomchest.api.SubCommand;
import com.ceylon.randomchest.util.MsgUtil;
import com.ceylon.randomchest.util.NumberFormat;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class RandomChestSetCmd extends SubCommand {
    private final RandomChestManager chestManager;

    public RandomChestSetCmd(Plugin plugin, String command, RandomChestManager chestManager) {
        super(plugin, command);
        this.chestManager = chestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        if(!(sender instanceof Player)) {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "플레이어만 사용 가능한 명령어입니다.");
            return true;
        }
        if(args.size() != 2) {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "§c시용 방법이 잘못되었습니다. §f(Usage: /랜덤상자 설정 <이름> <수량>");
            return true;
        }
        ItemStack itemStack = ((Player) sender).getInventory().getItemInMainHand();
        if(itemStack.getType() == Material.AIR) {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "§cYou are not holding an item!");
            return true;
        }
        int percent = NumberFormat.parseInt(args.get(1), 0);
        if(percent < 1) {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "§c<수량>은 자연수이여야 합니다.");
        }
        this.chestManager.setItems(args.get(0), itemStack, percent);
        MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "§aSet Item");
        return true;
    }
}
