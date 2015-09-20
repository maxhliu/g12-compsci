/*
 * Mister Lim
 * ICS4U1-1
 * 19 November 2013
 * Liu_Max_Project2
 */
package project2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * My Project2 is a collection of sorts which serves as a companion piece to my
 * Project1. It sorts a series of strings using many different methods with
 * varying efficiency and allows the user to compare their running time.
 */
public class Project2 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<>();
        System.out.println("Enter your text to be sorted. Type \"HALT\" to indicate the end of your text.");
        String input = s.next();
        while (!("HALT".equals(input))) {
//            delete all whitespace and make everything lowercase
            words.add(input.replaceAll("\\W", "").toLowerCase());
            input = s.next();
        }
//        prompt the user to choose their sort
        System.out.println("Enter one of the following numbers to choose a sort to use:\r\n"
                + "1. Collections.sort(Timsort)\r\n"
                + "2. Merge Sort\r\n"
                + "3. Insertion Sort\r\n"
                + "4. Selection Sort\r\n"
                + "5. Bogo Sort (WARNING: takes incredible amounts of time for more than 13 elements)\r\n"
                + "6. Radix Sort (sorts a bit differently: strings are sorted as base-26 numbers instead of alphabetically.\r\n"
                + "Radix Sort is a non-comparative sort, so String.compareTo(String s) was not available.\r\n"
                + "Ironically, it cannot be used to sort numbers.");

//        run the right sort
        switch (s.nextInt()) {
            case 1:
                TimSort.sort(words);
                break;
            case 2:
                MergeSort.sort(words);
                break;
            case 3:
                InsertionSort.sort(words);
                break;
            case 4:
                SelectionSort.sort(words);
                break;
            case 5:
                BogoSort.sort(words);
                break;
            case 6:
                RadixSort.sort(words);
                break;
        }
    }
}
