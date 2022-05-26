package net.citybuildnetwork.core.bootstrap.spigot.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;


public class ScoreboardApi {

    private static ScoreboardApi instance;
    private HashMap<String, Object> teams;

    public ScoreboardApi() {
        teams = new HashMap<>();
    }
    @SuppressWarnings("rawtypes")
    public void updateTeams() {
        for (Entry entry : teams.entrySet()) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                try {
                    Constructor<?> scoreboardTeamConstructor = getNMSClass("PacketPlayOutScoreboardTeam").getConstructor(getNMSClass("ScoreboardTeam"), int.class);
                    sendPacket(all, scoreboardTeamConstructor.newInstance(entry.getValue(), 1));
                    sendPacket(all, scoreboardTeamConstructor.newInstance(entry.getValue(), 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addToTeam(Player player, String team) {
        teams.entrySet().forEach(entry -> {
            Object packet = teams.get(entry.getKey());
            try {
                Field f = packet.getClass().getDeclaredField("c");
                f.setAccessible(true);
                Set<String> list = new HashSet<>((Collection<? extends String>) f.get(packet));
                if (list.contains(player.getName())) {
                    list.remove(player.getName());
                    setField(packet, "c", list);
                    teams.put(entry.getKey(), packet);
                }
                setPlayer(player, team);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void createTeam(String teamname, String hashMapName, String prefix, Object board) {
        try {
            Constructor<?> teamConstructor = getNMSClass("ScoreboardTeam").getConstructor(getNMSClass("Scoreboard"), String.class);
            Object packet = teamConstructor.newInstance(board, teamname);
            Object enumVisibility = getNMSClass("ScoreboardTeamBase").getDeclaredClasses()[0].getField("ALWAYS").get(null);
            setField(packet, "j", enumVisibility);
            setField(packet, "e", prefix);
            teams.put(hashMapName, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setPlayer(Player p, String HashMapName) {
        Object packet = teams.get(HashMapName);
        try {
            Field f = packet.getClass().getDeclaredField("c");
            f.setAccessible(true);
            Set<String> list = new HashSet<>((Collection<? extends String>) f.get(packet));
            list.add(p.getName());
            setField(packet, "c", list);
            teams.put(HashMapName, packet);
            for (Entry e : teams.entrySet()) {
                Constructor<?> scoreboardTeamConstructor = getNMSClass("PacketPlayOutScoreboardTeam").getConstructor(getNMSClass("ScoreboardTeam"), int.class);
                Object teampacket = scoreboardTeamConstructor.newInstance(e.getValue(), 3);
                sendPacket(p, teampacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setField(Object object, String fieldname, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldname);
            field.setAccessible(true);
            field.set(object, value);
            field.setAccessible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Class<?> getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
