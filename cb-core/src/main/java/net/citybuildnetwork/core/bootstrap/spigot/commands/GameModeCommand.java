package net.citybuildnetwork.core.bootstrap.spigot.commands;

import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.player.CBPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * JavaDoc this file!
 * Created: 26.05.2022
 *
 * @author ImLxsse (lasse.huenermund@gmx.de)
 */
public class GameModeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (commandSender instanceof Player) {

            final Player player = (Player) commandSender;
            final CBPlayer cbPlayer = new CBPlayer(player.getName(), player.getUniqueId());

            if (player.hasPermission("flamingfactory.administration.coins")) {
                if (args.length == 0) {
                    cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "gamemode survival - creative - spectator (Player)");
                } else if (args.length == 1) {
                    if (args[1].equalsIgnoreCase("survival") || args[1].equalsIgnoreCase("0")) {
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Dein §6");
                    }
                }
            } else if (!player.hasPermission("flamingfactory.administration.coins")) {
                cbPlayer.sendNoPermissionMessageToSpigotPlayer(player);
            }

        }

        return false;
    }
}
