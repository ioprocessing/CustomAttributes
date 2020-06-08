package net.craftgalaxy.customattributes.commands;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.craftgalaxy.customattributes.Main;
import net.craftgalaxy.customattributes.utils.Utils;

public class Commands implements CommandExecutor {
	
	private Main plugin;
	
	public Commands(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("customattributes").setExecutor(this);
		plugin.getCommand("customattributesadmin").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("customattributesadmin") && args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				plugin.reloadConfig();
				if (!(sender instanceof Player)) {
					sender.sendMessage("CustomAttributes config has been reloaded.");
				}
				else {
					Player p = (Player) sender;
					p.sendMessage(Utils.chat("&aCustomAttributes config has been reloaded."));
				}
				return true;
			}
		}
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command!");
			return true;
		}
		
		else {
			Player p = (Player) sender;
			ItemStack heldMain = p.getInventory().getItemInMainHand();
			ItemMeta itemmeta = heldMain.getItemMeta();
		    int length = args.length;
			PersistentDataContainer container = itemmeta.getPersistentDataContainer();
	
			if(cmd.getName().equalsIgnoreCase("customattributes") && length == 2) {

				String subcommand1 = args[0].toLowerCase();
				String subcommand2 = args[1].toLowerCase();
				
				if(subcommand1.equals("add") || subcommand1.equals("remove")) {
					if(subcommand1.equals("add")) {
						
						switch (subcommand2) {
						
						case "unplaceable":
							if (p.hasPermission("customattributes.unplaceable")) {
								if(heldMain.getType().isBlock()) {
									if(!(container.has(NamespacedKey.minecraft("ca-unplaceable"), PersistentDataType.STRING))) {
										p.sendMessage(Utils.chat("&6Added attribute: unplaceable."));
										itemmeta.getPersistentDataContainer().set(NamespacedKey.minecraft("ca-unplaceable"), PersistentDataType.STRING, "protected");
										heldMain.setItemMeta(itemmeta);
										break;
										
									}
									else {
										p.sendMessage(Utils.chat("&cThis item already has that attribute!"));
										break;
										
									}
									
								} else {
									p.sendMessage(Utils.chat("&cThis item is not a block!"));
									break;
									
								}
							}
							
						default:
							p.sendMessage(Utils.chat("&cYour command was not recognized (arg 2)."));	
							break;	
							
						}
					}
						
					if(subcommand1.equals("remove")) {
						
						switch (subcommand2) {
						
						case "unplaceable":
							if (p.hasPermission("customattributes.unplaceable")) {
								if(heldMain.getType().isBlock()) {
									if(container.has(NamespacedKey.minecraft("ca-unplaceable"), PersistentDataType.STRING)) {
										p.sendMessage(Utils.chat("&6Removed attribute: unplaceable."));
										itemmeta.getPersistentDataContainer().remove(NamespacedKey.minecraft("ca-unplaceable"));
										heldMain.setItemMeta(itemmeta);
										break;
										
									}
									else {
										p.sendMessage(Utils.chat("&cThis item doesn't have that attribute!"));
										break;
										
									}
									
								} 
								else {
									p.sendMessage(Utils.chat("&cThis item is not a block!"));
									break;
								}
							}
						default:
							p.sendMessage(Utils.chat("&cYour command was not recognized (arg 2)."));	
							break;	
							
						}
					}
					
				}
				else {
					p.sendMessage(Utils.chat("&cArgument not recognized."));
				}
			return false;
		
		}
			else {
				p.sendMessage(Utils.chat("&cIncorrect amount of arguments."));	
				
				return false;
			
			}

		}
	}
}
