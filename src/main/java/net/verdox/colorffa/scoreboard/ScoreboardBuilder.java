package net.verdox.colorffa.scoreboard;


import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class ScoreboardBuilder {

    protected final Scoreboard scoreboard;
    protected final Objective objective;

    protected final Player player;

    /**
     * The key is the scoreIndex and the String the value to show.
     * The score index starts at 0 and gets higher while going down the scoreboard meaning: scoreIndex 0 is the highest, scoreIndex 1 the second highest and so on.
     */
    protected final Map<Integer, Component> scoreMap;

    public ScoreboardBuilder(Player player, String displayName) {
        this.player = player;
        this.scoreMap = new HashMap<>();

        if (player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) {
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }

        this.scoreboard = player.getScoreboard();

        if (this.scoreboard.getObjective("display") != null) {
            Objects.requireNonNull(this.scoreboard.getObjective("display")).unregister();
        }

        this.objective = this.scoreboard.registerNewObjective("display", Criteria.DUMMY, Component.text(displayName));
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        initScores();
        createScoreboard();

        update();
    }

    /**
     * Method where to put score into scoreMap.
     */
    public abstract void initScores();

    /**
     * Must contain a Bukkit Runnable to update.
     */
    public abstract void update();

    /**
     * Creates the scoreboard with scores from scoreMap. Must be called in update() in order to update the scoreboard.
     */
    public void createScoreboard() {
        for (int i = 0; i < scoreMap.size(); i++) {
            setScore(scoreMap.get(i), Collections.max(scoreMap.keySet()) - i);
        }
    }

    /**
     * put a string as score in the map
     */
    public void putStringScore(String content, int index) {
        this.scoreMap.put(index, Component.text(content));
    }

    /**
     * Removes the score by the given at the given scoreIndex from scoreMap. Don't use scoreMap.remove()! Eventually break the scoreboard.
     */
    protected void removeScore(int scoreIndex) {
        int score = scoreMap.size() - scoreIndex;

        scoreMap.remove(scoreIndex);
        createScoreboard();
    }

    private void setScore(String content, int score) {
        Team team = getTeamByScore(score);

        if (team == null) return;

        team.prefix(Component.text(content));
        showScore(score);
    }

    private void setScore(Component content, int score) {
        Team team = getTeamByScore(score);

        if (team == null) return;

        team.prefix(content);
        showScore(score);
    }

    private void showScore(int score) {
        EntryName name = getEntryNameByScore(score);

        if (name == null) {
            return;
        }

        if (objective.getScore(name.getEntryName()).isScoreSet()) {
            return;
        }

        objective.getScore(name.getEntryName()).setScore(score);
    }

    private EntryName getEntryNameByScore(int score) {

        for (EntryName name : EntryName.values()) {
            if (score == name.getEntry()) {
                return name;
            }
        }

        return null;
    }

    private Team getTeamByScore(int score) {
        EntryName name = getEntryNameByScore(score);

        if (name == null) return null;

        Team team = scoreboard.getEntryTeam(name.getEntryName());

        if (team != null) {
            return team;
        }

        team = scoreboard.registerNewTeam(name.name());
        team.addEntry(name.getEntryName());

        return team;
    }

    enum EntryName {
        ENTRY_0(0, "§0"),
        ENTRY_1(1, "§1"),
        ENTRY_2(2, "§2"),
        ENTRY_3(3, "§3"),
        ENTRY_4(4, "§4"),
        ENTRY_5(5, "§5"),
        ENTRY_6(6, "§6"),
        ENTRY_7(7, "§7"),
        ENTRY_8(8, "§8"),
        ENTRY_9(9, "§9"),
        ENTRY_10(10, "§a"),
        ENTRY_11(11, "§b"),
        ENTRY_12(12, "§c"),
        ENTRY_13(13, "§d"),
        ENTRY_14(14, "§e"),
        ENTRY_15(15, "§f");

        private final int entry;
        private final String entryName;

        EntryName(int entry, String entryName) {
            this.entry = entry;
            this.entryName = entryName;
        }

        public int getEntry() {
            return entry;
        }

        public String getEntryName() {
            return entryName;
        }
    }
}