package Question1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is a very nasty class that violates Single Responsibility Principle and
 * probably more...
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
    public static final int DELAY = 75;
    private final Random rand;
    public final LinkedList<Character> snake;
    private final LinkedList<Integer> numbers;
    private Direction direction;
    private char letter;
    private int letterX;
    private int letterY;
    private boolean gameOver;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setFocusable(true);
        setBackground(Color.BLACK);

        this.rand = new Random();
        this.snake = new LinkedList<>();
        this.numbers = new LinkedList<>();
        this.gameOver = false;

        generateLetter();
        generateSnake();

        addKeyListener(new SnakeController(this));
    }

    private void generateSnake() {
        snake.add('@');
        Node head = snake.getNode(0);
        direction = Direction.RIGHT;
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
        this.direction = direction;
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

            checkFoodEaten();

            if (gameOver == true) {
                JOptionPane.showMessageDialog(this, "Game Over!");
                System.exit(0);
            }

            moveSnake();
            drawSnake(g);
            wrapSnake();
        }
    }

    private void checkFoodEaten() {
        Node head = snake.getNode(0);

        // if good apple
        if (head.x == letterX && head.y == letterY) {
            snake.addInOrder(letter);
            generateLetter();
        }

        // if bad apple
        Node currentNumber = numbers.getNode(0);
        while (currentNumber != null) {
            if (head.x == currentNumber.x && head.y == currentNumber.y) {
                if (snake.size <= 1 || (int) currentNumber.data == 0) {
                    gameOver = true;
                    return;
                }

                Node toRemove = snake.getNode((int) currentNumber.data);

                if ((int) currentNumber.data > snake.size - 1) {
                    snake.removeFromTail();
                } else {
                    snake.remove(toRemove);
                }

            }
            currentNumber = currentNumber.next;
        }

    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.WHITE);

        Node node = snake.getNode(0);
        while (node != null) {
            int x = convertPosition(node.x);
            int y = convertPosition(node.y);

            g.drawString(node.data.toString(), x, y);
            node = node.next;
        }
    }

    private void moveSnake() {
        Node head = snake.getNode(0);
        Node current = snake.getNode(snake.size - 1);

        while (current != null) {
            if (current == head) {
                switch (direction.axis) {
                    case 'x':
                        head.x += direction.positionChange;
                        break;
                    case 'y':
                        head.y += direction.positionChange;
                        break;
                    default:
                        break;
                }
            } else {
                current.x = current.prev.x;
                current.y = current.prev.y;
            }
            current = current.prev;
        }

        Util.pause(DELAY);
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
        g.setColor(Color.GREEN);
        g.drawString(Character.toString(letter), convertPosition(letterX), convertPosition(letterY));
    }

    private int convertPosition(int position) {
        return (position * SCALE) + (SCALE / 2);
    }

    private void wrapSnake() {
        Node node = snake.getNode(0);

        while (node != null) {
            if (node.x < 0) {
                node.x = MAX_X;
            }

            if (node.x > MAX_X) {
                node.x = 0;
            }

            if (node.y < 0) {
                node.y = MAX_Y;
            }

            if (node.y > MAX_Y) {
                node.y = 0;
            }

            node = node.next;
        }
    }

}
