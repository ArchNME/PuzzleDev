package com.xviable.PuzzleDev;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class MovementListener implements Listener {

	private PuzzleDev plugin;

	public MovementListener(PuzzleDev plugin) {
		this.plugin = plugin;
	}

	private ArrayList<JumpPad> jumpPads;

	public MovementListener(ArrayList<JumpPad> jumpPads) {
		this.jumpPads = jumpPads;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		System.out.println("Player Movement Detected : " + event.getAction());
		if (event.getAction() == event.getAction().PHYSICAL) {
			System.out.println("SUCCESS");
			Vector v = p.getVelocity();
			v.setY(0.5);
			v.setX(v.getX()*0.5);
			v.setZ(v.getZ()*0.5);
			p.setVelocity(v);
		}
		

//		for (int i = 0; i < jumpPads.size(); i++) {
//			System.out.println("JumpPad Checker Running(" + i + ")");
//			if (entity.getLocation() == jumpPads.get(i).getLocation()) {
//				jumpPads.get(i).launch(entity);
//			}
//		}
	}
}
