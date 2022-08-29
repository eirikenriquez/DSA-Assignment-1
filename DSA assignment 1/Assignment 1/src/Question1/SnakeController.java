package Question1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author eirik
 */
public class SnakeController implements KeyListener {

    private final Board board;

    public SnakeController(Board view) {
        this.board = view;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();

        // Netbeans suggested to write it like this instead of a regular switch statement...
        switch (key) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A ->
                board.setDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D ->
                board.setDirection(Direction.RIGHT);
            case KeyEvent.VK_UP, KeyEvent.VK_W ->
                board.setDirection(Direction.UP);
            case KeyEvent.VK_DOWN, KeyEvent.VK_S ->
                board.setDirection(Direction.DOWN);
            default -> {
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
