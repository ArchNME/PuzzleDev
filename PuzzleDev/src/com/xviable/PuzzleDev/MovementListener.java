package com.xviable.PuzzleDev;

import java.util.ArrayList;

import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;

public class MovementListener implements Listener {

	private PuzzleDev plugin;

	public MovementListener(PuzzleDev plugin) {
		this.plugin = plugin;
	}

	private ArrayList<JumpPad> jumpPads;

	public MovementListener(ArrayList<JumpPad> jumpPads) {
		this.jumpPads = jumpPads;
	}

	public void onPlayerInteract(EntityInteractEvent event) {
		Entity entity = event.getEntity();

		System.out.println("Player Movement Detected");

		for (int i = 0; i < jumpPads.size(); i++) {
			System.out.println("JumpPad Checker Running(" + i + ")");
			if (entity.getLocation() == jumpPads.get(i).getLocation()) {
				jumpPads.get(i).launch(entity);
			}
		}
	}
}
