package net.wqrld.Ferox.Listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import net.wqrld.Ferox.Main;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.*;
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
    public void onleave(PlayerQuitEvent e) {
        if (TeamManager.getred().contains(e.getPlayer())) {
            TeamManager.getred().remove(e.getPlayer());
        }

        if (TeamManager.getblue().contains(e.getPlayer())) {
            TeamManager.getblue().remove(e.getPlayer());
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (event.getMessage().contains("%")) {
            event.getPlayer().sendMessage("Please don't use a % sign in your message.");
            return;
        }
        Player player = event.getPlayer();
        String color = "§f";
        if (TeamManager.getblue().contains(player)) {
            color = "§9";
        }
        if (TeamManager.getred().contains(player)) {
            color = "§c";
        }


        String oldformat = color + "%luckperms_meta_prefix%§r" + color + "%player_name%§7> §f";
        String format = PlaceholderAPI.setPlaceholders(event.getPlayer(), oldformat) + event.getMessage();
        format = format.replace("%§", "%%§");
        //.replace("&", "§");
        event.setFormat(format);



    }


}
