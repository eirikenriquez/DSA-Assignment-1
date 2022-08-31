package Question1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eirik
 */
public class SnakeController implements KeyListener {

    private final Board board;
    private final Node head;
    private int previousX;
    private int previousY;
    private int previousInput;

    public SnakeController(Board board) {
        this.board = board;
        head = board.snake.getNode(0);
        this.previousX = head.x;
        this.previousY = head.y;
        this.previousInput = 0;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (head.x == previousX && head.y == previousY) {
            return;
        }

        previousX = head.x;
        previousY = head.y;

        int key = ke.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (previousInput == KeyEvent.VK_RIGHT || previousInput == KeyEvent.VK_D) {
                    return;
                }
                board.setDirection(Direction.LEFT);
                previousInput = key;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (previousInput == KeyEvent.VK_LEFT || previousInput == KeyEvent.VK_A) {
                    return;
                }
                board.setDirection(Direction.RIGHT);
                previousInput = key;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (previousInput == KeyEvent.VK_DOWN || previousInput == KeyEvent.VK_S) {
                    return;
                }
                board.setDirection(Direction.UP);
                previousInput = key;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (previousInput == KeyEvent.VK_UP || previousInput == KeyEvent.VK_W) {
                    return;
                }
                board.setDirection(Direction.DOWN);
                previousInput = key;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
