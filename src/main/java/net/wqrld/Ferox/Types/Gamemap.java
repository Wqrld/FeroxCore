package net.wqrld.Ferox.Types;

import org.bukkit.Location;

public class Gamemap {

    private String author, name;

    public String getAuthor() {
        return author;
    }

    public Location getRedspawn() {
        return redspawn;
    }

    public Location getBluespawn() {
        return bluespawn;
    }

    public Location getSpawn() {
        return spawn;
    }

    public Location getRedspawnarea1() {
        return redspawnarea1;
    }

    public Location getRedspawnarea2() {
        return redspawnarea2;
    }

    public Location getBluespawnarea1() {
        return bluespawnarea1;
    }

    public Location getBluespawnarea2() {
        return bluespawnarea2;
    }

    public Location getSpawnarea1() {
        return spawnarea1;
    }

    public Location getSpawnarea2() {
        return spawnarea2;
    }

    public Location getRednexus() {
        return rednexus;
    }

    public Location getBluenexus() {
        return bluenexus;
    }

    public Location getRednexus2() {
        return rednexus2;
    }

    public Location getBluenexus2() {
        return bluenexus2;
    }

    public Location getRednexus3() {
        return rednexus3;
    }

    public Location getBluenexus3() {
        return bluenexus3;
    }

    public String getName() {
        return name;
    }

    public Integer getNexuscount() {
        return nexuscount;
    }

    private final Location redspawn, bluespawn, spawn;
    private final Location redspawnarea1, redspawnarea2, bluespawnarea1, bluespawnarea2, spawnarea1, spawnarea2;
    private Location rednexus, bluenexus, rednexus2, bluenexus2, rednexus3, bluenexus3;
    private final Integer nexuscount;

    /**
     * A new game map with two nexuses per team
     * @param author the map author's name
     * @param name name of the map
     * @param nexuscount amount of nexusses
     * @param redspawn red spawn location
     * @param bluespawn blue spawn location
     * @param spawn main spawn location
     * @param redspawnarea1 red spawn bound 1
     * @param redspawnarea2 red spawn bound 2
     * @param bluespawnarea1 blue spawn bound 1
     * @param bluespawnarea2 blue spawn bound 2
     * @param spawnarea1 main spawn bound 1
     * @param spawnarea2 main spawn bound 2
     * @param rednexus first red nexus location
     * @param bluenexus first blue nexus location
     * @param rednexus2 second red nexus location
     * @param bluenexus2 second blue nexus location
     */
    public Gamemap(String author, String name, Integer nexuscount, Location redspawn, Location bluespawn, Location spawn,
                   Location redspawnarea1, Location redspawnarea2, Location bluespawnarea1, Location bluespawnarea2, Location spawnarea1, Location spawnarea2, Location rednexus, Location bluenexus, Location rednexus2, Location bluenexus2) {
        this(author, name, nexuscount, redspawn, bluespawn, spawn,
                redspawnarea1, redspawnarea2, bluespawnarea1, bluespawnarea2, spawnarea1, spawnarea2, rednexus, bluenexus);
        this.rednexus2 = rednexus2;
        this.bluenexus2 = bluenexus2;
    }

    /**
     * A new game map with three nexuses per team
     * @param author the map author's name
     * @param name name of the map
     * @param nexuscount amount of nexusses
     * @param redspawn red spawn location
     * @param bluespawn blue spawn location
     * @param spawn main spawn location
     * @param redspawnarea1 red spawn bound 1
     * @param redspawnarea2 red spawn bound 2
     * @param bluespawnarea1 blue spawn bound 1
     * @param bluespawnarea2 blue spawn bound 2
     * @param spawnarea1 main spawn bound 1
     * @param spawnarea2 main spawn bound 2
     * @param rednexus first red nexus location
     * @param bluenexus first blue nexus location
     * @param rednexus2 second red nexus location
     * @param bluenexus2 second blue nexus location
     * @param rednexus3 third red nexus location
     * @param bluenexus3 third blue nexus location
     */
    public Gamemap(String author, String name, Integer nexuscount, Location redspawn, Location bluespawn, Location spawn,
                   Location redspawnarea1, Location redspawnarea2, Location bluespawnarea1, Location bluespawnarea2, Location spawnarea1, Location spawnarea2, Location rednexus, Location bluenexus, Location rednexus2, Location bluenexus2, Location rednexus3, Location bluenexus3) {

        this(author, name, nexuscount, redspawn, bluespawn, spawn,
                redspawnarea1, redspawnarea2, bluespawnarea1, bluespawnarea2, spawnarea1, spawnarea2, rednexus, bluenexus, rednexus2, bluenexus2);
        this.rednexus3 = rednexus3;
        this.bluenexus3 = bluenexus3;
    }

    /**
     * A new game map with a single nexus per team
     * @param author the map author's name
     * @param name name of the map
     * @param nexuscount amount of nexusses
     * @param redspawn red spawn location
     * @param bluespawn blue spawn location
     * @param spawn main spawn location
     * @param redspawnarea1 red spawn bound 1
     * @param redspawnarea2 red spawn bound 2
     * @param bluespawnarea1 blue spawn bound 1
     * @param bluespawnarea2 blue spawn bound 2
     * @param spawnarea1 main spawn bound 1
     * @param spawnarea2 main spawn bound 2
     * @param rednexus red nexus location
     * @param bluenexus blue nexus location
     */
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


}
