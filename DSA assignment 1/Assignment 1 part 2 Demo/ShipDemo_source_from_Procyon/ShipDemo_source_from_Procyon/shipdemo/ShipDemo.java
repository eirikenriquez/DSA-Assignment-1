// 
// Decompiled by Procyon v0.5.36
// 

package shipdemo;

import java.awt.Component;
import javax.swing.JFrame;

public class ShipDemo
{
    public static void main(final String[] args) {
        final JFrame frame = new JFrame("Java Paint");
        frame.setDefaultCloseOperation(3);
        final Panel panel = new Panel();
        frame.getContentPane().add(panel);
        frame.setSize(1000, 1050);
        frame.setVisible(true);
    }
}
