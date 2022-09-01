package Question2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Panel extends JPanel {

    private static final int BOAT_WIDTH = 48; // the width of the image
    private static final int PANEL_HEIGHT = 1000;
    private static final int BOAT_AMOUNT = 25;
    private final int panelWidth;
    public final Island island;
    public Boat[] boats;
    public boolean started;

    public Panel() {
        this.panelWidth = BOAT_WIDTH * BOAT_AMOUNT;

        setPreferredSize(new Dimension(panelWidth, PANEL_HEIGHT));
        setFocusable(true);
        setBackground(new Color(102, 51, 0)); // the water is sewage water

        initButtons();

        this.island = new Island(panelWidth / 2, 100);
        initBoats();
    }

    private void initButtons() {
        JButton synchronizedButton = new JButton("Synchronized");
        JButton unSynchronizedButton = new JButton("Unsynchronized");

        synchronizedButton.addActionListener(new Controller(this));
        unSynchronizedButton.addActionListener(new Controller(this));

        add(synchronizedButton);
        add(unSynchronizedButton);
    }

    private void initBoats() {
        this.boats = new Boat[BOAT_AMOUNT];

        for (int i = 0; i < BOAT_AMOUNT; i++) {
            this.boats[i] = new Boat(this.island, BOAT_WIDTH * i + 1, 900);
        }
    }

    public void changeSyncSetting(boolean syncSetting) {
        if (!started) {
            for (int i = 0; i < BOAT_AMOUNT; i++) {
                boats[i].syncSetting = syncSetting;
                boats[i].start();
            }

            started = true;
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateBoats(g);
        g.drawImage(island.currentImage, island.x, island.y, this);
    }

    private void drawBoats(Graphics g) {
        for (int i = 0; i < BOAT_AMOUNT; i++) {
            g.drawImage(boats[i].image, boats[i].x, boats[i].y, this);
        }
    }

    private void updateBoats(Graphics g) {
        drawBoats(g);

        for (int i = 0; i < BOAT_AMOUNT; i++) {
            if (boats[i].island.boatComing) {
                g.drawImage(boats[i].image, boats[i].x, boats[i].y, this);
            }
            checkCollision(g, boats[i]);
            island.currentImage = boats[i].island.currentImage;
        }

        repaint();
    }

    private void checkCollision(Graphics g, Boat current) {
        for (int i = 0; i < BOAT_AMOUNT; i++) {
            if (boats[i] != current && boats[i].sailing && current.sailing) {
                g.setColor(Color.WHITE);
                g.setFont(new Font(g.getFont().getName(), Font.BOLD, 50));
                g.drawString("CRASHED!", (int) (panelWidth / 2.5), PANEL_HEIGHT / 2);
            }
        }
    }

}
