package net.wqrld.Ferox;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
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
    public void onRightCLick(InventoryClickEvent e){
        if(e.isRightClick() && e.getCurrentItem().getType() == Material.COMPASS){
            Bukkit.dispatchCommand(e.getWhoClicked(), "join");
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void blockbreak(BlockBreakEvent e){
        if(teammanager.getblue().contains(e.getPlayer()) || teammanager.getred().contains(e.getPlayer()) || e.getPlayer().getName() == "Xirial"){

e.getPlayer().sendMessage("§c§lPlease join using /join.");

        }
    }
    World world = Bukkit.getWorld("zenith");
    @EventHandler
    public void respawn(PlayerInteractEvent e){
        new BukkitRunnable() {
            @Override public void run() {
                if(teammanager.getred().contains(e.getPlayer())){

                    e.getPlayer().teleport(new Location(world, 42.5, 10, -148.5, 1, 1));
                    MatchManager.givearmor(e.getPlayer(), Color.RED);
                } else if (teammanager.getblue().contains(e.getPlayer())) {
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
