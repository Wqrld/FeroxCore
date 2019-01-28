package net.wqrld.Ferox;

import net.wqrld.Ferox.Commands.Joincommand;
import net.wqrld.Ferox.Commands.Pastemap;
import net.wqrld.Ferox.Listeners.*;
import net.wqrld.Ferox.Managers.PLH;
import net.wqrld.Ferox.Managers.RotationManager;
import net.wqrld.Ferox.Types.Gamemap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class Main extends JavaPlugin  implements Listener {

    public static Main plugin;

    public FileConfiguration fileConfiguration;
    private String prefix = "&8[&eFeroxCore&8] ";
    @Override
    public void onEnable() {
        //Fired when the server enables the plugin
        getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage(prefix + "&e - Wqrld#7373 - loaded");
        createConfig();
        this.getCommand("join").setExecutor(new Joincommand());
        this.getCommand("pastemap").setExecutor(new Pastemap());
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
        getServer().getPluginManager().registerEvents(new Spawnprotection(), this);
        getServer().getPluginManager().registerEvents(new RespawnHandler(), this);
        getServer().getPluginManager().registerEvents(new ChatHandler(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new Joincommand(), this);
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.broadcastMessage("placeholderapi found");
            new PLH().register();
        }


        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://wqrld.net/maps.json").openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String text = in.lines().collect(Collectors.joining());
            Bukkit.broadcastMessage(text);
            JSONObject o = new JSONObject(text);
            JSONArray j = o.getJSONArray("maps");
            for (int i = 0; i < j.length(); i++) {
                Bukkit.broadcastMessage(j.getJSONObject(i).getString("Name"));
            }

            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }


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
                        new Location(meadows, -68, 110, -62)//bluenexus

                ));














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
                Bukkit.getConsoleSender().sendMessage(prefix + "&e - " + this.getConfig().getString("test"));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }



}
