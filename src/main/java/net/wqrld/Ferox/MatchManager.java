package net.wqrld.Ferox;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public class MatchManager{

    public static boolean gamestarted = true;
//e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke the §9§lBlue§r nexus!");
static World world = Bukkit.getWorld("zenith");

public static void givearmor(Player p, Color c){
    ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
    ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
    ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
    ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
    meta.setColor(c);
    helmet.setItemMeta(meta);
    chest.setItemMeta(meta);
    leggings.setItemMeta(meta);
    boots.setItemMeta(meta);
    p.getInventory().setHelmet(helmet);
    p.getInventory().setChestplate(chest);
    p.getInventory().setLeggings(leggings);
    p.getInventory().setBoots(boots);


}



    public static void endgame(){
        //lock joining
        gamestarted = false;
        Bukkit.broadcastMessage("§9Game ended");
        for(Player p : Bukkit.getOnlinePlayers()){
            if(teammanager.red.contains(p) || teammanager.blue.contains(p)){
                p.getInventory().clear();
                p.teleport(new Location(world, 89.5, 28, -90.5, 90, 1));
            }
        }

        new BukkitRunnable() {
            @Override public void run() {
                Bukkit.broadcastMessage("§9Starting reset...");
                MatchManager.resetmap();

            }
        }.runTaskLater(Main.plugin, 60);

        teammanager.getblue().clear();
        teammanager.getred().clear();
    }
    public static void resetmap(){
        //reset map and then

        Bukkit.broadcastMessage("§9resetting map");

        File file = new File("zenith.schematic");
        Vector to = new Vector(0, 0, 0);
        MatchManager.startgame();
        try {
            EditSession editSession = ClipboardFormat.findByFile(file).load(file).paste(BukkitUtil.getLocalWorld(Bukkit.getWorld("zenith")), to);
        }catch(IOException err){
            //noop
        }




    }
    public static void startgame(){
        //open up joining
        gamestarted = true;
        Bukkit.broadcastMessage("§9Game started");
    }


}
