package net.verdox.colorffa.database;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public class DatabaseManager {
    @Getter private MongoClient client;
    @Getter private MongoDatabase database;

    public DatabaseManager(FileConfiguration configuration) {
        String host = configuration.getString("database.host");
        int port = configuration.getInt("database.port");
        String database = configuration.getString("database.database");
        String username = configuration.getString("database.username");
        String password = configuration.getString("database.password");
        String authDatabase = configuration.getString("database.authDatabase");

        ConnectionString connectionString = new ConnectionString("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/" + authDatabase);
        this.client = MongoClients.create(connectionString);
        this.database = client.getDatabase(database);
    }

    public void disconnect() {
        client.close();
    }
}
