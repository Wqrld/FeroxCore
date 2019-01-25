package net.wqrld.Ferox;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.wqrld.Ferox.Managers.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PLH extends PlaceholderExpansion {
    public String getIdentifier() {
        return "ferox";
    }

    public String getAuthor() {
        return "Wqrld";
    }

    public String getVersion() {
        return "1.1.0";
    }

    public String onPlaceholderRequest(Player player, String identifier) {

        // We check if the player is null (not online) before any player-related placeholder
        if (player == null) {
            return null;
        }
        if (identifier.equalsIgnoreCase("isstaff")) {
            return "true";
        }

        if (identifier.equalsIgnoreCase("nexuscount")) {

            return "1";

        }
//✅✅✅✘✔


        if (identifier.equalsIgnoreCase("team")) {

            if (TeamManager.getblue().contains(player)) {
                return "blue";
            } else if (TeamManager.getred().contains(player)) {
                return "red";
            } else {
                return "None";
            }


        }



        if (identifier.equalsIgnoreCase("teams")) {
            return TeamManager.getblue().toString() + " b | r " + TeamManager.getred().toString();
        }
        // Placeholder: %exampleplugin_player_name%
        if (identifier.equalsIgnoreCase("color")) {
            if (TeamManager.getblue().contains(player)) {
                return "§9";
            } else if (TeamManager.getred().contains(player)) {
                return "§c";
            } else {
                return "§f";
            }
        }

        // We return null, if an invalid placeholder was called.
        return "null";
    }


}
