package net.wqrld.Ferox.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.wqrld.Ferox.Main;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.isCancelled()) {
            return;
        }

        if (event.getMessage().contains("%")) {
            event.setMessage(event.getMessage().replaceAll("%", "%%"));
        }
        Player player = event.getPlayer();
        String color = "§f";
        if (TeamManager.getblue().contains(player)) {
            color = "§9";
        }
        if (TeamManager.getred().contains(player)) {
            color = "§c";
        }


        String oldformat = color + "§r" + color + event.getPlayer().getDisplayName() + "§7> §f";
        String format = PlaceholderAPI.setPlaceholders(event.getPlayer(), oldformat) + event.getMessage();
        event.setFormat(format);
        if (event.getMessage().equalsIgnoreCase("gg")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().sendMessage("§b+1 Karma");

                }
            }.runTaskLater(Main.plugin, 2);

        }


    }


}
