package net.verdox.colorffa.game;

import net.verdox.colorffa.ColorFFA;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
    private final List<Material> allowedMaterials = List.of(Material.WHITE_CONCRETE, Material.ORANGE_CONCRETE, Material.MAGENTA_CONCRETE, Material.LIGHT_BLUE_CONCRETE, Material.YELLOW_CONCRETE, Material.LIME_CONCRETE, Material.PINK_CONCRETE, Material.GRAY_CONCRETE, Material.LIGHT_GRAY_CONCRETE, Material.CYAN_CONCRETE, Material.PURPLE_CONCRETE, Material.BLUE_CONCRETE, Material.BROWN_CONCRETE, Material.GREEN_CONCRETE, Material.RED_CONCRETE, Material.BLACK_CONCRETE);
    private final Map<Player, Material> playerColors;
    private final Map<Location, Material> coloredBlocks;

    public GameManager() {
        playerColors = new HashMap<>();
        coloredBlocks = new HashMap<>();
    }

    public void addPlayerColor(Player player) {
        Material color = getRandomColor();
        playerColors.put(player, color);
    }

    public void removePlayerColor(Player player) {
        List<Location> remove = new ArrayList<>();
        for (Location location : coloredBlocks.keySet()) {
            if (coloredBlocks.get(location) == getPlayerColor(player)) {
                location.getBlock().setType(ColorFFA.getInstance().getMapManager().getCurrent().getDefaultMaterial());
               remove.add(location);
            }
        }

        for (Location location : remove) {
            coloredBlocks.remove(location);
        }

        /*colored.forEach(location -> { //colored as list
            if (coloredBlocks.get(location) == getPlayerColor(player)) {
                location.getBlock().setType(ColorFFA.getInstance().getMapManager().getCurrent().getDefaultMaterial());
                coloredBlocks.remove(location);
            }
        });*/

        Bukkit.getScheduler().runTaskLater(ColorFFA.getInstance(), () -> {
            playerColors.remove(player);
        }, 20*20);
    }

    public Material getPlayerColor(Player player) {
        return playerColors.get(player) == null ? Material.AIR : playerColors.get(player);
    }

    public Material getRandomColor() {
        Material material = allowedMaterials.get((int) (Math.random() * allowedMaterials.size()));
        if (playerColors.containsValue(material))
            return getRandomColor();
        return material;
    }

    public void addColoredBlock(Location location, Material material) {
        coloredBlocks.put(location, material);
    }
}
