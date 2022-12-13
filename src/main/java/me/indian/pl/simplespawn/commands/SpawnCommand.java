package me.indian.pl.simplespawn.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.level.Location;
import cn.nukkit.scheduler.NukkitRunnable;
import me.indian.pl.simplespawn.SimpleSpawn;
import me.indian.pl.simplespawn.utils.ChatColor;
import me.indian.pl.simplespawn.utils.LocationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpawnCommand implements CommandExecutor, Listener {

    private final SimpleSpawn plugin;
    public static List<UUID> tpPlayers = new ArrayList<UUID>();

    public SpawnCommand(SimpleSpawn plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Location location = LocationUtil.getSpawnLocation();
            if (args.length == 0) {
                    tpPlayers.add(p.getUniqueId());
                    p.sendMessage(ChatColor.replaceColorCode(plugin.getConfig().getString("Spawn.teleport-in")));
                    new NukkitRunnable() {
                        @Override
                        public void run() {
                            if (tpPlayers.contains(p.getUniqueId())) {
                                p.teleport(location);
                                p.sendMessage(ChatColor.replaceColorCode(plugin.getConfig().getString("Spawn.Message")));
                                tpPlayers.remove(p.getUniqueId());
                            } else {
                                if(plugin.getConfig().getBoolean("Spawn.cancel-on-move")) {
                                    cancel();
                                }
                            }
                        }
                    }.runTaskLater(plugin, 20 * plugin.getConfig().getInt("Telport-cooldown"));
            } else {
                if (p.hasPermission("simplespawn.admin")) {
                    Player cel = (Player) Server.getInstance().getPlayer(args[0]);
                    if (cel != null) {
                            cel.teleport(location);
                            cel.sendMessage(ChatColor.replaceColorCode(plugin.getConfig().getString("Spawn.admin-tp")));
                            p.sendMessage(ChatColor.replaceColorCode(plugin.getConfig().getString("Spawn.tp-player").replace("<cel>", cel.getName())));
                    } else {
                        p.sendMessage(ChatColor.replaceColorCode(plugin.getConfig().getString("Player.null")));
                    }
                } else {
                    p.sendMessage(ChatColor.replaceColorCode(plugin.getConfig().getString("Perms.admin")));
                }
            }
        } else {
            sender.sendMessage(ChatColor.replaceColorCode(plugin.getConfig().getString("Player.not-be")));
        }
        return true;
    }


}
