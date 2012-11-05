package com.xviable.PuzzleDev;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Diode;

public class BlockListeners implements Listener {
	
	private PuzzleDev plugin;

	public BlockListeners(PuzzleDev plugin) {
		this.plugin = plugin;
	}
	
	//Piston Event :: For a timer;
	@EventHandler
	public void onPistonMovement(BlockPistonExtendEvent event) {
		
		Block b = event.getBlock();
		System.out.println("Block Found: " + b);
		
		//Piston must have Lapis under it to register.
		if (b.getRelative(BlockFace.DOWN).getTypeId() == 22) {
			System.out.println("Lapis Underneath Piston = True");
			
			for (int z = -5; z <= 5; z++) {
				for (int x = -5; x <= 5; x++) {
					for (int y = 0; y <= 1; y++) {
						Block grDiode = b.getRelative(x, y, z);
						if (grDiode.getTypeId() == 93 || grDiode.getTypeId() == 94) {
							System.out.println("Diode Found Nearby");
							
							if (b.getRelative(x, (y - 1), z).getTypeId() == 22) {
								System.out.println("Lapis Underneath Diode = True");
								
								Diode diode = (Diode) grDiode.getState().getData();
								BlockFace bf = diode.getFacing();
								
								for (int xda = -1; xda <= 1; xda++) {
									for (int zda = -1; xda <= 1; zda++) {
										int faceZ = bf.getModZ();
										int faceX = bf.getModX();
										if (grDiode.getRelative((faceX * xda), 0, (faceZ * zda)).getTypeId() == 145) {
											System.out.println("Anvil Found Within 7 Blocks of Diode Face");
											
//											if (grDiode.getRelative(((faceX * xda) + faceX), 0, ((faceZ * zda)) + faceZ).getTypeId() == 0) {
//												System.out.println("Air Block Found in Path of Anvil");
												
//												grDiode.getRelative((faceX * xda), 0, (faceZ * zda)).setTypeId(0);
//												grDiode.getRelative(((faceX * xda) + faceX), 0, ((faceZ * zda)) + faceZ).setTypeId(145);
//											}
											
										}
										
									}
								}
								
							}
//							if (b.getRelative(x, y, (z + 1)).getTypeId() == 0 || b.getRelative(x, y, (z + 1)).getType() == Material.WATER) {
//								b.getRelative(x, y, z).setTypeId(0);
//								b.getRelative(x, y, (z + 1)).setTypeId(145);
//							}
						}
					}
				}
			}
		}
	
		
		
		
	}
	

}
