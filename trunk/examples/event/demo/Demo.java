package examples.event.demo;
import examples.event.event.EventManager;

/**
 * This demo shows how our event system works. In this demo, we only set
 * pacman's position. We can also make ghost listen or not listen to pacman's
 * movement. The ghost will automatically follow pacman's movement when it
 * listens to pacman's movement.
 * 
 * @author Hao He
 * @author Meng Li
 * @author Cody Kolodziejzyk
 * 
 */

public class Demo {
	public static void main(String[] args) {
		// Initialize event manager
		EventManager event = new EventManager();

		// Initliaze pacman and ghost
		System.out.println("Pacman initiated!");
		Pacman p = new Pacman(0, 0, event);

		System.out.println("Ghost initiated!");
		Ghost g = new Ghost(0, 0);

		// Make ghost listen to pacman's movement
		System.out.println("Ghost is listening to Pacman's movement!");
		event.addEventListener("PacmanMoveEvent", g);

		System.out.println();
		System.out.println("Pacman's current position:  (" + p.getX() + ", "
				+ p.getY() + ")");
		System.out.println("Ghost's current position:  (" + g.getX() + ", "
				+ g.getY() + ")");

		System.out.println();
		p.setX(10);
		System.out.println("Move pacman to position (10, 0)");
		System.out.println("Pacman moves to:  (" + p.getX() + ", " + p.getY()
				+ ")");
		System.out.println("Ghost's current position:  (" + g.getX() + ", "
				+ g.getY() + ")");

		System.out.println();
		p.setY(200);
		System.out.println("Move pacman to position (10, 200)");
		System.out.println("Pacman moves to:  (" + p.getX() + ", " + p.getY()
				+ ")");
		System.out.println("Ghost's current position:  (" + g.getX() + ", "
				+ g.getY() + ")");

		// Remove Ghost's listener on Pacman's movement
		System.out.println();
		System.out.println("Ghost stops listening on pacman's movement!");
		event.removeEventListener("PacmanMoveEvent", g);

		p.setY(300);
		System.out.println();
		System.out.println("Move pacman to position (10, 300)");
		System.out.println("Pacman moves to:  (" + p.getX() + ", " + p.getY()
				+ ")");
		System.out.println("Ghost's current position:  (" + g.getX() + ", "
				+ g.getY() + ")");

		p.setX(1000);
		System.out.println();
		System.out.println("Move pacman to position (1000, 300)");
		System.out.println("Pacman moves to:  (" + p.getX() + ", " + p.getY()
				+ ")");
		System.out.println("Ghost's current position:  (" + g.getX() + ", "
				+ g.getY() + ")");
	}
}
