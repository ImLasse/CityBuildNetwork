package net.citybuildnetwork.core.player;

import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.database.DataBaseConnector;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * JavaDoc this file!
 * Created: 15.05.2022
 *
 * @author ImLxsse (lasse.huenermund@gmx.de)
 */
public class CBPlayer {

    private final String username;
    private final UUID uuid;
    private static HashMap<String, UUID> players = new HashMap<String, UUID>();
    private static HashMap<UUID, String> playerNames = new HashMap<UUID, String>();

    private DataBaseConnector databaseConnector = Core.getDatabaseConnector();

    public CBPlayer(String username, UUID uuid) {
        this.username = username;
        this.uuid = uuid;
        players.put(username, uuid);
        playerNames.put(uuid, username);
    }

    public String getUsername() {
        if(uuid != null) return username;
        return "§cNot found";
    }

    public UUID getUniqueId() {
        if (uuid != null) return uuid;
        return null;
    }

    public void sendMessageToSpigotPlayer(final Player player, String message) {
        player.sendMessage(message);
    }

    public void sendMessageToProxyPlayer(final ProxiedPlayer player, String message) {
        player.sendMessage(message);
    }

    public void sendNoPermissionMessageToProxyPlayer(final ProxiedPlayer proxiedPlayer) {
        proxiedPlayer.sendMessage(Core.getPrefix() + "§7Du bist §cnicht §7berechtigt diesen §eCommand auszuführen§8.");
    }

    public void sendNoPermissionMessageToSpigotPlayer(final org.bukkit.entity.Player player) {
        player.sendMessage(Core.getPrefix() + "§7Du bist §cnicht §7berechtigt diesen §eCommand auszuführen§8.");
    }

    public void sendWrongUsageMessageToProxyPlayer(final ProxiedPlayer player, String commandUsage) {
        player.sendMessage(Core.getPrefix() + "§7Falsche Benutzung§8! §7Bitte benutze §8'§e/" + commandUsage + "§8'");
    }

    public void sendWrongUsageMessageToSpigotPlayer(final org.bukkit.entity.Player player, String commandUsage) {
        player.sendMessage(Core.getPrefix() + "§7Falsche Benutzung§8! §7Bitte benutze §8'§e/" + commandUsage + "§8'");
    }

    public void sendPlayerNotFoundMessageToProxyPlayer(final ProxiedPlayer player) {
        player.sendMessage(Core.getPrefix() + "§7Dieser §eSpieler §7konnte §cnicht §7gefunden werden§8.");
    }

    public void sendPlayerNotFoundMessageToSpigotPlayer(final org.bukkit.entity.Player player) {
        player.sendMessage(Core.getPrefix() + "§7Dieser §eSpieler §7konnte §cnicht §7gefunden werden§8.");
    }

}
