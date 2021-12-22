package net.wqrld.Ferox.Commands;

import net.wqrld.Ferox.Listeners.MapSetup;
import net.wqrld.Ferox.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Setup implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        if (!player.isOp()) {
            sender.sendMessage("ey, OP only!");
            return false;
        }

        if (MapSetup.inSetupMode.contains(player)) {
            MapSetup.inSetupMode.remove(player);
            sender.sendMessage("§cLeft setup mode");
            sender.sendMessage("§7Type /setup again to restart.");
        } else {
            MapSetup.inSetupMode.add(player);
            sender.sendMessage("§aEntered setup mode");
            sender.sendMessage("§7Type /setup again to exit.");
        }

        sender.sendMessage("§7Current players in setup mode:");
        for (Player p : MapSetup.inSetupMode) {
            sender.sendMessage(p.getDisplayName());
        }


        return true;

    }


}
