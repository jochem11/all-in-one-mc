package jochem_bergers.ranks.manager;

import jochem_bergers.ranks.Main;
import jochem_bergers.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NameTaggManager {

    private Main main;
    public NameTaggManager(Main main) {
        this.main = main;
    }

    public void setNameTaggs(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        for (Rank rank : Rank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.name());
            team.setPrefix(rank.getDisplay() + " ");
        }
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (player.getUniqueId() != target.getUniqueId()) {
                player.getScoreboard().getTeam(main.getRankManager().getRank(target.getUniqueId()).name()).addEntry(target.getName());
            }
        }
    }

    public void newTagg(Player player) {
        Rank rank = main.getRankManager().getRank(player.getUniqueId());
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getTeam(rank.name()).addEntry(player.getName());
        }
    }

    public void removeTagg(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }

}
