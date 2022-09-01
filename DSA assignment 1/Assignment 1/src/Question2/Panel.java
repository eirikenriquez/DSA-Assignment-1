package Question2;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Panel extends JPanel {

    private static final int PANEL_WIDTH = 1000;
    private static final int PANEL_HEIGHT = 1000;

    public Panel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setFocusable(true);
        this.setBackground(Color.BLUE);
    }

}
