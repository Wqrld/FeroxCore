package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Managers.TeamManager;
import net.wqrld.Ferox.Types.Gamemap;
import net.wqrld.Ferox.Utils.AreaUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Spawnprotection implements Listener {

    private boolean isSpawnArea(Location l) {
        Gamemap currentMap = RotationManager.GetCurrentMap();
        return AreaUtils.iswithin(l, currentMap.getSpawnarea1(), currentMap.getSpawnarea2()) ||
                AreaUtils.iswithin(l, currentMap.getRedspawnarea1(), currentMap.getRedspawnarea2()) ||
                AreaUtils.iswithin(l, currentMap.getBluespawnarea1(), currentMap.getBluespawnarea2());

    }

    @EventHandler
    public void blockbreak(BlockBreakEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }

        if (!TeamManager.getblue().contains(e.getPlayer()) && !TeamManager.getred().contains(e.getPlayer())) {
            e.getPlayer().sendMessage("§c§lPlease join using /join.");
            e.setCancelled(true);
            return;
        }

        if (isSpawnArea(e.getBlock().getLocation())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("You cannot build here");
        }

    }

    @EventHandler
    public void blockplace(BlockPlaceEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
            return;
        }

        if (!TeamManager.getblue().contains(e.getPlayer()) && !TeamManager.getred().contains(e.getPlayer())) {
            e.getPlayer().sendMessage("§c§lPlease join using /join.");
            e.setCancelled(true);
            return;
        }

        if (isSpawnArea(e.getBlock().getLocation())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("You cannot build here");
        }


    }


}
