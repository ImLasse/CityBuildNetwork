package net.citybuildnetwork.core.bootstrap.spigot.commands;

import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.player.CBPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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
public class GameModeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (commandSender instanceof Player) {

            final Player player = (Player) commandSender;
            final CBPlayer cbPlayer = new CBPlayer(player.getName(), player.getUniqueId());

            if (player.hasPermission("frozenfactory.administration.gamemode")) {
                if (args.length == 0) {
                    cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "gamemode survival - creative - adventure - spectator (Player)");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) {
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Dein §b§oGamemode §7wurde auf §b§osurvival §7gesetzt§8.");
                        player.setGameMode(GameMode.SURVIVAL);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                    } else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Dein §b§oGamemode §7wurde auf §b§ocreative §7gesetzt§8.");
                        player.setGameMode(GameMode.CREATIVE);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                    } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")) {
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Dein §b§oGamemode §7wurde auf §b§oadventure §7gesetzt§8.");
                        player.setGameMode(GameMode.ADVENTURE);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                    } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Dein §b§oGamemode §7wurde auf §b§ospectator §7gesetzt§8.");
                        player.setGameMode(GameMode.SPECTATOR);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                    } else {
                        cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "gamemode survival - creative - adventure - spectator (Player)");
                    }
                } else if (args.length == 2) {
                    String name = args[1];

                    if (Bukkit.getPlayer(name) == null) {
                        cbPlayer.sendPlayerNotFoundMessageToSpigotPlayer(player);
                        return true;
                    }


                    if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) {
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Der §b§oGamemode §7von §b§o" + name + " §7wurde auf §b§osurvival §7gesetzt§8.");
                        cbPlayer.sendMessageToSpigotPlayer(Bukkit.getPlayer(name), Core.getPrefix() + "§7Dein §b§oGamemode §7wurde auf §b§osurvival §7gesetzt§8.");
                        Bukkit.getPlayer(name).setGameMode(GameMode.SURVIVAL);
                        Bukkit.getPlayer(name).playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                    } else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Der §b§oGamemode §7von §b§o" + name + " §7wurde auf §b§ocreative §7gesetzt§8.");
                        cbPlayer.sendMessageToSpigotPlayer(Bukkit.getPlayer(name), Core.getPrefix() + "§7Dein §b§oGamemode §7wurde auf §b§ocreative §7gesetzt§8.");
                        Bukkit.getPlayer(name).setGameMode(GameMode.CREATIVE);
                        Bukkit.getPlayer(name).playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                    } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")) {
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Der §b§oGamemode §7von §b§o" + name + " §7wurde auf §b§oadventure §7gesetzt§8.");
                        cbPlayer.sendMessageToSpigotPlayer(Bukkit.getPlayer(name), Core.getPrefix() + "§7Dein §b§oGamemode §7wurde auf §b§oadventure §7gesetzt§8.");
                        Bukkit.getPlayer(name).setGameMode(GameMode.ADVENTURE);
                        Bukkit.getPlayer(name).playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                    } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Der §b§oGamemode §7von §b§o" + name + " §7wurde auf §b§ospectator §7gesetzt§8.");
                        cbPlayer.sendMessageToSpigotPlayer(Bukkit.getPlayer(name), Core.getPrefix() + "§7Dein §b§oGamemode §7wurde auf §b§ospectator §7gesetzt§8.");
                        Bukkit.getPlayer(name).setGameMode(GameMode.SPECTATOR);
                        Bukkit.getPlayer(name).playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
                    } else {
                        cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "gamemode survival - creative - adventure - spectator (Player)");
                    }




                } else if (args.length >= 3) {
                    cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "gamemode survival - creative - adventure - spectator (Player)");
                }
            } else if (!player.hasPermission("flamingfactory.administration.gamemode")) {
                cbPlayer.sendNoPermissionMessageToSpigotPlayer(player);
            }

        }

        return false;
    }
}
