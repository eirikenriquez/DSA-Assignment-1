package Question1;

/**
 *
 * @author eirik
 */
public enum Direction {
    LEFT(-1, 'x'),
    RIGHT(+1, 'x'),
    UP(-1, 'y'),
    DOWN(+1, 'y');

    public final int positionChange;
    public final char axis;

    private Direction(int positionChange, char axis) {
        this.positionChange = positionChange;
        this.axis = axis;
    }

}
