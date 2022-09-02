// Question: “Which object have you chosen for the synchronize? Why?”   Answer: I chose the Island object as the synchronized object for the Boats/Threads because all the Boat objects/threads update/access the boatDocked boolean in the Island object which enables the possibility of a race condition if the object weren't synchronized.
package Question2;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Main {

    public static void main(String[] args) {
        Frame frame = new Frame();
    }
}
