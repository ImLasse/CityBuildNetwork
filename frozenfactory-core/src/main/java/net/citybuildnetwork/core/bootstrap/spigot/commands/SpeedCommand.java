package net.citybuildnetwork.core.bootstrap.spigot.commands;

import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.player.CBPlayer;
import org.bukkit.Bukkit;
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
public class SpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (commandSender instanceof Player) {

            final Player player = (Player) commandSender;
            final CBPlayer cbPlayer = new CBPlayer(player.getName(), player.getUniqueId());

            if (player.hasPermission("frozenfactory.administration.speed")) {

                if (args.length == 0) {
                    cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "speed (Speed | 1-10) (Player)");
                } else if (args.length == 1) {

                    int speed;
                    try {
                        speed = Integer.parseInt(args[0]);
                    } catch (NumberFormatException numberFormatException) {
                        cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "speed (Speed | 1-10) (Player)");
                        return false;
                    }
                    if (speed < 1 || speed > 10) {
                        cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "speed (Speed | 1-10) (Player)");
                        return false;
                    }
                    if (player.isFlying()) {
                        player.setFlySpeed((float) speed / 10);
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Deine §b§oFluggeschwindigkeit §7wurde auf §b§o" + speed + " §7gesetzt§8.");
                    } else {
                        player.setWalkSpeed((float) speed / 10);
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Deine §b§oLaufgeschwindigkeit §7wurde auf §b§o" + speed + " §7gesetzt§8.");
                    }

                } else if(args.length == 2) {

                    String name = args[1];

                    if (Bukkit.getPlayer(name) == null) {
                        cbPlayer.sendPlayerNotFoundMessageToSpigotPlayer(player);
                        return true;
                    }

                    int speed;
                    try {
                        speed = Integer.parseInt(args[0]);
                    } catch (NumberFormatException numberFormatException) {
                        cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "speed (Speed | 1-10) (Player)");
                        return false;
                    }
                    if (speed < 1 || speed > 10) {
                        cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "speed (Speed | 1-10) (Player)");
                        return false;
                    }
                    if (Bukkit.getPlayer(name).isFlying()) {
                        Bukkit.getPlayer(name).setFlySpeed((float) speed / 10);
                        cbPlayer.sendMessageToSpigotPlayer(player, "Du hast die §b§oFluggeschwindigkeit §7von §b§o" + name + " §7auf §b§o" + speed + " §7gesetzt§8.");
                    } else {
                        Bukkit.getPlayer(name).setWalkSpeed((float) speed / 10);
                        cbPlayer.sendMessageToSpigotPlayer(player, "Du hast die §b§oLaufgeschwindigkeit §7von §b§o" + name + " §7auf §b§o" + speed + " §7gesetzt§8.");
                    }

                } else {
                    cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "speed (Speed | 1-10) (Player)");
                }

            } else {
                cbPlayer.sendNoPermissionMessageToSpigotPlayer(player);
            }
        }

        return false;
    }
}
