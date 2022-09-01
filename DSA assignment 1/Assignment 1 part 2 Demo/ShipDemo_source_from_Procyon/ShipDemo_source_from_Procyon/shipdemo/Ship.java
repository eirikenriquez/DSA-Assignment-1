// 
// Decompiled by Procyon v0.5.36
// 

package shipdemo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Ship extends Thread
{
    static boolean has_arrived;
    int x;
    int y;
    int delay;
    boolean has_finished;
    boolean moving;
    String name;
    Port port;
    boolean synchronized_mode;
    int port_x;
    int port_y;
    
    public Ship(final int x, final int y, final Port port) {
        this.has_finished = false;
        this.moving = false;
        this.name = "Ship";
        this.synchronized_mode = false;
        this.x = x;
        this.y = y;
        this.port = port;
        this.delay = Math.round((float)Math.random() * 5000.0f);
    }
    
    public void set_port_location(final int x, final int y) {
        this.port_x = x;
        this.port_y = y;
    }
    
    @Override
    public void run() {
        while (this.delay > 0) {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, null, ex);
            }
            --this.delay;
        }
        if (this.synchronized_mode) {
            synchronized (this.port) {
                while (this.port.has_ship) {
                    try {
                        Thread.sleep(10L);
                    }
                    catch (InterruptedException ex2) {
                        Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, null, ex2);
                    }
                }
                this.port.has_ship = true;
            }
        }
        else {
            while (this.port.has_ship) {
                try {
                    Thread.sleep(10L);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.port.has_ship = true;
        }
        this.moving = true;
        while (this.x != this.port.x || this.y != this.port.y) {
            if (this.x < this.port.x) {
                ++this.x;
            }
            if (this.y < this.port.y) {
                ++this.y;
            }
            if (this.y > this.port.y) {
                --this.y;
            }
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Ship.has_arrived = true;
        this.moving = false;
        try {
            Thread.sleep(1000L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Ship.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.port.has_ship = false;
        Ship.has_arrived = false;
        this.has_finished = true;
    }
    
    static {
        Ship.has_arrived = false;
    }
}
