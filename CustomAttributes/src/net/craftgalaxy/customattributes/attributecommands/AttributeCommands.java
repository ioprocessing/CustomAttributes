package net.craftgalaxy.customattributes.attributecommands;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.craftgalaxy.customattributes.Main;
import net.craftgalaxy.customattributes.utils.Utils;

public class AttributeCommands implements CommandExecutor {
	
	private Main plugin;
	
	public AttributeCommands(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("customattributes").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { 
		
	    int length = args.length;
		
		if(cmd.getName().equalsIgnoreCase("customattributes")) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command!");
			return true;
		}
		
		else {
			Player p = (Player) sender;
			
			if(!(p.getInventory().getItemInMainHand().getType().isAir())) {
				
				ItemStack heldMain = p.getInventory().getItemInMainHand();
				ItemMeta itemmeta = heldMain.getItemMeta();
				PersistentDataContainer container = itemmeta.getPersistentDataContainer();
			
			if(length == 2) {

				String subcommand1 = args[0].toLowerCase();
				String subcommand2 = args[1].toLowerCase();
				
				if(subcommand1.equals("add") || subcommand1.equals("remove")) {
					if(subcommand1.equals("add")) {
						
						switch (subcommand2) {
						
						case "unplaceable":
							if (p.hasPermission("customattributes.unplaceable")) {
								if(heldMain.getType().isBlock()) {
									if(!(container.has(NamespacedKey.minecraft("ca-unplaceable"), PersistentDataType.STRING))) {
										p.sendMessage(Utils.chat("&aAdded attribute: unplaceable."));
										itemmeta.getPersistentDataContainer().set(NamespacedKey.minecraft("ca-unplaceable"), PersistentDataType.STRING, "protected");
										heldMain.setItemMeta(itemmeta);
										return true;
										
									}
									else {
										p.sendMessage(Utils.chat("&cThis item already has that attribute!"));
										return true;
										
									}
									
								} else {
									p.sendMessage(Utils.chat("&cThis item is not a block!"));
									return true;
									
								}
							} else {
								p.sendMessage(Utils.chat("&cYou don't have permission to use this command!"));
								return true;
							}
						case "unenchantable":
							if (p.hasPermission("customattributes.unenchantable")) {
								if(Enchantment.DURABILITY.canEnchantItem(heldMain)) {
									if(!(container.has(NamespacedKey.minecraft("ca-unenchantable"), PersistentDataType.STRING))) {
										p.sendMessage(Utils.chat("&aAdded attribute: unenchantable."));
										itemmeta.getPersistentDataContainer().set(NamespacedKey.minecraft("ca-unenchantable"), PersistentDataType.STRING, "cantenchant");
										heldMain.setItemMeta(itemmeta);
										return true;
										
									}
									else {
										p.sendMessage(Utils.chat("&cThis item already has that attribute!"));
										return true;
										
									}
									
								} else {
									p.sendMessage(Utils.chat("&cThis item can't be put in an enchanting table!"));
									return true;
									
								}
							} else {
								p.sendMessage(Utils.chat("&cYou don't have permission to use this command!"));
								return true;
							}
						case "unforgeable":
							if (p.hasPermission("customattributes.unforgeable")) {
									if(!(container.has(NamespacedKey.minecraft("ca-unforgeable"), PersistentDataType.STRING))) {
										p.sendMessage(Utils.chat("&aAdded attribute: unforgeable."));
										itemmeta.getPersistentDataContainer().set(NamespacedKey.minecraft("ca-unforgeable"), PersistentDataType.STRING, "cantforge");
										heldMain.setItemMeta(itemmeta);
										return true;
										
									}
									else {
										p.sendMessage(Utils.chat("&cThis item already has that attribute!"));
										return true;
										
									}
									
							} else {
								p.sendMessage(Utils.chat("&cYou don't have permission to use this command!"));
								return true;
							}
						default:
							p.sendMessage(Utils.chat("&cYour command was not recognized."));	
							return false;	
							
						}
					}
						
					if(subcommand1.equals("remove")) {
						
						switch (subcommand2) {
						
						case "unplaceable":
							if (p.hasPermission("customattributes.unplaceable")) {
									if(container.has(NamespacedKey.minecraft("ca-unplaceable"), PersistentDataType.STRING)) {
										p.sendMessage(Utils.chat("&aRemoved attribute: unplaceable."));
										itemmeta.getPersistentDataContainer().remove(NamespacedKey.minecraft("ca-unplaceable"));
										heldMain.setItemMeta(itemmeta);
										return true;
										
									}
									else {
										p.sendMessage(Utils.chat("&cThis item doesn't have that attribute!"));
										return true;
										
									}
									
							} else {
								p.sendMessage(Utils.chat("&cYou don't have permission to use this command!"));
								return true;
							}
						case "unenchantable":
							if (p.hasPermission("customattributes.unenchantable")) {
									if(container.has(NamespacedKey.minecraft("ca-unenchantable"), PersistentDataType.STRING)) {
										p.sendMessage(Utils.chat("&aRemoved attribute: unenchantable."));
										itemmeta.getPersistentDataContainer().remove(NamespacedKey.minecraft("ca-unenchantable"));
										heldMain.setItemMeta(itemmeta);
										return true;
										
									}
									else {
										p.sendMessage(Utils.chat("&cThis item doesn't have that attribute!"));
										return true;
										
									}
									
							} else {
								p.sendMessage(Utils.chat("&cYou don't have permission to use this command!"));
								return true;
							}
						case "unforgeable":
							if (p.hasPermission("customattributes.unforgeable")) {
									if(container.has(NamespacedKey.minecraft("ca-unforgeable"), PersistentDataType.STRING)) {
										p.sendMessage(Utils.chat("&aRemoved attribute: unforgeable."));
										itemmeta.getPersistentDataContainer().remove(NamespacedKey.minecraft("ca-unforgeable"));
										heldMain.setItemMeta(itemmeta);
										return true;
										
									}
									else {
										p.sendMessage(Utils.chat("&cThis item doesn't have that attribute!"));
										return true;
										
									}
									
							} else {
								p.sendMessage(Utils.chat("&cYou don't have permission to use this command!"));
								return true;
							}
						default:
							p.sendMessage(Utils.chat("&cYour command was not recognized."));	
							return false;	
							
						}
					}
					
				}
				else {
					p.sendMessage(Utils.chat("&cYour command was not recognized."));
					
					return false;
				}
		
		}
			else {
				p.sendMessage(Utils.chat("&cIncorrect amount of arguments."));	
				
				return false;
			}
			} else {
			sender.sendMessage(Utils.chat("&cYou must have an item in your hand to perform this command!"));
			return true;
			}
		}
		}
		return true;
	}
}
