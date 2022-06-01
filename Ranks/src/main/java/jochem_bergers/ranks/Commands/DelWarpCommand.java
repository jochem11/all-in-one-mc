package jochem_bergers.ranks.Commands;

import jochem_bergers.ranks.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

public class DelWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("Delwarp.delwarp")) {
                if (args.length == 1) {
                    String warpname = args[0];
                    if (Main.getMain().getWarpManager().warpnames().contains(warpname)) {
                        Main.getMain().getWarpManager().DelWarp(warpname);
                        Main.getMain().getWarpManager().SaveFile();
                        player.sendMessage(ChatColor.GRAY + warpname + ChatColor.AQUA + " has been removed");
                    } else {
                        player.sendMessage(ChatColor.RED + "no warp found by " + ChatColor.AQUA + warpname);
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "use /delwarp <name>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "you dont have permission to use this command");
            }
        }
        return false;
    }
}
