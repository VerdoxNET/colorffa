package net.verdox.colorffa.inventories;

import lombok.NoArgsConstructor;
import net.verdox.colorffa.ColorFFA;
import net.verdox.colorffa.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

@NoArgsConstructor
public class SettingsInventory implements Listener {
    private Player player;
    private Inventory inventory;

    public SettingsInventory(Player player) {
        this.player = player;
        open();
    }

    private void open() {
        inventory = Bukkit.createInventory(null, 27, ColorFFA.MINI_MESSAGE.deserialize("<gradient:#ff0000:#ff00ff>Settings</gradient>"));

        inventory.setItem(10, new ItemBuilder(Material.GRASS_BLOCK).setDisplayName("§aChange Map").build());

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null)
            return;
        if (!event.getClickedInventory().equals(inventory))
            return;
        event.setCancelled(true);

        if (event.getCurrentItem() == null)
            return;
        if (event.getCurrentItem().getType() == Material.GRASS_BLOCK) {
            player.closeInventory();
            ColorFFA.getInstance().getMapManager().nextMap();
            player.sendMessage(ColorFFA.MINI_MESSAGE.deserialize("<green>Map changed to: </green><yellow>" + ColorFFA.getInstance().getMapManager().getCurrent().getName() + "</yellow>"));
        }
    }
}
