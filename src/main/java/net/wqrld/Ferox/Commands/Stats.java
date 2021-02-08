package net.wqrld.Ferox.Commands;

import net.wqrld.Ferox.Main;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.RotationManager;
import org.bukkit.Bukkit;
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
                sender.sendMessage("======== Stats for player " + player.getName() + " ===========");
                sender.sendMessage("Kills: " + result.getInt("kills"));
                sender.sendMessage("Deaths: " + result.getInt("deaths"));
                sender.sendMessage("Momuments: " + result.getInt("monuments"));
                sender.sendMessage("Match kills: " + result.getInt("matchkills"));
                sender.sendMessage("Match deaths: " + result.getInt("matchdeaths"));
                sender.sendMessage("Arrows shot: " + result.getInt("arrowsshot"));
                sender.sendMessage("Arrows hit: " + result.getInt("arrowshit"));
                sender.sendMessage("Wins: " + result.getInt("wins"));
                sender.sendMessage("Losses: " + result.getInt("loses"));
                sender.sendMessage("===========================================");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return true;

    }

}