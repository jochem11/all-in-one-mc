package jochem_bergers.ranks;

import jochem_bergers.ranks.Commands.*;
import jochem_bergers.ranks.Events.BanInventoryEvents;
import jochem_bergers.ranks.manager.Homesmanager;
import jochem_bergers.ranks.manager.NameTaggManager;
import jochem_bergers.ranks.manager.RankManager;
import jochem_bergers.ranks.manager.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {
    private static Main main;
    private RankManager rankManager;
    private NameTaggManager nameTaggManager;
    private WarpManager warpManager;
    private Homesmanager homesmanager;
    @Override
    public void onEnable() {
        Main.main = this;

        rankManager = new RankManager(this);
        warpManager = new WarpManager(this);
        homesmanager = new Homesmanager(this);

        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("Fly").setExecutor(new FlyCommand(this));
        getCommand("ban").setExecutor(new BanCommand(this));
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("warps").setExecutor(new ShowWarpsCommand());
        getCommand("delwarp").setExecutor(new DelWarpCommand());
        getCommand("setHome").setExecutor(new SetHomeCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("delHome").setExecutor(new DelHomeCommand());
        getCommand("homes").setExecutor(new ShowHomesCommand());
        getServer().getPluginManager().registerEvents(new BanInventoryEvents(this), this);

        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);

        nameTaggManager = new NameTaggManager(this);
    }

    public RankManager getRankManager() { return rankManager; }

    public NameTaggManager getNameTaggManager() {return nameTaggManager; }

    public WarpManager getWarpManager() {return warpManager;}

    public Homesmanager getHomesmanager() {return homesmanager;}






    public void openGUI( Player player) {
        ArrayList<Player> player_list = new ArrayList<>(player.getServer().getOnlinePlayers());
        Inventory bangui = Bukkit.createInventory(player, 45, ChatColor.AQUA + "player list");
        for (int i = 0; i < player_list.size(); i++) {
            ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemMeta meta = playerhead.getItemMeta();
            meta.setDisplayName(player_list.get(i).getDisplayName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "player health: " + player_list.get(i).getHealth());
            lore.add(ChatColor.GOLD + "player xp: " + player_list.get(i).getExp());
            meta.setLore(lore);
            playerhead.setItemMeta(meta);
            bangui.addItem(playerhead);
        }
        player.openInventory(bangui);
    }
    public void openBanConfermMenu (Player player, Player playerToBan) {

        Player banMe = playerToBan;

        Inventory banPlayerMenu = Bukkit.createInventory(player, 9, "Ban this nerd");

        //Ban Option
        ItemStack ban = new ItemStack(Material.WOODEN_AXE, 1);
        ItemMeta ban_meta = ban.getItemMeta();
        ban_meta.setDisplayName(ChatColor.DARK_GREEN + "Ban");
        ban.setItemMeta(ban_meta);
        banPlayerMenu.setItem(0, ban);

        //Add player
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta player_meta = playerHead.getItemMeta();
        player_meta.setDisplayName(banMe.getDisplayName());
        playerHead.setItemMeta(player_meta);
        banPlayerMenu.setItem(4, playerHead);

        //Cancel option
        ItemStack cancel = new ItemStack(Material.BARRIER, 1);
        ItemMeta cancel_meta = cancel.getItemMeta();
        cancel_meta.setDisplayName(ChatColor.RED + "Go Back!");
        cancel.setItemMeta(cancel_meta);
        banPlayerMenu.setItem(8, cancel);

        player.openInventory(banPlayerMenu);
    }

    public static Main getMain() {
        return main;
    }
}
