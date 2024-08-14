package net.verdox.colorffa.map;

import lombok.Getter;
import net.verdox.colorffa.ColorFFA;
import net.verdox.colorffa.utils.MapSerializer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MapManager {

    @Getter private Map current;
    @Getter private List<Map> maps;

    public MapManager() {
        maps = loadMaps();
    }

    public List<Map> loadMaps() {
        FileConfiguration configuration = ColorFFA.getInstance().getConfig();
        if (configuration.contains("maplist")) {
            List<String> mapNames = configuration.getStringList("maplist");
            List<Map> maps = new ArrayList<>();
            for (String mapName : mapNames) {
                maps.add(MapSerializer.serializeMap(configuration.getString("maps." + mapName)));
            }
            return maps;
        }
        return new ArrayList<>();
    }

    public void saveMaps() {
        FileConfiguration configuration = ColorFFA.getInstance().getConfig();
        List<String> mapNames = new ArrayList<>();
        for (Map map : maps) {
            mapNames.add(map.getName());
            configuration.set("maps." + map.getName(), MapSerializer.deserializeMap(map));
        }
        configuration.set("maplist", mapNames);
    }

    public void setCurrentMap(Map map) {
        current = map;
    }

    public void load() {
        if (maps.size() > 0) {
            setCurrentMap(getRandomMap());
        } else {
            System.out.println("No maps loaded, please create a map and restart the server.");
        }
    }

    public Map getRandomMap() {
        if (maps.size() > 0) {
            return (maps.get((int) (Math.random() * maps.size())));
        } else {
            System.out.println("No maps loaded, please create a map and restart the server.");
            return null;
        }
    }

    public void nextMap() {
        setCurrentMap(getRandomMap());
        Bukkit.broadcast(ColorFFA.MINI_MESSAGE.deserialize("<green>Map changed to: </green><yellow>" + current.getName() + "</yellow>").asComponent());
    }

    public void addMap(Map map) {
        maps.add(map);
    }
}
