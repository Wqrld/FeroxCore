package net.wqrld.Ferox.Commands;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import net.wqrld.Ferox.Managers.RotationManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;

public class Pastemap implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            sender.sendMessage("Pasting map");
            sender.sendMessage("index:" + RotationManager.getIndex() + " | " + RotationManager.GetCurrentMap().getName() + " currentmap|nextmap " + RotationManager.GetNextMap().getName() + " |new " + RotationManager.getIndex());

            pastemap();

            return true;
        } else {
            sender.sendMessage("Your not OP");
            return false;
        }
    }

    public static void pastemap() {
        File file = new File(RotationManager.GetCurrentMap().getName() + ".schematic");
        Vector to = new Vector(0, 0, 0);

        try {
            ClipboardFormat.findByFile(file).load(file).paste(BukkitUtil.getLocalWorld(RotationManager.GetCurrentMap().getRedspawn().getWorld()), to);
        } catch (IOException | NullPointerException err) {
            //noop
            Bukkit.getConsoleSender().sendMessage(err.getMessage());
        }
    }
}