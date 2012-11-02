package com.xviable.PuzzleDev;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class PuzzleDev extends JavaPlugin {
	//Listener Setup
	public MovementListener ml;
	public BlockListener bl;

	private ArrayList<JumpPad> jumpPads;

	public final Logger logger = Logger.getLogger("Minecraft");

	//Boolean for enable and disable commands;
	boolean pluginOn = false;

	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been enabled!");

		bl = new BlockListener(jumpPads);
		ml = new MovementListener(this);

		// Listeners
		Bukkit.getPluginManager().registerEvents(ml, this);
		Bukkit.getPluginManager().registerEvents(bl, this);

		pluginOn = true;
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " has been disabled!");

		pluginOn = false;
	}

}
