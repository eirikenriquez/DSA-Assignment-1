// 
// Decompiled by Procyon v0.5.36
// 

package shipdemo;

import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Panel extends JPanel implements KeyListener
{
    int number_ship;
    boolean program_starts;
    Ship[] ships;
    Port port;
    Image ship_image;
    Image island_image;
    Image boat_island_image;
    
    public Panel() {
        this.number_ship = 20;
        this.program_starts = false;
        this.ships = new Ship[this.number_ship];
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
        this.port = new Port(900, 500);
        for (int i = 0; i < this.number_ship; ++i) {
            this.ships[i] = new Ship(20, i * 50, this.port);
        }
        this.ship_image = new ImageIcon("boat.png").getImage();
        this.island_image = new ImageIcon("land.png").getImage();
        this.boat_island_image = new ImageIcon("boat_land.png").getImage();
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Monospaced", 1, 20));
        for (int i = 0; i < this.ships.length; ++i) {
            if (!this.ships[i].has_finished) {
                g.drawImage(this.ship_image, this.ships[i].x, this.ships[i].y, this);
            }
        }
        for (int i = 0; i < this.ships.length; ++i) {
            if (this.ships[i].moving) {
                for (int j = 0; j < this.ships.length; ++j) {
                    if (this.ships[j].moving && j != i && this.ships[j].x == this.ships[i].x && this.ships[j].y == this.ships[i].y) {
                        g.drawString("Crashed", 400, 50);
                    }
                }
            }
        }
        if (Ship.has_arrived) {
            g.drawImage(this.boat_island_image, this.port.x, this.port.y - 26, this);
        }
        else {
            g.drawImage(this.island_image, this.port.x, this.port.y - 26, this);
        }
        this.repaint();
    }
    
    @Override
    public void keyTyped(final KeyEvent ke) {
    }
    
    @Override
    public void keyPressed(final KeyEvent ke) {
        if (!this.program_starts) {
            for (int i = 0; i < this.ships.length; ++i) {
                this.ships[i].synchronized_mode = (ke.getKeyChar() == 's' || ke.getKeyChar() == 'S');
                this.ships[i].start();
            }
            this.program_starts = true;
        }
    }
    
    @Override
    public void keyReleased(final KeyEvent ke) {
    }
}
