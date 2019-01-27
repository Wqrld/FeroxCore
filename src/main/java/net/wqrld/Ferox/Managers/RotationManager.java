package net.wqrld.Ferox.Managers;

import net.wqrld.Ferox.Types.Gamemap;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class RotationManager {


    static ArrayList<Gamemap> maps = new ArrayList();

    public static ArrayList<Gamemap> getmaps() {
        return maps;
    }

    private static Integer index = 0;
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


    public static ArrayList<Gamemap> getMaps() {
        return maps;
    }

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
        Integer i = index;
        if (i + 1 > maps.size() - 1) {
            i = -1;
            //overflow
        }
        return maps.get(i + 1);
    }


}
