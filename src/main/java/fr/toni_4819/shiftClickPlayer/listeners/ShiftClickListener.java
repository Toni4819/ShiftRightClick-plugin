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
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public ShiftClickListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        Player clicker = event.getPlayer();
        if (!clicker.isSneaking()) {
            return;
        }

        Entity target = event.getRightClicked();
        if (!(target instanceof Player)) {
            return;
        }

        Player clicked = (Player) target;

        if (!this.plugin.getConfig().getStringList("config.enabled_worlds").contains(clicker.getWorld().getKey().toString())) {
            return;
        }

        if (!clicker.hasPermission("shiftclick.use")) {
            String prefix = this.plugin.getConfig().getString("messages.prefix");
            clicker.sendMessage(Chat.ChatColor(prefix + this.plugin.getConfig().getString("messages.no_permission")));
            return;
        }

        int cooldown = this.plugin.getConfig().getInt("config.cooldown");
        if (cooldown > 0) {
            UUID uuid = clicker.getUniqueId();
            long now = System.currentTimeMillis();
            if (this.cooldowns.containsKey(uuid)) {
                long endTime = this.cooldowns.get(uuid);
                if (endTime > now) {
                    return;
                }
            }
            this.cooldowns.put(uuid, now + (long) cooldown * 50L);
        }

        // Commandes exécutées par la console (permissions serveur)
        for (String command : this.plugin.getConfig().getStringList("config.server_commands")) {
            command = command.replace("%player_clicker%", clicker.getName())
                    .replace("%player_clicked%", clicked.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        }

        // Commandes exécutées en tant que joueur (permissions du clicker)
        for (String command : this.plugin.getConfig().getStringList("config.player_commands")) {
            command = command.replace("%player_clicker%", clicker.getName())
                    .replace("%player_clicked%", clicked.getName());
            Bukkit.dispatchCommand(clicker, command);
        }
    }
}