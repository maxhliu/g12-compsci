/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Selection sort finds the smallest element, then swaps it with the beginning.
 * It then repeats this for the 2nd smallest, 3rd smallest, etc. until the list
 * is sorted.
 */
public class SelectionSort extends Sort {

    public static ArrayList<String> sort(ArrayList<String> list) {
        System.err.println("working . . .");
        long time = -System.currentTimeMillis();
        list = theSort(list);
        time += System.currentTimeMillis();

        for (String string : list) {
            System.err.println(string);
        }
        System.err.println("The sort took " + (time) + " milliseconds to complete.");
        System.err.flush();
        if (!isSorted(list)) {
            throw new ArithmeticException();
        }
        return list;
    }

    public static ArrayList<String> theSort(ArrayList<String> list) {
//        counter for the outer loop cycle, the one which places the smallest string in the right position
        int positionIndex;
//        counter for the inner loop cycle, the one which finds the smallest string
        int findSmallestIndex;
//        the index of said smallest string
        for (positionIndex = 0; positionIndex < list.size() - 1; positionIndex++) {
//            use the position to swap as a starting point for smallestIndex
            int smallestIndex = positionIndex;
//            go through the elements and see if there is a smaller element
            for (findSmallestIndex = positionIndex + 1; findSmallestIndex < list.size(); findSmallestIndex++) {
//                if there is, make smallestIndex the index of this smaller element
                if (list.get(smallestIndex).compareTo(list.get(findSmallestIndex)) > 0) {
                    smallestIndex = findSmallestIndex;
                }
            }
//            swap the smallest element with the element at its correct position
            Collections.swap(list, smallestIndex, positionIndex);
        }
        return list;
    }
}
