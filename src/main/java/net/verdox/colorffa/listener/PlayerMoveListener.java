package net.verdox.colorffa.listener;

import net.kyori.adventure.text.Component;
import net.verdox.colorffa.ColorFFA;
import net.verdox.colorffa.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (ColorFFA.getInstance().getMapManager().getCurrent() == null) return;

        if (event.getTo().getY() < ColorFFA.getInstance().getMapManager().getCurrent().getSpawnHeight() &&
                event.getFrom().getY() >= ColorFFA.getInstance().getMapManager().getCurrent().getSpawnHeight()) {
            InventoryUtils.prepareJump(player);
            player.sendMessage(ColorFFA.MINI_MESSAGE.deserialize("<aqua>You are now in the game!"));
        }

        if (player.getLocation().getY() < ColorFFA.getInstance().getMapManager().getCurrent().getDeathHeight()) {
            //todo add death to player or not ?!
            if (player.getLastDamageCause() != null && player.getLastDamageCause().getEntity() != null && player.getLastDamageCause().getEntity() instanceof Player) {
                Player killer = (Player) player.getLastDamageCause().getEntity();
                Bukkit.broadcast(ColorFFA.MINI_MESSAGE.deserialize("<red>" + player.getName() + " was killed by " + killer.getName() + "!"));
            }

            player.teleport(ColorFFA.getInstance().getMapManager().getCurrent().getSpawn());
            InventoryUtils.prepareJoin(player);
            player.sendMessage(ColorFFA.MINI_MESSAGE.deserialize("<red>You died!"));
        }
    }
}
