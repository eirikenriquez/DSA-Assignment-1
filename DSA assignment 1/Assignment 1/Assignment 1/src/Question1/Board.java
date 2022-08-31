package Question1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
    public static final int DELAY = 40;
    private final Random rand;
    public final LinkedList<Character> snake;
    private final LinkedList<Integer> numbers;
    private final Queue<Integer> xDirectionChanges;
    private final Queue<Integer> yDirectionChanges;
    private char letter;
    private int letterX;
    private int letterY;
    private boolean gameOver = false;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setFocusable(true);
        setBackground(Color.BLACK);

        this.rand = new Random();
        this.snake = new LinkedList<>();
        this.numbers = new LinkedList<>();
        this.xDirectionChanges = new Queue<>();
        this.yDirectionChanges = new Queue<>();

        generateLetter();
        generateSnake();

        addKeyListener(new SnakeController(this));
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

        if (snake.size > 1) {
            xDirectionChanges.enqueue(head.x);
            yDirectionChanges.enqueue(head.y);
        }

        setDirection(direction, head);
    }

    private void setDirection(Direction direction, Node node) {
        node.direction = direction;
    }

    private void checkDirection() {
        Node head = snake.getNode(0);
        Node node = head.next;

        System.out.println("x" + xDirectionChanges.first());
        System.out.println("Y" + yDirectionChanges.first());

        while (node != null) {
            if (node.x == xDirectionChanges.first() && node.y == yDirectionChanges.first()) {
                setDirection(node.prev.direction, node);
                if (node.next == null) {
                    xDirectionChanges.dequeue();
                    yDirectionChanges.dequeue();
                }
            }

            node = node.next;
        }

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
            wrapSnake();

            checkFoodEaten();
        } else {
            JOptionPane.showMessageDialog(this, "Game Over!");
            System.exit(0);
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
                Node toRemove = snake.getNode((int) currentNumber.data);

                if ((int) currentNumber.data > snake.size - 1) {
                    snake.removeFromTail();
                } else if ((int) currentNumber.data == 0) {
                    snake.removeFromHead();
                } else {
                    snake.remove(toRemove);
                }

                if (snake.size == 0) {
                    gameOver = true;
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
        Node current = head;

        while (current != null) {
            switch (current.direction.axis) {
                case 'x':
                    current.x += current.direction.positionChange;
                    break;
                case 'y':
                    current.y += current.direction.positionChange;
                    break;
                default:
                    break;
            }
            current = current.next;
        }

        if (snake.size > 1 && xDirectionChanges.getSize() != 0 && yDirectionChanges.getSize() != 0) {
            checkDirection();
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
        g.setColor(Color.GREEN);
        g.drawString(Character.toString(letter), convertPosition(letterX), convertPosition(letterY));
    }

    private int convertPosition(int position) {
        return (position * SCALE);
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