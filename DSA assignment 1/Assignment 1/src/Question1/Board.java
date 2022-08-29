package Question1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import sun.java2d.loops.DrawRect;

/**
 * This is a very nasty class that violates Single Responsibility Principle and
 * probably more... But, c'mon this assignment is really difficult :c
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
    private static final int DELAY = 100;
    private final Random rand;
    private final LinkedList<Character> snake;
    private LinkedList<Integer> numbers;
    private char letter;
    private int letterX;
    private int letterY;
    private boolean gameOver = false;

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
        if (!gameOver) {
            drawNumbers(g);
            drawLetter(g);

            drawSnake(g);
            moveSnake();

            checkCollision();
        }
    }

    private void checkCollision() {

    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.GREEN);

        Node node = snake.getNode(0);
        while (node != null) {
            int x = convertPosition(node.x);
            int y = convertPosition(node.y);

            g.drawString(node.data.toString(), x, y);
            node = node.next;
        }
    }

    private void moveSnake() {
        Node node = snake.getNode(0);
        while (node != null) {
            switch (node.direction) {
                case LEFT:
                    node.x--;
                    break;
                case RIGHT:
                    node.x++;
                    break;
                case UP:
                    node.y--;
                    break;
                case DOWN:
                    node.y++;
                    break;
                default:
                    break;
            }
            node = node.next;
        }

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        repaint();
    }

    private void drawNumbers(Graphics g) {
        g.setColor(Color.RED);

        if (numbers.size == 0) {
            // populate the numbers list
            for (int i = 0; i < MAX_NUMBERS; i++) {
                int num = rand.nextInt(10);
                int x = rand.nextInt(MAX_X);
                int y = rand.nextInt(MAX_Y);

                g.drawString(Integer.toString(num), convertPosition(x), convertPosition(y));

                numbers.add(num);
                Node node = numbers.getNode(i);
                node.x = x;
                node.y = y;
            }
        } else {
            // display existing numbers list
            Node node = numbers.getNode(0);
            while (node != null) {
                g.drawString(Integer.toString((int) node.data), convertPosition(node.x), convertPosition(node.y));
                node = node.next;
            }
        }

    }

    private void drawLetter(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawString(Character.toString(letter), convertPosition(letterX), convertPosition(letterY));
    }

    private int convertPosition(int position) {
        return (position * SCALE + 1) + SCALE;
    }

}
