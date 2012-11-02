package com.xviable.PuzzleDev;

import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

public class VectorUtil {

	public static Vector vectorConvert(BlockFace b) {
		if (b.equals(BlockFace.NORTH)) {
			return new Vector(0,0,-1);
		}
		if (b.equals(BlockFace.EAST)) {
			return new Vector(1,0,0);
		}
		if (b.equals(BlockFace.SOUTH)) {
			return new Vector(0,0,1);
		}
		if (b.equals(BlockFace.WEST)) {
			return new Vector(-1,0,0);
		}
		return new Vector(0,0,0);
	}
}
