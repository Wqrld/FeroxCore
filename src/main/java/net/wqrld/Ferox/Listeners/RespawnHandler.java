package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Main;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnHandler implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        
        new BukkitRunnable() {
            @Override public void run() {
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
                    e.getPlayer().teleport(RotationManager.GetCurrentMap().getLocation("redspawn"));
                    MatchManager.givearmor(e.getPlayer(), Color.RED);
                    MatchManager.giveitems(e.getPlayer());
                } else if (TeamManager.getblue().contains(e.getPlayer())) {
                    e.getPlayer().teleport(RotationManager.GetCurrentMap().getLocation("bluespawn"));
                    MatchManager.givearmor(e.getPlayer(), Color.BLUE);
                    MatchManager.giveitems(e.getPlayer());
                } else {
                    e.getPlayer().teleport(RotationManager.GetCurrentMap().getLocation("spawn"));
                    ItemStack i = new ItemStack(Material.COMPASS);
                    ItemMeta meta = i.getItemMeta();
                    meta.setDisplayName("Click to join");
                    i.setItemMeta(meta);
                    //https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fredditpublic.com%2Fimages%2Fb%2Fb2%2FItems_slot_number.png&f=1
                    e.getPlayer().getInventory().setItem(0, i);

                }


            }
        }.runTaskLater(Main.plugin, 10);


    }
}
