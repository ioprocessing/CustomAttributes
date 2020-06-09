package net.craftgalaxy.customattributes.admincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.craftgalaxy.customattributes.Main;
import net.craftgalaxy.customattributes.utils.Utils;

public class AdminCommands implements CommandExecutor {
	
	private Main plugin;
	
	public AdminCommands(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("customattributesadmin").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { 
		
	    int length = args.length;
		
		if (cmd.getName().equalsIgnoreCase("customattributesadmin")) {
			if (length == 1)  {
				String subcommand1 = args[0].toLowerCase();
				switch (subcommand1) {
				
				case "reload":
					if (sender.hasPermission("customattributesadmin.reload")) {
						plugin.reloadConfig();
						sender.sendMessage(Utils.chat("&aCustomAttributes config has been reloaded."));
						return true;
					} else {
						sender.sendMessage(Utils.chat("&cYou don't have permission to use this command!"));
						return true;
					}
				case "help":
					if (sender.hasPermission("customattributesadmin.help")) {
						sender.sendMessage(Utils.chat("&a&m----------------&8 [&aCustom&9Attributes&8] &a&m----------------"));	
						sender.sendMessage(Utils.chat("&2&l> &a/caa help&f: Displays help page"));	
						sender.sendMessage(Utils.chat("&2&l> &a/caa reload&f: Reloads the config.yml"));
						sender.sendMessage(Utils.chat("&2&l> &a/caa attributes&f: Lists all possible attributes"));	
						sender.sendMessage(Utils.chat("&a&m-------------------------------------------------"));	
						return true;
					} else {
						sender.sendMessage(Utils.chat("&cYou don't have permission to use this command!"));
						return true;
					}
				case "attributes":
					if (sender.hasPermission("customattributesadmin.attributes")) {
						sender.sendMessage(Utils.chat("&a&m----------------&8 [&aCustom&9Attributes&8] &a&m----------------"));
						sender.sendMessage(Utils.chat("&r"));	
						sender.sendMessage(Utils.chat("&2&l> &aSome attributes are exclusive to one item type."));	
						sender.sendMessage(Utils.chat("&2&l> &a(a) = all types of items"));	
						sender.sendMessage(Utils.chat("&2&l> &a(b) = only blocks"));	
						sender.sendMessage(Utils.chat("&2&l> &a(e) = only enchantable items"));	
						sender.sendMessage(Utils.chat("&r"));	
						sender.sendMessage(Utils.chat("&2&l> &aUnplaceable (b)&f: Prevents block from being placed"));	
						sender.sendMessage(Utils.chat("&2&l> &aUnenchantable (e)&f: Prevents item from being enchanted"));	
						sender.sendMessage(Utils.chat("&2&l> &aUnforgeable (a)&f: Prevents item from being used in an anvil"));	
						sender.sendMessage(Utils.chat("&a&m-------------------------------------------------"));	
						return true;
					} else {
						sender.sendMessage(Utils.chat("&cYou don't have permission to use this command!"));
						return true;
					}
				default:
					sender.sendMessage(Utils.chat("&cYour command was not recognized."));
					return false;
				
			} 
			} else {
				sender.sendMessage(Utils.chat("&cIncorrect amount of arguments."));
				return false;
			}
		}
		return false;
	}
}