package net.verdox.colorffa.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Optional;
import net.verdox.colorffa.ColorFFA;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@CommandAlias("stats")
public class StatsCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void onStats(Player player, @Optional String targetPlayer) {
        if (targetPlayer == null) {
            ColorFFA.getInstance().getStatsManager().getStats(player, player.getUniqueId().toString());
        } else {
            OfflinePlayer target = Bukkit.getOfflinePlayer(targetPlayer);
            ColorFFA.getInstance().getStatsManager().getStats(player, target.getUniqueId().toString());
        }
    }
}
