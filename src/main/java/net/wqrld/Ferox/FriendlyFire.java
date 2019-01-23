package net.wqrld.Ferox;

import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FriendlyFire implements Listener {
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getEntity().getType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER){

if(teammanager.red.contains(e.getDamager()) && teammanager.red.contains(e.getEntity())){
    e.getDamager().sendMessage("ff is disabled");
    e.setCancelled(true);
}
            if(teammanager.blue.contains(e.getDamager()) && teammanager.blue.contains(e.getEntity())){
                e.getDamager().sendMessage("ff is disabled");
                e.setCancelled(true);
            }

        }
    }
}
