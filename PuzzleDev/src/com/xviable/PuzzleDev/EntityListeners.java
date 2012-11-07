package com.xviable.PuzzleDev;

import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.entity.CraftChicken;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.entity.CraftSquid;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Diode;
import org.bukkit.util.Vector;

public class EntityListeners implements Listener {

	private PuzzleDev plugin;

	public EntityListeners(PuzzleDev plugin) {
		this.plugin = plugin;
	}


	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		
		if (event.getAction() == event.getAction().RIGHT_CLICK_BLOCK) {
			Block b = event.getClickedBlock();
			if (b.getType() == Material.GRAVEL) {
				BlockFace bf = event.getBlockFace();
				BlockFace obf = event.getBlockFace().getOppositeFace();
				
				int faceX = obf.getModX();
				int faceZ = obf.getModZ();
				
				ArrayList<Integer> A = new ArrayList<Integer>();
				ArrayList<Integer> U = new ArrayList<Integer>();
				
				for (int xda = 0; xda <= 7; xda++) {
						A.add(b.getRelative(((faceX * xda) + faceX), 0, ((faceZ * xda)) + faceZ).getTypeId());
				}
				for (int xda = 0; xda <= 7; xda++) {
					U.add(b.getRelative(((faceX * xda) + faceX), 0, ((faceZ * xda)) + faceZ).getRelative(BlockFace.DOWN).getTypeId());
				}
				
				if (A.get(0) != 0) {
					return;
				}
				
				if (A.get(7) + A.get(6) + A.get(5) + A.get(4) + A.get(3) + A.get(2) + A.get(1) == 0 && U.get(6) != 0 &&  U.get(5) != 0 && U.get(4) != 0 && U.get(3) != 0 && U.get(2) != 0 && U.get(1) != 0 && U.get(0) != 0) {
					b.setTypeId(0);
					b.getRelative(((faceX * 7) + faceX), 0, ((faceZ * 7)) + faceZ).setType(Material.GRAVEL);
					return;
				}
				
				if (A.get(6) == 0 && A.get(5) == 0 && A.get(4) == 0 && A.get(3) == 0 && A.get(2) == 0 && A.get(1) == 0 && U.get(5) != 0 && U.get(4) != 0 && U.get(3) != 0 && U.get(2) != 0 && U.get(1) != 0 && U.get(0) != 0) {
					b.setTypeId(0);
					b.getRelative(((faceX * 6) + faceX), 0, ((faceZ * 6)) + faceZ).setType(Material.GRAVEL);
					return;
				}
				if (A.get(5) == 0 && A.get(4) == 0 && A.get(3) == 0 && A.get(2) == 0 && A.get(1) == 0 && U.get(4) != 0 && U.get(3) != 0 && U.get(2) != 0 && U.get(1) != 0 && U.get(0) != 0) {
					b.setTypeId(0);
					b.getRelative(((faceX * 5) + faceX), 0, ((faceZ * 5)) + faceZ).setType(Material.GRAVEL);
					return;
				}
				if (A.get(4) == 0 && A.get(3) == 0 && A.get(2) == 0 && A.get(1) == 0 && U.get(3) != 0 && U.get(2) != 0 && U.get(1) != 0 && U.get(0) != 0) {
					b.setTypeId(0);
					b.getRelative(((faceX * 4) + faceX), 0, ((faceZ * 4)) + faceZ).setType(Material.GRAVEL);
					return;
				}
				if (A.get(3) == 0 && A.get(2) == 0 && A.get(1) == 0 && U.get(2) != 0 && U.get(1) != 0 && U.get(0) != 0) {
					b.setTypeId(0);
					b.getRelative(((faceX * 3) + faceX), 0, ((faceZ * 3)) + faceZ).setType(Material.GRAVEL);
					return;
				}
				if (A.get(2) == 0 && A.get(1) == 0 && U.get(1) != 0 && U.get(0) != 0) {
					b.setTypeId(0);
					b.getRelative(((faceX * 2) + faceX), 0, ((faceZ * 2)) + faceZ).setType(Material.GRAVEL);
					return;
				}
				if (A.get(1) == 0 && U.get(0) != 0) {
					b.setTypeId(0);
					b.getRelative(((faceX * 1) + faceX), 0, ((faceZ * 1)) + faceZ).setType(Material.GRAVEL);
					return;
				}
				if (A.get(0) == 0) {
					b.setTypeId(0);
					b.getRelative(((faceX * 0) + faceX), 0, ((faceZ * 0)) + faceZ).setType(Material.GRAVEL);
					return;
				}
			}
		}
		
		
		if (event.getAction() == event.getAction().PHYSICAL) {
		Block b = event.getClickedBlock();
		if (b.getType() == Material.STONE_PLATE) {
		for (int z = -1; z <= 1; z++) {
		for (int x = -1; x <= 1; x++) {
		for (int y = -1; y <= 1; y++) {
		if ((x * x + y * y + z * z == 1) && b.getRelative(x, y, z).getTypeId() == 94 || (x * x + y * y + z * z == 1) && b.getRelative(x, y, z).getTypeId() == 93) {
		Diode diode = (Diode) b.getRelative(x, y, z).getState().getData();
		BlockFace bf = diode.getFacing();
		int delay = diode.getDelay();
		Vector v1 = new Vector(0, 0, 0);
		p.setVelocity(v1);
		Vector v = new Vector(bf.getModX(), bf.getModY(), bf.getModZ());
		v.setX(bf.getModX() * (delay / 1.45));
		v.setZ(bf.getModZ() * (delay / 1.45));
		v.setY(1);
		p.setVelocity(v);
		}
		}
		}
		}
		}
		}

		if (event.getAction() == event.getAction().LEFT_CLICK_AIR || event.getAction() == event.getAction().LEFT_CLICK_BLOCK) {
		if (p.getPassenger() != null) {
		Entity target = p.getPassenger();
		if (target instanceof CraftSquid) {
		((CraftSquid) target).leaveVehicle();
		Location loc = p.getLocation();
		loc.setY(loc.getY() + 8);
		loc.add(p.getLocation().getDirection().multiply(2));
		target.teleport(loc);
		((CraftLivingEntity) target).setHealth(((CraftLivingEntity) target).getMaxHealth());
		}
		}
		}
		}

	
	@EventHandler
	public void onEntityInteract(EntityInteractEvent event) {
		Block b = event.getBlock();
		if (b.getType() == Material.STONE_PLATE) {
			Entity p = event.getEntity();
			for (int z = -1; z <= 1; z++) {
				for (int x = -1; x <= 1; x++) {
					for (int y = -1; y <= 1; y++) {
						if ((x * x + y * y + z * z == 1) && b.getRelative(x, y, z).getTypeId() == 94 || (x * x + y * y + z * z == 1) && b.getRelative(x, y, z).getTypeId() == 93) {
							Location loc = b.getRelative(0,1,0).getLocation();
//							System.out.println(b.getRelative(0,1,0).getLocation());
							Vector reset = new Vector(0,0,0);
							p.setVelocity(reset);
							Diode diode = (Diode) b.getRelative(x, y, z).getState().getData();
							BlockFace bf = diode.getFacing();
							int delay = diode.getDelay();
							Vector v = new Vector(bf.getModX(), bf.getModY(), bf.getModZ());
							p.setVelocity(v);
							v.setX(bf.getModX() * (delay/1.5));
							v.setZ(bf.getModZ() * (delay/1.5));
							v.setY(1);
//							System.out.println(delay);
							p.setVelocity(v);
						}
					}
				}
			}
		}
	}
	
	
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
	Player player = event.getPlayer();
	Entity target = event.getRightClicked();
	Entity vehicle = target.getVehicle();

	if (player.getPassenger() != null) {
	Entity passenger = player.getPassenger();
	if (target instanceof CraftSquid) {
	((CraftSquid) passenger).leaveVehicle();
	Location loc = player.getLocation();
	loc.setY(loc.getY() + 8);
	loc.add(player.getLocation().getDirection().multiply(2));
	passenger.teleport(loc);
	((CraftLivingEntity) passenger).setHealth(((CraftLivingEntity) passenger).getMaxHealth());
	}
	} else if (target instanceof CraftSquid) {
	player.setPassenger(target);
	}
	}


}
