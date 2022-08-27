package Question1;

import javax.swing.JFrame;

/**
 *
 * @author eirikenriquez
 */
public class Main {

    public static void main(String[] args) {
        final int frameWidth = 600;
        final int frameHeight = 600;

        JFrame frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);

        Board board = new Board(frameWidth, frameHeight);

        frame.add(board);

        frame.setVisible(true);

    }
}
