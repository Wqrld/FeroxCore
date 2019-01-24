package net.wqrld.Ferox.Listeners;

import net.wqrld.Ferox.Main;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinListener implements Listener{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage("You were sent to spawn");
        World world = Bukkit.getWorld("zenith");
        Location loc = new Location(world, 89.5, 28, -90.5, 90, 1);
        e.getPlayer().teleport(loc);
        ItemStack i = new ItemStack(Material.COMPASS);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName("Click to join");
        i.setItemMeta(meta);
        //https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fredditpublic.com%2Fimages%2Fb%2Fb2%2FItems_slot_number.png&f=1
        e.getPlayer().getInventory().setItem(0, i);

    }
    @EventHandler
    public void onRightCLick(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem().getType() == Material.COMPASS){
            Bukkit.dispatchCommand(e.getPlayer(), "join");
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void blockbreak(BlockBreakEvent e){
        if(!TeamManager.getblue().contains(e.getPlayer()) && !TeamManager.getred().contains(e.getPlayer()) && !e.getPlayer().getName().equals("Xirial")){
e.getPlayer().sendMessage("§c§lPlease join using /join.");
//            e.getPlayer().sendMessage("red: " + TeamManager.getred().toString());
//            e.getPlayer().sendMessage("blue: " + TeamManager.getblue().toString());
//            e.getPlayer().sendMessage(e.getPlayer().toString());
           e.setCancelled(true);
        }
        if(MatchManager.iswithin(e.getBlock().getLocation(), new Location(world, 82, 20, -81), new Location(world, 94, 35, -100))){
            e.setCancelled(true);
            e.getPlayer().sendMessage("You cannot build here");
        }
    }
    World world = Bukkit.getWorld("zenith");
    @EventHandler
    public void respawn(PlayerRespawnEvent e){
        new BukkitRunnable() {
            @Override public void run() {
                if(TeamManager.getred().contains(e.getPlayer())){

                    e.getPlayer().teleport(new Location(world, 42.5, 10, -148.5, 1, 1));
                    MatchManager.givearmor(e.getPlayer(), Color.RED);
                } else if (TeamManager.getblue().contains(e.getPlayer())) {
                    e.getPlayer().teleport(new Location(world, 42.5, 10, -32.5, 179, 1));
                    MatchManager.givearmor(e.getPlayer(), Color.BLUE);
                }else{
                    e.getPlayer().teleport(new Location(world, 89.5, 28, -90.5, 90, 1));

                }
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit halcyon " + e.getPlayer().getName());
                e.getPlayer().getInventory().addItem(new ItemStack(Material.ARROW, 64));

            }
        }.runTaskLater(Main.plugin, 10);




    }

}
