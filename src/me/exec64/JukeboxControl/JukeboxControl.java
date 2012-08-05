package me.exec64.JukeboxControl;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class JukeboxControl extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new JCBlockListener(this), this);
		
		getLogger().info("JukeboxControl has been enabled.");
	}

	public void onDisable() {
		getLogger().info("JukeboxControl has been disabled");
	}
}
