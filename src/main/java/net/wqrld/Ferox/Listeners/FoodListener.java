package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodListener implements Listener {
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if(e.getEntity().getType() == EntityType.PLAYER){
            Player player = (Player) e.getEntity();
            if(!TeamManager.getblue().contains(player) && !TeamManager.getred().contains(player)){
                e.setCancelled(true);
            }
        }
    }
}
