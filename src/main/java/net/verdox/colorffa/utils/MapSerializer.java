package net.verdox.colorffa.utils;

import net.verdox.colorffa.ColorFFA;
import net.verdox.colorffa.map.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class MapSerializer {

    public static String deserializeMap(Map map) {
        return map.getName() + ";" + map.getSpawn().getWorld().getName() + ";" + map.getSpawn().getX() + ";" + map.getSpawn().getY() + ";" + map.getSpawn().getZ() + ";" + map.getSpawnHeight() + ";" + map.getDeathHeight() + ";" + map.getDefaultMaterial().name() + ";" + String.join(",", map.getAuthors());
    }

    public static Map serializeMap(String serializedMap) {
        String[] split = serializedMap.split(";");
        String name = split[0];
        String world = split[1];
        double x = Double.parseDouble(split[2]);
        double y = Double.parseDouble(split[3]);
        double z = Double.parseDouble(split[4]);
        Location spawn = new Location(Bukkit.getWorld(world), x, y, z);
        int spawnHeight = Integer.parseInt(split[5]);
        int deathHeight = Integer.parseInt(split[6]);
        Material defaultMaterial = Material.valueOf(split[7]);
        String[] authors = split[8].split(",");
        return new Map(name, spawn, spawnHeight, deathHeight, defaultMaterial, authors);
    }
}
