package project2;

import java.util.ArrayList;

/**
 * Considered a "joke" sort, BogoSort has possibly the longest running time of
 * any sort, with n! complexity. It permutes the elements randomly, and checks
 * if it's sorted. If it isn't it just shuffles them and tries again.
 */
public class BogoSort extends Sort {

//    count the number of tries it took to sort these elements
    private static long tries = 0;

//    shuffle the elements
    public static ArrayList flipDesk(ArrayList<String> desk) {
        ArrayList<String> flippedDesk = new ArrayList<>();
//        take a random element, and put it into the result ArrayList until all the elements are removed.
        for (int i = desk.size(); i > 0; i--) {
            flippedDesk.add(desk.remove(randomTo(i)));
        }
        return flippedDesk;
    }

    public static ArrayList<String> theSort(ArrayList<String> list) {
//        while the list is not sorted
        while (!isSorted(list)) {
//            continue shuffling the elements
            list = flipDesk(list);
//            and record down the number of tries
            tries++;
        }
        return list;
    }

    public static ArrayList sort(ArrayList<String> list) {
        System.err.println("working . . .");
        long time = -System.currentTimeMillis();
        list = theSort(list);
        time += System.currentTimeMillis();

        for (String string : list) {
            System.err.println(string);
        }
        System.err.println("The sort took " + (time) + " milliseconds to complete.");
        System.err.println("It only took " + tries + " tries to sort it.");
        System.err.flush();
        if (!isSorted(list)) {
            throw new ArithmeticException();
        }
        return list;
    }
}