package net.wqrld.Ferox.Utils;

import org.bukkit.Location;

public class AreaUtils {
    public static Boolean iswithin(Location original, Location loc1, Location loc2) {
        double xMin, yMin, zMin;
        double xMax, yMax, zMax;
        if (loc1.getX() > loc2.getX()) {
            xMax = loc1.getX();
            xMin = loc2.getX();
        } else {
            xMax = loc2.getX();
            xMin = loc1.getX();
        }
        if (loc1.getY() > loc2.getY()) {
            yMax = loc1.getY();
            yMin = loc2.getY();
        } else {
            yMax = loc2.getY();
            yMin = loc1.getY();
        }
        if (loc1.getZ() > loc2.getZ()) {
            zMax = loc1.getZ();
            zMin = loc2.getZ();
        } else {
            zMax = loc2.getZ();
            zMin = loc1.getZ();
        }
        if (original.getX() < xMax && original.getX() > xMin) {
            if (original.getY() < yMax && original.getY() > yMin) {
                if (original.getZ() < zMax && original.getZ() > zMin) {
//within region
                    return true;
                }
            }
        }

        return false;
    }
}
