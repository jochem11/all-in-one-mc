package jochem_bergers.ranks.Commands;

import jochem_bergers.ranks.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FlyCommand implements CommandExecutor {

    private Main main;

    public FlyCommand(Main main) {
        this.main = main;
    }

    private ArrayList<Player> vliegers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("Fly.fly")) {
                if (vliegers.contains(player)) {
                    vliegers.remove(player);
                    player.setAllowFlight(false);
                    player.sendMessage(ChatColor.AQUA + "you cant fly anymore");
                } else {
                    vliegers.add(player);
                    player.setAllowFlight(true);
                    player.sendMessage(ChatColor.AQUA + "you can fly");
                }
            } else {
                player.sendMessage(ChatColor.RED + "you dont have permission to use this command");
            }
        }

        return true;
    }
}
