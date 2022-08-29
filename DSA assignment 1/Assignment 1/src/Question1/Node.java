package Question1;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Node<E> {

    public E data;
    public Node next;
    public int x;
    public int y;
    public Direction direction;

    public Node() {
        this.data = null;
        this.next = null;
        this.x = 0;
        this.y = 0;
        this.direction = null;
    }

    public boolean equals(Node node) {
        return node.data.equals(this.data);
    }

    public int compareTo(Node node) {
        int toReturn = 1;

        if (this.data instanceof String && node.data instanceof String) {
            String thisData = (String) this.data;
            String argData = (String) node.data;

            toReturn = thisData.compareTo(argData);
        }

        if (this.data instanceof Integer && node.data instanceof Integer) {
            Integer thisData = (Integer) this.data;
            Integer argData = (Integer) node.data;

            toReturn = thisData.compareTo(argData);
        }

        if (this.data instanceof Float && node.data instanceof Float) {
            Float thisData = (Float) this.data;
            Float argData = (Float) node.data;

            toReturn = thisData.compareTo(argData);
        }

        if (this.data instanceof Character && node.data instanceof Character) {
            Character thisData = (Character) this.data;
            Character argData = (Character) node.data;

            toReturn = thisData.compareTo(argData);
        }

        return toReturn;

    }

}
