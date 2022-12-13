package me.indian.SimpleSpawn.komendyAdmin;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.*;
import cn.nukkit.level.Location;
import cn.nukkit.scheduler.NukkitRunnable;
import me.indian.pl.simplespawn.SimpleSpawn;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.level.Location;
import me.indian.pl.simplespawn.SimpleSpawn;
import me.indian.pl.simplespawn.utils.ChatColor;
import me.indian.pl.simplespawn.utils.LocationUtil;


import java.util.ArrayList;
import java.util.List;

public class SetSpawnCommand implements CommandExecutor {

    private final SimpleSpawn plugin;

    public SetSpawnCommand(SimpleSpawn plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;


        if (!sender.hasPermission("simplespawn.admin")) {
            sender.sendMessage(plugin.getConfig().getString("Perms.admin"));
            return false;
        }
        if (sender instanceof Player) {

            double x = p.getX();
            double y = p.getY();
            double z = p.getZ();

            LocationUtil.saveLocation(x, y , z);

            p.sendMessage(ChatColor.replaceColorCode(plugin.getConfig().getString("Spawn.set")));
        } else {
            sender.sendMessage(plugin.getConfig().getString("Player.not-be"));
        }
        return true;
    }
}
