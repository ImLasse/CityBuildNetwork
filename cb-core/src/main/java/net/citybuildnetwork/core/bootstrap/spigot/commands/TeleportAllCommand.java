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
public class TeleportAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (commandSender instanceof Player) {

            final Player player = (Player) commandSender;
            final CBPlayer cbPlayer = new CBPlayer(player.getName(), player.getUniqueId());

            if (commandSender instanceof Player) {
                if (player.hasPermission("flamingfactory.administration.tpall")) {

                    if (args.length == 0) {

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (player.getUniqueId().equals(all.getUniqueId())) {
                                continue;
                            }
                            all.teleport(player.getLocation());
                        }

                        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 10,10);
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Du hast alle §e§oSpieler §7zu §e§odir §7teleportiert§8.");

                    } else if (args.length == 1) {
                        String name = args[0];

                        if (Bukkit.getPlayer(name) == null) {
                            cbPlayer.sendPlayerNotFoundMessageToSpigotPlayer(player);
                            return true;
                        }

                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (Bukkit.getPlayer(name).equals(all.getUniqueId())) {
                                continue;
                            }
                            all.teleport(Bukkit.getPlayer(name).getLocation());
                        }

                        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 10,10);
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Du hast alle §e§oSpieler §7zu §e§o" + name + " §7teleportiert§8.");

                    } else {
                        cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "tpall (Player)");
                    }

                } else {
                    cbPlayer.sendNoPermissionMessageToSpigotPlayer(player);
                }

            }
        }

        return false;
    }
}
