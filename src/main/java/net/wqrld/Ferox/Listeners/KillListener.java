package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class KillListener implements Listener {
    @EventHandler
    public void ondeath(PlayerDeathEvent e) {
//        List<ItemStack> list = e.getDrops();
//        Iterator<ItemStack> i = list.iterator();
//        while (i.hasNext()) {
//            ItemStack drop = i.next();
//            if (drop.getType() == Material.LEATHER_BOOTS ||
//                    drop.getType() == Material.STONE_SWORD ||
//                    drop.getType() == Material.BOW ||
//                    drop.getType() == Material.DIAMOND_PICKAXE ||
//                    drop.getType() == Material.STONE_AXE ||
//                    drop.getType() == Material.LEATHER_LEGGINGS ||
//                    drop.getType() == Material.LEATHER_CHESTPLATE ||
//                    drop.getType() == Material.LEATHER_HELMET) {
//                i.remove();
//            }
//        }
//        UUID uuid = e.getEntity().getUniqueId();
//        try {
//            ResultSet result = Main.statement.executeQuery("INSERT INTO Stats VALUES ('" + uuid + "', 0, 0, 0, 0, 0, 0, 0, 0, 0) ON DUPLICATE KEY UPDATE kills = kills + 1 where uuid = " + uuid);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }
}
