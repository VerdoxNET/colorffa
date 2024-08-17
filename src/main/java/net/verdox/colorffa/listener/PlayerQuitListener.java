package net.verdox.colorffa.listener;

import net.kyori.adventure.text.Component;
import net.verdox.colorffa.ColorFFA;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ColorFFA.getInstance().getGameManager().removePlayerColor(player);
        event.quitMessage(Component.empty());
    }
}
