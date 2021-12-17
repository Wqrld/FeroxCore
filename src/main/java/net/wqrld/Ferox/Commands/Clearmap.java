package net.wqrld.Ferox.Commands;

import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.RotationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Clearmap implements CommandExecutor {
    // World world = Bukkit.getWorld("Spawn");
    //Location loc = new Location(world, 648, 26, -523);
    //   e.getPlayer().teleport(loc);
    //WorldEditPlugin we = new WorldEditPlugin();

    //  public BukkitImplAdapter adapter = we.getInstance().getBukkitImplAdapter();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            Player player = (Player) sender;
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
            sender.sendMessage("debug map");
            sender.sendMessage("index:" + RotationManager.getIndex() + " | " + RotationManager.GetCurrentMap().getName() + " currentmap|nextmap " + RotationManager.GetNextMap().getName() + " |nexuscount " + RotationManager.GetCurrentMap().getNexuscount());
            for (int i = 0; i < RotationManager.getMaps().size(); i++) {
                sender.sendMessage(RotationManager.getMaps().get(i).getName());
            }
            sender.sendMessage("---------------------------------------------------");
            sender.sendMessage("looking at:" + player.getTargetBlock(null, 20).getLocation().toString());
            sender.sendMessage("---------nexusstatusses-----------------------------");
            sender.sendMessage(MatchManager.getNexusStatusses());
            return true;

        } else {
            sender.sendMessage("Your not OP");
            return false;
        }
    }
}