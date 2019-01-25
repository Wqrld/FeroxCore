package net.wqrld.Ferox.Managers;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import net.wqrld.Ferox.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
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
        ItemStack i = new ItemStack(Material.COMPASS);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName("Click to join");
        i.setItemMeta(meta);
        //lock joining
        gamestarted = false;
        Bukkit.broadcastMessage("§9Game ended");
        for(Player p : Bukkit.getOnlinePlayers()){
            if(TeamManager.getred().contains(p) || TeamManager.getblue().contains(p)){
                p.getInventory().clear();
                p.teleport(new Location(world, 89.5, 28, -90.5, 90, 1));

                //https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fredditpublic.com%2Fimages%2Fb%2Fb2%2FItems_slot_number.png&f=1
                p.getInventory().setItem(0, i);
            }
        }

        new BukkitRunnable() {
            @Override public void run() {
                Bukkit.broadcastMessage("§9Starting reset...");
                MatchManager.resetmap();

            }
        }.runTaskLater(Main.plugin, 60);

        TeamManager.getblue().clear();
        TeamManager.getred().clear();
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
public static Boolean iswithin(Location original, Location loc1, Location loc2) {
    double xMin, yMin, zMin;
    double xMax, yMax, zMax;
    if (loc1.getX() > loc2.getX()) {
        xMax = loc1.getX();
        xMin = loc2.getX();
    } else {
        xMax = loc2.getX();
        xMin = loc1.getX();
    }
    if (loc1.getY() > loc2.getY()) {
        yMax = loc1.getY();
        yMin = loc2.getY();
    } else {
        yMax = loc2.getY();
        yMin = loc1.getY();
    }
    if (loc1.getZ() > loc2.getZ()) {
        zMax = loc1.getZ();
        zMin = loc2.getZ();
    } else {
        zMax = loc2.getZ();
        zMin = loc1.getZ();
    }
    if (original.getX() < xMax && original.getX() > xMin) {
        if (original.getY() < yMax && original.getY() > yMin) {
            if (original.getZ() < zMax && original.getZ() > zMin) {
//within region
                return true;
            }
        }
    }

return false;
}

}
