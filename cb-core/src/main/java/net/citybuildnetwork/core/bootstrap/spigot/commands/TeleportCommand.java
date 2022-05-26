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
public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (commandSender instanceof Player) {

            final Player player = (Player) commandSender;
            final CBPlayer cbPlayer = new CBPlayer(player.getName(), player.getUniqueId());

            if (player.hasPermission("flamingfactory.administration.teleport")) {

                if (args.length == 0) {
                    player.teleport(Bukkit.getOnlinePlayers().stream().skip((int) (Bukkit.getOnlinePlayers().size() * Math.random())).findFirst().orElse(null).getLocation());
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 10,10);
                    cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Du wurdest zu einem §e§ozufälligen Spieler §7teleportiert§8.");
                } else if (args.length == 1) {
                    String name = args[0];

                    if (Bukkit.getPlayer(name) == null) {
                        cbPlayer.sendPlayerNotFoundMessageToSpigotPlayer(player);
                        return true;
                    }

                    player.teleport(Bukkit.getPlayer(name));
                    cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Du wurdest zu §e§o" + name + " §7teleportiert§8.");

                } else if (args.length >= 3) {
                cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "teleport (Player)");
            }

            } else {
                cbPlayer.sendNoPermissionMessageToSpigotPlayer(player);
            }

        }

        return false;
    }
}
