/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question1;

/**
 *
 * @author xhu
 */
public class LinkedListTest {

    public static void main(String[] args) {

        int mark = 0;
        LinkedList<String> linked_list = new LinkedList<>();

        //checking for add method
        System.out.println("Testing data: [\"abc\", \"def\", 123, 456].\n"
                + "There should be 4 nodes in a linked list.\n");
        String[] objects = new String[4];
        objects[0] = "abc";
        objects[1] = "def";
        objects[2] = "123";
        objects[3] = "456";

        test_add(linked_list, objects);

        System.out.println();

        //check for contains method
        mark += test_contains(linked_list, objects);
        System.out.println("Current mark: " + mark);
        System.out.println();

        //check for remove method
        mark += test_remove(linked_list, objects);
        System.out.println("Current mark: " + mark);
        System.out.println();

        //check for remove from head method
        mark += test_remove_head(linked_list);
        System.out.println("Current mark: " + mark);
        System.out.println();

        //check for remove from tail method
        mark += test_remove_tail(linked_list);
        System.out.println("Current mark: " + mark);
        System.out.println();

        System.out.println();

        //part 2
        String word = "Hello Data Structures & Algorithms Students.".toLowerCase();
        Queue<Character> queue = new Queue();

        for (int i = 0; i < word.length(); i++) {
            queue.enqueue((Character) word.charAt(i));
        }

        mark += test_dequeue(queue, word);

        System.out.println("Current mark: " + mark);
        System.out.println();

        Stack<Character> stack = new Stack();
        for (int i = 0; i < word.length(); i++) {
            stack.push(word.charAt(i));
        }
        mark += test_pop(stack, word);

        System.out.println("Current mark: " + mark);
        System.out.println();

        mark += test_palindrome();

        System.out.println("Current mark: " + mark);
        System.out.println();

        mark += test_function_symmetric();

        System.out.println("Current mark: " + mark);
        System.out.println();

        mark += test_add_in_order();

        System.out.println("Current mark: " + mark);
        if (mark == 31) {
            mark += 5; //if mark is 31, all linked list tests passed. Node Class should be fine.
            System.out.println("Node Class should be fine");
        }
        System.out.println();
        //check recursion
        //mark += test_check_recursion();
        System.out.println("Total mark of part 1: " + mark);
    }

    private static void test_add(LinkedList linked_list, String[] objects) {
        for (int i = 0; i < 4; i++) {
            linked_list.add(objects[i]);
        }

        System.out.println("Checking for \"add\" method");
        linked_list.printLinkedList();
    }

    private static int test_contains(LinkedList linked_list, String[] objects) {
        int mark = 0;
        System.out.println("Testing for \"contains\" method");
        Node node = new Node();
        for (int i = 0; i < linked_list.size; i++) {
            node.data = objects[i];
            if (linked_list.contains(node)) {
                mark += 1; //****************************************************** mark for contains()
                System.out.println("Passed, mark: " + mark);
            } else {
                System.out.println("Failed, mark: " + mark);
            }
        }

        linked_list.printLinkedList();
        if (mark == 4) {
            mark += 1;
            System.out.println("add method passed\ncontains method passed\n");
        }
        return mark;
    }

    private static int test_remove(LinkedList linked_list, String[] objects) {
        System.out.println("Testing for \"remove\" method");
        int mark = 0;
        Node node = new Node();
        node.data = (String) objects[2];
        linked_list.remove(node);

        if (linked_list.size == 3 && !linked_list.contains(node)) {
            mark = 3;
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }

        linked_list.printLinkedList();

        return mark;
    }

    private static int test_remove_head(LinkedList linked_list) {
        System.out.println("Testing for \"remove from head\" method");
        Node node = new Node();
        int mark = 0;
        node.data = "abc";
        linked_list.removeFromHead();
        if (linked_list.size == 2 && !linked_list.contains(node)) {
            mark = 2;
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }

        linked_list.printLinkedList();

        return mark;
    }

    private static int test_remove_tail(LinkedList linked_list) {
        System.out.println("Testing for \"remove from tail\" method");
        Node node = new Node();
        int mark = 0;
        node.data = "456";
        linked_list.removeFromTail();
        if (linked_list.size == 1 && !linked_list.contains(node)) {
            mark = 2;
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }

        linked_list.printLinkedList();

        return mark;
    }

//    private static int test_check_recursion()
//    {
//        FileManager fm = new FileManager("src\\linkedlistdemo\\LinkedList.java");
//        fm.readFile("src\\linkedlistdemo\\LinkedList.java");
//        String[] lines = fm.lineData;
//        boolean has_loop = false;
//        for(int i = 0; i < fm.numberOfLines; i++)
//        {
//            has_loop = lines[i].contains("for")||lines[i].contains("while") || has_loop;
//        }
//        System.out.println(has_loop? "Has loop":"No loop");
//
//        return 10;
//    }
    private static int test_dequeue(Queue queue, String word) {
        System.out.println("Testing for \"dequeue\" method");
        int mark = 0;
        for (int i = 0; queue.getSize() > 0 && ((Character) queue.dequeue()).equals(word.charAt(i)); i++);
        if (queue.getSize() == 0) {
            mark = 5;
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
        return mark;
    }

    private static int test_pop(Stack stack, String word) {
        System.out.println("Testing for \"pop\" method");
        int mark = 0;
        for (int i = 0; stack.getSize() > 0 && ((Character) stack.pop()).equals(word.charAt(word.length() - 1 - i)); i++);
        if (stack.getSize() == 0) {
            mark = 5;
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
        return mark;
    }

    private static int test_palindrome() {
        System.out.println("Testing for \"palindrome\" method");
        int mark = 0;
        boolean true_test;
        boolean false_test;
        char[] chars = "Able was I ere I saw Elba".toLowerCase().toCharArray();
        Character[] characters = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            characters[i] = chars[i];
        }
        DataAnalysis<Character> da = new DataAnalysis(characters);

        true_test = da.isSymmetrical();

        chars = "Able was I ere I  saw Elba".toLowerCase().toCharArray();
        characters = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            characters[i] = chars[i];
        }
        da = new DataAnalysis(characters);

        false_test = da.isSymmetrical();

        if (true_test && !false_test) {
            mark = 3;
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }

        return mark;
    }

    private static int test_function_symmetric() {
        System.out.println("Testing for \"function symmetric\" method");
        int mark = 0;
        boolean true_test;
        boolean false_test;
        int function_range_from = -10;
        int function_range_to = 40;
        Integer[] y = new Integer[function_range_to - function_range_from + 1];
        int index = 0;
        for (int x = function_range_from; x <= function_range_to; x++) {
            y[index] = (int) Math.pow(x - 15, 2) + 10;
            index++;
        }
        DataAnalysis<Integer> da = new DataAnalysis<>(y);

        true_test = da.isSymmetrical();

        index = 0;
        for (int x = function_range_from; x <= function_range_to; x++) {
            y[index] = (int) Math.pow(x - 14, 2) + 10;
            index++;
        }
        da = new DataAnalysis(y);

        false_test = da.isSymmetrical();

        if (true_test && !false_test) {
            mark = 3;
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }

        return mark;
    }

    private static int test_add_in_order() {
        System.out.println("Testing for \"add in order\" method");
        LinkedList<String> string_list = new LinkedList<>();
        int mark = 0;
        string_list.addInOrder("ABC");
        string_list.addInOrder("ABA");
        string_list.addInOrder("ABB");
        string_list.addInOrder("BBB");

        if (string_list.getNode(0).data.equals("ABA")
                && string_list.getNode(1).data.equals("ABB")
                && string_list.getNode(2).data.equals("ABC")
                && string_list.getNode(3).data.equals("BBB")) {
            System.out.println("\tString test Passed");
            mark++;
        } else {
            System.out.println("\tString test Failed");
        }

        LinkedList<Integer> int_list = new LinkedList();
        int_list.addInOrder(3);
        int_list.addInOrder(2);
        int_list.addInOrder(0);
        int_list.addInOrder(1);

        if (int_list.getNode(0).data.equals(0)
                && int_list.getNode(1).data.equals(1)
                && int_list.getNode(2).data.equals(2)
                && int_list.getNode(3).data.equals(3)) {
            System.out.println("\tInteger test Passed");
            mark++;
        } else {
            System.out.println("\tInteger test Failed");
        }
        if (mark == 2) {
            mark++;
        }
        return mark;
    }
}
