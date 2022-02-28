package com.ceylon.randomchest.command;

import com.ceylon.randomchest.RandomChestManager;
import com.ceylon.randomchest.RandomChestPlugin;
import com.ceylon.randomchest.api.SubCommand;
import com.ceylon.randomchest.util.MsgUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class RandomChestListCmd extends SubCommand {
    private final RandomChestManager chestManager;

    public RandomChestListCmd(Plugin plugin, String command, RandomChestManager chestManager) {
        super(plugin, command);
        this.chestManager = chestManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        if(args.size() != 0) {
            MsgUtil.sendMsg(sender, RandomChestPlugin.prefix + "§c시용 방법이 잘못되었습니다. §f(Usage: /랜덤상자 목록");
            return true;
        }
        MsgUtil.sendMsg(sender, this.chestManager.getItemsToStrings());
        return true;
    }
}
