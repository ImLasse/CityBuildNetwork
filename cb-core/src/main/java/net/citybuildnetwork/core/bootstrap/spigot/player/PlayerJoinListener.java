package net.citybuildnetwork.core.bootstrap.spigot.player;

import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.database.DataBaseConnector;
import net.citybuildnetwork.core.player.CBPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    final DataBaseConnector dataBaseConnector = new DataBaseConnector();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConnect(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if(Core.getPlayerPropertyHandler().getProperty(player.getUniqueId()) == null) {
            Core.getPlayerPropertyHandler().setDefault(player.getUniqueId());
        }

        final CBPlayer cbplayer = new CBPlayer(player.getName(), player.getUniqueId());

        if(dataBaseConnector.getFromTable("base_users", "uuid", player.getUniqueId().toString(), "isVanished").equalsIgnoreCase("true")) {
            player.setInvisible(true);
        } else {
            player.setInvisible(false);
        }

    }
}
