package net.craftgalaxy.customattributes.listeners;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.craftgalaxy.customattributes.Main;
import net.craftgalaxy.customattributes.utils.Utils;

public class InventoryClick_Listener implements Listener {
	
	private Main plugin;

	public InventoryClick_Listener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onClickItem(InventoryClickEvent e) {
		String enabled = plugin.getConfig().getString("unforgeable_message.enabled");
		int slot = e.getRawSlot();
		if(e.getInventory().getType().equals(InventoryType.ANVIL)) {
			PersistentDataContainer container = e.getInventory().getItem(2).getItemMeta().getPersistentDataContainer();
			if(slot == 2) {
				if(container.has(NamespacedKey.minecraft("ca-unforgeable"), PersistentDataType.STRING)) {
				e.setCancelled(true);
					if(enabled.equalsIgnoreCase("true")) {
					e.getWhoClicked().sendMessage(Utils.chat(plugin.getConfig().getString("unforgeable_message.message")));
					}
				}
			}
		}
	}
}
