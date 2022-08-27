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
        newNode.data = data;

        if (head == null) {
            head = newNode;
        } else {
            Node tail = getTail(head);
            tail.next = newNode;
        }

        size++;
    }

    public void addInOrder(E data) {
        Node newNode = new Node();
        newNode.data = data;

        if (head == null) {
            head = newNode;
            size++;
//            printLinkedList();
            return;
        }

        // No need to go through the whole list if there is only one comparison...
        if (size == 1) {
            if (head.compareTo(newNode) > 0) {
                newNode.next = head;
                head = newNode;
                size++;

//                printLinkedList();
                return;
            }
        }

        addInOrder(head, newNode);

//        printLinkedList();
    }

    // I know that we were taught in P1 that multiple return statements are a no-no but,
    // I think it's more readable than a nested if statement or an if-else statement.
    private void addInOrder(Node current, Node newNode) {
        // if newNode is smaller than head
        if (current == head && current.compareTo(newNode) > 0) {
            newNode.next = current;
            head = newNode;
            size++;
            return;
        }

        // if newNode is between the head and the tail
        if (current.next != null && current.compareTo(newNode) < 0 && current.next.compareTo(newNode) > 0) {
            newNode.next = current.next;
            current.next = newNode;
            size++;
            return;
        }

        // if newNode is bigger than tail
        if (current.compareTo(newNode) < 0 && current.next == null) {
            current.next = newNode;
            size++;
            return;
        }

        addInOrder(current.next, newNode);

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
        if (current.next != null && current.next.equals(target)) {
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
