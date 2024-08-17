package net.verdox.colorffa.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class InventoryUtils {

    public static void prepareJoin(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);

        player.getInventory().setItem(4, new ItemBuilder(Material.WRITTEN_BOOK).setDisplayName("<green>Tutorial")
                .setBookContent("Tutorial", "SERVER", "This game is a nice game...").build());
        if (player.hasPermission("verdox.colorffa.settings"))
            player.getInventory().setItem(8, new ItemBuilder(Material.NETHER_STAR).setDisplayName("<aqua><b>Settings").build());
    }

    public static void prepareJump(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);

        player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("<green><b>Sword").setUnbreakable(true).build());
        player.getInventory().setItem(1, new ItemBuilder(Material.DIAMOND_HOE).setDisplayName("<green><b>Color Gun").setUnbreakable(true).build());
    }
}
