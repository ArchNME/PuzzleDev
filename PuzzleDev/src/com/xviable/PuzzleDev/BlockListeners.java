package com.xviable.PuzzleDev;

import java.util.HashMap;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;


public class BlockListeners implements Listener {
	
	private PuzzleDev plugin;

	public BlockListeners(PuzzleDev plugin) {
		this.plugin = plugin;
	}
	
	private HashMap<String,Location> signLocations = new HashMap<String,Location>(); 
	
	@EventHandler
	public void onBlockRedstoneEvent(BlockRedstoneEvent event) {
		
		int newC = event.getNewCurrent();
		int oldC = event.getOldCurrent();
		
		World w = event.getBlock().getLocation().getWorld();
		
		if (newC - oldC > 0) {
			Block b = event.getBlock();
			
			for(int z = -1; z <= 1; z++) {
				for(int x = -1; x <= 1; x++) {
					for(int y = -1; y <= 1; y++) {
						if((x*x+y*y+z*z==1) && b.getRelative(x, y, z).getTypeId() == 68 || (x*x+y*y+z*z==1) && b.getRelative(x, y, z).getTypeId() == 63) {
							Sign sign = (Sign)b.getRelative(x, y, z).getState();
							if (sign.getLine(0).equalsIgnoreCase("Region")) {
								String line2 = sign.getLine(1);
								if (signLocations.containsKey(line2)) {
									if (signLocations.containsKey(line2 + "b")) {
										Location l1 = signLocations.get(line2);
										Location l2 = signLocations.get(line2 + "b");
										
										double x1 = l1.getX();
										double x2 = l2.getX();
										
										double maxx = Math.max(x1,x2);
										
										double z1 = l1.getZ();
										double z2 = l2.getZ();
										
										double maxz = Math.max(z1,z2);
										
										double y1 = l1.getY();
										double y2 = l2.getY();
										
										double maxy = Math.max(y1,y2);
										
										
										for (double minz = Math.min(z1,z2); minz <= maxz; minz++) {
											for (double minx = Math.min(x1,x2); minx <= maxx; minx++) {
												for (double miny = Math.min(y1,y2); miny <= maxy; miny++) {
													
													Location dummyloc = new Location(w, minx,miny,minz);
													
													
													if (dummyloc.getBlock().getTypeId() == 12 && dummyloc.getBlock().getRelative(BlockFace.DOWN).getTypeId() != 22) {
														dummyloc.getBlock().setTypeId(0);
													}
													if (dummyloc.getBlock().getTypeId() == 22) {
														dummyloc.getBlock().getRelative(BlockFace.UP).setTypeId(12);
													}
												}
											}
										}
										
										
										
									}
								}
							}
						}
					}
				}
			}
		}
	}
		
	@EventHandler
	public void onSignPlace(SignChangeEvent event) {
		
		Player p = event.getPlayer();
		Block b = event.getBlock();
		
		if (event.getLine(0).equalsIgnoreCase("Region") && p.isOp()) {
			
			String line2 = event.getLine(1);
			if (line2.isEmpty()) {
				return;
			}
			
			if (signLocations.containsKey(line2) && !signLocations.containsKey(line2 + "b")) {
				System.out.println("Region point 2 being created. . .");
				System.out.println("Region name is " + line2 + ". . .");
				signLocations.put(line2 + "b", b.getLocation());
			}
			
			if (!signLocations.containsKey(line2)) {
				System.out.println("Region point 1 being created. . .");
				System.out.println("Region name is " + line2 + ". . .");
				signLocations.put(line2, b.getLocation());
			}
			
		}
		
	}
		
	
	
	
	
}
