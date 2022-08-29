package Question1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import sun.java2d.loops.DrawRect;

/**
 *
 * @author eirikenriquez
 */
public class Board extends JPanel {

    private static final int MAX_NUMBERS = 10;
    private static final int SCALE = 20;
    private final Random rand;
    private final LinkedList<Character> snake;
    private final int width;
    private final int height;
    private Direction direction;
    private int[] x;
    private int[] y;
    private int numbers;
    private char letter;

    public Board(int width, int height) {
        this.rand = new Random();
        this.width = width;
        this.height = height;
        addKeyListener(new SnakeController(this));
        this.snake = new LinkedList<>();
        this.direction = Direction.RIGHT;
        this.x = new int[width * SCALE];
        this.y = new int[height * SCALE];
        generateLetter();

        snake.add('a');
        snake.add('b');
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

        g.setColor(Color.BLACK);

        // draw vertical lines
        for (int yLine = 0; yLine <= width; yLine += SCALE) {
            g.drawLine(yLine, 0, yLine, height * SCALE);
        }

        // draw horizontal lines
        for (int xLine = 0; xLine <= width; xLine += SCALE) {
            g.drawLine(0, xLine, width * SCALE, xLine);
        }

        // draw snake
        g.setColor(Color.yellow);
        g.fillRect(0 * SCALE, 0 * SCALE, SCALE, SCALE);
    }

}

enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;
}
