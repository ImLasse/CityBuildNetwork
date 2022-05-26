package net.citybuildnetwork.core.bootstrap.spigot.commands;

import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.bootstrap.spigot.coins.CoinsAPI;
import net.citybuildnetwork.core.player.CBPlayer;
import net.citybuildnetwork.core.player.uuid.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){

            final Player player = (Player) sender;
            final CBPlayer cbPlayer = new CBPlayer(player.getName(), player.getUniqueId());
            if(player.hasPermission("flamingfactory.administration.coins")) {
                if(args.length == 0) {
                    cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Du hast aktuell §e§o" + CoinsAPI.getCoins(player.getUniqueId().toString()) + " §7coins§8.");
                } else if(args.length==1) {
                    if(args[0] != null && CoinsAPI.existPlayer(UUIDFetcher.getUUID(args[0]).toString())) {
                        cbPlayer.sendMessageToSpigotPlayer(player, Core.getPrefix() + "§7Der Spieler §e§o" + args[0] + "§7 hat aktuell §e§o" + CoinsAPI.getCoins(UUIDFetcher.getUUID(args[0]).toString()) + " §7coins§8.");
                    } else {
                        cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "coins add - remove - set (Player) (Amount)");
                    }
                } else if(args.length == 2) {
                    cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "coins add - remove - set (Player) (Amount)");
                } else if(args.length == 3) {
                    String name = args[1];

                    if(Bukkit.getPlayer(name) == null) {
                        cbPlayer.sendPlayerNotFoundMessageToSpigotPlayer(player);
                        return true;
                    }

                    int coins = Integer.parseInt(args[2]);
                    if (args[0].equalsIgnoreCase("add")) {
                        CoinsAPI.addCoins(UUIDFetcher.getUUID(name).toString(), coins);
                        cbPlayer.sendMessageToSpigotPlayer(player,Core.getPrefix() + "§7Du hast erfolgreich §e§o" + coins + " §7coins zu §e§o"+ name +" §7hinzugefügt§8.");
                    } else if (args[0].equalsIgnoreCase("remove")) {
                        CoinsAPI.removeCoins(UUIDFetcher.getUUID(name).toString(), coins);
                        cbPlayer.sendMessageToSpigotPlayer(player,Core.getPrefix() + "§7Du hast erfolgreich §e§o" + coins + " §7coins von §e§o"+ name +" §7entfernt§8.");
                    } else if (args[0].equalsIgnoreCase("set")) {
                        CoinsAPI.setCoins(UUIDFetcher.getUUID(name).toString(), coins);
                        cbPlayer.sendMessageToSpigotPlayer(player,Core.getPrefix() + "§7Du hast erfolgreich §e§o" + name + "§8'§es §7coins auf §e§o"+ coins +" §7geändert§8.");
                    } else {
                        cbPlayer.sendWrongUsageMessageToSpigotPlayer(player, "coins add - remove - set (Spieler) (Anzahl)");
                    }
                } else {
                    cbPlayer.sendMessageToSpigotPlayer(player,Core.getPrefix() + "§7Du hast aktuell §e§o" + CoinsAPI.getCoins(player.getUniqueId().toString()) + " §7coins§8!");
                }
            } else {
                cbPlayer.sendMessageToSpigotPlayer(player,Core.getPrefix() + "§7Du hast aktuell §e§o" + CoinsAPI.getCoins(player.getUniqueId().toString()) + " §7coins§8!");
            }
        }
        return false;
    }

}