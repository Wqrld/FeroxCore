package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getBlock().getType() == Material.OBSIDIAN) {

            World world = Bukkit.getWorld("zenith");

            if(e.getBlock().getLocation().equals(new Location(world, 42, 12, -127))){

                if(TeamManager.getblue().contains(e.getPlayer())) {
                    e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke the §c§lRed§r nexus!");
                    e.getBlock().setType(Material.OBSIDIAN);
                    MatchManager.endgame();

            }else{
                e.getPlayer().sendMessage("nope");
                e.setCancelled(true);
            }
            }else if(e.getBlock().getLocation().equals(new Location(world, 42, 12, -55))) {
                if(TeamManager.getred().contains(e.getPlayer())) {
                    e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke the §9§lBlue§r nexus!");
                    e.getBlock().setType(Material.OBSIDIAN);
                    MatchManager.endgame();
                }else{
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            }


       //     e.getPlayer().sendMessage(e.getBlock().getLocation().toString());




        }



    }
}
