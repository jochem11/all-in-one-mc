package jochem_bergers.ranks.manager;

import jochem_bergers.ranks.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WarpManager {
    private File file;
    private YamlConfiguration config;
    private Main main;

    public WarpManager(Main main) {
        this.main = main;
        if (!main.getDataFolder().exists()) {
            main.getDataFolder().mkdir();
        }
        file = new File(main.getDataFolder(), "warps.yml");
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
    public void setWarp(String warpname, double x, double y, double z, float pitch, float yaw, String world) {
        config.set(warpname + ".x" , x);
        config.set(warpname + ".y" ,y);
        config.set(warpname + ".z" , z);
        config.set(warpname + ".pitch" , pitch);
        config.set(warpname + ".yaw" , yaw);
        config.set(warpname + ".world", world);
    }
    public Double getX(String warpname) {
        return (Double) config.get(warpname + ".x");
    }
    public Double getY(String warpname) {
        return (Double) config.get(warpname + ".y");
    }
    public Double getZ(String warpname) {
        return (Double) config.get(warpname + ".z");
    }
    public float getPitch(String warpname) {
        return (float) config.getDouble(warpname + ".pitch");
    }
    public float getYaw(String warpname) {
        return (float) config.getDouble(warpname + ".yaw");
    }
    public String getWorld (String warpname) {return (String) config.get(warpname + ".world");}

    public ArrayList<Object> warpnames() {
        ArrayList<Object> warpnames = new ArrayList<>();
        for (String key : config.getKeys(false)) {
            warpnames.add(key);
        }
        return warpnames;
    }
    public void DelWarp (String warpname) {
            config.set(warpname, null);
    }
}
