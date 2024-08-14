package net.verdox.colorffa;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.verdox.colorffa.commands.SetupCommand;
import net.verdox.colorffa.database.DatabaseManager;
import net.verdox.colorffa.game.GameManager;
import net.verdox.colorffa.listener.*;
import net.verdox.colorffa.map.MapManager;
import net.verdox.colorffa.stats.StatsManager;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class ColorFFA extends JavaPlugin {
    public static final MiniMessage MINI_MESSAGE = MiniMessage.builder().build();
    @Getter private static ColorFFA instance;
    @Getter private DatabaseManager databaseManager;
    @Getter private StatsManager statsManager;
    @Getter private GameManager gameManager;
    @Getter private MapManager mapManager;
    private PaperCommandManager commandManager;

    @Override
    public void onLoad() {
        //init instance
        instance = this;
        //save default config with defaults (db)
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        //initialize command manager
        commandManager = new PaperCommandManager(this);
        //initialize map manager
        mapManager = new MapManager();
        //initialize database manager
        databaseManager = new DatabaseManager(getConfig());
        //initialize game manager
        gameManager = new GameManager();
        //initialize stats manager
        statsManager = new StatsManager();
        //register commands
        commandManager.registerCommand(new SetupCommand());
        //register listeners
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new ProtectionListener(), this);
        //load first map
        mapManager.load();
        //enable map cycle
        mapManager.runMapChange();
    }

    @Override
    public void onDisable() {
        //save all maps
        mapManager.saveMaps();
        //close database connection
//        databaseManager.disconnect();
        //save config and its edits
        saveConfig();
        //last thing remove instance
        instance = null;
    }
}
