package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerListener implements Listener {
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {

        // Not in-game
      if(!TeamManager.getblue().contains(e.getEntity()) && !TeamManager.getred().contains(e.getEntity())){
          e.setCancelled(true);
        }

    }
}
