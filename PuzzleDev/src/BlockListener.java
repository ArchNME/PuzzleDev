import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Diode;

public class BlockListener implements Listener {
	private ArrayList<JumpPad> jumpPads;
		
	public BlockListener(ArrayList<JumpPad> jumpPads) {
		this.jumpPads = jumpPads;
	}
	
	public void onBlockPlaced(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Location blockLoc = block.getLocation();
		
		//If a stone plate is placed, with a block of obsidian below it, then check for any redstone diodes
		if(block.getType() == Material.STONE_PLATE) {
			if(block.getRelative(BlockFace.DOWN).getType() == Material.OBSIDIAN) 
				
				for(BlockFace face : BlockFace.values()) {
					if(block.getRelative(face).getType() == Material.DIODE) {
						//Conditions met to create a jumpPad
						Diode diode = (Diode)block.getRelative(face);
						BlockFace dir = diode.getFacing(); 
						Location plateLoc = block.getLocation();
						int power = diode.getDelay();
						for (int i = 0; i < jumpPads.size(); i++) {							
							if(jumpPads.get(i).getLocation() == plateLoc) return;
						}
						jumpPads.add(new JumpPad(plateLoc, dir, power));
						return;
					}
				}		
		}
		//If a redstone diode is placed, check around it for stone pressure plates on obsidian
		if(block.getType() == Material.DIODE) {
			for(BlockFace face : BlockFace.values()) {
				if(block.getRelative(face).getType() == Material.STONE_PLATE) {
					if(block.getRelative(BlockFace.DOWN).getType() == Material.OBSIDIAN) {
						//Conditions met to create a jumpPad
						Diode diode = (Diode)block;
						BlockFace dir = diode.getFacing(); 
						Location plateLoc = block.getRelative(face).getLocation();
						int power = diode.getDelay();
						for (int i = 0; i < jumpPads.size(); i++) {							
							if(jumpPads.get(i).getLocation() == plateLoc) return;
						}
						jumpPads.add(new JumpPad(plateLoc, dir, power));						
					}
				}
			}
		}
	}
}
