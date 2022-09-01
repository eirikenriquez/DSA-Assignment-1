package Question2;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Extend_thread extends Thread {

    int id;
    int[] shared_array;
    boolean done = false;
    int booked = 0;

    public Extend_thread(int id, int[] shared_array) {
        this.id = id;
        this.shared_array = shared_array;
    }

    @Override
    public void run() {
        for (int i = 0; i < shared_array.length; i++) {
            synchronized (shared_array) {
                book();
            }
        }
    }

    public void book() {
        int i = 0;

        // looking for an empty seat
        for (; i < shared_array.length && shared_array[i] != 0; i++);

        // check the seat available before booking
        if (i < shared_array.length && shared_array[i] == 0) {
            shared_array[i] = id; // booking
            booked++; // increase number of seats booked

        }
    }

    public static void main(String[] args) {
        int[] seats = new int[1000];

        for (int i = 0; i < seats.length; i++) {
            seats[i] = 0; // empty the booking of the seat
        }

        Extend_thread et1 = new Extend_thread(1, seats);
        Extend_thread et2 = new Extend_thread(2, seats);
        Extend_thread et3 = new Extend_thread(3, seats);

        et1.start();
        et2.start();
        et3.start();

        // waiting for all threads to finish
        while (et1.isAlive() || et2.isAlive() || et3.isAlive());

        for (int i = 0; i < seats.length; i++) {
            System.out.println(seats[i] + " ");

            if (i % 10 == 9) {
                System.out.println();
            }

            System.out.println("ET 1 booked:" + et1.booked
                    + " ET 2 booked: " + et2.booked
                    + " ET 3 booked: " + et3.booked
                    + "\nTotal Booked: " + (et1.booked + et2.booked + et3.booked));
        }
    }
}
