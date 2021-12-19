package net.wqrld.Ferox.Managers;

import net.wqrld.Ferox.Main;
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

        Main.plugin.getConfig().set("mapindex", index);
        Main.plugin.saveConfig();
    }

    public static void setIndex(int ind){
        index = ind;
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

    public static Gamemap GetCurrentMap() {
        return maps.get(index);
    }

    public static Gamemap GetNextMap() {//1
        int indexcopy = index;
        if (indexcopy + 1 > maps.size() - 1) {
            indexcopy = -1;
            //overflow
        }
        return maps.get(indexcopy + 1);
    }


}
