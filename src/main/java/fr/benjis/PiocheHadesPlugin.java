package fr.benjis;

import org.bukkit.plugin.java.JavaPlugin;

public class PiocheHadesPlugin extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Listeners(), this);
		getCommand("piochehades").setExecutor(new CommandPiocheHades());
	}

}
