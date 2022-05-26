package net.citybuildnetwork.core.bootstrap.proxy;

import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.database.DataBaseConnector;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import org.fusesource.jansi.AnsiConsole;

public class ProxyBootstrap extends Plugin {

    private static ProxyBootstrap instance;
    private DataBaseConnector databaseConnector;
    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        instance = this;

        AnsiConsole.systemInstall();
        this.databaseConnector = Core.getDatabaseConnector();
        this.databaseConnector.connect();
        this.databaseConnector.createSqlTables();
        this.pluginManager = ProxyServer.getInstance().getPluginManager();

        System.out.println("| CityBuildBase | ProxyBootstrap initialized!");

        registerListeners();
        registerCommands();

    }

    private void registerCommands() {
    
    }

    private void registerListeners() {

    }

    @Override
    public void onDisable() {
        instance = null;

        AnsiConsole.systemUninstall();
        this.databaseConnector.shutdownSqlDatabase();
    }

    public static ProxyBootstrap getInstance() {
        return instance;
    }
}
