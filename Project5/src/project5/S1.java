/*
 * Mister Lim
 * ICS4U1-1
 * 7 March 2014
 * Liu_Max_Project5
 */
package project5;

import java.util.Scanner;

/**
 * S1
 */
public class S1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //the number of lines of text
        int lines = sc.nextInt();
        String text = "";
        //input all the text into a string for analysis
        //use one more line than necessary because sc.nextLine() ironically reads the current line, not the next line
        for (int i = 0; i < lines+1; i++) {
            text += sc.nextLine();
        }
        //the number of t or T characters in the string
        int t = 0;
        //the number of s or S characters in the string
        int s = 0;
        //loop through all the characters in the text
        for (char c : text.toCharArray()) {
            //if it is a t then increment the t variable
            if (c == 't' || c == 'T') {
                t++;
            }
            //if it is a s then increment the s variable
            if (c == 's' || c == 'S') {
                s++;
            }
        }
        //if there are more t characters than s characters, it is English text
        if (t > s) {
            System.out.println("English");
        } else {
            //if not, then it is French text. This will happen if the number of s characters are greater or equal to the
            //number of t characters
            System.out.println("French");
        }
    }
}
