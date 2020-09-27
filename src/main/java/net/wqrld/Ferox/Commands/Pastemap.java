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
    // World world = Bukkit.getWorld("Spawn");
    //Location loc = new Location(world, 648, 26, -523);
    //   e.getPlayer().teleport(loc);
    //WorldEditPlugin we = new WorldEditPlugin();

  //  public BukkitImplAdapter adapter = we.getInstance().getBukkitImplAdapter();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        File file = new File("meadows.schematic");
//        Vector to = new Vector(0, 0, 0);
//
//
//        try {
//            EditSession editSession = ClipboardFormat.findByFile(file).load(file).paste(BukkitUtil.getLocalWorld(Bukkit.getWorld("meadows")), to);
//
//        }catch(IOException err){
//            sender.sendMessage("Could not find file");
//        }
        sender.sendMessage("Pasting map");
        sender.sendMessage("index:" + RotationManager.getIndex() + " | " + RotationManager.GetCurrentMap().getName() + " currentmap|nextmap " + RotationManager.GetNextMap().getName() + " |new " + RotationManager.getIndex());

        File file = new File(RotationManager.GetCurrentMap().getName() + ".schematic");
        Vector to = new Vector(0, 0, 0);

        try {
            EditSession editSession = ClipboardFormat.findByFile(file).load(file).paste(BukkitUtil.getLocalWorld(RotationManager.GetCurrentMap().getLocation("redspawn").getWorld()), to);
        } catch (IOException err) {
            //noop
            Bukkit.getConsoleSender().sendMessage(err.getMessage());
        }


return true;
    }
}