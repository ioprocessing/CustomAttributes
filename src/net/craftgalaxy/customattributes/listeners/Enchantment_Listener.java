package net.craftgalaxy.customattributes.listeners;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.craftgalaxy.customattributes.Main;
import net.craftgalaxy.customattributes.utils.Utils;

public class Enchantment_Listener implements Listener {
	
	private Main plugin;

	public Enchantment_Listener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEnchantItem(EnchantItemEvent e) {
		String enabled = plugin.getConfig().getString("unenchantable_message.enabled");
		PersistentDataContainer container = e.getItem().getItemMeta().getPersistentDataContainer();
		if(container.has(NamespacedKey.minecraft("ca-unenchantable"), PersistentDataType.STRING)) {
		e.setCancelled(true);
			if(enabled.equalsIgnoreCase("true")) {
			e.getEnchanter().sendMessage(Utils.chat(plugin.getConfig().getString("unenchantable_message.message")));
			}
		}
		
	}
	
}
