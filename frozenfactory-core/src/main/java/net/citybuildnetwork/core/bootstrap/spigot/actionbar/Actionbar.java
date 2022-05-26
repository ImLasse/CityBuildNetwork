package net.citybuildnetwork.core.bootstrap.spigot.actionbar;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;


public class Actionbar {

    public static void sendActionBar(Player player, String message) {
        if (!player.isOnline()) {
            return; // Player may have logged out
        }
        player.spigot().sendMessage((ChatMessageType.ACTION_BAR), new TextComponent(message));

    }




}
