package net.wqrld.Ferox;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Joincommand implements CommandExecutor {
   // World world = Bukkit.getWorld("Spawn");
    //Location loc = new Location(world, 648, 26, -523);
     //   e.getPlayer().teleport(loc);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
if(teammanager.getblue().contains(sender) || teammanager.getred().contains(sender)){
    sender.sendMessage("Your already in a team.");
    return true;
}
sender.sendMessage("joining...");
Player s = (Player) sender;
double random = Math.random();
        World world = Bukkit.getWorld("zenith");


        ((Player) sender).getInventory().clear();
if (random < 0.5){
    teammanager.getblue().add(s);

    ((Player) sender).teleport(new Location(world, 42.5, 10, -32.5, 179, 1));
    ((Player) sender).setGameMode(GameMode.SURVIVAL);
    MatchManager.givearmor((Player) sender, Color.BLUE);
    sender.sendMessage("Joined BLUE");


    //red
}else{
    teammanager.getred().add(s);
    ((Player) sender).teleport(new Location(world, 42.5, 10, -148.5, 1, 1));

    ((Player) sender).setGameMode(GameMode.SURVIVAL);
    MatchManager.givearmor((Player) sender, Color.RED);
    sender.sendMessage("Joined RED");

}

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kit halcyon " + sender.getName());
        sender.sendMessage("§7§m§l---------------- §r§cFerox§6Mc§7§m§l ----------------");
        sender.sendMessage("§cRed: 0 §r§8| §9blue: 0 §r§8| §bspecators: 1");
        sender.sendMessage("§lGoal: §r§7Destroy the enemy nexuses.");
        
        return true;


    }

}
