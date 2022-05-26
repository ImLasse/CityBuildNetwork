package net.citybuildnetwork.core.bootstrap.spigot.coins;

import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.database.DataBaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinsAPI {

    private static final DataBaseConnector databaseConnector = Core.getDatabaseConnector();

    public static boolean existPlayer(String uuid) {
        ResultSet resultSet = databaseConnector.getSqlData("SELECT UUID FROM base_coins WHERE UUID= '" + uuid + "'");
        String s = null;
        try {
            while (resultSet.next()) {
                s = resultSet.getString("UUID");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s != null;
    }

    public static Integer getCoins(String uuid) {
        ResultSet resultSet = databaseConnector.getSqlData("SELECT coins FROM base_coins WHERE UUID ='" + uuid + "'");
        Integer o = 0;
        try {
            while (resultSet.next()) {
                o = resultSet.getInt("Coins");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static void setCoins(String uuid, Integer coins) {
        databaseConnector.updateSqlConnection("UPDATE base_coins SET coins ='" + coins + "' WHERE UUID='" + uuid + "'");
    }

    public static void removeCoins(String uuid,Integer coins){
        Integer i = getCoins(uuid) - coins;
        databaseConnector.updateSqlConnection("UPDATE base_coins SET coins ='" + i + "' WHERE UUID='" + uuid + "'");
    }

    public static void addCoins(String uuid,Integer coins){
        Integer i = getCoins(uuid) + coins;
        databaseConnector.updateSqlConnection("UPDATE base_coins SET coins ='" + i + "' WHERE UUID='" + uuid + "'");
    }
}
