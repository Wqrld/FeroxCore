package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FriendlyFire implements Listener {
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getEntity().getType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER){

if(TeamManager.getred().contains(e.getDamager()) && TeamManager.getred().contains(e.getEntity())){
    e.getDamager().sendMessage("ff is disabled");
    e.setCancelled(true);
}
            if(TeamManager.getblue().contains(e.getDamager()) && TeamManager.getblue().contains(e.getEntity())){
                e.getDamager().sendMessage("ff is disabled");
                e.setCancelled(true);
            }

        }
    }
}
