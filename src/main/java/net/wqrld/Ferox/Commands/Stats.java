package net.wqrld.Ferox.Commands;

import net.wqrld.Ferox.Main;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.RotationManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Stats implements CommandExecutor {
    // World world = Bukkit.getWorld("Spawn");
    //Location loc = new Location(world, 648, 26, -523);
    //   e.getPlayer().teleport(loc);
    //WorldEditPlugin we = new WorldEditPlugin();

    //  public BukkitImplAdapter adapter = we.getInstance().getBukkitImplAdapter();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
       if(args.length == 1){
           player = Bukkit.getPlayer(args[0]);
       }

       
        UUID uuid = player.getUniqueId();
        try {
            ResultSet result = Main.statement.executeQuery("SELECT * FROM Stats where uuid = '" + uuid + "'");
            if(result.next()){
                sender.sendMessage(ChatColor.DARK_GRAY + "======== Stats for player " + ChatColor.WHITE + player.getName() + ChatColor.DARK_GRAY + " ===============");
                sender.sendMessage(ChatColor.GRAY + "Kills: " + ChatColor.WHITE + result.getInt("kills"));
                sender.sendMessage(ChatColor.GRAY + "Deaths: " + ChatColor.WHITE + result.getInt("deaths"));
                sender.sendMessage(ChatColor.GRAY + "Momuments: " + ChatColor.WHITE + result.getInt("monuments"));
                sender.sendMessage(ChatColor.GRAY + "Match kills: " + ChatColor.WHITE + result.getInt("matchkills"));
                sender.sendMessage(ChatColor.GRAY + "Match deaths: " + ChatColor.WHITE + result.getInt("matchdeaths"));
                sender.sendMessage(ChatColor.GRAY + "Arrows shot: " + ChatColor.WHITE + result.getInt("arrowsshot"));
                sender.sendMessage(ChatColor.GRAY + "Arrows hit: " + ChatColor.WHITE + result.getInt("arrowshit"));
                sender.sendMessage(ChatColor.GRAY + "Wins: " + ChatColor.WHITE + result.getInt("wins"));
                sender.sendMessage(ChatColor.GRAY + "Losses: " + ChatColor.WHITE + result.getInt("loses"));
                sender.sendMessage(ChatColor.DARK_GRAY + "===========================================");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return true;

    }

}