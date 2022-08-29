package Question1;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author eirikenriquez
 */
public class Board extends JPanel {

    private static final int BOUNDARY_OFFSET = 50;
    private static final int MAX_NUMBERS = 10;
    private final Random rand;
    private final SnakeController snakeController;
    private final LinkedList<Character> snake;
    private final int maxWidth;
    private final int maxHeight;
    private Direction direction;
    private int x;
    private int y;
    private int numbers;
    private char letter;

    public Board(int width, int height) {
        this.rand = new Random();
        this.maxWidth = width - (BOUNDARY_OFFSET * 2);
        this.maxHeight = height - (BOUNDARY_OFFSET * 2);
        addKeyListener(new SnakeController(this));
        this.snake = new LinkedList<>();
        generateLetter();
    }

    private void generateLetter() {
        if (rand.nextBoolean()) {
            generateLowercase();
        } else {
            generateUppercase();
        }
    }

    private void generateLowercase() {
        letter = (char) (rand.nextInt(26) + 'a');
    }

    private void generateUppercase() {
        letter = (char) (rand.nextInt(26) + 'A');
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void paintComponent(Graphics g) {
        draw(g);
    }

    private void draw(Graphics g) {
        /*
        // Draw initial 10 numbers
        for (int i = 0; i < MAX_NUMBERS; i++) {
            g.drawString(rand.nextInt(10) + "", rand.nextInt(maxWidth) + BOUNDARY_OFFSET, rand.nextInt(maxHeight) + BOUNDARY_OFFSET);
        }

        g.drawString(letter + "", rand.nextInt(maxWidth) + BOUNDARY_OFFSET, rand.nextInt(maxHeight) + BOUNDARY_OFFSET);
         */
    }

}

enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;
}
