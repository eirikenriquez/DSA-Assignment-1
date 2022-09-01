package Question2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Panel extends JPanel {

    private static final int BOAT_HEIGHT = 43;
    private static final int BOAT_AMOUNT = 25;
    private static final int PANEL_WIDTH = 1000;
    private static final int PANEL_HEIGHT = BOAT_AMOUNT * BOAT_HEIGHT;
    private final Island island;
    private Boat[] boats;

    public Panel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setFocusable(true);
        this.setBackground(Color.BLUE);

        this.island = new Island(900, 500);
        initBoats();
    }

    private void initBoats() {
        this.boats = new Boat[BOAT_AMOUNT];

        for (int i = 0; i < BOAT_AMOUNT; i++) {
            this.boats[i] = new Boat(10, BOAT_HEIGHT * i + 1);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(island.image, island.x, island.y, this);

        drawBoats(g);
    }

    private void drawBoats(Graphics g) {
        for (int i = 0; i < BOAT_AMOUNT; i++) {
            g.drawImage(boats[i].image, boats[i].x, boats[i].y, this);
        }
    }

}
