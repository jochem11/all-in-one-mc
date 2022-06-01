package jochem_bergers.ranks.Commands;

import jochem_bergers.ranks.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("home.home")) {
                if (args.length == 1) {
                    String homename2 = args[0];
                    String homename = "." + args[0];
                    if (Main.getMain().getHomesmanager().Homes(player.getUniqueId()).contains(homename2)) {
                        double x = Main.getMain().getHomesmanager().getX(player.getUniqueId(), homename);
                        double y = Main.getMain().getHomesmanager().getY(player.getUniqueId(), homename);
                        double z = Main.getMain().getHomesmanager().getZ(player.getUniqueId(), homename);
                        float pitch = Main.getMain().getHomesmanager().getPitch(player.getUniqueId(), homename);
                        float yaw = Main.getMain().getHomesmanager().getYaw(player.getUniqueId(), homename);
                        String world = Main.getMain().getHomesmanager().getWorld(player.getUniqueId(), homename);
                        Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
                        player.teleport(loc);
                    } else {
                        player.sendMessage(ChatColor.RED + "no home found");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "use /home <name>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "you dont have permission to use this command");
            }
        }
        return false;
    }
}
