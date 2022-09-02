package Question1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public abstract class Util {

    private Util() {
    }

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
