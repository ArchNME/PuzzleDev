package com.xviable.PuzzleDev;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Diode;

//BlockListenComment? WORKS?
public class BlockListener implements Listener {
	private ArrayList<JumpPad> jumpPads;

	public BlockListener(ArrayList<JumpPad> jumpPads) {
		this.jumpPads = jumpPads;
	}

	@EventHandler
	public void onBlockPlaced(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Location blockLoc = block.getLocation();
		System.out.println("Block Place Event Detected");

		//If a stone plate is placed, with a block of obsidian below it, then check for any redstone diodes
		if (block.getType() == Material.STONE_PLATE) {
			System.out.println("Stone Plate Placed");
			if (block.getRelative(BlockFace.DOWN).getType() == Material.OBSIDIAN) System.out.println("Obsidian Below Accepted");
			for (BlockFace face : BlockFace.values()) {
				if (block.getRelative(face).getTypeId() == 93 || block.getRelative(face).getTypeId() == 94) {
					System.out.println("Diode Face Accepted");
					//Conditions met to create a jumpPad
					Diode diode = (Diode) block.getRelative(face);
					BlockFace bdir = diode.getFacing();
					Location plateLoc = block.getLocation();
					int power = diode.getDelay();
					for (int i = 0; i < jumpPads.size(); i++) {
						if (jumpPads.get(i).getLocation() == plateLoc) return;
					}
					System.out.println("Added new JumpPad");
					jumpPads.add(new JumpPad(plateLoc, bdir, power));
					return;
				}
			}
		}
		//If a redstone diode is placed, check around it for stone pressure plates on obsidian
		if (block.getTypeId() == 93 || block.getTypeId() == 94) {
			System.out.println("Diode Placed");
			for (BlockFace face : BlockFace.values()) {
				if (block.getRelative(face).getType() == Material.STONE_PLATE) {
					if (block.getRelative(BlockFace.DOWN).getType() == Material.OBSIDIAN) {
						//Conditions met to create a jumpPad
						Diode diode = (Diode) block;
						BlockFace bdir = diode.getFacing();
						Location plateLoc = block.getRelative(face).getLocation();
						int power = diode.getDelay();
						for (int i = 0; i < jumpPads.size(); i++) {
							if (jumpPads.get(i).getLocation() == plateLoc) return;
						}
						jumpPads.add(new JumpPad(plateLoc, bdir, power));
					}
				}
			}
		}
	}
}
