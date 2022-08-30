package Question1;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Node<E extends Comparable> implements Comparable<Node> {

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

    @Override
    public int compareTo(Node t) {
        return this.data.compareTo(t.data);
    }

}
