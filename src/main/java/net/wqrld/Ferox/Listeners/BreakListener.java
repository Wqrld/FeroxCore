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
        if (e.getBlock().getType() == Material.OBSIDIAN) {


            if (e.getBlock().getLocation().equals(RotationManager.GetCurrentMap().getLocation("rednexus"))) {

                if (TeamManager.getblue().contains(e.getPlayer())) {
                    e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke a §c§lRed§r nexus!");
                    e.getBlock().setType(Material.OBSIDIAN);
                    MatchManager.breaknexus("rednexus1");
                    if (RotationManager.GetCurrentMap().getNexuscount().equals(1)) {
                        MatchManager.endgame();
                    } else {
                        if (MatchManager.isBroken("rednexus2")) {
                            MatchManager.endgame();
                        }
                    }
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            } else if (e.getBlock().getLocation().equals(RotationManager.GetCurrentMap().getLocation("rednexus2"))) {

                if (TeamManager.getblue().contains(e.getPlayer())) {
                    e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke a §c§lRed§r nexus!");
                    e.getBlock().setType(Material.OBSIDIAN);
                    MatchManager.breaknexus("rednexus2");
                    if (RotationManager.GetCurrentMap().getNexuscount().equals(1)) {
                        MatchManager.endgame();
                    } else {
                        if (MatchManager.isBroken("rednexus1")) {
                            MatchManager.endgame();
                        }
                    }
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }

            } else if (e.getBlock().getLocation().equals(RotationManager.GetCurrentMap().getLocation("bluenexus"))) {

                if (TeamManager.getred().contains(e.getPlayer())) {
                    e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke a §9§lBlue§r nexus!");
                    e.getBlock().setType(Material.OBSIDIAN);
                    MatchManager.breaknexus("bluenexus1");
                    if (RotationManager.GetCurrentMap().getNexuscount().equals(1)) {
                        MatchManager.endgame();
                    } else {
                        if (MatchManager.isBroken("bluenexus2")) {
                            MatchManager.endgame();
                        }
                    }
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            }
            else if (e.getBlock().getLocation().equals(RotationManager.GetCurrentMap().getLocation("bluenexus2"))) {

                if (TeamManager.getred().contains(e.getPlayer())) {
                    e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke a §9§lBlue§r nexus!");
                    e.getBlock().setType(Material.OBSIDIAN);
                    MatchManager.breaknexus("bluenexus2");
                    if (RotationManager.GetCurrentMap().getNexuscount().equals(1)) {
                        MatchManager.endgame();
                    } else {
                        if (MatchManager.isBroken("bluenexus1")) {
                            MatchManager.endgame();
                        }
                    }
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            }

            //     e.getPlayer().sendMessage(e.getBlock().getLocation().toString());


        }


    }
}
