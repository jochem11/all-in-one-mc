package jochem_bergers.ranks.Commands;

import jochem_bergers.ranks.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("delhome.delhome")) {
                if (args.length == 1) {
                    String homename = args[0];
                    String homename2 = "." + args[0];
                    if (Main.getMain().getHomesmanager().Homes(player.getUniqueId()).contains(homename)) {
                        Main.getMain().getHomesmanager().DelHome(player.getUniqueId(), homename2);
                        player.sendMessage(ChatColor.GRAY + homename + ChatColor.AQUA + " has been removed");
                        Main.getMain().getHomesmanager().SaveFile();
                    } else {
                        player.sendMessage(ChatColor.RED + "no home found");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "use /delhome <name>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "you dont have permission to use this command");
            }
        }
        return false;
    }
}
