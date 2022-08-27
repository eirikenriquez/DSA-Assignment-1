package Question1;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Queue<E> {

    private LinkedList<E> queue;

    public Queue() {
        this.queue = new LinkedList<>();
    }

    public void enqueue(E data) {
        queue.add(data);
    }

    public E dequeue() {
        Node node = queue.getNode(0);
        queue.removeFromHead();

        return (E) node.data;
    }

    public int getSize() {
        return queue.size;
    }

    public void printQueue() {
        queue.printLinkedList();
    }
}
