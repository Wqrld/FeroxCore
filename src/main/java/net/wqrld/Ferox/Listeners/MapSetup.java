package net.wqrld.Ferox.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class MapSetup implements Listener {

    public static ArrayList<Player> inSetupMode = new ArrayList<>();
    private static HashMap<Player, Integer> step = new HashMap<>();

    String[] order = {"Author", "Name", "nexuscount", "redspawn", "bluespawn", "spawn", "redspawn1", "redspawn2", "bluespawn1", "bluespawn2", "spawn1", "spawn2"}; // plus nexusses

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (inSetupMode.contains(e.getPlayer())) {

            if (e.getMessage().equalsIgnoreCase("next")) {
                step.put(e.getPlayer(), step.get(e.getPlayer()) + 1);
                e.getPlayer().sendMessage("current step: " + step.get(e.getPlayer()));
                e.setCancelled(true);
            } else if (e.getMessage().equalsIgnoreCase("prev")) {
                step.put(e.getPlayer(), step.get(e.getPlayer()) - 1);
                e.getPlayer().sendMessage("current step: " + step.get(e.getPlayer()));
                e.setCancelled(true);
            } else if (e.getMessage().equalsIgnoreCase("step")) {
                e.getPlayer().sendMessage("current step: " + step.get(e.getPlayer()));
                e.setCancelled(true);
            } else if (e.getMessage().equalsIgnoreCase("loc")) {
                e.getPlayer().sendMessage("current step: " + step.get(e.getPlayer()));
                e.setCancelled(true);
            } else if (e.getMessage().equalsIgnoreCase("newmap")) {
                e.getPlayer().sendMessage("Creating new map step: ");
                step.put(e.getPlayer(), 0);
                e.setCancelled(true);
            }


        }

    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (inSetupMode.contains(e.getPlayer())) {

        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (inSetupMode.contains(e.getPlayer())) {

        }
    }


}
