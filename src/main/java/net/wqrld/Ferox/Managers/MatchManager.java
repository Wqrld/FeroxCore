package net.wqrld.Ferox.Managers;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import net.wqrld.Ferox.Main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
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
                p.teleport(RotationManager.NextMap().getLocation("Spawn"));

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
        Bukkit.broadcastMessage("§9Last map: " + RotationManager.CurrentMap().getName());
        Bukkit.broadcastMessage("§9Next map: " + RotationManager.NextMap().getName());


        File file = new File(RotationManager.CurrentMap().getName() + ".schematic");
        Vector to = new Vector(0, 0, 0);

        try {
            EditSession editSession = ClipboardFormat.findByFile(file).load(file).paste(BukkitUtil.getLocalWorld(Bukkit.getWorld(RotationManager.CurrentMap().getName())), to);
        }catch(IOException err){
            //noop
        }
        RotationManager.upindex();
        MatchManager.startgame();


    }
    public static void startgame(){
        //open up joining
        gamestarted = true;
        Bukkit.broadcastMessage("§9Game started");
    }


}
