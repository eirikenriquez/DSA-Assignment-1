package Question2;

import java.awt.Image;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Island {

    public final Image noShip;
    public final Image withShip;
    public Image currentImage;
    public int x;
    public int y;
    public boolean boatComing;

    public Island(int x, int y) {
        this.noShip = Util.readImage("./images/land.png");
        this.withShip = Util.readImage("./images/boat_land.png/");
        this.currentImage = noShip;
        this.x = x;
        this.y = y;
        this.boatComing = false;
    }
}
