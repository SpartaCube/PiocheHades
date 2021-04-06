package fr.benjis;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandPiocheHades implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player player = (Player)sender;

		if(player.hasPermission("spartacube.piochehades")) {
			player.getInventory().addItem(getHadesItem());
			player.updateInventory();

		}

		return false;

	}
	
	private ItemStack getHadesItem() {
		ItemStack hades = new ItemStack(Material.NETHERITE_PICKAXE);

		ItemMeta cus = hades.getItemMeta();
		cus.setDisplayName("§e§lPioche d'Hades");
		cus.setLore(Arrays.asList("§e§l-------------","§e§lCette pioche fait fondre","§e§lvos minerais que vous minez.","§e§l-------------","§c§l[ITEM LEGENDAIRE]"));
		hades.setItemMeta(cus);
		return hades;
	}
	
}
