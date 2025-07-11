package net.verdox.colorffa.scoreboard;

import net.kyori.adventure.text.Component;
import net.verdox.colorffa.ColorFFA;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScoreboardManager extends ScoreboardBuilder {
    public ScoreboardManager(Player player) {
        super(player, "<gray>• ● <dark_blue>C<dark_green>o<dark_aqua>l<dark_red>o<dark_purple>r<gold>FFA <gray>● •");
    }

    @Override
    public void initScores() {
        scoreMap.put(0, Component.text(""));
//        scoreMap.put(1, Component.text("K/D: §r"));
//        scoreMap.put(2, Component.text("§a " + ColorFFA.getInstance().getStatsManager().getKD(player.getUniqueId().toString())));
        scoreMap.put(1, Component.text(""));
        scoreMap.put(2, Component.text(""));
        scoreMap.put(3, Component.text(""));
        scoreMap.put(4, Component.text("Map: §r"));
        scoreMap.put(5, Component.text("§a" + ColorFFA.getInstance().getMapManager().getCurrent().getName()));
        scoreMap.put(6, Component.text(""));
        scoreMap.put(7, Component.text("§rColor:"));
        scoreMap.put(8, Component.text("§a" + ColorFFA.getInstance().getGameManager().getPlayerColor(player).name().split("_")[0])); //todo why is color air?
    }

    @Override
    public void update() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(ColorFFA.getInstance(), () -> {
//            scoreMap.put(2, Component.text("§a " + ColorFFA.getInstance().getStatsManager().getKD(player.getName()))); //TODO cause lags?!
            scoreMap.put(5, Component.text("§a" + ColorFFA.getInstance().getMapManager().getCurrent().getName()));
            scoreMap.put(8, Component.text("§a" + ColorFFA.getInstance().getGameManager().getPlayerColor(player).name().split("_")[0]));
        }, 0, 20);
    }
}
