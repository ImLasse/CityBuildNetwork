package net.citybuildnetwork.core.database;

public class Credentials {

    private String databaseHost = "localhost";
    private String databaseDatabase = "flamingfactory";
    private String databaseUsername = "root";
    private String databasePassword = "";
    private String databasePort = "3306";

    public String getDatabaseHost() {
        return databaseHost;
    }

    public String getDatabaseDatabase() {
        return databaseDatabase;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getDatabasePort() {
        return databasePort;
    }
}
