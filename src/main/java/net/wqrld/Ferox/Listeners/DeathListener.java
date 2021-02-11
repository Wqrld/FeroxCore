package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Main;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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
                    drop.getType() == Material.CHAINMAIL_CHESTPLATE ||
                    drop.getType() == Material.LEATHER_HELMET) {
                i.remove();
            }
        }
        UUID uuid = e.getEntity().getUniqueId();
        try {
          Main.statement.executeUpdate("INSERT INTO Stats VALUES ('" + uuid + "', 0, 1, 0, 0, 0, 0, 0, 0, 0) ON DUPLICATE KEY UPDATE deaths = deaths + 1");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        if(e.getEntity().getKiller().getType() == EntityType.PLAYER){
            UUID killeruuid = e.getEntity().getKiller().getUniqueId();
            try {
                Main.statement.executeUpdate("INSERT INTO Stats VALUES ('" + killeruuid + "', 1, 0, 0, 0, 0, 0, 0, 0, 0) ON DUPLICATE KEY UPDATE kills = kills + 1");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getEntity().getLastDamageCause().equals(EntityDamageEvent.DamageCause.VOID)) {
            e.setDeathMessage(e.getEntity().getName() + " is gestorven aan luc'ism");
        }


    }
}
