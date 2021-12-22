package net.wqrld.Ferox.Managers;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import net.wqrld.Ferox.Commands.Pastemap;
import net.wqrld.Ferox.Main;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MatchManager {
    private static boolean red1broken, blue1broken, red2broken, blue2broken, red3broken, blue3broken;
    private static Date matchstart = new Date();
    public static boolean gamestarted = true;
    public static String lastwinner = "Red";

    private static MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");

    public static String getNextWorld(){
        if(CurrentMVWorld == "world1"){
            return "world2";
        }else{
            return "world1";
        }
    }

public static String switchWorlds(){
    CurrentMVWorld = getNextWorld();
    Main.plugin.getConfig().set("worldID", CurrentMVWorld);
    Main.plugin.saveConfig();
    return CurrentMVWorld;
}

    public static String getCurrentMVWorld() {
        return CurrentMVWorld;
    }

    public static World getCurrentMVBukkitWorld(){
        return Bukkit.getWorld(CurrentMVWorld);
    }

    public static World getNextMVBukkitWorld(){
        return Bukkit.getWorld(getNextWorld());
    }

    public static void setCurrentMVWorld(String currentMVWorld) {
        CurrentMVWorld = currentMVWorld;
        Main.plugin.getConfig().set("worldID", CurrentMVWorld);
        Main.plugin.saveConfig();
    }



    public static String CurrentMVWorld = Main.plugin.getConfig().getString("worldID");

//TODO title on win / hotbar
    public static void setwinner(String winner) {
        lastwinner = winner;
    }

    public static String getwinner() {
        return lastwinner;
    }
//e.getPlayer().getServer().broadcastMessage(e.getPlayer().getDisplayName() + " broke the §9§lBlue§r nexus!");


    public static void givearmor(Player p, Color c) {
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        //apply color
        LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
        meta.setColor(c);
        helmet.setItemMeta(meta);
        leggings.setItemMeta(meta);
        boots.setItemMeta(meta);
        //place in inventory
        p.getInventory().setHelmet(helmet);
        p.getInventory().setChestplate(chest);
        p.getInventory().setLeggings(leggings);
        p.getInventory().setBoots(boots);
        //apply
        p.updateInventory();
    }

    public static void giveitems(Player p) {
        Inventory inv = p.getInventory();
        //init items
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack axe = new ItemStack(Material.STONE_AXE);
        ItemStack arrows = new ItemStack(Material.ARROW, 32);
        ItemStack logs = new ItemStack(Material.LOG, 64);
        ItemStack glass = new ItemStack(Material.GLASS, 64);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
        //add stuff
        pickaxe.addEnchantment(Enchantment.DIG_SPEED, 1);
        //add to inv
        inv.setItem(0, sword);
        inv.setItem(1, bow);
        inv.setItem(2, pickaxe);
        inv.setItem(3, axe);
        inv.setItem(4, arrows);
        inv.setItem(5, logs);
        inv.setItem(6, glass);
        inv.setItem(7, gapple);
        inv.setItem(8, steak);
        //apply
        p.updateInventory();
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
        if (nexus.equalsIgnoreCase("rednexus3")) {
            red3broken = true;
        }
        if (nexus.equalsIgnoreCase("bluenexus3")) {
            blue3broken = true;
        }
        Bukkit.getConsoleSender().sendMessage("broken nexus " + nexus);
    }

    public static boolean AreAllBroken(String color){
        if(RotationManager.GetCurrentMap().getNexuscount().equals(1)){
            return isBroken(color + "nexus1");
        }
        if(RotationManager.GetCurrentMap().getNexuscount().equals(2)){
            return (isBroken(color + "nexus1") && isBroken(color + "nexus2"));
        }
        if(RotationManager.GetCurrentMap().getNexuscount().equals(3)){
            return (isBroken(color + "nexus1") && isBroken(color + "nexus2") && isBroken(color + "nexus3"));
        }

        return false;
    }

    public static boolean isBroken(String nexus) {
//        Bukkit.getConsoleSender().sendMessage("requested nexus broken status of nexus: " + nexus);
//        Bukkit.getConsoleSender().sendMessage("" + red1broken + " " + red2broken + " " + blue1broken + " " + blue2broken);
        if (nexus.equalsIgnoreCase("rednexus1") && red1broken == true) {
            return true;
        } else if (nexus.equalsIgnoreCase("rednexus2") && red2broken == true) {
            return true;
        } else if (nexus.equalsIgnoreCase("bluenexus1") && blue1broken == true) {
            return true;
        } else if (nexus.equalsIgnoreCase("bluenexus2") && blue2broken == true) {
            return true;
        } else if (nexus.equalsIgnoreCase("rednexus3") && red3broken == true) {
            return true;
        } else if (nexus.equalsIgnoreCase("bluenexus3") && blue3broken == true) {
            return true;
        } else {
            return false;
        }
    }

    private static String formatDuration(long duration) {
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
        if (hours < 1) {
            return String.format("%02d Minutes", minutes, seconds);
        } else {
            return String.format("%02d:%02d", hours, minutes);
        }

    }

    public static String getNexusStatusses() {
        return "" + red1broken + " " + red2broken + " " + blue1broken + " " + blue2broken;
    }

    public static String getMatchTime() {
        return formatDuration(new Date().getTime() - matchstart.getTime());
    }

    public static void Monumentbroken(Player player) {

        if (Bukkit.getOnlinePlayers().size() > 1) {

            try {
                Main.statement.executeUpdate("INSERT INTO Stats VALUES ('" + player.getUniqueId() + "', 0, 0, 1, 0, 0, 0, 0, 0, 0) ON DUPLICATE KEY UPDATE monuments = monuments + 1");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }


    }

    public static void endgame() {
        ItemStack i = new ItemStack(Material.COMPASS);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + "Click to join");
        i.setItemMeta(meta);
        //lock joining
        gamestarted = false;
        Bukkit.broadcastMessage("§9Game ended");

        for (Player p : Bukkit.getOnlinePlayers()) {

            //api met ints ne existe op 1.8


//            if(getwinner() == "Blue"){
//                p.sendTitle(ChatColor.BLUE + "" + ChatColor.BOLD + "Blue " + ChatColor.RESET + ChatColor.GRAY + "has won!", "");
//            }else{
//                p.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "Red " + ChatColor.RESET + ChatColor.GRAY + "has won!", "");
//            }


            //  p.teleport(RotationManager.GetCurrentMap().getLocation("Spawn"));

            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            p.getInventory().clear();
            p.setGameMode(GameMode.SPECTATOR);
            //https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fredditpublic.com%2Fimages%2Fb%2Fb2%2FItems_slot_number.png&f=1
            UUID uuid = p.getUniqueId();
            String query = "";
            if (TeamManager.getblue().contains(p) && getwinner() == "Blue") {
                query = "INSERT INTO Stats VALUES ('" + uuid + "', 0, 0, 0, 0, 0, 0, 0, 1, 0) ON DUPLICATE KEY UPDATE wins = wins + 1";
                p.sendTitle(ChatColor.BLUE + "" + ChatColor.BOLD + "Blue " + ChatColor.RESET + ChatColor.GRAY + "has won!", ChatColor.GREEN + "You win!");
            } else if (TeamManager.getblue().contains(p) && getwinner() == "Red") {
                query = "INSERT INTO Stats VALUES ('" + uuid + "', 0, 0, 0, 0, 0, 0, 0, 0, 1) ON DUPLICATE KEY UPDATE loses = loses + 1";
                p.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "Red " + ChatColor.RESET + ChatColor.GRAY + "has won!", ChatColor.RED + "Better luck next time");
            }else if (TeamManager.getred().contains(p) && getwinner() == "Red") {
                query = "INSERT INTO Stats VALUES ('" + uuid + "', 0, 0, 0, 0, 0, 0, 0, 1, 0) ON DUPLICATE KEY UPDATE wins = wins + 1";
                p.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "Red " + ChatColor.RESET + ChatColor.GRAY + "has won!", ChatColor.GREEN + "You win!");
            }else if (TeamManager.getred().contains(p) && getwinner() == "Blue") {
                query = "INSERT INTO Stats VALUES ('" + uuid + "', 0, 0, 0, 0, 0, 0, 0, 0, 1) ON DUPLICATE KEY UPDATE loses = loses + 1";
                p.sendTitle(ChatColor.BLUE + "" + ChatColor.BOLD + "Blue " + ChatColor.RESET + ChatColor.GRAY + "has won!", ChatColor.RED + "Better luck next time");
            }
            if(!query.equalsIgnoreCase("")){
                try {
                    Main.statement.executeUpdate(query);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }


        }
        TeamManager.getblue().clear();
        TeamManager.getred().clear();

        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("§9Starting reset...");
                blue1broken = false;
                blue2broken = false;
                blue3broken = false;
                red1broken = false;
                red2broken = false;
                red3broken = false;


                MatchManager.resetmap();


            }
        }.runTaskLater(Main.plugin, 100);

    }

    public static void resetmap() {
        //reset map and then

        Bukkit.broadcastMessage("§9resetting map");
        Bukkit.broadcastMessage("§9Last map: " + RotationManager.GetCurrentMap().getName());
        Bukkit.broadcastMessage("§9Next map: " + RotationManager.GetNextMap().getName());


        MVWorldManager worldManager = core.getMVWorldManager();

        if(getCurrentMVWorld() == "world2"){

            // Remove all players before deleting world
            for (Player p : Bukkit.getOnlinePlayers()) {
                if(p.getWorld().getName() == "world1"){
                    p.teleport(new Location(Bukkit.getWorld("world2"), 0, 0, 0));
                }
            }

            worldManager.deleteWorld("world1");
            //copied, newname
            worldManager.cloneWorld(RotationManager.GetNextMap().getName(), "world1");
            worldManager.getMVWorld("world1").setAlias("world1");
        }else{

            // Remove all players before deleting world
            for (Player p : Bukkit.getOnlinePlayers()) {
                if(p.getWorld().getName() == "world2"){
                    p.teleport(new Location(Bukkit.getWorld("world1"), 0, 0, 0));
                }
            }

            worldManager.deleteWorld("world2");
            //copied, newname
            worldManager.cloneWorld(RotationManager.GetNextMap().getName(), "world2");
            worldManager.getMVWorld("world2").setAlias("world2");

        }


      //  Pastemap.pastemap();


        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("§9Reset done.");
                MatchManager.switchWorlds();
                RotationManager.upindex();


                for (Player p : Bukkit.getOnlinePlayers()) {
                    Location nextmap = RotationManager.GetCurrentMap().getLocation("Spawn");
                    nextmap.setWorld(getCurrentMVBukkitWorld());
                    p.teleport(nextmap);

                    p.getInventory().setHelmet(null);
                    p.getInventory().setChestplate(null);
                    p.getInventory().setLeggings(null);
                    p.getInventory().setBoots(null);
                    p.getInventory().clear();
                    p.setGameMode(GameMode.SURVIVAL);

                    p.setHealth(20);
                    p.setFoodLevel(20);

                }



                for (Player p : Bukkit.getOnlinePlayers()) {
                    ItemStack i = new ItemStack(Material.COMPASS);
                    ItemMeta meta = i.getItemMeta();
                    meta.setDisplayName(ChatColor.RESET + "Click to join");
                    i.setItemMeta(meta);
                    p.getInventory().setItem(0, i);
                }
                startgame();

            }
        }.runTaskLater(Main.plugin, 200);


    }

    public static void startgame() {
        //open up joining
        gamestarted = true;
        matchstart = new Date();
        Bukkit.broadcastMessage("§9Game started");
    }


}
