package com.xviable.PuzzleDev;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class PuzzleDev extends JavaPlugin {
	//Listener Setup
	public EntityListeners el;



	public final Logger logger = Logger.getLogger("Minecraft");

	//Boolean for enable and disable commands;
	boolean pluginOn = false;

	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been enabled!");

		el = new EntityListeners(this);

		// Listeners
		Bukkit.getPluginManager().registerEvents(el, this);

		pluginOn = true;
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " has been disabled!");
		HandlerList.unregisterAll(el);

		pluginOn = false;
	}

}
