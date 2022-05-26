package net.citybuildnetwork.core.bootstrap.spigot.commands;

import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.database.DataBaseConnector;
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
public class VanishCommand implements CommandExecutor {
    private DataBaseConnector dataBaseConnector = new DataBaseConnector();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(!(commandSender instanceof Player))
            return true;

        final Player player = (Player) commandSender;
        final CBPlayer cbPlayer = new CBPlayer(player.getName(), player.getUniqueId());

        if (player.hasPermission("frozenfactory.administration.vanish")) {
            if (args.length == 0) {
                if (dataBaseConnector.getFromTable("base_users", "uuid", player.getUniqueId().toString(), "isVanished").equalsIgnoreCase("true")) {

                    cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Du bist nun §cnicht §7mehr im §b§oVanish§8-§b§oMode§8!");
                    //player.setInvisible(false);

                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.showPlayer(player);
                    }


                    dataBaseConnector.deleteTableColumn("base_users", "uuid", player.getUniqueId().toString());
                    dataBaseConnector.updateSqlConnection("INSERT INTO base_users (uuid, username, isVanished) VALUES ('" + player.getUniqueId() + "','" + player.getName() + "','" + "false" + "')");

                } else {
                    cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Du bist nun im §b§oVanish§8-§b§oMode§8!");
                    //player.setInvisible(true);


                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (!all.hasPermission("flamingfactory.administration.showvanishedplayers")) {
                            all.hidePlayer(player);
                        }
                    }

                    dataBaseConnector.deleteTableColumn("base_users", "uuid", player.getUniqueId().toString());
                    dataBaseConnector.updateSqlConnection("INSERT INTO base_users (uuid, username, isVanished) VALUES ('" + player.getUniqueId() + "','" + player.getName() + "','" + "true" + "')");
                }

            } else {
                cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "vanish");
            }
        } else {
            cbPlayer.sendNoPermissionMessageToSpigotPlayer(player);
        }

        return false;
    }
}
