package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Main;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnHandler implements Listener {
    World world = Bukkit.getWorld("zenith");

    @EventHandler
    public void respawn(PlayerRespawnEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (TeamManager.getred().contains(e.getPlayer())) {

                    e.getPlayer().teleport(new Location(world, 42.5, 10, -148.5, 1, 1));
                    MatchManager.givearmor(e.getPlayer(), Color.RED);
                } else if (TeamManager.getblue().contains(e.getPlayer())) {
                    e.getPlayer().teleport(new Location(world, 42.5, 10, -32.5, 179, 1));
                    MatchManager.givearmor(e.getPlayer(), Color.BLUE);
                } else {
                    e.getPlayer().teleport(new Location(world, 89.5, 28, -90.5, 90, 1));

                }
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit halcyon " + e.getPlayer().getName());
                e.getPlayer().getInventory().addItem(new ItemStack(Material.ARROW, 64));

            }
        }.runTaskLater(Main.plugin, 10);


    }
}
