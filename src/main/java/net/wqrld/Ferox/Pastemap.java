package net.wqrld.Ferox;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.adapter.BukkitImplAdapter;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.bukkit.BukkitUtil;


import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class Pastemap implements CommandExecutor {
    // World world = Bukkit.getWorld("Spawn");
    //Location loc = new Location(world, 648, 26, -523);
    //   e.getPlayer().teleport(loc);
    //WorldEditPlugin we = new WorldEditPlugin();

  //  public BukkitImplAdapter adapter = we.getInstance().getBukkitImplAdapter();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        File file = new File("zenith.schematic");
        Vector to = new Vector(0, 0, 0);



        try {
            EditSession editSession = ClipboardFormat.findByFile(file).load(file).paste(BukkitUtil.getLocalWorld(Bukkit.getWorld("Spawn")), to);

        }catch(IOException err){
            sender.sendMessage("Could not find file");
        }
        sender.sendMessage("Pasting map");

return true;
    };
}