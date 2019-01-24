package net.wqrld.Ferox.Managers;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TeamManager {
    static ArrayList<Player> red = new ArrayList();;
    static ArrayList<Player> blue = new ArrayList();;


    public static ArrayList<Player> getred(){
        return red;
    }
    public static ArrayList<Player> getblue(){
        return blue;
    }

}
