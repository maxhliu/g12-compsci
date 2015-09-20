/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;

/**
 * Insertion sort takes an element and compares it to previous elements until it
 * finds an element which is smaller than it. It is inserted after that element,
 * and a new element to insert is picked.
 */
public class InsertionSort extends Sort {

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
//        the outer loop is to determine which element to insert; it starts at the second and ends at the last
        for (int indexToInsert = 1; indexToInsert < list.size(); indexToInsert++) {
//            start the insertion place at one before the element to insert
            int insertedIndex = indexToInsert - 1;
//            take out the element to insert
            String temp = list.remove(indexToInsert);
//            find the place where the inserted index is greater than the element to insert
            for (; insertedIndex >= 0 && temp.compareTo(list.get(insertedIndex)) < 0; insertedIndex--);
//            insert the elemtn at that index. The +1 is becuase the -- at the end of a for loop runs 1 time when the condition is false.
            list.add(insertedIndex + 1, temp);
        }
        return list;
    }
}
