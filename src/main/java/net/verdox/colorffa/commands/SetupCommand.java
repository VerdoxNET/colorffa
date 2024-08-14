package net.verdox.colorffa.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.verdox.colorffa.ColorFFA;
import net.verdox.colorffa.map.Map;
import net.verdox.colorffa.utils.MapSerializer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

@CommandAlias("setup")
@CommandPermission("verdox.colorffa.setup")
public class SetupCommand extends BaseCommand {
    private HashMap<Player, Map> mapsChache = new HashMap<>();

    @Default
    public void onSetup(Player player) {
        player.sendMessage("§cUsage: ");
        player.sendMessage("§c/setup create <mapName>");
        player.sendMessage("§c/setup setspawn <mapName>");
        player.sendMessage("§c/setup setspawnheight <mapName>");
        player.sendMessage("§c/setup setdeathheight <mapName>");
        player.sendMessage("§c/setup save <mapName>");
        player.sendMessage("§c/setup listMaps");
    }

    @Subcommand("create")
    public void onCreate(Player player, String mapName) {
        if (mapsChache.containsKey(player)) {
            player.sendMessage("§cYou are already creating a map");
            return;
        }
        Map map = new Map();
        map.setName(mapName);
        mapsChache.put(player, map);
    }

    @Subcommand("setspawn")
    public void onSetSpawn(Player player, String mapName) {
        Location spawn = player.getLocation();
        if (mapsChache.containsKey(player)) {
            Map map = mapsChache.get(player);
            map.setSpawn(spawn);
            mapsChache.put(player, map);
        } else {
            player.sendMessage("§cYou are not creating a map");
        }
    }

    @Subcommand("setspawnheight")
    public void onSetSpawnHeight(Player player, String mapName) {
        int spawnHeight = (int) player.getLocation().getY();
        if (mapsChache.containsKey(player)) {
            Map map = mapsChache.get(player);
            map.setSpawnHeight(spawnHeight);
            mapsChache.put(player, map);
        } else {
            player.sendMessage("§cYou are not creating a map");
        }
    }

    @Subcommand("setdeathheight")
    public void onSetDeathHeight(Player player, String mapName) {
        int deathHeight = (int) player.getLocation().getY();
        if (mapsChache.containsKey(player)) {
            Map map = mapsChache.get(player);
            map.setDeathHeight(deathHeight);
            mapsChache.put(player, map);
        } else {
            player.sendMessage("§cYou are not creating a map");
        }
    }

    @Subcommand("addauthor")
    public void onAddAuthor(Player player, String mapName, String author) {
        if (mapsChache.containsKey(player)) {
            Map map = mapsChache.get(player);
            map.setAuthors(author.split(","));
            mapsChache.put(player, map);
        } else {
            player.sendMessage("§cYou are not creating a map");
        }
    }

    @Subcommand("setdefaultmaterial")
    public void onSetDefaultMaterial(Player player, String mapName, String material) {
        if (mapsChache.containsKey(player)) {
            Map map = mapsChache.get(player);
            map.setDefaultMaterial(Material.valueOf(material.toUpperCase()));
            mapsChache.put(player, map);
        } else {
            player.sendMessage("§cYou are not creating a map");
        }
    }

    @Subcommand("save")
    public void onSave(Player player, String mapName) {
        if (mapsChache.containsKey(player)) {
            Map map = mapsChache.get(player);
            ColorFFA.getInstance().getMapManager().addMap(map);
            ColorFFA.getInstance().getMapManager().saveMaps();
            mapsChache.remove(player);
            player.sendMessage("§aMap saved: " + map.getName());
        } else {
            player.sendMessage("§cYou are not creating a map");
        }
    }

    @Subcommand("listMaps")
    public void onListMaps(Player player) {
        player.sendMessage("§aMaps: ");
        for (Map map : ColorFFA.getInstance().getMapManager().getMaps()) {
            player.sendMessage("§a- " + map.getName());
        }
    }
}
