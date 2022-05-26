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
public class VanishCommand implements CommandExecutor {

    private boolean isVanished = true;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(!(commandSender instanceof Player))
            return true;

        final Player player = (Player) commandSender;
        final CBPlayer cbPlayer = new CBPlayer(player.getName(), player.getUniqueId());

        if(args.length == 0) {
            if(!isVanished) {
                isVanished = true;
                cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Du bist nun im §e§oVanish§8-§e§oMode§8!");
                player.setInvisible(true);
            } else {
                isVanished = false;
                cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Du bist nun §cnicht §7mehr im §e§oVanish§8-§e§oMode§8!");
                player.setInvisible(false);
            }

        } else {
            cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "vanish");
        }

        return false;
    }
}
