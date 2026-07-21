//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package fr.toni_4819.shiftClickPlayer.listeners;

import fr.toni_4819.shiftClickPlayer.Main;
import fr.toni_4819.shiftClickPlayer.utils.Chat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ShiftClickListener implements Listener {
    private final Main plugin;
    private final Map<UUID, Long> cooldowns = new HashMap();

    public ShiftClickListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if (event.getHand() == EquipmentSlot.HAND) {
            Player clicker = event.getPlayer();
            if (clicker.isSneaking()) {
                Entity var4 = event.getRightClicked();
                if (var4 instanceof Player) {
                    Player clicked = (Player)var4;
                    if (this.plugin.getConfig().getStringList("config.enabled_worlds").contains(clicker.getWorld().getKey().toString())) {
                        if (!clicker.hasPermission("shiftclick.use")) {
                            String var10001 = this.plugin.getConfig().getString("messages.prefix");
                            clicker.sendMessage(Chat.ChatColor(var10001 + this.plugin.getConfig().getString("messages.no_permission")));
                        } else {
                            int cooldown = this.plugin.getConfig().getInt("config.cooldown");
                            if (cooldown > 0) {
                                UUID uuid = clicker.getUniqueId();
                                long now = System.currentTimeMillis();
                                if (this.cooldowns.containsKey(uuid)) {
                                    long endTime = (Long)this.cooldowns.get(uuid);
                                    if (endTime > now) {
                                        return;
                                    }
                                }

                                this.cooldowns.put(uuid, now + (long)cooldown * 50L);
                            }

                            for(String command : this.plugin.getConfig().getStringList("config.commands")) {
                                command = command.replace("%player_clicker%", clicker.getName()).replace("%player_clicked%", clicked.getName());
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                            }

                        }
                    }
                }
            }
        }
    }
}
