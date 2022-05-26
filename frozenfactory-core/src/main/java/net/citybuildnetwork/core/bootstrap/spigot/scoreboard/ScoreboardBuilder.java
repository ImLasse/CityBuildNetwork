package net.citybuildnetwork.core.bootstrap.spigot.scoreboard;

import com.google.common.collect.Maps;
import net.citybuildnetwork.core.player.CBPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Map;

public class ScoreboardBuilder {

    public void removeScore(){
        scoreMap.clear();
    }

    private Map<Integer, String> scoreMap = Maps.newConcurrentMap();
    private String displayName;
    private Player player;
    private Map<String, Integer> schedulerMap = Maps.newConcurrentMap();
    private CBPlayer frozenPlayer;

    public ScoreboardBuilder(Player player, String displayName){
        this.player = player;
        this.displayName = displayName;
        frozenPlayer = new CBPlayer(player.getName(),player.getUniqueId());
    }

    public String getLine(int line) {
        return (String)this.scoreMap.get(line);
    }

    public void setLine(int score, String prefix, String suffix) {
        this.scoreMap.put(score, prefix + ";" + suffix);
    }

    public void setBoard() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(displayName);

        for(int i = 0; i < 20; ++i) {
            if (this.scoreMap.get(i) != null) {
                Team team;
                String[] raw = (this.scoreMap.get(i)).split(";");
                team = scoreboard.registerNewTeam("x" + i);

                team.setPrefix(raw[0]);
                team.setSuffix(raw[1]);

                if (i < 10) {
                    team.addEntry("§" + i);
                    objective.getScore("§" + i).setScore(i);
                } else {
                    team.addEntry("§" + this.getColorCodeByNumber(i));
                    objective.getScore("§" + this.getColorCodeByNumber(i)).setScore(i);
                }
                player.setScoreboard(scoreboard);
            }
        }
    }

    public void updateBoard(Player player, int score, String prefix, String suffix) {
        if (player.getScoreboard() != null && player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null) {
            player.getScoreboard().getTeam("x" + score).setPrefix(prefix);
            player.getScoreboard().getTeam("x" + score).setSuffix(suffix);
            player.setScoreboard(player.getScoreboard());
        }
    }

    private String getColorCodeByNumber(int number) {
        switch(number) {
            case 10:
                return "a";
            case 11:
                return "b";
            case 12:
                return "c";
            case 13:
                return "d";
            case 14:
                return "e";
            case 15:
                return "f";
            case 16:
                return "g";
            case 17:
                return "h";
            case 18:
                return "i";
            case 19:
                return "j";
            case 20:
                return "k";
            default:
                return "z";
        }
    }

    public Player getPlayer() {
        return player;
    }
}