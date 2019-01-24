package net.wqrld.Ferox;

import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FriendlyFire implements Listener {
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getEntity().getType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER){

if(teammanager.getred().contains(e.getDamager()) && teammanager.getred().contains(e.getEntity())){
    e.getDamager().sendMessage("ff is disabled");
    e.setCancelled(true);
}
            if(teammanager.getblue().contains(e.getDamager()) && teammanager.getblue().contains(e.getEntity())){
                e.getDamager().sendMessage("ff is disabled");
                e.setCancelled(true);
            }

        }
    }
}
