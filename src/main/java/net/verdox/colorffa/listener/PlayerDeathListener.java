package net.verdox.colorffa.listener;

import net.verdox.colorffa.ColorFFA;
import net.verdox.colorffa.stats.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (ColorFFA.getInstance().getMapManager().getCurrent() == null) return;
        Player player = event.getEntity();
        Player killer = player.getKiller();
        event.setCancelled(true);
        player.teleport(ColorFFA.getInstance().getMapManager().getCurrent().getSpawn());
        //TODO random death message
        Bukkit.broadcast(ColorFFA.MINI_MESSAGE.deserialize("<red>" + player.getName() + " was killed by " + killer.getName()));

        event.getDrops().clear();
        event.setDroppedExp(0);
        player.setLevel(0);

        StatsManager statsManager = ColorFFA.getInstance().getStatsManager();

        if (event.getEntity().getKiller() != null && !killer.equals(player)) {
            killer.playSound(killer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5F, 5F);
            killer.setLevel(killer.getLevel() + 1);
            killer.setHealth(20);
            killer.setFireTicks(0);
            killer.setFoodLevel(20);
            killer.setSaturation(20);

            statsManager.addKill(killer.getUniqueId().toString());
            statsManager.addElo(killer.getUniqueId().toString(), 10);

            killer.sendMessage(ColorFFA.MINI_MESSAGE.deserialize("<green>You killed " + player.getName() + "!"));
        }

        statsManager.addDeath(player.getUniqueId().toString());
        statsManager.removeElo(player.getUniqueId().toString(), 10);

        player.setRespawnLocation(ColorFFA.getInstance().getMapManager().getCurrent().getSpawn());
        player.teleport(ColorFFA.getInstance().getMapManager().getCurrent().getSpawn());
        player.sendMessage(ColorFFA.MINI_MESSAGE.deserialize("<green>You have been killed and respawned."));
    }
}
