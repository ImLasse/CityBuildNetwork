package net.citybuildnetwork.core.bootstrap.spigot;

import net.citybuildnetwork.core.Core;
import net.citybuildnetwork.core.bootstrap.spigot.commands.CoinsCommand;
import net.citybuildnetwork.core.bootstrap.spigot.player.PlayerJoinListener;
import net.citybuildnetwork.core.bootstrap.spigot.player.PlayerQuitListener;
import net.citybuildnetwork.core.bootstrap.spigot.scoreboard.ScoreboardApi;
import net.citybuildnetwork.core.database.DataBaseConnector;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.fusesource.jansi.AnsiConsole;

public class SpigotBootstrap extends JavaPlugin {

    private static SpigotBootstrap instance;
    private DataBaseConnector databaseConnector;
    private PluginManager pluginManager;
    public ScoreboardApi scoreboardAPI;

    @Override
    public void onEnable() {
        instance = this;

        AnsiConsole.systemInstall();
        this.databaseConnector = Core.getDatabaseConnector();
        this.databaseConnector.connect();
        this.databaseConnector.createSqlTables();
        this.pluginManager = Bukkit.getPluginManager();

        Bukkit.getConsoleSender().sendMessage("§8[§bFrozenBase§8] §7SpigotBootstrap initialized§8!");
        
        registerListeners();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("coins").setExecutor(new CoinsCommand());
    }

    private void registerListeners() {
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
        AnsiConsole.systemUninstall();
    }

    public ScoreboardApi getScoreboardAPI() {
        return scoreboardAPI;
    }

    public static SpigotBootstrap getInstance() {
        return instance;
    }
}
