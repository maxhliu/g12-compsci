/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;
import java.util.List;

/**
 * My implementation of mergesort is a list-based recursive method. First, the
 * sort calls itself, splitting the list in half until it is comprised of small
 * lists of a single element. It then "merges" these lists back together
 * into a final sorted list.
 */
public class MergeSort extends Sort {

    public static ArrayList sort(ArrayList<String> list) {
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

//    a recursive method
    public static ArrayList<String> theSort(List<String> list) {
//        if there is only a single element, then it is sorted.
        if (list.size() < 2) {
//            return it as an ArrayList
            ArrayList<String> rep = new ArrayList<>();
            rep.addAll(list);
            return rep;
        }
//        split the list into equal or unequal by one parts
        List<String> list1 = list.subList(0, list.size() / 2);
        List<String> list2 = list.subList(list.size() / 2, list.size());
//        recursively sort both of them
        list1 = theSort(list1);
        list2 = theSort(list2);
//        create a arrayList to put the merged result into
        ArrayList<String> result = new ArrayList<>();
//        create two indexes for each merged array, starting at the beginning
        int i1 = 0, i2 = 0;
//        make sure there will be no ArrayOutOfBoundsExceptions
        while (i1 < list1.size() && i2 < list2.size()) {
//            compare two elements of the arrays to merge
//            if the list1 element belongs before, put it into the result arraylist and advance the index
            if (list1.get(i1).compareTo(list2.get(i2)) < 0) {
                result.add(list1.get(i1++));
            } else {
//                if not, put the list2 element in
                result.add(list2.get(i2++));
            }
        }
//        see which list is not completely in the result arrayList
        if (i1 == list1.size()) {
            for (; i2 < list2.size(); i2++) {
                //since the array is sorted, it can simply be placed at the end of the result arraylist
                result.add(list2.get(i2));
            }
        } else if (i2 == list2.size()) {
            for (; i1 < list1.size(); i1++) {
                result.add(list1.get(i1));
            }
        }
        return result;
    }
}
