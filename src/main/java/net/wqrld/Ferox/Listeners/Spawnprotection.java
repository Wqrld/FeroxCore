package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Managers.TeamManager;
import net.wqrld.Ferox.Utils.AreaUtils;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Spawnprotection implements Listener {


    @EventHandler
    public void blockbreak(BlockBreakEvent e){
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {


            if(!TeamManager.getblue().contains(e.getPlayer()) && !TeamManager.getred().contains(e.getPlayer()) && !e.getPlayer().getName().equals("Xirial")){
                e.getPlayer().sendMessage("§c§lPlease join using /join.");
                e.setCancelled(true);
            }

            if (AreaUtils.iswithin(e.getBlock().getLocation(), RotationManager.GetCurrentMap().getLocation("spawnarea1"), RotationManager.GetCurrentMap().getLocation("spawnarea2"))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You cannot build here");
            }
            //red

            if (AreaUtils.iswithin(e.getBlock().getLocation(), RotationManager.GetCurrentMap().getLocation("redspawnarea1"), RotationManager.GetCurrentMap().getLocation("redspawnarea2"))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You cannot build here");
            }
            //blue
            if (AreaUtils.iswithin(e.getBlock().getLocation(), RotationManager.GetCurrentMap().getLocation("bluespawnarea1"), RotationManager.GetCurrentMap().getLocation("bluespawnarea2"))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You cannot build here");
            }


        }

    }
    @EventHandler
    public void blockplace(BlockPlaceEvent e){
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            if(!TeamManager.getblue().contains(e.getPlayer()) && !TeamManager.getred().contains(e.getPlayer()) && !e.getPlayer().getName().equals("Xirial")){
                e.getPlayer().sendMessage("§c§lPlease join using /join.");
                e.setCancelled(true);
                return;
            }

            if (AreaUtils.iswithin(e.getBlock().getLocation(), RotationManager.GetCurrentMap().getLocation("spawnarea1"), RotationManager.GetCurrentMap().getLocation("spawnarea2"))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You cannot build here");
            }
            //red

            if (AreaUtils.iswithin(e.getBlock().getLocation(), RotationManager.GetCurrentMap().getLocation("redspawnarea1"), RotationManager.GetCurrentMap().getLocation("redspawnarea2"))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You cannot build here");
            }
            //blue
            if (AreaUtils.iswithin(e.getBlock().getLocation(), RotationManager.GetCurrentMap().getLocation("bluespawnarea1"), RotationManager.GetCurrentMap().getLocation("bluespawnarea2"))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You cannot build here");
            }


        }
    }



}
