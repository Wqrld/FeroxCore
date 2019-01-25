package net.wqrld.Ferox.Managers;

import net.wqrld.Ferox.Types.Gamemap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RotationManager {


    static ArrayList<Gamemap> maps = new ArrayList();
    ;

    public static ArrayList<Gamemap> getmaps() {
        return maps;
    }


    public static void addmap(Gamemap m) {
        maps.add(m);
        Bukkit.broadcastMessage("map " + m.getName() + " loaded.");
    }

    public static void upindex() {
        if (index + 1 > maps.size() - 1) {
            index = 0;
        } else {
            index++;
        }
    }


    static Integer index = 0;

    public static Integer getIndex() {
        return index;
    }

    public static Gamemap getMap(Integer i) {

        return maps.get(i);

    }

    public static Gamemap CurrentMap() {
        return maps.get(index);
    }

    public static Gamemap NextMap() {//1
        if (index + 1 > maps.size() - 1) {
            index = -1;
            //overflow
        }
        return maps.get(index + 1);
    }


}
