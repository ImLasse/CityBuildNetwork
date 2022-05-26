package net.citybuildnetwork.core.player;

import com.google.common.collect.Maps;
import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.database.DataBaseConnector;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.UUID;

/**
 * JavaDoc this file!
 * Created: 15.05.2022
 *
 * @author ImLxsse (lasse.huenermund@gmx.de)
 */
public class PlayerPropertyHandler {

    private HashMap<UUID, String> playerLibrary = Maps.newHashMap();
    private DataBaseConnector databaseConnector = Core.getDatabaseConnector();

    public void setDefault(UUID uuid){
        if(databaseConnector.existPlayer(uuid.toString())) {
            setProperty(uuid, Bukkit.getPlayer(uuid).getName());
        } else {
            databaseConnector.createPlayer(uuid.toString(), Bukkit.getPlayer(uuid).getName());
            setProperty(uuid, Bukkit.getPlayer(uuid).getName());
        }
    }

    public String getProperty(final UUID uuid) {
        return this.playerLibrary.get(uuid);
    }

    private boolean hasProperty(final UUID uuid){
        return this.playerLibrary.containsKey(uuid);
    }

    private void setProperty(final UUID uuid, String username){
        playerLibrary.put(uuid, username);
    }

    public void removeProperty(final UUID uuid){
        this.playerLibrary.remove(uuid);
    }

    public HashMap<UUID, String> getPlayerLibrary() {
        return playerLibrary;
    }

}
