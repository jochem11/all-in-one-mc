package jochem_bergers.ranks.manager;

import jochem_bergers.ranks.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Homesmanager {
    private File file;
    private YamlConfiguration config;
    private Main main;

    public Homesmanager(Main main) {
        this.main = main;
        if (!main.getDataFolder().exists()) {
            main.getDataFolder().mkdir();
        }
        file = new File(main.getDataFolder(), "homes.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    public void SaveFile() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SetHome (String homename, double x, double y, double z, float pitch, double yaw, String world, UUID uuid, String playername) {
        config.set(uuid.toString() + ".playername" , playername);
        config.set(uuid.toString() + homename + ".x" , x);
        config.set(uuid.toString() + homename + ".y" , y);
        config.set(uuid.toString() + homename + ".z" , z);
        config.set(uuid.toString() + homename + ".pitch" , pitch);
        config.set(uuid.toString() + homename + ".yaw" , yaw);
        config.set(uuid.toString() + homename + ".world" , world);
    }
    public Double getX (UUID uuid, String homename) {return config.getDouble(uuid.toString() + homename + ".x");}
    public Double getY (UUID uuid, String homename) {return config.getDouble(uuid.toString() + homename + ".y");}
    public Double getZ (UUID uuid, String homename) {return config.getDouble(uuid.toString() + homename + ".z");}
    public float getPitch (UUID uuid, String homename) {return (float) config.getDouble(uuid.toString() + homename + ".pitch");}
    public float getYaw (UUID uuid, String homename) {return (float) config.getDouble(uuid.toString() + homename + ".yaw");}
    public String getWorld (UUID uuid, String homename) {return config.getString(uuid.toString() + homename + ".world");}

    public ArrayList<Object> Homes(UUID uuid) {
        ArrayList<Object> homes = new ArrayList<>();
        for (String key : config.getConfigurationSection(uuid.toString()).getKeys(false)) {
            homes.add(key);
        }
        return homes;
    }
    public void DelHome (UUID uuid, String homename) {
        config.set(uuid.toString() + homename , null);
    }
}
