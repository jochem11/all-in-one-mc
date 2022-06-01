package jochem_bergers.ranks.Commands;

import jochem_bergers.ranks.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BanCommand implements CommandExecutor {

    Main main;

    public BanCommand(Main plugin) {
        this.main = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("Ban.ban")) {
                main.openGUI(player);
            } else {
                player.sendMessage(ChatColor.RED + "you dont have permission to use this command");
            }
        }

        return true;
    }
}
