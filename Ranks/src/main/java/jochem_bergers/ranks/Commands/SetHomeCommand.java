package jochem_bergers.ranks.Commands;

import jochem_bergers.ranks.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("sethome.sethome")) {
                if (args.length == 1) {
                    String playername = player.getName();
                    String homename =  "." + args[0];
                    double x = player.getLocation().getX();
                    double y = player.getLocation().getY();
                    double z = player.getLocation().getZ();
                    float pitch = player.getLocation().getPitch();
                    float yaw = player.getLocation().getYaw();
                    String world = player.getWorld().getName();
                    Main.getMain().getHomesmanager().SetHome(homename, x, y, z, pitch, yaw, world, player.getUniqueId(), playername);
                    Main.getMain().getHomesmanager().SaveFile();
                } else {
                    player.sendMessage(ChatColor.RED + "use /sethome <name>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "you dont have permission to use this command");
            }
        }
        return false;
    }
}
