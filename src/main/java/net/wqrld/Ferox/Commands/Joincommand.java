package net.wqrld.Ferox.Commands;

import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Joincommand implements CommandExecutor {
   // World world = Bukkit.getWorld("Spawn");
    //Location loc = new Location(world, 648, 26, -523);
     //   e.getPlayer().teleport(loc);
   World world = Bukkit.getWorld("zenith");
    public void addtored(CommandSender sender){
        TeamManager.getred().add((Player) sender);
        ((Player) sender).teleport(new Location(world, 42.5, 10, -148.5, 1, 1));

        MatchManager.givearmor((Player) sender, Color.RED);

        sender.sendMessage("Joined §c§lRED");
    }
    public void addtoblue(CommandSender sender){
        TeamManager.getblue().add((Player) sender);

        ((Player) sender).teleport(new Location(world, 42.5, 10, -32.5, 179, 1));
        MatchManager.givearmor((Player) sender, Color.BLUE);
        sender.sendMessage("Joined §9§lBLUE");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
if(TeamManager.getblue().contains(sender) || TeamManager.getred().contains(sender)){
    sender.sendMessage("Your already in a team.");
    return true;
}
        ((Player) sender).getInventory().clear();
        if(TeamManager.getblue().size() > TeamManager.getred().size()){//up red
            addtored(sender);
        }else if(TeamManager.getred().size() > TeamManager.getblue().size()){//up blue
            addtoblue(sender);
        }else {//random
            if (Math.random() < 0.5) {
                addtored(sender);
            } else {
                addtoblue(sender);
            }
        }
Integer spectators = Bukkit.getOnlinePlayers().size() - TeamManager.getblue().size() - TeamManager.getred().size();

        ((Player) sender).setGameMode(GameMode.SURVIVAL);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit halcyon " + sender.getName());
        ((Player) sender).getInventory().addItem(new ItemStack(Material.ARROW, 64));
        sender.sendMessage("§7§m§l---------------- §r§cFerox§6Mc§7§m§l ----------------");
        sender.sendMessage("§cRed: " + TeamManager.getred().size() + " §r§8| §9blue: " + TeamManager.getblue().size() + " §r§8| §bspecators: " + spectators);
        sender.sendMessage("§lGoal: §r§7Destroy the enemy nexuses.");
        return true;


    }

}
