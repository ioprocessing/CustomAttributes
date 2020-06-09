package net.craftgalaxy.customattributes.listeners;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.craftgalaxy.customattributes.Main;
import net.craftgalaxy.customattributes.utils.Utils;

public class BlockPlace_Listener implements Listener {
	
	private Main plugin;

	public BlockPlace_Listener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		String enabled = plugin.getConfig().getString("unplaceable_message.enabled");
		PersistentDataContainer container = e.getItemInHand().getItemMeta().getPersistentDataContainer();
		if(container.has(NamespacedKey.minecraft("ca-unplaceable"), PersistentDataType.STRING)) {
		e.setCancelled(true);
			if(enabled.equalsIgnoreCase("true")) {
			p.sendMessage(Utils.chat(plugin.getConfig().getString("unplaceable_message.message")));
			}
		}
		
	}
	
}
