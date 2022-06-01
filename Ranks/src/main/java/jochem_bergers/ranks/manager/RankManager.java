package jochem_bergers.ranks.manager;

import jochem_bergers.ranks.Main;
import jochem_bergers.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {

    private File file;
    private YamlConfiguration config;
    private Main main;
    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    public RankManager(Main main) {
        this.main = main;
        if (!main.getDataFolder().exists()) {
            main.getDataFolder().mkdir();
        }
        file = new File(main.getDataFolder(), "ranks.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);

    }

    public void setRank(UUID uuid, Rank rank, boolean firstjoin) {
        if (Bukkit.getOfflinePlayer(uuid).isOnline() && !firstjoin) {
            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment attachment;
            if (perms.containsKey(uuid)) {
                attachment = perms.get(uuid);
            } else {
                attachment = player.addAttachment(main);
                perms.put(uuid, attachment);
            }
            for (String perm : getRank(uuid).getPermissions()){
                if (player.hasPermission(perm)) {
                    attachment.unsetPermission(perm);
                }
            }
            for (String perm : rank.getPermissions()){
                attachment.setPermission(perm, true);
            }
        }
        if (!firstjoin) {

        }
        config.set(uuid.toString(), rank.name());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
            Player player = Bukkit.getPlayer(uuid);
            main.getNameTaggManager().removeTagg(player);
            main.getNameTaggManager().newTagg(player);
        }


    }
    public Rank getRank(UUID uuid) {
        return Rank.valueOf(config.getString(uuid.toString()));
    }

    public HashMap<UUID, PermissionAttachment> getPerms() {
        return perms;
    }
}
