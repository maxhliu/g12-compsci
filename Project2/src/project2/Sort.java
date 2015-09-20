/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;

/**
 * The superclass of all sorts; provides the isSorted and randomTo functions for
 * readability.
 */
public class Sort {

//    checks if the list is sorted at the end
    public static boolean isSorted(ArrayList<String> list) {
        boolean sorted = false;
//        go through all the elements
        for (int i = 1; i < list.size(); i++) {
//            if each element is greater than the one before it, then the list must be sorted
            if (list.get(i).compareTo(list.get(i - 1)) < 0) {
//                if not, return false
                return false;
            }
        }
//        return true iff every single element is sorted
        return true;
    }

//    generates a number from zero to i
    public static int randomTo(int i) {
        return (int) (Math.random() * i);
    }
}
