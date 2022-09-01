// Question: “Which object have you chosen for the synchronize? Why?”   Answer:
package Question2;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Boat extends Thread {

    public final Image image;
    public int x;
    public int y;

    public Boat(int x, int y) {
        this.image = new ImageIcon("./images/boat.png/").getImage();
        this.x = x;
        this.y = y;

    }

    @Override
    public void run() {

    }

}
