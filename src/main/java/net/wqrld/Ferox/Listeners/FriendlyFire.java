package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class FriendlyFire implements Listener {
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

            if (TeamManager.getred().contains(Damager) && TeamManager.getred().contains(e.getEntity())) {
                e.setCancelled(true);
            }
            if (TeamManager.getblue().contains(Damager) && TeamManager.getblue().contains(e.getEntity())) {
                e.setCancelled(true);
            }

        }
    }
}
