package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.TeamManager;
import net.wqrld.Ferox.Utils.AreaUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Spawnprotection implements Listener {
    World world = Bukkit.getWorld("zenith");


    @EventHandler
    public void blockbreak(BlockBreakEvent e){
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {


            if(!TeamManager.getblue().contains(e.getPlayer()) && !TeamManager.getred().contains(e.getPlayer()) && !e.getPlayer().getName().equals("Xirial")){
                e.getPlayer().sendMessage("§c§lPlease join using /join.");
                e.setCancelled(true);
            }
//spawn
            if (AreaUtils.iswithin(e.getBlock().getLocation(), new Location(world, 82, 20, -81), new Location(world, 94, 35, -100))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You cannot build here");
            }
            //red

            if (AreaUtils.iswithin(e.getBlock().getLocation(), new Location(world, 38, 5, -144), new Location(world, 46, 32, -153))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You cannot build here");
            }
            //blue
            if (AreaUtils.iswithin(e.getBlock().getLocation(), new Location(world, 38, 5, -46), new Location(world, 46, 32, -27))) {
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

            if (AreaUtils.iswithin(e.getBlock().getLocation(), new Location(world, 82, 20, -81), new Location(world, 94, 35, -100))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You cannot build here");
            }

            if (AreaUtils.iswithin(e.getBlock().getLocation(), new Location(world, 38, 5, -144), new Location(world, 46, 32, -153))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You cannot build here");
            }
            //blue
            if (AreaUtils.iswithin(e.getBlock().getLocation(), new Location(world, 38, 5, -46), new Location(world, 46, 32, -27))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You cannot build here");
            }


        }
    }



}
