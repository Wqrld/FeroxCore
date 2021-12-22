package net.wqrld.Ferox.Listeners;

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

    private Location getLoc(String NexusName){
        Location currentMap = RotationManager.GetCurrentMap().getLocation(NexusName);
        currentMap.setWorld(MatchManager.getCurrentMVBukkitWorld());
        return currentMap;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.WEB) {
            e.getBlock().setType(Material.AIR);
            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.WEB, 1));
        }


        //TODO on end op game, tps you to good location in the ended map's world

        if (e.getBlock().getType() == Material.OBSIDIAN) {


            if (e.getBlock().getLocation().equals(getLoc("rednexus"))) {

                if (TeamManager.getblue().contains(e.getPlayer())) {
                    ProcessRedNexusBroken(e, "rednexus1");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            } else if (RotationManager.GetCurrentMap().getNexuscount() >= 2 && e.getBlock().getLocation().equals(getLoc("rednexus2"))) {

                if (TeamManager.getblue().contains(e.getPlayer())) {
                    ProcessRedNexusBroken(e, "rednexus2");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            } else if (RotationManager.GetCurrentMap().getNexuscount() >= 3 && e.getBlock().getLocation().equals(getLoc("rednexus3"))) {

                if (TeamManager.getblue().contains(e.getPlayer())) {
                    ProcessRedNexusBroken(e, "rednexus3");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }

            } else if (e.getBlock().getLocation().equals(getLoc("bluenexus"))) {

                if (TeamManager.getred().contains(e.getPlayer())) {
                    ProcessBlueNexusBroken(e, "bluenexus1");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            } else if (RotationManager.GetCurrentMap().getNexuscount() >= 2 && e.getBlock().getLocation().equals(getLoc("bluenexus2"))) {

                if (TeamManager.getred().contains(e.getPlayer())) {
                    ProcessBlueNexusBroken(e, "bluenexus2");
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }

            } else if (RotationManager.GetCurrentMap().getNexuscount() >= 3 && e.getBlock().getLocation().equals(getLoc("bluenexus3"))) {

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

