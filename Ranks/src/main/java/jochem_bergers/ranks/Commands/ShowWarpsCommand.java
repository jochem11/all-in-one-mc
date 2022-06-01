package jochem_bergers.ranks.Commands;

import jochem_bergers.ranks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ShowWarpsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("warps.warps")) {
                ArrayList<Object> warps = Main.getMain().getWarpManager().warpnames();
                for (Object name: warps) {
                    player.sendMessage(String.valueOf(name));
                }
            }
        }
        return false;
    }
}
