package net.wqrld.Ferox;

import net.wqrld.Ferox.Commands.*;
import net.wqrld.Ferox.Listeners.*;
import net.wqrld.Ferox.Managers.MatchManager;
import net.wqrld.Ferox.Managers.PLH;
import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Types.Gamemap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class Main extends JavaPlugin  implements Listener {

    private Connection connection;

    public static Statement statement;
    public static Main plugin;

    public FileConfiguration fileConfiguration;
    private String prefix = "&8[&eFeroxCore&8] ";


    static Plugin getPlugin(){
        return plugin;
    }

    @Override
    public void onEnable() {
        //Fired when the server enables the plugin
        getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage(prefix + "&e - Wqrld#7373 - loaded");
        createConfig();
        this.getCommand("join").setExecutor(new Joincommand());
        this.getCommand("pastemap").setExecutor(new Pastemap());
        this.getCommand("debugmap").setExecutor(new Debugmap());
        this.getCommand("skipmap").setExecutor(new Skipmap());
        this.getCommand("stats").setExecutor(new Stats());
        this.getCommand("setup").setExecutor(new Setup());
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
        getServer().getPluginManager().registerEvents(new Spawnprotection(), this);
        getServer().getPluginManager().registerEvents(new RespawnHandler(), this);
        getServer().getPluginManager().registerEvents(new FriendlyFire(), this);
        getServer().getPluginManager().registerEvents(new ChatHandler(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new Joincommand(), this);
        getServer().getPluginManager().registerEvents(new LeafListener(), this);
        getServer().getPluginManager().registerEvents(new WeatherListener(), this);
        getServer().getPluginManager().registerEvents(new FoodListener(), this);
        getServer().getPluginManager().registerEvents(new HungerListener(), this);
        getServer().getPluginManager().registerEvents(new MapSetup(), this);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.broadcastMessage("placeholderapi found");
            new PLH().register();
        }


//        try {
//            HttpURLConnection con = (HttpURLConnection) new URL("https://wqrld.net/maps.json").openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String text = in.lines().collect(Collectors.joining());
//            Bukkit.broadcastMessage(text);
//            JSONObject o = new JSONObject(text);
//            JSONArray j = o.getJSONArray("maps");
//            for (int i = 0; i < j.length(); i++) {
//                Bukkit.broadcastMessage(j.getJSONObject(i).getString("Name"));
//            }
//
//            con.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }




        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    openConnection();
                    statement = connection.createStatement();
                } catch(ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        };

        runnable.runTaskAsynchronously(this);

        World Sahara = Bukkit.getWorld("Sahara");
        RotationManager.addmap(
                new Gamemap("Xirial",//author
                        "Sahara",//name
                        2,

                        new Location(Sahara, -66, 65, -1, -90, 1),//redspawn
                        new Location(Sahara, 65.5, 65, -1, 90, 1),//bluespawn
                        new Location(Sahara, -0.5, 91, -71, 1, 1),//spawn

                        new Location(Sahara, -69, 71, 4),//redspawn1
                        new Location(Sahara, -66, 63, -6),//redspawn2

                        new Location(Sahara, 67, 71, -5),//bluespawn1
                        new Location(Sahara, 63, 63, 4),//bluespawn2

                        new Location(Sahara, -9, 96, -77),//spawn1
                        new Location(Sahara, 8, 84, -66),//spawn2

                        new Location(Sahara, -50, 67, 47),//rednexus1
                        new Location(Sahara, 48, 67, -49),//bluenexus1

                        new Location(Sahara, -50, 67, -49),//rednexus2
                        new Location(Sahara, 48, 67, 47)//bluenexus2
                ));


        World Castle = Bukkit.getWorld("Castle");
        RotationManager.addmap(
                new Gamemap("Xirial",//author
                        "Castle",//name
                        2,

                        new Location(Castle, -93.5, 64, 1, -90, 1),//redspawn
                        new Location(Castle, 94, 64, 1, 90, 1),//bluespawn
                        new Location(Castle, 0.5, 87, -61, 1, 1),//spawn

                        new Location(Castle, -90, 69, -4),//redspawn1
                        new Location(Castle, -97, 62, 6),//redspawn2

                        new Location(Castle, 90, 69, -4),//bluespawn1
                        new Location(Castle, 96, 62, -6),//bluespawn2

                        new Location(Castle, 7, 94, -65),//spawn1
                        new Location(Castle, -10, 81, -52),//spawn2

                        new Location(Castle, -94, 74, 31),//rednexus1
                        new Location(Castle, 94, 74, 31),//bluenexus1

                        new Location(Castle, -94, 74, -31),//rednexus2
                        new Location(Castle, 94, 74, -31)//bluenexus2
                ));

        World Sphinx = Bukkit.getWorld("Sphinx");
        RotationManager.addmap(
                new Gamemap("Xirial",//author
                        "Sphinx",//name
                        2,

                        new Location(Sphinx, -136.5, 88, -1.5, -90, 1),//redspawn
                        new Location(Sphinx, -32.5, 88, -1.5, 90, 1),//bluespawn
                        new Location(Sphinx, -84.5, 112, -68.5, 1, 1),//spawn

                        new Location(Sphinx, -131, 87, -7),//redspawn1
                        new Location(Sphinx, -141, 93, 5),//redspawn2

                        new Location(Sphinx, -37, 87, -6),//bluespawn1
                        new Location(Sphinx, -27, 93, 5),//bluespawn2

                        new Location(Sphinx, -80, 107, -61),//spawn1
                        new Location(Sphinx, -88,117,-73),//spawn2

                        new Location(Sphinx, -176,97,26),//rednexus1
                        new Location(Sphinx, 6,97,26),//bluenexus1

                        new Location(Sphinx, -176, 97, -30),//rednexus2
                        new Location(Sphinx, 6,97,-30)//bluenexus2
                ));

        World Fallen = Bukkit.getWorld("Fallen");
        RotationManager.addmap(
                new Gamemap("Xirial",//author
                        "Fallen",//name
                        3,

                        new Location(Fallen, 271, 66, -121, 90, 1),//redspawn
                        new Location(Fallen, 120, 66, -121, -90, 1),//bluespawn
                        new Location(Fallen, 195, 90, -170, 1, 1),//spawn

                        new Location(Fallen, 274, 64, -119),//redspawn1
                        new Location(Fallen, 270, 69, -123),//redspawn2

                        new Location(Fallen, 115, 64, -123),//bluespawn1
                        new Location(Fallen, 120, 69, -119),//bluespawn2

                        new Location(Fallen, 205, 94, -176),//spawn1
                        new Location(Fallen, 184, 85, -167),//spawn2

                        new Location(Fallen, 249, 70, -146),//rednexus1
                        new Location(Fallen, 141, 70, -96),//bluenexus1

                        new Location(Fallen, 249, 70, -96),//rednexus2
                        new Location(Fallen, 141, 70, -146),//bluenexus2

                new Location(Fallen, 310, 71, -121),//reddnexus3
                new Location(Fallen, 80, 71, -121)//bluenexus3

                ));
//        World aardvark = Bukkit.getWorld("aardvark");
//        RotationManager.addmap(
//                new Gamemap("Xirial",//author
//                        "aardvark",//name
//                        2,
//
//                        new Location(aardvark, -16.5, 36, -14.5, 135, 1),//redspawn
//                        new Location(aardvark, -120.5, 36, -118.5, -50, 1),//bluespawn
//                        new Location(aardvark, -46.5, 43, -93.5, -89, 1),//spawn
//
//                        new Location(aardvark, -9, 41, -25),//redspawn1
//                        new Location(aardvark, -26, 26, -7),//redspawn2
//
//                        new Location(aardvark, -125, 41, -110),//bluespawn1
//                        new Location(aardvark, -109, 26, -125),//bluespawn2
//
//                        new Location(aardvark, -48, 48, -89),//spawn1
//                        new Location(aardvark, -41, 39, -98),//spawn2
//
//                        new Location(aardvark, -67, 24, -25),//rednexus1
//                        new Location(aardvark, -71, 24, -109),//bluenexus1
//
//                        new Location(aardvark, -27, 24, -65),//rednexus2
//                        new Location(aardvark, -111, 24, 69)//bluenexus2
//                ));
        /*
        World marina = Bukkit.getWorld("Marina");
        RotationManager.addmap(
                new Gamemap("Xirial",//author
                        "marina",//name
                        2,

                        new Location(marina, 9.5, 19, 28.5, -90, 1),//redspawn
                        new Location(marina, 101.5, 19, 28.5, 90, 1),//bluespawn
                        new Location(marina, 55.5, 32, -26.5, 1, 1),//spawn

                        new Location(marina, 20, 18, 24),//redspawn1
                        new Location(marina, 5, 26, 34),//redspawn2

                        new Location(marina, 92, 26, 23),//bluespawn1
                        new Location(marina, 104, 18, 36),//bluespawn2

                        new Location(marina, 44, 41, -20),//spawn1
                        new Location(marina, 70, 22, -43),//spawn2

                        new Location(marina, 21, 23, 4),//rednexus1
                        new Location(marina, 89, 23, 52),//bluenexus1

                        new Location(marina, 21, 23, 52),//rednexus2
                        new Location(marina, 89, 23, 4)//bluenexus2
                ));

        //TODO Automate from like a json file
        //  public Gamemap(String author, String name, Location redspawn, Location bluespawn, Location spawn, Area redspawnarea, Area bluespawnarea, Area spawnarea) {
        World world = Bukkit.getWorld("zenith");
        RotationManager.addmap(
                new Gamemap("Xirial",//author
                        "zenith",//name
                        1,

                        new Location(world, 42.5, 10, -148.5, 1, 1),//redspawn
                        new Location(world, 42.5, 10, -32.5, 179, 1),//bluespawn
                        new Location(world, 89.5, 28, -90.5, 90, 1),//spawn

                        new Location(world, 38, 5, -144),//redspawn1
                        new Location(world, 46, 32, -153),//redspawn2

                        new Location(world, 38, 5, -46),//bluespawn1
                        new Location(world, 46, 32, -27),//bluespawn2

                        new Location(world, 82, 20, -81),//spawn1
                        new Location(world, 94, 35, -100),//spawn2

                        new Location(world, 42, 12, -127),//rednexus
                        new Location(world, 42, 12, -55)//bluenexus

                ));


        World meadows = Bukkit.getWorld("meadows");
        RotationManager.addmap(
                new Gamemap("Xirial",//author
                        "meadows",//name
                        1,

                        new Location(meadows, -67.5, 111, -177.5, 1, 1),//redspawn
                        new Location(meadows, -67.5, 111, -35.5, 179, 1),//bluespawn
                        new Location(meadows, -27.5, 123, -106.5, 90, 1),//spawn

                        new Location(meadows, -58, 124, -182),//redspawn1
                        new Location(meadows, -75, 100, -170),//redspawn2

                        new Location(meadows, -58, 100, -42),//bluespawn1
                        new Location(meadows, -75, 124, -31),//bluespawn2

                        new Location(meadows, -21, 115, -98),//spawn1
                        new Location(meadows, -32, 123, -114),//spawn2

                        new Location(meadows, -68, 110, -152),//rednexus
                        new Location(meadows, -68, 110, -62)//bluenexus,
                ));



        World palmrust = Bukkit.getWorld("palm_rust");
        RotationManager.addmap(
                new Gamemap("Xirial",//author
                        "palmrust",//name
                        2,

                        new Location(palmrust, -16.5, 36, -14.5, 135, 1),//redspawn
                        new Location(palmrust, -120.5, 36, -118.5, -50, 1),//bluespawn
                        new Location(palmrust, -46.5, 43, -93.5, -89, 1),//spawn

                        new Location(palmrust, -9, 41, -25),//redspawn1
                        new Location(palmrust, -26, 26, -7),//redspawn2

                        new Location(palmrust, -125, 41, -110),//bluespawn1
                        new Location(palmrust, -109, 26, -125),//bluespawn2

                        new Location(palmrust, -48, 48, -89),//spawn1
                        new Location(palmrust, -41, 39, -98),//spawn2

                        new Location(palmrust, -67, 24, -25),//rednexus1
                        new Location(palmrust, -71, 24, -109),//bluenexus1

                        new Location(palmrust, -27, 24, -65),//rednexus2
                        new Location(palmrust, -111, 24, -69)//bluenexus2
                ));
         */

        MatchManager.setCurrentMVWorld(Main.plugin.getConfig().getString("worldID"));
        RotationManager.setIndex(Main.plugin.getConfig().getInt("mapindex"));

    }
    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.getConfig().getString("mysqlhost") + "/" + this.getConfig().getString("mysqldatabase") + "?autoReconnect=true", this.getConfig().getString("mysqluser"), this.getConfig().getString("mysqlpassword"));
        }
    }

    public Main() {
        plugin = this;
    }


    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");

            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                 saveDefaultConfig();
                YamlConfiguration.loadConfiguration(file);
            } else {
                getLogger().info("Config.yml found, loading!");
                fileConfiguration = YamlConfiguration.loadConfiguration(file);
                Bukkit.getConsoleSender().sendMessage(prefix + "&e - " + this.getConfig().getString("mysqlhost"));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }



}
