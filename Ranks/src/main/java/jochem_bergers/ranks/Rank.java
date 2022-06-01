package jochem_bergers.ranks;

import com.sun.jmx.remote.internal.ClientCommunicatorAdmin;
import jochem_bergers.ranks.Commands.FlyCommand;
import org.bukkit.ChatColor;

public enum Rank {

    OWNER(ChatColor.DARK_RED + "Owner", new String[]{"Fly.fly", "Ban.ban", "setwarp.setwarp", "warp.warp", "Delwarp.delwarp", "warps.warps", "delhome.delhome", "home.home", "showhome.showhome", "sethome.sethome" }),
    ADMIN(ChatColor.RED + "Admin", new String[]{"Fly.fly", "setwarp.setwarp", "warp.warp", "warps.warps", "Delwarp.delwarp", "delhome.delhome", "home.home", "showhome.showhome", "sethome.sethome"}),
    MEMBER(ChatColor.YELLOW + "Member", new String[]{"warp.warp", "warps.warps", "delhome.delhome", "home.home", "showhome.showhome", "sethome.sethome"}),
    GUEST( ChatColor.GRAY + "Guest", new String[]{});

    private String display;
    private String[] permissions;

    Rank(String display, String[] permissions) {
        this.display = display;
        this.permissions = permissions;
    }
    public String getDisplay() { return display; }

    public String[] getPermissions() {
        return permissions;
    }
}
