package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Managers.GameStatTracker;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Location l = e.getBlock().getLocation();
        if (e.getBlock().getType() == Material.WEB) {
            e.setDropItems(false);
            e.getBlock().getWorld().dropItemNaturally(l, new ItemStack(Material.WEB, 1));
        }

        if (e.getBlock().getType() == Material.OBSIDIAN) {
            Integer nexuscount = RotationManager.GetCurrentMap().getNexuscount();

            if (l.equals(RotationManager.GetCurrentMap().getRednexus())) {

                if (TeamManager.getblue().contains(e.getPlayer())) {
                    ProcessRedNexusBroken(e, "rednexus1");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            } else if (nexuscount >= 2 && l.equals(RotationManager.GetCurrentMap().getRednexus2())) {

                if (TeamManager.getblue().contains(e.getPlayer())) {
                    ProcessRedNexusBroken(e, "rednexus2");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            } else if (nexuscount >= 3 && l.equals(RotationManager.GetCurrentMap().getRednexus3())) {

                if (TeamManager.getblue().contains(e.getPlayer())) {
                    ProcessRedNexusBroken(e, "rednexus3");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }

            } else if (l.equals(RotationManager.GetCurrentMap().getBluenexus())) {

                if (TeamManager.getred().contains(e.getPlayer())) {
                    ProcessBlueNexusBroken(e, "bluenexus1");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            } else if (nexuscount >= 2 && l.equals(RotationManager.GetCurrentMap().getBluenexus2())) {

                if (TeamManager.getred().contains(e.getPlayer())) {
                    ProcessBlueNexusBroken(e, "bluenexus2");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }

            } else if (nexuscount >= 3 && l.equals(RotationManager.GetCurrentMap().getBluenexus3())) {

                if (TeamManager.getred().contains(e.getPlayer())) {
                    ProcessBlueNexusBroken(e, "bluenexus3");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            }


        }
        GameStatTracker.blocksplaced.put(e.getPlayer(), GameStatTracker.blocksplaced.get(e.getPlayer()) + 1);
    }

    private void ProcessBlueNexusBroken(BlockBreakEvent e, String NexusName) {
        e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke a §9§lBlue§r nexus!");
        e.getBlock().setType(Material.AIR);
        MatchManager.breaknexus(NexusName);
        MatchManager.Monumentbroken(e.getPlayer());

        if(MatchManager.AreAllBroken("blue")){
            MatchManager.setwinner("Red");
            MatchManager.endgame();
        }
    }

    private void ProcessRedNexusBroken(BlockBreakEvent e, String nexusName) {
        e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke a §c§lRed§r nexus!");
        e.getBlock().setType(Material.AIR);
        MatchManager.breaknexus(nexusName);
        MatchManager.Monumentbroken(e.getPlayer());

        if(MatchManager.AreAllBroken("red")){
            MatchManager.setwinner("Blue");
            MatchManager.endgame();
        }
    }
}

