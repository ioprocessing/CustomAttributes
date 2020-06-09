package net.craftgalaxy.customattributes;

import org.bukkit.plugin.java.JavaPlugin;

import net.craftgalaxy.customattributes.admincommands.AdminCommands;
import net.craftgalaxy.customattributes.attributecommands.AttributeCommands;
import net.craftgalaxy.customattributes.listeners.BlockPlace_Listener;
import net.craftgalaxy.customattributes.listeners.Enchantment_Listener;
import net.craftgalaxy.customattributes.listeners.InventoryClick_Listener;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		new BlockPlace_Listener(this);
		new Enchantment_Listener(this);
		new InventoryClick_Listener(this);
		new AttributeCommands(this);
		new AdminCommands(this);
		saveDefaultConfig();
	}
	
}