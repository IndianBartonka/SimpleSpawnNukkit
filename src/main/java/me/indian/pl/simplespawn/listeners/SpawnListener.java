package me.indian.pl.simplespawn.listeners;


import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.level.Location;
import me.indian.pl.simplespawn.SimpleSpawn;
import me.indian.pl.simplespawn.utils.ChatColor;
import me.indian.pl.simplespawn.utils.LocationUtil;

import static me.indian.pl.simplespawn.commands.SpawnCommand.tpPlayers;

public class SpawnListener implements Listener {

    private final SimpleSpawn plugin;

    public SpawnListener(SimpleSpawn plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void OnPlayerRespawn(PlayerRespawnEvent e) {
        Player p = (Player) e.getPlayer();
        Location location = LocationUtil.getSpawnLocation();

        if (e.isFirstSpawn()) {
            e.setRespawnPosition(location);
        }
            if(plugin.getConfig().getBoolean("Spawn.spawn-after-death-always")) {
               e.setRespawnPosition(location);
            } else {
                if (!e.getRespawnPosition().isValid()) {
                    e.setRespawnPosition(location);
                }
            }
    }

    @EventHandler
    public void moveOnTeleport(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (tpPlayers.contains(p.getUniqueId())) {
            tpPlayers.remove(p.getUniqueId());
            p.sendMessage(ChatColor.replaceColorCode(plugin.getConfig().getString("Spawn.canceled-teleport")));
        }
    }
}
