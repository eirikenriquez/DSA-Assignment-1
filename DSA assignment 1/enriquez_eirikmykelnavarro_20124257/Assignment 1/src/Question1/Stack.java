package Question1;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Stack<E> {

    private LinkedList<E> stack;

    public Stack() {
        this.stack = new LinkedList<>();
    }

    public void push(E data) {
        stack.add(data);
    }

    public E pop() {
        Node node = stack.getNode(stack.size - 1);
        stack.removeFromTail();

        return (E) node.data;
    }

    public int getSize() {
        return stack.size;
    }

    public void printStack() {
        stack.printLinkedList();
    }
}
