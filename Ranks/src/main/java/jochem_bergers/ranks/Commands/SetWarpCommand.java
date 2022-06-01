package jochem_bergers.ranks.Commands;

import jochem_bergers.ranks.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("setwarp.setwarp")) {
                if (args.length == 0) {
                    //GUI shit ?
                }
                if (args.length == 1) {
                    String warpname = args[0];
                    player.sendMessage(ChatColor.AQUA + "warp have been set to " + warpname);
                    double x = player.getLocation().getX();
                    double y = player.getLocation().getY();
                    double z = player.getLocation().getZ();
                    float pitch = player.getLocation().getPitch();
                    float yaw = player.getLocation().getYaw();
                    String world = player.getWorld().getName();
                    Main.getMain().getWarpManager().setWarp(warpname ,x, y, z, pitch, yaw, world);
                    Main.getMain().getWarpManager().SaveFile();
                } else {
                    player.sendMessage(ChatColor.RED + "use /setwarp <name>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "you dont have permission to use this command");
            }
        }
        return false;
    }
}
