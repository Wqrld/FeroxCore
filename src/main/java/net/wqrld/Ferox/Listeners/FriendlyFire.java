package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Main;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendlyFire implements Listener {

    public static HashMap<Entity, Entity> lastHitter = new HashMap<>();
    public static HashMap<Entity, BukkitTask> queue = new HashMap<>();



    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && (e.getDamager() instanceof Player || e.getDamager() instanceof Projectile)) {
            Entity Damager = e.getDamager();

            if (e.getDamager() instanceof Projectile) {
                ProjectileSource shooter = ((Projectile) e.getDamager()).getShooter();
                if (!(shooter instanceof Player)) {
                    return;
                } else {
                    Damager = (Entity) shooter;
                }
            }

            if (!TeamManager.getred().contains(Damager) && !TeamManager.getblue().contains(Damager)) {
                e.setCancelled(true);
            }

            else if (TeamManager.getred().contains(Damager) && TeamManager.getred().contains(e.getEntity())) {
                e.setCancelled(true);
            }
            else if (TeamManager.getblue().contains(Damager) && TeamManager.getblue().contains(e.getEntity())) {
                e.setCancelled(true);
            }else{
                //Valid hit
                lastHitter.put(e.getEntity(), Damager);
                if(queue.get(e.getEntity()) != null){
                    queue.get(e.getEntity()).cancel();
                }
                BukkitTask br = new BukkitRunnable() {
                    @Override
                    public void run() {
                        lastHitter.remove(e.getEntity());
                    }
                }.runTaskLater(Main.plugin, 300);
                queue.put(e.getEntity(), br);


            }

        }
    }
}
