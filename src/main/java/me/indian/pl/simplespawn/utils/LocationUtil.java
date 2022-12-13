package me.indian.pl.simplespawn.utils;

import cn.nukkit.Server;
import cn.nukkit.level.Location;
import me.indian.pl.simplespawn.SimpleSpawn;

public class LocationUtil {

    private static SimpleSpawn plugin;

    public LocationUtil(){
        plugin = SimpleSpawn.getInstance();
    }

    public static void saveLocation(double x , double y, double z){
        plugin.getConfig().set("Spawn-coords.x", x);
        plugin.getConfig().set("Spawn-coords.y", y);
        plugin.getConfig().set("Spawn-coords.z", z);
        plugin.getConfig().save();
    }
    public static Location getSpawnLocation(){
        Location loc = new Location(0 , 90 ,0);
        Server.getInstance().getLevelByName("");
        double x = plugin.getConfig().getDouble("Spawn-coords.x");
        double y = plugin.getConfig().getDouble("Spawn-coords.y");
        double z = plugin.getConfig().getDouble("Spawn-coords.z");
        loc = new Location(x, y, z);


        return loc;
    }
}
