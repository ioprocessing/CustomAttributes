package net.craftgalaxy.customattributes;

import org.bukkit.plugin.java.JavaPlugin;

import net.craftgalaxy.customattributes.commands.Commands;
import net.craftgalaxy.customattributes.listeners.BlockPlace_Listener;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		new BlockPlace_Listener(this);
		saveDefaultConfig();
		new Commands(this);
	}
	
}