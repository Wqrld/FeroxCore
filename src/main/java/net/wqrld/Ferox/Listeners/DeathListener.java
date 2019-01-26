package net.wqrld.Ferox.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class DeathListener implements Listener {
    @EventHandler
    public void ondeath(PlayerDeathEvent e) {
        List<ItemStack> list = e.getDrops();
        Iterator<ItemStack> i = list.iterator();
        while (i.hasNext()) {
            ItemStack drop = i.next();
            if (drop.getType() == Material.LEATHER_BOOTS ||
                    drop.getType() == Material.STONE_SWORD ||
                    drop.getType() == Material.BOW ||
                    drop.getType() == Material.DIAMOND_PICKAXE ||
                    drop.getType() == Material.STONE_AXE ||
                    drop.getType() == Material.LEATHER_LEGGINGS ||
                    drop.getType() == Material.LEATHER_CHESTPLATE ||
                    drop.getType() == Material.LEATHER_HELMET) {
                i.remove();
            }
        }
    }
}
