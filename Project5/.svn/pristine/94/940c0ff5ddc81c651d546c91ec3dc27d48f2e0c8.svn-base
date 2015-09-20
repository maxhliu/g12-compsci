/*
 * Mister Lim
 * ICS4U1-1
 * 7 March 2014
 * Liu_Max_Project5
 */
package project5;

import java.util.Scanner;

/**
 * S2
 */
public class S2 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        //the number of lines, or the number of questions on the test
        final int lines = sc.nextInt();
        //the student's responses are stored in this character array
        char[] studentResponses = new char[lines];
        //loop through the test
        for(int i = 0; i < lines; i++) {
            //there is no *scanner object*.nextChar() method so I am using the .next() method,
            //converting the string to a character array, and taking the first element.
            //since each choice is only one character long, this is the same as taking the next character.
            studentResponses[i] = sc.next().toCharArray()[0];
        }
        //do the same thing for the correct solutions
        char[] correctAnswers = new char[lines];
        for (int i = 0; i < lines; i++) {
            correctAnswers[i] = sc.next().toCharArray()[0];
        }
        //this will hold the number of questions the student answered correctly
        int score = 0;
        //go through the test and check if the student's answers are correct
        for (int i = 0; i < lines; i++) {
            //add one to the score if it is correct
            if (correctAnswers[i] == studentResponses[i]) {
                score++;
            }
        }
        //print it out
        System.out.println(score);
    }
}
