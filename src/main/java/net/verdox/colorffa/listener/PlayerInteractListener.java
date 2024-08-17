package net.verdox.colorffa.listener;

import net.verdox.colorffa.ColorFFA;
import net.verdox.colorffa.inventories.SettingsInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!event.getPlayer().getGameMode().equals(org.bukkit.GameMode.CREATIVE))
            event.setCancelled(true);
        if (event.getItem() == null)
            return;
        if (event.getAction().isRightClick() && event.getItem().getType() == Material.DIAMOND_HOE && event.getClickedBlock() != null) {
            Location blockLocation = event.getClickedBlock().getLocation();
            if (blockLocation.getBlock().getType() == ColorFFA.getInstance().getMapManager().getCurrent().getDefaultMaterial() ||
                    blockLocation.getBlock().getType().name().contains("CONCRETE")) {
                blockLocation.getBlock().setType(ColorFFA.getInstance().getGameManager().getPlayerColor(event.getPlayer()));
                //TODO add coins/concrete/blocks idk
            }
        } else if (event.getAction().isRightClick() && event.getItem().getType() == Material.NETHER_STAR) {
            new SettingsInventory(event.getPlayer());
        }
    }
}
