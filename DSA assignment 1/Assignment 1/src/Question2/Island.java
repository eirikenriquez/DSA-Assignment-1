package Question2;

import java.awt.Image;
import javax.swing.ImageIcon;

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
        this.noShip = new ImageIcon("./images/land.png/").getImage();
        this.withShip = new ImageIcon("./images/boat_land.png/").getImage();
        this.currentImage = noShip;
        this.x = x;
        this.y = y;
        this.boatComing = false;
    }
}
