//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package fr.toni_4819.shiftClickPlayer;

import fr.toni_4819.shiftClickPlayer.listeners.ShiftClickListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public Main() {
    }

    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new ShiftClickListener(this), this);
        this.getLogger().info("ShiftClickPlayer enabled!");
    }

    public void onDisable() {
        this.getLogger().info("ShiftClickPlayer disabled!");
    }
}
