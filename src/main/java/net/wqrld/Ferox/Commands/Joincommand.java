package net.wqrld.Ferox.Commands;

import net.jpountz.lz4.LZ4Utils;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Joincommand implements CommandExecutor, Listener {
   // World world = Bukkit.getWorld("Spawn");
    //Location loc = new Location(world, 648, 26, -523);
     //   e.getPlayer().teleport(loc);

    public void addtored(CommandSender sender){
        TeamManager.getred().add((Player) sender);

        ((Player) sender).teleport(RotationManager.GetCurrentMap().getLocation("redspawn"));

        MatchManager.givearmor((Player) sender, Color.RED);
        MatchManager.giveitems((Player) sender);

        sender.sendMessage("Joined §c§lRED");
    }
    public void addtoblue(CommandSender sender){
        TeamManager.getblue().add((Player) sender);

        ((Player) sender).teleport(RotationManager.GetCurrentMap().getLocation("bluespawn"));
        MatchManager.givearmor((Player) sender, Color.BLUE);
        MatchManager.giveitems((Player) sender);
        sender.sendMessage("Joined §9§lBLUE");
    }

    public void autojoin(CommandSender sender) {

        if (TeamManager.getblue().size() > TeamManager.getred().size()) {//up red
            addtored(sender);
        } else if (TeamManager.getred().size() > TeamManager.getblue().size()) {//up blue
            addtoblue(sender);
        } else {//random
            if (Math.random() < 0.5) {
                addtored(sender);
            } else {
                addtoblue(sender);
            }
        }
    }


    //https://i.wqrld.net/Wqrld_ei
    //https://i.wqrld.net/Wqrld_eY
    public void joinmessage(CommandSender sender) {
        Integer spectators = Bukkit.getOnlinePlayers().size() - TeamManager.getblue().size() - TeamManager.getred().size();
        sender.sendMessage("§7§m§l---------------- §r§cFerox§6Mc§7§m§l ----------------");
        sender.sendMessage("§cRed: " + TeamManager.getred().size() + " §r§8| §9blue: " + TeamManager.getblue().size() + " §r§8| §bspecators: " + spectators);
        sender.sendMessage("§lGoal: §r§7Destroy the enemy nexuses.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (TeamManager.getblue().contains(sender) || TeamManager.getred().contains(sender)) {
            sender.sendMessage("Your already in a team.");
            return true;
        }


        Inventory inv = Bukkit.createInventory(null, 9, "Join");


        ItemStack random = new ItemStack(Material.WOOL);
        ItemMeta meta = random.getItemMeta();
        meta.setDisplayName("§a§lJoin a random team.");
        random.setItemMeta(meta);
        inv.setItem(4, random);


        ItemStack red = new ItemStack(Material.WOOL, 1, (byte) 14);
        ItemMeta redmeta = red.getItemMeta();
        redmeta.setDisplayName("§c§lJoin RED");
        red.setItemMeta(redmeta);
        inv.setItem(2, red);

        ItemStack blue = new ItemStack(Material.WOOL, 1, (byte) 11);
        ItemMeta bluemeta = blue.getItemMeta();
        bluemeta.setDisplayName("§9§lJoin blue");
        blue.setItemMeta(bluemeta);
        inv.setItem(6, blue);


        ((Player) sender).openInventory(inv);
        return true;

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equalsIgnoreCase("Join")) {

          if(e.getCurrentItem().getType() == Material.WOOL) {
              e.getWhoClicked().getInventory().clear();
              e.getWhoClicked().setGameMode(GameMode.SURVIVAL);
              e.getWhoClicked().setHealth(20);
              Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit halcyon " + e.getWhoClicked().getName());



              if (e.getCurrentItem().getType() == Material.WOOL && e.getCurrentItem().getDurability() == 0) {
                  autojoin(e.getWhoClicked());
                  joinmessage(e.getWhoClicked());
              } else if (e.getCurrentItem().getType() == Material.WOOL && e.getCurrentItem().getDurability() == 14) {
                  if (e.getWhoClicked().isOp()) {
                      addtored(e.getWhoClicked());
                      joinmessage(e.getWhoClicked());
                  } else {
                      e.getWhoClicked().sendMessage("§cYou do not have the required rank to do this.");
                  }
              } else if (e.getCurrentItem().getType() == Material.WOOL && e.getCurrentItem().getDurability() == 11) {
                  if (e.getWhoClicked().isOp()) {
                      addtoblue(e.getWhoClicked());
                      joinmessage(e.getWhoClicked());
                  } else {
                      e.getWhoClicked().sendMessage("§cYou do not have the required rank to do this.");
                  }
              }
              e.setCancelled(true);
              e.getWhoClicked().closeInventory();
          }



        }

    }
}
