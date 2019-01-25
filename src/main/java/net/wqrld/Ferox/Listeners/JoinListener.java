package net.wqrld.Ferox.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.wqrld.Ferox.Main;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.*;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinListener implements Listener{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage("You were sent to spawn");
        World world = Bukkit.getWorld("zenith");
        Location loc = new Location(world, 89.5, 28, -90.5, 90, 1);
        e.getPlayer().teleport(loc);
        ItemStack i = new ItemStack(Material.COMPASS);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName("Click to join");
        i.setItemMeta(meta);
        //https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fredditpublic.com%2Fimages%2Fb%2Fb2%2FItems_slot_number.png&f=1
        e.getPlayer().getInventory().setItem(0, i);

    }
    @EventHandler
    public void onRightCLick(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getType() == Material.COMPASS){
            Bukkit.dispatchCommand(e.getPlayer(), "join");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onleave(PlayerQuitEvent e) {
        if (TeamManager.getred().contains(e.getPlayer())) {
            TeamManager.getred().remove(e.getPlayer());
        }

        if (TeamManager.getblue().contains(e.getPlayer())) {
            TeamManager.getblue().remove(e.getPlayer());
        }

    }


    //@EventHandler
//    public void ondamage(EntityDamageEvent e){
//        if(e.getEntityType() == EntityType.PLAYER) {
//            if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
//                e.setDamage(40);
//            }
//        }
//}
    @EventHandler
    public void ondeath(PlayerDeathEvent e) {
        for (ItemStack drop : e.getDrops()) {
            if (drop.getType() == Material.LEATHER_BOOTS ||
                    drop.getType() == Material.STONE_SWORD ||
                    drop.getType() == Material.BOW ||
                    drop.getType() == Material.DIAMOND_PICKAXE ||
                    drop.getType() == Material.STONE_AXE ||
                    drop.getType() == Material.LEATHER_LEGGINGS ||
                    drop.getType() == Material.LEATHER_CHESTPLATE ||
                    drop.getType() == Material.LEATHER_HELMET) {
                e.getDrops().remove(drop);

            }
        }
    }
}
