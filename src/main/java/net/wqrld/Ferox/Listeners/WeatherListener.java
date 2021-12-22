package net.wqrld.Ferox.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if(e.toWeatherState()){ //regen
            e.setCancelled(true);
        }

    }
}
