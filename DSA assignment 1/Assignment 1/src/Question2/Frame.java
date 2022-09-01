package Question2;

import javax.swing.JFrame;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Frame extends JFrame {

    public Frame() {

        super("Ship-Island Port Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new Panel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
