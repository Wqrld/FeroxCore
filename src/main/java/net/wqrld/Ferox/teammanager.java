package net.wqrld.Ferox;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class teammanager {
    static ArrayList<Player> red = new ArrayList();;

    public static ArrayList<Player> getred(){
        return red;
    }

    public static ArrayList<Player> blue = new ArrayList();;
    public static ArrayList<Player> getblue(){
        return (ArrayList<Player>) teammanager.blue;
    }

}
