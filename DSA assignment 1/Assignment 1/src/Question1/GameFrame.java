package Question1;

import javax.swing.JFrame;

/**
 *
 * @author eirik
 */
public class GameFrame extends JFrame {

    private final static int FRAMEWIDTH = 600;
    private final static int FRAMEHEIGHT = 600;

    public GameFrame() {
        super("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAMEWIDTH, FRAMEHEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        Board board = new Board(FRAMEWIDTH, FRAMEHEIGHT);
        add(board);
        setVisible(true);
    }

}
