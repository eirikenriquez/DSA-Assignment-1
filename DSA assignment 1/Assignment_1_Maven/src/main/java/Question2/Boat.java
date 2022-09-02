package Question2;

import java.awt.Image;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

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
        this.image = new ImageIcon("./src/main/resources/boat.png/").getImage();
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
                try {
                    Thread.sleep(1); // wait if another boat is coming
                } catch (InterruptedException ex) {
                    Logger.getLogger(Boat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            sailBoat();
        }
    }

    private void runUnSynchronized() {
        while (island.boatComing) {
            try {
                Thread.sleep(1); // wait if another boat is coming
            } catch (InterruptedException ex) {
                Logger.getLogger(Boat.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        try {
            Thread.sleep(INTERVAL);
        } catch (InterruptedException ex) {
            Logger.getLogger(Boat.class.getName()).log(Level.SEVERE, null, ex);
        }

        island.currentImage = island.noShip;
        island.boatComing = false;
        this.sailing = false;
    }

    private void move() {
        try {
            Thread.sleep(BOAT_SPEED);
        } catch (InterruptedException ex) {
            Logger.getLogger(Boat.class.getName()).log(Level.SEVERE, null, ex);
        }

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
