package jochem_bergers.ranks.Commands;

import jochem_bergers.ranks.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("warp.warp")) {
                if (args.length == 1) {
                    String warpname = args[0];
                    if (Main.getMain().getWarpManager().warpnames().contains(warpname)) {
                        double x = Main.getMain().getWarpManager().getX(warpname);
                        double y = Main.getMain().getWarpManager().getY(warpname);
                        double z = Main.getMain().getWarpManager().getZ(warpname);
                        float pitch = Main.getMain().getWarpManager().getPitch(warpname);
                        float yaw = Main.getMain().getWarpManager().getYaw(warpname);
                        String world = Main.getMain().getWarpManager().getWorld(warpname);
                        Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
                        player.teleport(loc);
                    } else {
                        player.sendMessage(ChatColor.RED + "no warp found");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "use /warp <name>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "you dont have permission to use this command");
            }
        }
        return false;
    }
}
