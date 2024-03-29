package net.wqrld.Ferox.Managers;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import net.wqrld.Ferox.Main;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
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
        if(CurrentMVWorld.equals("world1")){
            return "world2";
        }else{
            return "world1";
        }
    }

public static void switchWorlds(){
    Main.plugin.getServer().getConsoleSender().sendMessage("[F] CW to " + CurrentMVWorld);
    Main.plugin.getServer().getConsoleSender().sendMessage("[F] NW to " + getNextWorld());
    CurrentMVWorld = getNextWorld();
    Main.plugin.getServer().getConsoleSender().sendMessage("[F] switching to " + CurrentMVWorld);
    Main.plugin.getConfig().set("worldID", CurrentMVWorld);
    Main.plugin.saveConfig();
}

    public static String getCurrentMVWorld() {
        return CurrentMVWorld;
    }

    public static World getCurrentMVBukkitWorld(){
        return Bukkit.getWorld(CurrentMVWorld);
    }

//    public static World getNextMVBukkitWorld(){
//        return Bukkit.getWorld(getNextWorld());
//    }

    public static void setCurrentMVWorld(String currentMVWorld) {
        Main.plugin.getServer().getConsoleSender().sendMessage("[F] setCurrentMVWorld " + currentMVWorld);
        CurrentMVWorld = currentMVWorld;
        Main.plugin.getConfig().set("worldID", CurrentMVWorld);
        Main.plugin.saveConfig();
    }



    public static String CurrentMVWorld;

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
        if (nexus.equalsIgnoreCase("rednexus1") && red1broken) {
            return true;
        } else if (nexus.equalsIgnoreCase("rednexus2") && red2broken) {
            return true;
        } else if (nexus.equalsIgnoreCase("bluenexus1") && blue1broken) {
            return true;
        } else if (nexus.equalsIgnoreCase("bluenexus2") && blue2broken) {
            return true;
        } else if (nexus.equalsIgnoreCase("rednexus3") && red3broken) {
            return true;
        } else if (nexus.equalsIgnoreCase("bluenexus3") && blue3broken) {
            return true;
        } else {
            return false;
        }
    }

    private static String formatDuration(long duration) {
        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) % 60;
        if (hours < 1) {
            return String.format("%02d Minutes", minutes);
        } else {
            return String.format("%02d:%02d", hours, minutes);
        }

    }

    public static String getNexusStatuses() {
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

        // Stop people from joining
        gamestarted = false;

        Bukkit.broadcastMessage("§9Game ended");

        // Reset all players
        for (Player p : Bukkit.getOnlinePlayers()) {

            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
            p.getInventory().clear();
            p.setGameMode(GameMode.SPECTATOR);

            UUID uuid = p.getUniqueId();
            String query = "";
            if (TeamManager.getblue().contains(p) && getwinner().equals("Blue")) {
                query = "INSERT INTO Stats VALUES ('" + uuid + "', 0, 0, 0, 0, 0, 0, 0, 1, 0) ON DUPLICATE KEY UPDATE wins = wins + 1";
                p.sendTitle(ChatColor.BLUE + "" + ChatColor.BOLD + "Blue " + ChatColor.RESET + ChatColor.GRAY + "has won!", ChatColor.GREEN + "You win!");
            } else if (TeamManager.getblue().contains(p) && getwinner().equals("Red")) {
                query = "INSERT INTO Stats VALUES ('" + uuid + "', 0, 0, 0, 0, 0, 0, 0, 0, 1) ON DUPLICATE KEY UPDATE loses = loses + 1";
                p.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "Red " + ChatColor.RESET + ChatColor.GRAY + "has won!", ChatColor.RED + "Better luck next time");
            } else if (TeamManager.getred().contains(p) && getwinner().equals("Red")) {
                query = "INSERT INTO Stats VALUES ('" + uuid + "', 0, 0, 0, 0, 0, 0, 0, 1, 0) ON DUPLICATE KEY UPDATE wins = wins + 1";
                p.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "Red " + ChatColor.RESET + ChatColor.GRAY + "has won!", ChatColor.GREEN + "You win!");
            } else if (TeamManager.getred().contains(p) && getwinner().equals("Blue")) {
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


           p.sendMessage(ChatColor.DARK_GRAY + "====== Game Stats for player " + ChatColor.WHITE + p.getName() + ChatColor.DARK_GRAY + " =========");
           p.sendMessage(ChatColor.GRAY + "Kills: " + ChatColor.WHITE + GameStatTracker.matchkills.get(p));
           p.sendMessage(ChatColor.GRAY + "Deaths: " + ChatColor.WHITE + GameStatTracker.matchdeaths.get(p));
           p.sendMessage(ChatColor.GRAY + "Blocks Placed: " + ChatColor.WHITE + GameStatTracker.blocksplaced.get(p));
           p.sendMessage(ChatColor.DARK_GRAY + "========================================");


        }
        TeamManager.getblue().clear();
        TeamManager.getred().clear();
        GameStatTracker.matchkills.clear();
        GameStatTracker.matchdeaths.clear();
        GameStatTracker.blocksplaced.clear();

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

        Bukkit.broadcastMessage("§9resetting map");
        Bukkit.broadcastMessage("§9Last map: " + RotationManager.GetCurrentMap().getName());
        Bukkit.broadcastMessage("§9Next map: " + RotationManager.GetNextMap().getName());

        MVWorldManager worldManager = core.getMVWorldManager();

        if(getCurrentMVWorld().equals("world2")){

            // Remove all players before deleting world
            for (Player p : Bukkit.getOnlinePlayers()) {
                if(p.getWorld().getName().equals("world1")){
                    p.teleport(new Location(Bukkit.getWorld("world2"), 0, 0, 0));
                }
            }

            worldManager.deleteWorld("world1");
            worldManager.cloneWorld(RotationManager.GetNextMap().getName(), "world1");

            // Fix alias as to not interfere with real map
            worldManager.getMVWorld("world1").setAlias("world1");
        }else{

            // Remove all players before deleting world
            for (Player p : Bukkit.getOnlinePlayers()) {
                if(p.getWorld().getName().equals("world2")){
                    p.teleport(new Location(Bukkit.getWorld("world1"), 0, 0, 0));
                }
            }

            worldManager.deleteWorld("world2");
            worldManager.cloneWorld(RotationManager.GetNextMap().getName(), "world2");

            // Fix alias as to not interfere with real map
            worldManager.getMVWorld("world2").setAlias("world2");

        }


        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("§9Reset done.");
                MatchManager.switchWorlds();
                RotationManager.upindex();

                // Reset all players and send them to spawn
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Location nextmap = RotationManager.GetCurrentMap().getSpawn();
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

                    // Give the join compass
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

    /**
     * Opens the game up to new joins
     */
    public static void startgame() {
        // Open up joining
        gamestarted = true;
        matchstart = new Date();
        Bukkit.broadcastMessage("§9Game started");
    }


}
