package org.optimum;

import java.math.BigInteger;
import java.util.Scanner;

public class MinimumSum {

    public static BigInteger findMinimumSum(String n) {
        BigInteger minSum = new BigInteger(n); // Start with the largest possible sum

        // Iterate over all possible split positions
        for (int i = 1; i < n.length(); i++) {
            BigInteger part1 = new BigInteger(n.substring(0, i));
            BigInteger part2 = new BigInteger(n.substring(i));
            BigInteger sum = part1.add(part2);

            // Update minSum if a smaller sum is found
            if (sum.compareTo(minSum) < 0) {
                minSum = sum;
            }
        }

        return minSum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter inputs");
        int d = scanner.nextInt();

        String n = scanner.next();


        if (n.length() != d) {
            System.out.println("The length of the number does not match the given number of digits.");
            return;
        }

        BigInteger result = findMinimumSum(n);
        System.out.println(result);
    }
}
