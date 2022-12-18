package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Main;
import net.wqrld.Ferox.Managers.GameStatTracker;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class DeathListener implements Listener {

    private String getColor(Player player){
        if (TeamManager.getblue().contains(player)) {
            return "§9";
        } else if (TeamManager.getred().contains(player)) {
            return "§c";
        } else {
            return "§f";
        }
    }

    @EventHandler
    public void ondamage(EntityDamageEvent e){
        if(e.getEntityType() == EntityType.PLAYER){
            if(!TeamManager.getblue().contains((Player) e.getEntity()) && !TeamManager.getred().contains((Player) e.getEntity())){
                e.setCancelled(true);
                Location currentMap = RotationManager.GetCurrentMap().getSpawn();
                currentMap.setWorld(MatchManager.getCurrentMVBukkitWorld());
                e.getEntity().teleport(currentMap);
            }
        }
    }

    @EventHandler
    public void ondeath(PlayerDeathEvent e) {

        if(e.getEntityType() != EntityType.PLAYER){
            return;
        }

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
          GameStatTracker.matchdeaths.put(e.getEntity(), GameStatTracker.matchdeaths.get(e.getEntity()) + 1);
          Main.statement.executeUpdate("INSERT INTO Stats VALUES ('" + uuid + "', 0, 1, 0, 0, 0, 0, 0, 0, 0) ON DUPLICATE KEY UPDATE deaths = deaths + 1");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Entity killer = null;

        if(FriendlyFire.lastHitter.get(e.getEntity()) != null){
            e.getEntity().sendMessage(FriendlyFire.lastHitter.get(e.getEntity()).getName());
            if (e.getEntity().getLastDamageCause() instanceof EntityDamageByBlockEvent) {
                EntityDamageByBlockEvent cause = (EntityDamageByBlockEvent) e.getEntity().getLastDamageCause();
                if (cause.getCause() == EntityDamageEvent.DamageCause.VOID) {
                    Entity entity = FriendlyFire.lastHitter.get(e.getEntity());
                    if (entity instanceof Player) {
                        Player pl = (Player) entity;
                        e.setDeathMessage(e.getEntity().getDisplayName() + " got yeeted into the void by " + pl.getName());
                        FriendlyFire.lastHitter.remove(e.getEntity());
                        killer = entity;
                    }
                }


            }
        }
        if(e.getEntity().getKiller() != null && e.getEntity().getKiller().getType() == EntityType.PLAYER){
            killer = e.getEntity().getKiller();
        }
        
        if(killer != null || (e.getEntity().getKiller() != null && e.getEntity().getKiller().getType() == EntityType.PLAYER)){
            UUID killeruuid;
            if(killer != null){
                killeruuid = killer.getUniqueId();
                GameStatTracker.matchkills.put((Player) killer, GameStatTracker.matchkills.get(killer) + 1);
            }else{
                killeruuid = e.getEntity().getKiller().getUniqueId();
                GameStatTracker.matchkills.put(e.getEntity().getKiller(), GameStatTracker.matchkills.get(e.getEntity().getKiller()) + 1);
            }

            try {
                Main.statement.executeUpdate("INSERT INTO Stats VALUES ('" + killeruuid + "', 1, 0, 0, 0, 0, 0, 0, 0, 0) ON DUPLICATE KEY UPDATE kills = kills + 1");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        e.setDeathMessage(e.getDeathMessage().replace(e.getEntity().getPlayer().getDisplayName(), getColor(e.getEntity()) + e.getEntity().getPlayer().getDisplayName() + "§7"));
            //TODO eigen naam altijd bold maken / eigen kill messages bold

        if(killer != null || (e.getEntity().getKiller() != null && e.getEntity().getKiller().getType() == EntityType.PLAYER)){
            if(killer == null){
                killer = e.getEntity().getKiller();
            }
            if(killer instanceof Player) {
                String killerUserName = killer.getName();
                e.setDeathMessage(e.getDeathMessage().replace(killerUserName, getColor((Player) killer) + killerUserName + "§7"));
            }
        }

    }
}
