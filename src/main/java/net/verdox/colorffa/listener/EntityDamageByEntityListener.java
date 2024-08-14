package net.verdox.colorffa.listener;

import net.verdox.colorffa.ColorFFA;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();
                if (ColorFFA.getInstance().getMapManager().getCurrent() == null)
                    return;

                if (player.getLocation().getY() > ColorFFA.getInstance().getMapManager().getCurrent().getSpawnHeight()) {
                    event.setCancelled(true);
                    damager.sendMessage("§cYou can't hit players in the spawn area!");
                }

                if (damager.getLocation().getY() > ColorFFA.getInstance().getMapManager().getCurrent().getSpawnHeight()) {
                    event.setCancelled(true);
                    damager.sendMessage("§cYou can't hit players from the spawn area!");
                }
            }
        }
    }
}
