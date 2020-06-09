package net.craftgalaxy.customattributes.listeners;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.craftgalaxy.customattributes.Main;
import net.craftgalaxy.customattributes.utils.Utils;

public class InventoryMove_Listener implements Listener {
	
	private Main plugin;

	public InventoryMove_Listener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onMoveItem(InventoryMoveItemEvent e) {
		String enabled = plugin.getConfig().getString("unforgeable_message.enabled");
		PersistentDataContainer container = e.getItem().getItemMeta().getPersistentDataContainer();
		Player p = (Player) e.getSource().getHolder();
		if(e.getDestination().getType().equals(InventoryType.ANVIL)) {
		Bukkit.broadcastMessage("step one passed");
			if(container.has(NamespacedKey.minecraft("ca-unforgeable"), PersistentDataType.STRING)) {
			e.setCancelled(true);
				if(enabled.equalsIgnoreCase("true")) {
				p.sendMessage(Utils.chat(plugin.getConfig().getString("unforgeable_message.message")));
				}
			}
		}
	}
}
