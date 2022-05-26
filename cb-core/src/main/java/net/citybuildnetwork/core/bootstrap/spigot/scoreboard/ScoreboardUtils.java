package net.citybuildnetwork.core.bootstrap.spigot.scoreboard;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.UUID;

public class ScoreboardUtils {

    private HashMap<UUID, ScoreboardBuilder> scoreLibs = Maps.newHashMap();

    public void setProperty(UUID uuid, ScoreboardBuilder scoreboardBuilder){
        this.scoreLibs.put(uuid, scoreboardBuilder);
    }

    public boolean hasProperty(final UUID uuid){
        return scoreLibs.containsKey(uuid);
    }

    public void removeProperty(final UUID uuid){
        this.scoreLibs.remove(uuid);
    }

    public ScoreboardBuilder getProperty(final UUID uuid){
        return this.scoreLibs.get(uuid);
    }

}
