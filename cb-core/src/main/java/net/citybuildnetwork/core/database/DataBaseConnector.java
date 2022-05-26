package net.citybuildnetwork.core.database;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataBaseConnector {

    private Credentials credentials = new Credentials();
    private String host = credentials.getDatabaseHost();
    private String database = credentials.getDatabaseDatabase();
    private String username = credentials.getDatabaseUsername();
    private String password = credentials.getDatabasePassword();
    private String port = credentials.getDatabasePort();
    private static Connection connection;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSqlTables() {
        if(connection != null) {
            try {
                connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS base_users (uuid VARCHAR(100), username VARCHAR(100), isVanished VARCHAR(100))");
                connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS base_coins (uuid VARCHAR(100), coins VARCHAR(100))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean existPlayer(String uuid) {
        ResultSet resultSet = getSqlData("SELECT UUID FROM base_users WHERE UUID= '" + uuid + "'");
        String s = null;
        try {
            while (resultSet.next()) {
                s = resultSet.getString("uuid");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s != null;
    }

    public void createPlayer(String uuid, String username){
        if(!existPlayer(uuid)) {

            // Base Users
            updateSqlConnection("INSERT INTO base_users(uuid, username) VALUES ('" + uuid + "','" + username + "');");

            // CoinFactory
            updateSqlConnection("INSERT INTO base_coins (uuid, coins) VALUES ('" + uuid + "','0');");
        }
    }

    /*public boolean existUserbyName(String name){
        try {
            ResultSet rs = getSqlData("SELECT * FROM proxy_user WHERE Name='" + name + "'");
            if (rs.next()) {
                return rs.getString("Name") != null;
            }
        } catch (SQLException localSQLException) {}
        return false;
    }*/

    /*public void setPlayTime(String uuid,int anzahl){
        try {
            PreparedStatement ps = getStatement("UPDATE proxy_user SET PlayTime= ? WHERE UUID= ?");
            ps.setInt(1, anzahl);
            ps.setString(2, uuid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Integer getPlayTime(String uuid){
        ResultSet rs = getSqlData("SELECT * FROM proxy_user WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                return rs.getInt("PlayTime");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }*/

    public void shutdownSqlDatabase() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateSqlConnection(String query) {
        if(connection != null) {
            try {
                connection.createStatement().executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public PreparedStatement getStatement(String sql) {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteTableColumn(final String tableName, final String key, final String value) {
        updateSqlConnection("DELETE FROM " + tableName +" WHERE " + key + "='" + value + "'");
    }

    public ResultSet getSqlData(String query) {
        if(connection != null) {
            try {
                return connection.createStatement().executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getFromTable(final String tableName, final String key, final String value, final String type) {
        ResultSet resultSet = getSqlData("SELECT * FROM " + tableName +" WHERE " + key + "='" + value + "'");
        try {
            while (resultSet.next()) {
                return resultSet.getString(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Integer getIntegerFromTable(final String tableName, final String key, final String value, final String type) {
        ResultSet resultSet = getSqlData("SELECT * FROM " + tableName +" WHERE " + key + "='" + value + "'");
        try {
            while (resultSet.next()) {
                return resultSet.getInt(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Double getDoubleFromTable(final String tableName, final String key, final String value, final String type) {
        ResultSet resultSet = getSqlData("SELECT * FROM " + tableName +" WHERE " + key + "='" + value + "'");
        try {
            while (resultSet.next()) {
                return resultSet.getDouble(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public Float getFloatFromTable(final String tableName, final String key, final String value, final String type) {
        ResultSet resultSet = getSqlData("SELECT * FROM " + tableName +" WHERE " + key + "='" + value + "'");
        try {
            while (resultSet.next()) {
                return resultSet.getFloat(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exist(final String tableName, final String key, final String value){
        ResultSet resultSet = null;
        try {
            resultSet = getSqlData("SELECT * FROM " + tableName +" WHERE " + key + "= '" + value + "'");
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setInTable(String database, String search, String getLine, Object i, String line) {
        executorService.execute(() -> {
            updateSqlConnection("UPDATE " + database + " SET " + line + "= '" + i + "' WHERE " + search + "= '" + getLine + "';");
        });
    }

}
