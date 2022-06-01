package jochem_bergers.ranks.Events;

import jochem_bergers.ranks.Main;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class BanInventoryEvents implements Listener {

    Main main;

    public BanInventoryEvents(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA + "player list")) {
            if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                Player whoToBan = player.getServer().getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
                main.openBanConfermMenu(player, whoToBan);
            }
        } else if (e.getView().getTitle().equalsIgnoreCase("Ban this nerd")){
            switch (e.getCurrentItem().getType()) {
                case WOODEN_AXE:
                    String name = e.getClickedInventory().getItem(4).getItemMeta().getDisplayName();
                    player.getServer().getBanList(BanList.Type.NAME).addBan(name, "just quz", null, "fk jou");
                player.sendMessage(ChatColor.AQUA + "banned player");
                    break;
                case BARRIER:
                    main.openGUI(player);
                    break;
            }
        }
        e.setCancelled(true);
    }
}
