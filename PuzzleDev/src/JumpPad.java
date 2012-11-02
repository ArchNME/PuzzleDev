import org.bukkit.Location;
import org.bukkit.util.Vector;


public class JumpPad {
	private Location loc;
	private Vector dir;
	private int power;
	
	public JumpPad(Location loc, Vector dir, int power) {
		this.loc = loc;
		this.dir = dir;
		this.power = power;
	}
	
	public jump(Player p) {
		p.setVelocity(dir);
	}
}
