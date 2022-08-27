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

    private final int boundaryOffset;
    private final int maxWidth;
    private final int maxHeight;
    private final Random rand;
    public Queue numbers;
    public char letter;

    public Board(int width, int height) {
        boundaryOffset = 50;
        this.maxWidth = width - (boundaryOffset * 2);
        this.maxHeight = height - (boundaryOffset * 2);
        rand = new Random();
        numbers = new Queue();
        initNumbers();
        generateLetter();
    }

    private void initNumbers() {
        for (int i = 0; i < 10; i++) {
            numbers.enqueue(rand.nextInt(10));
        }
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

    @Override
    public void paintComponent(Graphics g) {
        drawBoard(g);
    }

    private synchronized void drawBoard(Graphics g) {
        for (int i = 0; i < numbers.getSize(); i++) {
            System.out.println(i);
            System.out.println("size" + numbers.getSize());
            g.drawString(numbers.dequeue().toString(), rand.nextInt(maxWidth) + boundaryOffset, rand.nextInt(maxHeight) + boundaryOffset);
        }

        g.drawString(letter + "", rand.nextInt(maxWidth) + boundaryOffset, rand.nextInt(maxHeight) + boundaryOffset);
    }

}
