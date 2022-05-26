package net.citybuildnetwork.core.bootstrap.spigot.commands;

import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.player.CBPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
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
public class TeleportHereCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (commandSender instanceof Player) {

            final Player player = (Player) commandSender;
            final CBPlayer cbPlayer = new CBPlayer(player.getName(), player.getUniqueId());

            if (player.hasPermission("flamingfactory.administration.teleport")) {

                if (args.length == 0) {
                    cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "tphere (Player)");
                } else if (args.length == 1) {
                    String name = args[0];

                    if (Bukkit.getPlayer(name) == null) {
                        cbPlayer.sendPlayerNotFoundMessageToSpigotPlayer(player);
                        return true;
                    }

                    Bukkit.getPlayer(name).teleport(player);
                    cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Du hast §b§o" + name + " §7zu §b§odir §7teleportiert§8.");
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 10,10);

                } else if (args.length >= 3) {
                    cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "tphere (Player)");
                }

            } else {
                cbPlayer.sendNoPermissionMessageToSpigotPlayer(player);
            }

        }
        return false;
    }
}
