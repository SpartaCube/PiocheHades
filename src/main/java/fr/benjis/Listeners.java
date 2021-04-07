package fr.benjis;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Listeners implements Listener {


	@EventHandler
	private void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		PlayerInventory inv = player.getInventory();
		ItemStack itemInHand = inv.getItemInMainHand();
		Block block = e.getBlock();
		Location loc = block.getLocation();
		if(isHadesItem(itemInHand)) {
			switch (block.getType()) {
			case GOLD_ORE:
				drop(e, Material.GOLD_INGOT, 1, loc, true);
				break;
			case IRON_ORE:
				drop(e, Material.IRON_INGOT, 0.7, loc, true);
				break;
			case ANCIENT_DEBRIS:
				drop(e, Material.GOLD_INGOT, 1, loc, false);
				break;
			case NETHER_GOLD_ORE:
				drop(e, Material.NETHERITE_SCRAP, 1, loc, false);
				break;
			default:
				break;
			}

		}
	}

	private void drop(BlockBreakEvent e, Material newDrop, double xp, Location loc, boolean fortuneMultiply) {
		Player player = e.getPlayer();
		ItemStack toDrop = new ItemStack(newDrop);
		int amountToDrop = 1;
		
		if(fortuneMultiply) {
			int fortuneLevel = player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
			amountToDrop = getMultiplier(fortuneLevel);
			toDrop.setAmount(amountToDrop);
		}
		
		e.setDropItems(false);
		e.setExpToDrop((int)(xp * amountToDrop));
		player.getWorld().dropItem(loc, toDrop);
	}

	private boolean isHadesItem(ItemStack item) {
		if(item.hasItemMeta() && item.getItemMeta().hasLore() && item.getItemMeta().getLore().contains("§e§lCette pioche fait fondre")) {
			return true;
		}
		return false;
	}

	private int getMultiplier(int fortunelevel) {
		Random rand = new Random();
		int alz2 = rand.nextInt(100 + 1);
		switch(fortunelevel) {

		case(3):
			if(alz2 <= 20 && alz2 >= 1) {
				return 2;
			}else if(alz2 <= 40 && alz2 > 20) {
				return 3;
			}else if(alz2 <= 60 && alz2 > 40) {
				return 4;
			}else {
				return 1;
			}
		case(2):
			if(alz2 <= 25 && alz2 >= 1) {
				return 2;
			}else if(alz2 <= 50 && alz2 > 25) {
				return 3;
			}else {
				return 1;
			}
		case(1):
			if(alz2 <= 33 && alz2 >= 1) {
				return 2;
			}else {
				return 1;
			}
		}

		return 1;
	}

}