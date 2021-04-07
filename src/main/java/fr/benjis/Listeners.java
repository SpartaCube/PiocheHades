package fr.benjis;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Listeners implements Listener {


	@EventHandler(priority = EventPriority.HIGH)
	private void onBlockBreak(BlockBreakEvent ev) {
		if(!ev.isCancelled()) {
			Player player = ev.getPlayer();
			PlayerInventory inv = player.getInventory();
			ItemStack itemInHand = inv.getItemInMainHand();
			Location test = ev.getBlock().getLocation();
			Block block1 = ev.getBlock();
			Boolean enchantFortune = false;
			int enchantFortuneLevel = 0;
			if(itemInHand.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
				enchantFortune = true;
				enchantFortuneLevel = itemInHand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
			}
			int amount = getMultiplier(enchantFortuneLevel);
			if(isHadesItem(itemInHand)) {
				ItemStack item;
				switch (ev.getBlock().getType()) {
				case GOLD_ORE:
					((ExperienceOrb)player.getWorld().spawn(test, ExperienceOrb.class)).setExperience(1 * amount);
					ev.setCancelled(true);
					block1.setType(Material.AIR);
					item = new ItemStack(Material.GOLD_INGOT);
					if(enchantFortune == true) {
						item.setAmount(amount);
					}
					player.getWorld().dropItem(test, item);
					break;
				case IRON_ORE:
					((ExperienceOrb)player.getWorld().spawn(test, ExperienceOrb.class)).setExperience((int) 0.7 * amount);
					ev.setCancelled(true);
					block1.setType(Material.AIR);
					item = new ItemStack(Material.IRON_INGOT);
					if(enchantFortune == true) {
						item.setAmount(amount);
					}
					player.getWorld().dropItem(test, item);
					break;
				case ANCIENT_DEBRIS:
					item = new ItemStack(Material.NETHERITE_SCRAP);
					((ExperienceOrb)player.getWorld().spawn(test, ExperienceOrb.class)).setExperience(2);
					block1.setType(Material.AIR);
					player.getWorld().dropItem(test, item);
					break;
				default:
					break;
				}
			}
		}
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