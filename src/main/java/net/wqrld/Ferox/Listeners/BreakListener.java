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
                    e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke a §c§lRed§r nexus!");
                    e.getBlock().setType(Material.AIR);
                    MatchManager.breaknexus("rednexus1");
                    if (RotationManager.GetCurrentMap().getNexuscount().equals(1)) {
                        MatchManager.setwinner("Blue");
                        MatchManager.endgame();
                    } else {
                        if (MatchManager.isBroken("rednexus2")) {
                            MatchManager.Monumentbroken(e.getPlayer());
                            MatchManager.setwinner("Blue");
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
                    e.getBlock().setType(Material.AIR);
                    MatchManager.breaknexus("rednexus2");
                    if (RotationManager.GetCurrentMap().getNexuscount().equals(1)) {
                        MatchManager.setwinner("Blue");
                        MatchManager.endgame();
                    } else {
                        if (MatchManager.isBroken("rednexus1")) {
                            MatchManager.setwinner("Blue");
                            MatchManager.Monumentbroken(e.getPlayer());
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
                    e.getBlock().setType(Material.AIR);
                    MatchManager.breaknexus("bluenexus1");
                    if (RotationManager.GetCurrentMap().getNexuscount().equals(1)) {
                        MatchManager.setwinner("Red");
                        MatchManager.Monumentbroken(e.getPlayer());
                        MatchManager.endgame();
                    } else {
                        if (MatchManager.isBroken("bluenexus2")) {
                            MatchManager.setwinner("Red");
                            MatchManager.endgame();
                        }
                    }
                } else {
                    e.getPlayer().sendMessage("nope");
                    e.setCancelled(true);
                }
            } else if (e.getBlock().getLocation().equals(RotationManager.GetCurrentMap().getLocation("bluenexus2"))) {

                if (TeamManager.getred().contains(e.getPlayer())) {
                    e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke a §9§lBlue§r nexus!");
                    e.getBlock().setType(Material.AIR);
                    MatchManager.breaknexus("bluenexus2");
                    if (RotationManager.GetCurrentMap().getNexuscount().equals(1)) {
                        MatchManager.setwinner("Red");
                        MatchManager.Monumentbroken(e.getPlayer());
                        MatchManager.endgame();
                    } else {
                        if (MatchManager.isBroken("bluenexus1")) {
                            MatchManager.setwinner("Red");
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
