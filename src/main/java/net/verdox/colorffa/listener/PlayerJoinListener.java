package net.verdox.colorffa.listener;

import net.verdox.colorffa.ColorFFA;
import net.verdox.colorffa.scoreboard.ScoreboardManager;
import net.verdox.colorffa.utils.InventoryUtils;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.joinMessage(ColorFFA.MINI_MESSAGE.deserialize("<green>" + player.getName() + " joined color ffa"));

        if (ColorFFA.getInstance().getMapManager().getCurrent() != null) {
            player.teleport(ColorFFA.getInstance().getMapManager().getCurrent().getSpawn());
        }

        ColorFFA.getInstance().getGameManager().addPlayerColor(player);

        InventoryUtils.prepareJoin(player);
        new ScoreboardManager(player);
    }
}
