package net.citybuildnetwork.core.util.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jline.terminal.Size;

/**
 * JavaDoc this file!
 * Created: 20.05.2022
 *
 * @author ImLxsse (lasse.huenermund@gmx.de)
 */
public class InventoryBuilder {

    //NEEDS TO BE USED IN AN PLAYERINTERACTEVENT

    public void openInventory(Player player, String inventoryItemName, Sound openSound, Inventory inventory, String inventoryName, Integer inventorySize) {

        if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(inventoryItemName)) {

            player.playSound(player.getLocation(), openSound, 10,10);
            inventory = Bukkit.createInventory(player, inventorySize, inventoryName);

        }
    }



}
