/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;
import java.util.List;

/**
 * Perhaps the most interesting sort of them all, Radix sort makes 0
 * comparisons. At the cost of a large amount of memory and implementation
 * difficulty, radix sort doesn't make a single comparison, and is also one of
 * the fastest sorts (for integers or similar data types). It does this by
 * looking at the integers digit-by-digit. It separates the integers into
 * "buckets" (designated arraylists), one for each letter or number. You can
 * start from either the first or last digit, and I chose the first.
 */
public class RadixSort extends Sort {

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

    public static ArrayList<String> theSort(List<String> list) {
//        make an arrray of 26 buckets, one for each letter
//        2 dimensional ArrayList gave concurrent modification exceptions during runtime
        ArrayList<String>[] buckets = new ArrayList[26];
//        remember to initialize the arraylists within the array
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
//        not all words are the same length; when the nth letter is being evaluated, some words will have less than n letters
//        these words are placed in the first bucket (index 0). when all words have less than n letters, all words will always go in the same bucket
//        we can stop at that point; the list is sorted
        boolean noMoreLetters = false;
//        we are sorting each letter at a time
        int nthLetter = 0;
//        keep on running as long as there is more than 1 bucket
        while (!noMoreLetters) {
//            start with assuming there is only 1 bucket, and that there are no more letters. HOWEVER
//            if there is a single word which has the nth letter, then noMoreLetters = false, and the loop continues
            noMoreLetters = true;
//            iterate through each word
            for (String word : list) {
//                if the word has at least n letters
                if (nthLetter < word.length()) {
//                    put it in the corresponding bucket after translating the char to an int
//                    unfortunately, there was no time to implement a proper translation, so it crashes with numbers
                    buckets[((int) word.charAt(nthLetter)) - 'a'].add(word);
                } else {
//                    if not, place "leading zeroes" in front, putting it in the first bucket
                    buckets[0].add(word);
                }
//                as long as a single word is in a separate bucket, keep on going
                if (noMoreLetters && nthLetter < word.length()) {
                    noMoreLetters = false;
                }
            }
            //combine all the buckets into the original list
            list.clear();
            for (List<String> i : buckets) {
                for (String word : i) {
                    list.add(word);
                }
                i.clear();
            }
//            move on to the next letter
            nthLetter++;
        }
        return (ArrayList) list;
    }
}