package Question1;

import javax.swing.JFrame;

/**
 *
 * @author eirik
 */
public class GameFrame extends JFrame {

    public GameFrame() {
        super("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new Board());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
