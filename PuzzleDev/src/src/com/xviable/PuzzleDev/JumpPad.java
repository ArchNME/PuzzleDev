package com.xviable.PuzzleDev;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class JumpPad {
	
	private Location loc;
	private BlockFace bdir;
	private Vector dir = VectorUtil.vectorConvert(bdir);
	
	public JumpPad(Location loc, BlockFace bdir, int power) {
		this.loc = loc;
		this.bdir = bdir;
		}

	public Location getLocation(){
		return loc;
	}
	
	public void launch(Entity e) {
		e.setVelocity(dir);
		}
	
}
