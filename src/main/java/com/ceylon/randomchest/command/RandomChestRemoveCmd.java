package com.ceylon.randomchest.command;

import com.ceylon.randomchest.RandomChestManager;
import com.ceylon.randomchest.RandomChestPlugin;
import com.ceylon.randomchest.api.SubCommand;
import com.ceylon.randomchest.util.MsgUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class RandomChestRemoveCmd extends SubCommand {
    private final RandomChestManager chestManager;

    public RandomChestRemoveCmd(Plugin plugin, String command, RandomChestManager chestManager) {
        super(plugin, command);
        this.chestManager = chestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        if(args.size() != 1) {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "§c시용 방법이 잘못되었습니다. §f(Usage: /랜덤상자 제거 <이름>");
            return true;
        }
        if(this.chestManager.removeItems(args.get(0))) {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix+"§aRemove item");
        } else {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix+"§cNot Found");
        }
        return true;
    }
}
