package Question2;

import java.awt.Image;
import java.util.Random;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Boat extends Thread {

    private static final int INTERVAL = 1000; // interval between the boats
    private static final int BOAT_SPEED = 1; // lower is faster
    private final Random rand;
    public final Image image;
    public final Island island;
    public boolean landed;
    public boolean sailing;
    public boolean syncSetting;
    public int x;
    public int y;

    public Boat(Island island, int x, int y) {
        this.rand = new Random();
        this.image = Util.readImage("./images/boat.png/");
        this.island = island;
        this.landed = false;
        this.x = x;
        this.y = y;
        syncSetting = false;
    }

    @Override
    public void run() {
        if (syncSetting) {
            runSynchronized();
        } else {
            runUnSynchronized();
        }
    }

    private void runSynchronized() {
        synchronized (island) {
            while (island.boatComing) {
                Util.pause(1);
            }
            sailBoat();
        }
    }

    private void runUnSynchronized() {
        while (island.boatComing) {
            Util.pause(1);
        }
        sailBoat();
    }

    private void sailBoat() {
        // These two variables are effectively the same but since the island object is shared, the sailing boolean variable is necessary for detecting crashes.
        island.boatComing = true;
        this.sailing = true;

        do {
            move();
        } while (!(landed = this.x == island.x && this.y == island.y));

        island.currentImage = island.withShip;

        Util.pause(INTERVAL);

        island.currentImage = island.noShip;
        island.boatComing = false;
        this.sailing = false;
    }

    private void move() {
        Util.pause(BOAT_SPEED);

        // move horizontally
        if (this.x < island.x) {
            this.x++;
        } else if (this.x > island.x) {
            this.x--;
        }

        // move vertically
        if (this.y > island.y) {
            this.y--;
        }
    }

}
