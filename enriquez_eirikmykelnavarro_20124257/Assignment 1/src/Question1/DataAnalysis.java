package Question1;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class DataAnalysis<E> {

    private E[] data;
    private Queue<E> queue;
    private Stack<E> stack;

    public DataAnalysis(E[] data) {
        this.data = data;
        this.queue = new Queue<>();
        this.stack = new Stack<>();
    }

    public boolean isSymmetrical() {
        populateList(0);
        return isSymmetrical(0);
    }

    private boolean isSymmetrical(int index) {
        if (index < queue.getSize()) {
            if (!(queue.dequeue().equals(stack.pop()))) {
                return false;
            }
            return isSymmetrical(index + 1);
        }

        return true;
    }

    private void populateList(int index) {
        if (index < data.length) {
            queue.enqueue(data[index]);
            stack.push(data[index]);
            populateList(index + 1);
        }
    }
}
