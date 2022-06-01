package jochem_bergers.ranks.Commands;

import jochem_bergers.ranks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ShowHomesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("showhome.showhome")) {
                ArrayList<Object> homes = Main.getMain().getHomesmanager().Homes(player.getUniqueId());;
                for (Object name: homes) {
                    player.sendMessage(String.valueOf(name));
                }
            }
        }
        return false;
    }
}
