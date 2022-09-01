package Question2;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Island {

    public final Image image;
    public int x;
    public int y;

    public Island(int x, int y) {
        this.image = new ImageIcon("./images/land.png/").getImage();
        this.x = x;
        this.y = y;
    }
}
