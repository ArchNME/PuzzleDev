package com.xviable.PuzzleDev;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Diode;
import org.bukkit.util.Vector;

public class EntityListeners implements Listener {

	private PuzzleDev plugin;

	public EntityListeners(PuzzleDev plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onEntityInteract(EntityInteractEvent e) {
		Entity entity = e.getEntity();
		Block block = e.getBlock();

		System.out.println("Player Movement Detected");

//		if (event.getAction() == event.getAction().PHYSICAL && b.getType() == Material.STONE_PLATE) {
//			for (int z = -1; z <= 1; z++) {
//				for (int x = -1; x <= 1; x++) {
//					for (int y = -1; y <= 1; y++) {
//						if ((x * x + y * y + z * z == 1) && b.getRelative(x, y, z).getTypeId() == 94 || (x * x + y * y + z * z == 1) && b.getRelative(x, y, z).getTypeId() == 93) {
//							System.out.println("Diode Found");
//							Diode diode = (Diode) b.getRelative(x, y, z).getState().getData();
//							BlockFace bf = diode.getFacing();
//							Vector v = new Vector(bf.getModX(), bf.getModY(), bf.getModZ());
//							v = v.multiply(1.5);
//							v.setY((diode.getDelay() + 1) / 2);
//							entity.setVelocity(v);
//						}
//					}
//				}
//			}
//		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if (event.getAction() == event.getAction().PHYSICAL) {
			Block b = event.getClickedBlock();
			if (b.getType() == Material.STONE_PLATE) {
				for (int z = -1; z <= 1; z++) {
					for (int x = -1; x <= 1; x++) {
						for (int y = -1; y <= 1; y++) {
							if ((x * x + y * y + z * z == 1) && b.getRelative(x, y, z).getTypeId() == 94 || (x * x + y * y + z * z == 1) && b.getRelative(x, y, z).getTypeId() == 93) {
								System.out.println("Diode Found");
								Diode diode = (Diode) b.getRelative(x, y, z).getState().getData();
								BlockFace bf = diode.getFacing();
								int delay = diode.getDelay();
								Vector v1 = new Vector(0,0,0);
								p.setVelocity(v1);
								Vector v = new Vector(bf.getModX(), bf.getModY(), bf.getModZ());
								v = v.multiply(1.5);
								v.setY((delay + 1) / 2);
								p.setVelocity(v);
							}
						}
					}
				}
			}
		}
	}
}
