package com.ceylon.randomchest.command;

import com.ceylon.randomchest.RandomChestManager;
import com.ceylon.randomchest.RandomChestPlugin;
import com.ceylon.randomchest.api.CommandConstructor;
import com.ceylon.randomchest.util.MsgUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class RandomChestCommand extends CommandConstructor {

    public RandomChestCommand(Plugin plugin, String command, RandomChestManager chestManager) {
        super(plugin, command);
        registerSubCommand(new RandomChestWandCmd(plugin, "완드", chestManager));
        registerSubCommand(new RandomChestRunCmd(plugin, "실행", chestManager));
        registerSubCommand(new RandomChestClearCmd(plugin, "초기화", chestManager));
        registerSubCommand(new RandomChestSetCmd(plugin, "설정", chestManager));
        registerSubCommand(new RandomChestRemoveCmd(plugin, "제거", chestManager));
        registerSubCommand(new RandomChestListCmd(plugin, "목록", chestManager));
        registerSubCommand(new RandomChestBlankCmd(plugin, "꽝아이템", chestManager));
        registerSubCommand(new RandomChestItemClearCmd(plugin, "아이템초기화", chestManager));
    }

    @Override
    public boolean onCommandEmpty(CommandSender sender, Command command, String label) {
        this.sendHelp(sender);
        return true;
    }

    @Override
    public boolean onBeforeCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    @Override
    public boolean onAfterCommand(CommandSender sender, Command command, String label, String[] args, boolean sub_result) {
        if(!sub_result) {
            this.sendHelp(sender);
        }
        return true;
    }

    private void sendHelp(CommandSender sender) {
        String[] help = new String[] {
                RandomChestPlugin.prefix + "§6Usage:",
                " => /랜덤상자 완드",
                " => /랜덤상자 실행",
                " => /랜덤상자 초기화",
                " => /랜덤상자 설정 <이름> <수량>",
                " => /랜덤상자 제거 <이름>",
                " => /랜덤상자 목록",
                " => /랜덤상자 아이템초기화"
        };
        MsgUtil.sendMsg(sender, help);
    }
}