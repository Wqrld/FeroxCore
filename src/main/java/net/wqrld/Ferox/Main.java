package net.wqrld.Ferox;


import net.wqrld.Ferox.Commands.Joincommand;
import net.wqrld.Ferox.Commands.Pastemap;
import net.wqrld.Ferox.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.broadcastMessage("placeholderapi found");
            new PLH().register();
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
                Bukkit.getConsoleSender().sendMessage(prefix + "&e - " + this.getConfig().getString("test"));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }



}
