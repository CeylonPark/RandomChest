package com.ceylon.randomchest.command;

import com.ceylon.randomchest.RandomChestManager;
import com.ceylon.randomchest.RandomChestPlugin;
import com.ceylon.randomchest.api.SubCommand;
import com.ceylon.randomchest.util.MsgUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class RandomChestWandCmd extends SubCommand {
    private final RandomChestManager chestManager;

    public RandomChestWandCmd(Plugin plugin, String command, RandomChestManager chestManager) {
        super(plugin, command);
        this.chestManager = chestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        if(!(sender instanceof Player)) {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "플레이어만 사용 가능한 명령어입니다.");
            return true;
        }
        if(args.size() != 0) {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "§c시용 방법이 잘못되었습니다. §f(Usage: /랜덤상자 완드");
            return true;
        }
        ((Player) sender).getInventory().addItem(this.chestManager.getWand());
        MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "§dLeft click: select pos #1: Right click: select pos #2");
        return true;
    }
}
