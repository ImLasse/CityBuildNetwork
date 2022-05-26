package net.citybuildnetwork.core;

import net.citybuildnetwork.core.database.DataBaseConnector;
import net.citybuildnetwork.core.player.PlayerPropertyHandler;

/**
 * JavaDoc this file!
 * Created: 15.05.2022
 *
 * @author ImLxsse (lasse.huenermund@gmx.de)
 */
public class Core {

    private static String Prefix = "§8» §6Flaming§eFactory §8- §7";

    private static final DataBaseConnector databaseConnector = new DataBaseConnector();
    private static final PlayerPropertyHandler playerPropertyHandler = new PlayerPropertyHandler();

    public static DataBaseConnector getDatabaseConnector() {
        return databaseConnector;
    }

    public static PlayerPropertyHandler getPlayerPropertyHandler() {
        return playerPropertyHandler;
    }

    public static String getPrefix() {
        return Prefix;
    }

}
