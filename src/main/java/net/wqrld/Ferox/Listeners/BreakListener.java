package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.WEB) {
            e.getBlock().setType(Material.AIR);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.WEB, 1));
        }


        if (e.getBlock().getType() == Material.OBSIDIAN) {


            if (e.getBlock().getLocation().equals(RotationManager.GetCurrentMap().getLocation("rednexus"))) {

                if (TeamManager.getblue().contains(e.getPlayer())) {
                    ProcessRedNexusBroken(e, "rednexus1");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            } else if (e.getBlock().getLocation().equals(RotationManager.GetCurrentMap().getLocation("rednexus2"))) {

                if (TeamManager.getblue().contains(e.getPlayer())) {
                    ProcessRedNexusBroken(e, "rednexus2");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            } else if (e.getBlock().getLocation().equals(RotationManager.GetCurrentMap().getLocation("rednexus3"))) {

                if (TeamManager.getblue().contains(e.getPlayer())) {
                    ProcessRedNexusBroken(e, "rednexus3");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }

            } else if (e.getBlock().getLocation().equals(RotationManager.GetCurrentMap().getLocation("bluenexus"))) {

                if (TeamManager.getred().contains(e.getPlayer())) {
                    ProcessBlueNexusBroken(e, "bluenexus1");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            } else if (e.getBlock().getLocation().equals(RotationManager.GetCurrentMap().getLocation("bluenexus2"))) {

                if (TeamManager.getred().contains(e.getPlayer())) {
                    ProcessBlueNexusBroken(e, "bluenexus2");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }

            } else if (e.getBlock().getLocation().equals(RotationManager.GetCurrentMap().getLocation("bluenexus3"))) {

                if (TeamManager.getred().contains(e.getPlayer())) {
                    ProcessBlueNexusBroken(e, "bluenexus3");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            }

            //     e.getPlayer().sendMessage(e.getBlock().getLocation().toString());


        }

    }

    private void ProcessBlueNexusBroken(BlockBreakEvent e, String NexusName) {
        e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke a §9§lBlue§r nexus!");
        e.getBlock().setType(Material.AIR);
        MatchManager.breaknexus(NexusName);
        MatchManager.Monumentbroken(e.getPlayer());

        if(MatchManager.AreAllBroken("blue")){
            MatchManager.setwinner("Red");;
            MatchManager.endgame();
        }
    }

    private void ProcessRedNexusBroken(BlockBreakEvent e, String nexusName) {
        e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke a §c§lRed§r nexus!");
        e.getBlock().setType(Material.AIR);
        MatchManager.breaknexus(nexusName);
        MatchManager.Monumentbroken(e.getPlayer());

        if(MatchManager.AreAllBroken("red")){
            MatchManager.setwinner("Blue");;
            MatchManager.endgame();
        }
    }
}

