package net.verdox.colorffa.stats;

import com.mongodb.client.MongoCollection;
import net.verdox.colorffa.ColorFFA;
import org.bson.Document;
import org.bukkit.entity.Player;

public class StatsManager {
    private MongoCollection<Document> statsCollection;

    public StatsManager() {
        statsCollection = ColorFFA.getInstance().getDatabaseManager().getDatabase().getCollection("colorffastats");
    }

    public void addKill(String playerName) {
        Document document = new Document("player", playerName);
        Document found = statsCollection.find(document).first();
        if (found == null) {
            document.append("kills", 1);
            statsCollection.insertOne(document);
        } else {
            int kills = found.getInteger("kills");
            kills++;
            statsCollection.updateOne(found, new Document("$set", new Document("kills", kills)));
        }
    }

    public void addDeath(String playerName) {
        Document document = new Document("player", playerName);
        Document found = statsCollection.find(document).first();
        if (found == null) {
            document.append("deaths", 1);
            statsCollection.insertOne(document);
        } else {
            int deaths = found.getInteger("deaths");
            deaths++;
            statsCollection.updateOne(found, new Document("$set", new Document("deaths", deaths)));
        }
    }

    public int getKills(String playerName) {
        Document document = new Document("player", playerName);
        Document found = statsCollection.find(document).first();
        if (found == null) {
            return 0;
        } else {
            return found.getInteger("kills");
        }
    }

    public int getDeaths(String playerName) {
        Document document = new Document("player", playerName);
        Document found = statsCollection.find(document).first();
        if (found == null) {
            return 0;
        } else {
            return found.getInteger("deaths");
        }
    }

    public double getKD(String playerName) {
        int kills = getKills(playerName);
        int deaths = getDeaths(playerName);
        if (deaths == 0) {
            return kills;
        } else {
            return (double) kills / deaths;
        }
    }

    public void addPoints(String playerName, int points) {
        Document document = new Document("player", playerName);
        Document found = statsCollection.find(document).first();
        if (found == null) {
            document.append("points", points);
            statsCollection.insertOne(document);
        } else {
            int currentPoints = found.getInteger("points");
            currentPoints += points;
            statsCollection.updateOne(found, new Document("$set", new Document("points", currentPoints)));
        }
    }

    public int getPoints(String playerName) {
        Document document = new Document("player", playerName);
        Document found = statsCollection.find(document).first();
        if (found == null) {
            return 0;
        } else {
            return found.getInteger("points");
        }
    }

    public void resetStats(String playerName) {
        Document document = new Document("player", playerName);
        statsCollection.deleteOne(document);
    }

    public void getStats(Player player) {
        player.sendMessage("§aKills: §r" + getKills(player.getName()));
        player.sendMessage("§aDeaths: §r" + getDeaths(player.getName()));
        player.sendMessage("§aK/D: §r" + getKD(player.getName()));
        player.sendMessage("§aPoints: §r" + getPoints(player.getName()));
    }

    public void addElo(String playerName, int elo) {
        Document document = new Document("player", playerName);
        Document found = statsCollection.find(document).first();
        if (found == null) {
            document.append("elo", elo);
            statsCollection.insertOne(document);
        } else {
            int currentElo = found.getInteger("elo");
            currentElo += elo;
            statsCollection.updateOne(found, new Document("$set", new Document("elo", currentElo)));
        }
    }

    public void removeElo(String playerName, int elo) {
        Document document = new Document("player", playerName);
        Document found = statsCollection.find(document).first();
        if (found == null) {
            document.append("elo", -elo);
            statsCollection.insertOne(document);    
        } else {
            int currentElo = found.getInteger("elo");
            currentElo -= elo;
            statsCollection.updateOne(found, new Document("$set", new Document("elo", currentElo)));
        }
    }

    public int getElo(String playerName) {
        Document document = new Document("player", playerName);
        Document found = statsCollection.find(document).first();
        if (found == null) {
            return 1000;
        } else {
            return found.getInteger("elo");
        }
    }
}
