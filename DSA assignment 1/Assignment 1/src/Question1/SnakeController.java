package Question1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author eirik
 */
public class SnakeController implements KeyListener {

    private final Board board;

    public SnakeController(Board board) {
        this.board = board;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                board.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                board.setDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                board.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                board.setDirection(Direction.DOWN);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
