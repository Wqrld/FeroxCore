package net.wqrld.Ferox.Types;

import org.bukkit.Location;

public class Gamemap {

    private String author, name;
    private Location redspawn, bluespawn, spawn;
    private Location redspawnarea1, redspawnarea2, bluespawnarea1, bluespawnarea2, spawnarea1, spawnarea2;
    private Location rednexus, bluenexus, rednexus2, bluenexus2;
    private Integer nexuscount;

    public Gamemap(String author, String name, Integer nexuscount, Location redspawn, Location bluespawn, Location spawn,
                   Location redspawnarea1, Location redspawnarea2, Location bluespawnarea1, Location bluespawnarea2, Location spawnarea1, Location spawnarea2, Location rednexus, Location bluenexus, Location rednexus2, Location bluenexus2) {
        this.author = author;
        this.name = name;
        this.nexuscount = nexuscount;
        this.redspawn = redspawn;
        this.bluespawn = bluespawn;
        this.spawn = spawn;
        this.redspawnarea1 = redspawnarea1;
        this.redspawnarea2 = redspawnarea2;
        this.bluespawnarea1 = bluespawnarea1;
        this.bluespawnarea2 = bluespawnarea2;
        this.spawnarea1 = spawnarea1;
        this.spawnarea2 = spawnarea2;
        this.rednexus = rednexus;
        this.bluenexus = bluenexus;
        this.rednexus2 = rednexus2;
        this.bluenexus2 = bluenexus2;
    }

    public Gamemap(String author, String name, Integer nexuscount, Location redspawn, Location bluespawn, Location spawn,
                   Location redspawnarea1, Location redspawnarea2, Location bluespawnarea1, Location bluespawnarea2, Location spawnarea1, Location spawnarea2, Location rednexus, Location bluenexus) {
        this.author = author;
        this.name = name;
        this.nexuscount = nexuscount;
        this.redspawn = redspawn;
        this.bluespawn = bluespawn;
        this.spawn = spawn;
        this.redspawnarea1 = redspawnarea1;
        this.redspawnarea2 = redspawnarea2;
        this.bluespawnarea1 = bluespawnarea1;
        this.bluespawnarea2 = bluespawnarea2;
        this.spawnarea1 = spawnarea1;
        this.spawnarea2 = spawnarea2;
        this.rednexus = rednexus;
        this.bluenexus = bluenexus;
    }

    public Location getLocation(String what) {
        if (what.equalsIgnoreCase("spawn")) {
            return spawn;
        }
        if (what.equalsIgnoreCase("redspawn")) {
            return redspawn;
        }
        if (what.equalsIgnoreCase("bluespawn")) {
            return bluespawn;
        }
        if (what.equalsIgnoreCase("bluespawnarea1")) {
            return bluespawnarea1;
        }
        if (what.equalsIgnoreCase("bluespawnarea2")) {
            return bluespawnarea2;
        }
        if (what.equalsIgnoreCase("redspawnarea1")) {
            return redspawnarea1;
        }
        if (what.equalsIgnoreCase("redspawnarea2")) {
            return redspawnarea2;
        }
        if (what.equalsIgnoreCase("spawnarea1")) {
            return spawnarea1;
        }
        if (what.equalsIgnoreCase("spawnarea2")) {
            return spawnarea2;
        }
        if (what.equalsIgnoreCase("rednexus")) {
            return rednexus;
        }
        if (what.equalsIgnoreCase("bluenexus")) {
            return bluenexus;
        }
        if (what.equalsIgnoreCase("bluenexus2")) {
            return bluenexus2;
        }
        if (what.equalsIgnoreCase("rednexus2")) {
            return rednexus2;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Integer getNexuscount() {
        return nexuscount;
    }
    //TODO some more fields

}
