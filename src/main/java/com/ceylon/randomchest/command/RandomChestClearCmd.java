package com.ceylon.randomchest.command;

import com.ceylon.randomchest.RandomChestManager;
import com.ceylon.randomchest.RandomChestPlugin;
import com.ceylon.randomchest.api.SubCommand;
import com.ceylon.randomchest.util.MsgUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class RandomChestClearCmd extends SubCommand {
    private final RandomChestManager chestManager;

    public RandomChestClearCmd(Plugin plugin, String command, RandomChestManager chestManager) {
        super(plugin, command);
        this.chestManager = chestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        if(args.size() != 0) {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "§c시용 방법이 잘못되었습니다. §f(Usage: /랜덤상자 초기화");
            return true;
        }
        if(this.chestManager.clearRandomChest()) {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "§aSuccess");
        } else {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "§cFailure");
        }
        return true;
    }
}
