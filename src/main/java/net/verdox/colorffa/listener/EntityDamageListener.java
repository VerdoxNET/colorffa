package net.verdox.colorffa.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if ((event.getEntity() instanceof Player) && (event.getCause() == EntityDamageEvent.DamageCause.FALL)) {
            event.setCancelled(true);
        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (event.getEntity() instanceof ItemFrame && player.getGameMode().equals(GameMode.CREATIVE)) {
                event.setCancelled(true);
            }
        } else {
            if (event.getEntity() instanceof ItemFrame) {
                event.setCancelled(true);
            }
        }
    }
}