package net.citybuildnetwork.core.bootstrap.spigot.functions;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

/**
 * JavaDoc this file!
 * Created: 02.01.2022
 *
 * @author ImLxsse (lasse.huenermund@gmx.de)
 */
public class UserAPI {

    public static void resetPlayer(Player player){
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealthScale(20.0D);
        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.setExp(0.0F);
        player.setLevel(0);
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

}
