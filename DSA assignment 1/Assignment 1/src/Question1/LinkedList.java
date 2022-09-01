package Question1;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class LinkedList<E> {

    public int size;
    private Node head;

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    public void add(E data) {
        Node newNode = new Node();
        newNode.data = (Comparable) data;

        if (head == null) {
            head = newNode;
        } else {
            Node tail = getTail(head);
            tail.next = newNode;
            newNode.prev = tail;
        }

        size++;
    }

    public void addInOrder(E data) {
        Node newNode = new Node();
        newNode.data = (Comparable) data;

        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        // No need to go through the whole list if there is only one comparison...
        if (size == 1) {
            if (head.compareTo(newNode) >= 0) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
                size++;
            } else {
                head.next = newNode;
                newNode.prev = head;
                size++;
            }

            return;
        }

        addInOrder(head, newNode);
    }

    private void addInOrder(Node current, Node newNode) {
        if (current == head && current.compareTo(newNode) > 0) {
            // if newNode is smaller than head
            newNode.next = current;
            current.prev = newNode;
            head = newNode;
            size++;
        } else if (current.compareTo(newNode) < 0 && current.next == null) {
            // if newNode is bigger than tail
            current.next = newNode;
            newNode.prev = current;
            size++;
        } else if (current.next != null && current.compareTo(newNode) <= 0 && current.next.compareTo(newNode) >= 0) {
            // if newNode is between the head and the tail
            newNode.next = current.next;
            current.next.prev = newNode;
            newNode.prev = current;
            current.next = newNode;
            size++;
        } else {
            addInOrder(current.next, newNode);
        }
    }

    // this is a bad bubble sort
    // this is just used for testing but not in actual program...
    public void sort(Node node, int timesRan) {
        if (node.next == null && timesRan < (size * size)) {
            sort(head, timesRan + 1);
        }

        if (node.next != null) {
            if (node.compareTo(node.next) > 0) {
                E temp = (E) node.data;
                node.data = node.next.data;
                node.next.data = (Comparable) temp;
            }

            sort(node.next, timesRan + 1);
        }
    }

    public Boolean contains(Node node) {
        return contains(head, node);
    }

    private Boolean contains(Node current, Node target) {
        if (current != null) {
            if (current.equals(target)) {
                return true;
            }
            return contains(current.next, target);
        }

        return false;
    }

    public void remove(Node node) {
        remove(head, node);
    }

    private void remove(Node current, Node target) {
        if (current.next.next == null) {
            removeFromTail();
        } else if (target == head) {
            removeFromHead();
        } else if (current.next != null && current.next.equals(target)) {
            current.next.next.prev = current;
            current.next = current.next.next;
            size--;
        } else {
            remove(current.next, target);
        }

    }

    public void removeFromHead() {
        head = head.next;
        size--;
    }

    public void removeFromTail() {
        removeFromTail(head, size - 2, 0);
    }

    private void removeFromTail(Node currentNode, int targetIndex, int currentIndex) {
        if (currentIndex != targetIndex && currentNode.next != null) {
            removeFromTail(currentNode.next, targetIndex, currentIndex + 1);
        } else {
            currentNode.next = null;
            size--;
        }
    }

    public void printLinkedList() {
        System.out.print("[");
        printLinkedList(head);
    }

    private void printLinkedList(Node current) {
        if (current.next != null) {
            System.out.print(current.data.toString() + ", ");
            printLinkedList(current.next);
        } else if (current.next == null) {
            System.out.print(current.data.toString() + "]");
        }

    }

    private Node getTail(Node node) {
        if (node.next != null) {
            return getTail(node.next);
        }

        return node;
    }

    public Node getNode(int index) {
        return getNode(head, index, 0);
    }

    private Node getNode(Node currentNode, int targetIndex, int currentIndex) {
        if (currentNode != null && targetIndex != currentIndex) {
            return getNode(currentNode.next, targetIndex, currentIndex + 1);
        }

        return currentNode;
    }

}
