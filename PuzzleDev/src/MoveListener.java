import java.util.ArrayList;

import org.bukkit.event.Listener;

public class MoveListener implements Listener {
	
	private ArrayList<JumpPad> jumpPads;

	public MoveListener(ArrayList<JumpPad> jumpPads) {
		this.jumpPads = jumpPads;
	}	
	
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		for (int i = 0; i < array.length; i++) {
			
		}
		if(player.location == jumpPads[i].location) {
			jumpPad[i].jump(player);
		}
	}

}
