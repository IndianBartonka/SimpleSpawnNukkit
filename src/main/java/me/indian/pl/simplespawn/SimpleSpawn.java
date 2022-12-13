package me.indian.pl.simplespawn;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import me.indian.SimpleSpawn.komendyAdmin.SetSpawnCommand;
import me.indian.pl.simplespawn.commands.SpawnCommand;
import me.indian.pl.simplespawn.listeners.SpawnListener;
import me.indian.pl.simplespawn.utils.LocationUtil;

public class SimpleSpawn extends PluginBase {

    private static SimpleSpawn instance;
    public SimpleSpawn() {
        instance = this;
    }
    public static SimpleSpawn getInstance() {
        return instance;
    }

    @Override
    public void onEnable(){
        saveDefaultConfig();
        PluginManager pm = getServer().getPluginManager();
        ((PluginCommand<?>) this.getCommand("setspawn")).setExecutor(new SetSpawnCommand(this));
        ((PluginCommand<?>) this.getCommand("spawn")).setExecutor(new SpawnCommand(this));
        pm.registerEvents(new SpawnListener(this), this);
        new LocationUtil();

    }

}