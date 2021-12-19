package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Main;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnHandler implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        new BukkitRunnable() {
            @Override
            public void run() {
                e.getEntity().spigot().respawn();
            }
        }.runTaskLater(Main.plugin, 20);

    }

    @EventHandler
    public void respawn(PlayerRespawnEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (TeamManager.getred().contains(e.getPlayer())) {
                    Location nextmap = RotationManager.GetCurrentMap().getLocation("redspawn");
                    nextmap.setWorld(MatchManager.getCurrentMVBukkitWorld());
                    e.getPlayer().teleport(nextmap);
                    MatchManager.givearmor(e.getPlayer(), Color.RED);
                    MatchManager.giveitems(e.getPlayer());
                } else if (TeamManager.getblue().contains(e.getPlayer())) {
                    Location nextmap = RotationManager.GetCurrentMap().getLocation("bluespawn");
                    nextmap.setWorld(MatchManager.getCurrentMVBukkitWorld());
                    e.getPlayer().teleport(nextmap);
                    MatchManager.givearmor(e.getPlayer(), Color.BLUE);
                    MatchManager.giveitems(e.getPlayer());
                } else {

                    Location nextmap = RotationManager.GetCurrentMap().getLocation("Spawn");
                    nextmap.setWorld(MatchManager.getCurrentMVBukkitWorld());
                    e.getPlayer().teleport(nextmap);

                    ItemStack i = new ItemStack(Material.COMPASS);
                    ItemMeta meta = i.getItemMeta();
                    meta.setDisplayName(ChatColor.RESET + "Click to join");
                    i.setItemMeta(meta);
                    //https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fredditpublic.com%2Fimages%2Fb%2Fb2%2FItems_slot_number.png&f=1
                    e.getPlayer().getInventory().setItem(0, i);

                }


            }
        }.runTaskLater(Main.plugin, 10);


    }
}
