/*
 * Mister Lim
 * ICS4U1-1
 * 7 March 2014
 * Liu_Max_Project5
 */
package project5;

import java.util.Scanner;

/**
 * S4
 */
public class S4 {

    static int[] donors = new int[8];
    static int[] recipients = new int[8];
    static int donated = 0;

    //transfer the maximum amount of blood from one donor group to a recipient group
    static void transfer(int a, int b) {
        //take the minimum of the two so that neither group becomes zero
        int transferred = Math.min(donors[a], recipients[b]);
        //do the actual transfer and take away from the donors available and the recipients who still need blood
        donors[a] -= transferred;
        recipients[b] -= transferred;
        //record how many donations were made
        donated += transferred;
    }

    public static void main(String args[]) {
        /**
         * This is the order in which we will distribute blood.
         * Starting from 0 to 7, the number represents:
         * O-, O+, A-, A+, B-, B+, AB-, AB+
         * The first number is the donor, and the second number is the recipient.
         * We then pair as many donors and recipients as possible and carry out the procedure.
         * For example, 23 would mean as many A- donors as possible would be paired with A+ recipients.
         * 
         * The strategy used for pairing donors with recipients is to pair the "pickiest" recipients with their required blood type,
         * then gradually move on to less "picky" recipients. Blood with can be given to less blood types is "used" first.
         * This way, when there is less available blood to select from, this blood can be given to a wider variety of blood types,
         * and the recipients can accept a greater variety of blood types.
         */
        String bloodOrder = "00\n"
                + "11\n"
                + "01\n"
                + "22\n"
                + "02\n"
                + "44\n"
                + "04\n"
                + "33\n"
                + "23\n"
                + "55\n"
                + "45\n"
                + "13\n"
                + "03\n"
                + "15\n"
                + "05\n"
                + "66\n"
                + "46\n"
                + "26\n"
                + "06\n"
                + "77\n"
                + "67\n"
                + "57\n"
                + "47\n"
                + "37\n"
                + "27\n"
                + "17\n"
                + "07";
        Scanner sc = new Scanner(System.in);
        //fill in the donor and recipient arrays with the number of donors and recipients of each blood type, respectively
        for (int i = 0; i < 8; i++) {
            donors[i] = sc.nextInt();
        }
        for (int i = 0; i < 8; i++) {
            recipients[i] = sc.nextInt();
        }
        //now that we have read all of the input, we can use the scanner to read from our blood pairing order.
        sc = new Scanner(bloodOrder);
        //for each blood type pairing, transfer as much blood as possible to the recipients
        for (int i = 0; i < 27; i++) {
            //read the two numbers as a char array
            char[] a = sc.next().toCharArray();
            //and then convert them to their int values by subtracting '0', which is actually 48 in int form
            //then carry out the transfer
            transfer(a[0] - '0', a[1] - '0');
        }
        //print out the maximum number of recipients who could have recieved blood
        System.out.println(donated);
    }
}
