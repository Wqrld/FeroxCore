package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getBlock().getType() == Material.OBSIDIAN) {


            if (e.getBlock().getLocation().equals(RotationManager.CurrentMap().getLocation("rednexus"))) {

                if(TeamManager.getblue().contains(e.getPlayer())) {
                    e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke the §c§lRed§r nexus!");
                    e.getBlock().setType(Material.OBSIDIAN);
                    MatchManager.endgame();

            }else{
                e.getPlayer().sendMessage("nope");
                e.setCancelled(true);
            }
            } else if (e.getBlock().getLocation().equals(RotationManager.CurrentMap().getLocation("bluenexus"))) {
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
