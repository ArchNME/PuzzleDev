package com.xviable.PuzzleDev;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Logger;

import net.minecraft.server.EntityTypes;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class PuzzleDev extends JavaPlugin {
	//Listener Setup
	public EntityListeners el;
	public BlockListeners bl;



	public final Logger logger = Logger.getLogger("Minecraft");

	//Boolean for enable and disable commands;
	boolean pluginOn = false;

	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been enabled!");

		el = new EntityListeners(this);
		bl = new BlockListeners(this);

		// Listeners
		Bukkit.getPluginManager().registerEvents(el, this);
		Bukkit.getPluginManager().registerEvents(bl, this);

		pluginOn = true;
		
		// Sand Entities;
		
		try
	    {
	      Class[] args = new Class[3];
	      args[0] = Class.class;
	      args[1] = String.class;
	      args[2] = Integer.TYPE;

	      Method a = EntityTypes.class.getDeclaredMethod("a", args);
	      a.setAccessible(true);

	      a.invoke(a, new Object[] { EntityPushedSand.class, "PushedSand", Integer.valueOf(21) });
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      System.out.println("Error while registering PushSand entity.");
	      setEnabled(false);
	      return;
	    }

		
		
		
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		this.logger.info(pdfFile.getName() + " has been disabled!");
		HandlerList.unregisterAll(el);

		pluginOn = false;
	}

}
