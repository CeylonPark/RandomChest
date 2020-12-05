package com.hollyday.randomchest;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class RandomChestListener implements Listener {
    private final RandomChestManager chestManager;

    public RandomChestListener(RandomChestManager chestManager) {
        this.chestManager = chestManager;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if(event.getHand() == EquipmentSlot.HAND && event.getClickedBlock() != null
                && event.getPlayer().getInventory().getItemInMainHand().equals(this.chestManager.getWand())) {
            event.setCancelled(true);
            Location location = event.getClickedBlock().getLocation();
            int pos = event.getAction() == Action.LEFT_CLICK_BLOCK ? 0 : 1;
            this.chestManager.setPos(location, pos);
            event.getPlayer().sendMessage(new StringBuilder(RandomChest.prefix)
                    .append("§d").append(pos == 0 ? "First" : "Second").append(" position set to (")
                    .append(location.getX()).append(", ").append(location.getY()).append(", ")
                    .append(location.getZ()).append(")").toString());
        }
    }
}
