package jochem_bergers.ranks.Commands;

import jochem_bergers.ranks.Main;
import jochem_bergers.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    private Main main;

    public RankCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender CommandSender, Command command, String label, String[] args) {
        if (CommandSender instanceof Player) {
            Player player = (Player) CommandSender;

            if (player.isOp()) {
                if (args.length == 2) {
                    if (Bukkit.getOfflinePlayer(args[0]) != null) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                        for (Rank rank : Rank.values()) {
                            if (rank.name().equals(args[1])) {
                                main.getRankManager().setRank(target.getUniqueId(), rank, false);

                                player.sendMessage(ChatColor.GREEN + "you changed " + target.getName() + "'s rank to" + rank.getDisplay() + ChatColor.GREEN );
                                if (target.isOnline()) {
                                    target.getPlayer().sendMessage(ChatColor.GREEN + player.getName() + " set your rank to " + rank.getDisplay() + ChatColor.GREEN);
                                }
                                return false;
                            }
                        }

                        player.sendMessage(ChatColor.RED + "that is not a rank");
                    } else {
                        player.sendMessage(ChatColor.RED + "this player doesnt exist");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "use /rank <player> <rank>.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "you dont have permission to use this command");
            }

        }

         return false;
    }
}
