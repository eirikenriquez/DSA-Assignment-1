package Question1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
    private static final int SCALE = 15;
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 600;
    private static final int MAX_X = BOARD_WIDTH / SCALE;
    private static final int MAX_Y = BOARD_HEIGHT / SCALE;
    private final Random rand;
    private final LinkedList<Character> snake;
    private LinkedList<Integer> numbers;
    private char letter;
    private int letterX;
    private int letterY;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        addKeyListener(new SnakeController(this));
        setFocusable(true);
        setBackground(Color.BLACK);

        this.rand = new Random();
        this.snake = new LinkedList<>();
        numbers = new LinkedList<>();
        generateLetter();
        generateSnake();
    }

    private void generateSnake() {
        snake.add('@');
        Node head = snake.getNode(0);
        head.direction = Direction.RIGHT;
        head.x = 0;
        head.y = 0;
    }

    private void generateLetter() {
        if (rand.nextBoolean()) {
            generateLowercase();
        } else {
            generateUppercase();
        }

        letterX = rand.nextInt(MAX_X);
        letterY = rand.nextInt(MAX_Y);
    }

    private void generateLowercase() {
        letter = (char) (rand.nextInt(26) + 'a');
    }

    private void generateUppercase() {
        letter = (char) (rand.nextInt(26) + 'A');
    }

    public void setDirection(Direction direction) {
        Node head = snake.getNode(0);
        head.direction = direction;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // draw snake
        drawSnake(g);

        // draw first 10 numbers
        drawFirstNumbers(g);

        // draw letter (food)
        drawLetter(g);
    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.GREEN);

        Node node = snake.getNode(0);
        while (node != null) {
            int x = (node.x * SCALE + 1) + SCALE;
            int y = (node.y * SCALE + 1) + SCALE;

            g.drawString(node.data.toString(), x, y);
            node = node.next;
        }
    }

    private void moveSnake() {
        Node node = snake.getNode(0);
        while (node != null) {
            switch (node.direction) {
                case LEFT:

                    break;
                case RIGHT:

                    break;
                case UP:

                    break;
                case DOWN:

                    break;
                default:
                    throw new AssertionError();
            }
            node = node.next;
        }
    }

    private void drawFirstNumbers(Graphics g) {
        g.setColor(Color.RED);

        for (int i = 0; i < MAX_NUMBERS; i++) {
            int num = rand.nextInt(10);
            int x = rand.nextInt(MAX_X);
            int y = rand.nextInt(MAX_Y);
            int xToDisplay = ((x * SCALE + 1) + SCALE);
            int yToDisplay = ((y * SCALE + 1) + SCALE);

            g.drawString(Integer.toString(num), xToDisplay, yToDisplay);

            numbers.add(num);
            Node node = numbers.getNode(i);
            node.x = x;
            node.y = y;
        }

    }

    private void drawLetter(Graphics g) {
        int x = ((letterX * SCALE + 1) + SCALE);
        int y = ((letterY * SCALE + 1) + SCALE);

        g.setColor(Color.YELLOW);
        g.drawString(Character.toString(letter), x, y);
    }

}
