package net.citybuildnetwork.core.bootstrap.spigot.title;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class TitleAPI {

    public static void send(Player player, String title, String subtitle, Sound titlesound) {
        player.sendTitle(title, subtitle);
        player.playSound(player.getLocation(), titlesound, 10,10);
    }


}
