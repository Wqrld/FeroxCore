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

public class MatchManager {

    private static Boolean red1broken, blue1broken, red2broken, blue2broken;
    public static boolean gamestarted = true;
//e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke the §9§lBlue§r nexus!");


    public static void givearmor(Player p, Color c) {
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

    public static void breaknexus(String nexus) {
        if (nexus.equalsIgnoreCase("rednexus1")) {
            red1broken = true;
        }
        if (nexus.equalsIgnoreCase("bluenexus1")) {
            blue1broken = true;
        }
        if (nexus.equalsIgnoreCase("rednexus2")) {
            red2broken = true;
        }
        if (nexus.equalsIgnoreCase("bluenexus2")) {
            blue2broken = true;
        }
    }

    public static Boolean isBroken(String nexus) {
        if (nexus == "rednexus1" && red1broken) {
            return true;
        }
        if (nexus == "rednexus2" && red2broken) {
            return true;
        }
        if (nexus == "bluenexus1" && blue1broken) {
            return true;
        }
        if (nexus == "bluenexus2" && blue2broken) {
            return true;
        }

        return false;
    }


    public static void endgame() {
        ItemStack i = new ItemStack(Material.COMPASS);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "Click to join");
        i.setItemMeta(meta);
        //lock joining
        gamestarted = false;
        Bukkit.broadcastMessage("§9Game ended");

        blue1broken = false;
        blue2broken = false;
        red1broken = false;
        red2broken = false;
        for (Player p : Bukkit.getOnlinePlayers()) {

          //  p.teleport(RotationManager.GetCurrentMap().getLocation("Spawn"));

            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            p.getInventory().clear();
            p.setGameMode(GameMode.SPECTATOR);
            //https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fredditpublic.com%2Fimages%2Fb%2Fb2%2FItems_slot_number.png&f=1


        }
        TeamManager.getblue().clear();
        TeamManager.getred().clear();

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("§9Starting reset...");
                for (Player p : Bukkit.getOnlinePlayers()) {

                    p.teleport(RotationManager.GetNextMap().getLocation("Spawn"));

                    p.getInventory().setHelmet(null);
                    p.getInventory().setChestplate(null);
                    p.getInventory().setLeggings(null);
                    p.getInventory().setBoots(null);
                    p.getInventory().clear();
                    p.setGameMode(GameMode.SURVIVAL);

                    p.getInventory().setItem(0, i);
                    p.setHealth(20);

                }

                MatchManager.resetmap();


            }
        }.runTaskLater(Main.plugin, 140);

    }

    public static void resetmap() {
        //reset map and then

        Bukkit.broadcastMessage("§9resetting map");
        Bukkit.broadcastMessage("§9Last map: " + RotationManager.GetCurrentMap().getName());
        Bukkit.broadcastMessage("§9Next map: " + RotationManager.GetNextMap().getName());


        File file = new File(RotationManager.GetCurrentMap().getName() + ".schematic");
        Vector to = new Vector(0, 0, 0);

        try {
            EditSession editSession = ClipboardFormat.findByFile(file).load(file).paste(BukkitUtil.getLocalWorld(RotationManager.GetCurrentMap().getLocation("redspawn").getWorld()), to);
        } catch (IOException err) {
            //noop
            Bukkit.getConsoleSender().sendMessage(err.getMessage());
        }


        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("§9Reset done.");
                RotationManager.upindex();
                startgame();

            }
        }.runTaskLater(Main.plugin, 40);




    }

    public static void startgame() {
        //open up joining
        gamestarted = true;
        Bukkit.broadcastMessage("§9Game started");
    }


}
