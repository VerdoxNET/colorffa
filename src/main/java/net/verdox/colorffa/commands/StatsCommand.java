package net.verdox.colorffa.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Optional;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@CommandAlias("stats")
public class StatsCommand extends BaseCommand {

    @Default
    @CommandCompletion("@players")
    public void onStats(Player player, @Optional String targetPlayer) {
        if (targetPlayer == null) {
            //TODO get player stats
        } else {
            OfflinePlayer target = Bukkit.getOfflinePlayer(targetPlayer);

        }
    }

}
